package com.example.assignment.dto;

import com.example.assignment.model.Job;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class JobDto {

    private Long id;

    @NotNull
    private Job.Status status;

    @NotNull
    private String title;

    @NotNull
    private Integer priority;

    @DateTimeFormat
    private LocalDateTime creationTime;

    @DateTimeFormat
    private LocalDateTime executionTime;

    public JobDto(@NotNull final Job job) {
        this.setExecutionTime(job.getLastModified());
        this.setCreationTime(job.getCreated());
        this.setId(job.getId());
        this.setPriority(job.getJobPriority());
        this.setTitle(job.getJobTaskName());
        this.setStatus(job.getStatus());
    }
}
