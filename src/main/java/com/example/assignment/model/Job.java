package com.example.assignment.model;

import com.example.assignment.dto.JobDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Job extends AuditableEntity {

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
    private Job.Status status;

    public void mapDTO(@NotNull final JobDto dto) {
        this.status = dto.getStatus();
        this.setJobTaskName(dto.getTitle());
        this.setJobPriority(dto.getPriority());
    }

    public enum Status {
        running,
        failed,
        inprogress
    }
}
