package com.example.assignment.controller;

import com.example.assignment.dto.ExecuteJobDto;
import com.example.assignment.dto.JobDto;
import com.example.assignment.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/jobs")
@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity addJob(@RequestBody JobDto request) {
        jobService.addJob(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getJobs() {
        List<JobDto> jobs = jobService.getJobs();
        return ResponseEntity.ok(jobs);
    }

    @PatchMapping("executeJob")
    public ResponseEntity executeJob(@RequestBody final ExecuteJobDto executeJobDto) {
        List<JobDto> jobs = jobService.executeJob(executeJobDto);
        return ResponseEntity.ok(jobs);
    }

}
