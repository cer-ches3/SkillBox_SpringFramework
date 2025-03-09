package com.example.spring_web.repository;

import com.example.spring_web.Tables;
import com.example.spring_web.exception.TaskNotFoundException;
import com.example.spring_web.model.Task;
import com.example.spring_web.tables.records.TaskRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
@Primary
public class JooqTaskRepository implements TaskRepository{
    private final DSLContext dslContext;

    @Override
    public List<Task> findAll() {
        log.debug("JooqTaskRepository -> findAll");

        return dslContext.selectFrom(Tables.TASK)
                .fetchInto(Task.class);
    }

    @Override
    public Optional<Task> findById(Long id) {
        log.debug("JooqTaskRepository -> findById {}", id);

        return dslContext.selectFrom(Tables.TASK)
                .where(Tables.TASK.ID.eq(id))
                .fetchOptional()
                .map(tableRecord -> tableRecord.into(Task.class));
    }

    @Override
    public Task save(Task task) {
        log.debug("JooqTaskRepository -> save {}", task);

        task.setId(System.currentTimeMillis());
        TaskRecord taskRecord = dslContext.newRecord(Tables.TASK, task);
        taskRecord.store();

        return taskRecord.into(Task.class);
    }

    @Override
    public Task update(Task task) {
        log.debug("JooqTaskRepository -> update {}", task);

        var mayBeUpdateRecord = dslContext.update(Tables.TASK)
                .set(dslContext.newRecord(Tables.TASK, task))
                .where(Tables.TASK.ID.eq(task.getId()))
                .returning()
                .fetchOptional();

        return mayBeUpdateRecord.map(taskRecord -> taskRecord.into(Task.class))
                .orElseThrow(() -> new TaskNotFoundException("Task not found! ID: " + task.getId()));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("JooqTaskRepository -> deleteById {}", id);

        dslContext.deleteFrom(Tables.TASK)
                .where(Tables.TASK.ID.eq(id))
                .execute();
    }

    @Override
    public void batchInsert(List<Task> tasks) {
        log.debug("JooqTaskRepository -> batchInsert");

        List<Query> insertQueries = new ArrayList<>();
        for (Task task : tasks) {
            insertQueries.add(
                    dslContext.insertInto(
                            Tables.TASK,
                            Tables.TASK.ID,
                            Tables.TASK.DESCRIPTION,
                            Tables.TASK.PRIORITY,
                            Tables.TASK.TITLE
                    ).values(
                            task.getId(),
                            task.getDescription(),
                            task.getPriority(),
                            task.getTitle()
                    )
            );
        }
        dslContext.batch(insertQueries).execute();
    }
}
