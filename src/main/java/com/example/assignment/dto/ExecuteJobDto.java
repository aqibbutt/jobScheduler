package com.example.assignment.dto;

import com.example.assignment.model.Job;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ExecuteJobDto {

    @NotNull
    private Job.JobStatus status;

    @NotNull
    private Integer jobPriority;

    private Integer timeToExecuteJobInSecond;

}
