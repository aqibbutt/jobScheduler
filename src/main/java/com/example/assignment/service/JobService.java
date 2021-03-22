package com.example.assignment.service;

import com.example.assignment.dto.ExecuteJobDto;
import com.example.assignment.dto.JobDto;
import com.example.assignment.model.Job;
import com.example.assignment.repository.JobRepository;
import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    /**
     * adding job to a scheduler
     *
     * @param dto dto for job
     */
    public void addJob(@NotNull final JobDto dto) {
        if (dto.getId() != null && jobRepository.findById(dto.getId()).isPresent()) {
            throw new IllegalArgumentException("Job Already exist.");
        }
        Job job = new Job();
        job.mapDTO(dto);
        jobRepository.save(job);
        System.out.println("Testing completed");
    }

    public List<JobDto> getJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDto> dtoList = new ArrayList<>();
        jobs.forEach(e -> dtoList.add(new JobDto(e))
        );
        return dtoList;
    }

    public List<JobDto> executeJob(@NotNull final ExecuteJobDto executeJobDto) {
        List<Job> jobs = jobRepository.findAll();
        List<JobDto> dtoList = new ArrayList<>();

        // thread to send email
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> updateJobStatusByPriority(executeJobDto.getStatus(), executeJobDto.getJobPriority()), executeJobDto.getTimeToExecuteJobInSecond(), TimeUnit.SECONDS);

        jobs.forEach(e -> dtoList.add(new JobDto(e))
        );
        return dtoList;
    }

    private void updateJobStatusByPriority(@NotNull Job.JobStatus status, @NotNull Integer jobPriority) {
        List<Job> jobs = jobRepository.findByJobPriority(jobPriority);
        jobs.forEach(e -> e.setStatus(status));
        jobRepository.saveAll(jobs);
    }
}
