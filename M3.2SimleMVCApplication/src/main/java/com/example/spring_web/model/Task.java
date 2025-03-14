package com.example.spring_web.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class Task {
    private long id;
    private String title;
    private String description;
    private int priority;

}
