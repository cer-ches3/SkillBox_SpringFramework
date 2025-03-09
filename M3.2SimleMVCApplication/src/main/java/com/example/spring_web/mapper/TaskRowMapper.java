package com.example.spring_web.mapper;

import com.example.spring_web.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setId(rs.getLong(Task.Fields.id));
        task.setDescription(rs.getString(Task.Fields.description));
        task.setTitle(rs.getString(Task.Fields.title));
        task.setPriority(rs.getInt(Task.Fields.priority));
        return task;
    }
}
