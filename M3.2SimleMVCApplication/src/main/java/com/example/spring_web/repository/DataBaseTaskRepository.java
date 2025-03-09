package com.example.spring_web.repository;

import com.example.spring_web.exception.TaskNotFoundException;
import com.example.spring_web.mapper.TaskRowMapper;
import com.example.spring_web.model.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
//@Primary
@RequiredArgsConstructor
@Slf4j
public class DataBaseTaskRepository implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAll() {
        log.debug("Calling DataBaseTaskRepository-> findAll");
        String sql = "select * from task";

        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    @Override
    public Optional<Task> findById(Long id) {
        log.debug("Calling DataBaseTaskRepository-> findById with ID: {}", id);
        String sql = "Select * from task where id = ?";
        Task task = DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new TaskRowMapper(), 1)
                )
        );
        return Optional.ofNullable(task);
    }

    @Override
    public Task save(Task task) {
        log.debug("Calling DataBaseTaskRepository-> save with Task: {}", task);
        task.setId(System.currentTimeMillis());
        String sql = "insert into task (title,description, priority, id) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getPriority(), task.getId());
        return task;
    }

    @Override
    public Task update(Task task) {
        log.debug("Calling DataBaseTaskRepository-> update with Task: {}", task);
        Task existedTask = findById(task.getId()).orElse(null);
        if (existedTask != null) {
            String sql = "update task set title = ?, description = ?, priority = ? where id = ?";
            jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getPriority(), task.getId());
            return task;
        }
        log.warn("Task with ID: {} not found!", task.getId());

        throw new TaskNotFoundException("Task for update not found! ID: " + task.getId());
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Calling DataBaseTaskRepository-> delete with ID: {}", id);
        String sql = "delete from task where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void batchInsert(List<Task> tasks) {
        log.debug("Calling DataBaseTaskRepository-> batchInsert");
        String sql = "insert into task (title,description, priority, id) values (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Task task = tasks.get(i);
                ps.setString(1, task.getTitle());
                ps.setString(2, task.getDescription());
                ps.setInt(3, task.getPriority());
                ps.setLong(4, task.getId());
            }

            @Override
            public int getBatchSize() {
                return tasks.size();
            }
        });
    }
}
