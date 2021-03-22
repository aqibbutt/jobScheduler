package com.example.assignment.model;

import com.example.assignment.dto.JobDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
public class Job  extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String jobTaskName;


    @NotNull
    @Column
    private Integer jobPriority;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private JobStatus status;


    public enum JobStatus {
        running,
        failed,
        inprogress
    }

    public void mapDTO(@NotNull final JobDto dto) {
        this.status = dto.getStatus();
        this.setJobTaskName(dto.getJobTaskName());
        this.setJobPriority(dto.getJobPriority());
    }
}
