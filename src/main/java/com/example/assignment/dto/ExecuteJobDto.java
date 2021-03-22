package com.example.assignment.dto;

import com.example.assignment.model.Job;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class ExecuteJobDto {

    @NotNull
    private Job.Status status;

    @NotNull
    private Integer priority;

    private Integer timeToExecuteJobInSecond;

}
