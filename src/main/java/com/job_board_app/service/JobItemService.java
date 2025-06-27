package com.job_board_app.service;

import com.job_board_app.model.ERole;
import com.job_board_app.model.JobItem;
import com.job_board_app.model.User;
import com.job_board_app.payload.request.JobItemDTO;
import com.job_board_app.payload.request.JobItemFilterDTO;
import com.job_board_app.repository.JobItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JobItemService {
    @Autowired
    private JobItemRepository jobItemRepository;

    public JobItem createJob(JobItemDTO jobItemDTO) {
        var job = JobItem.builder()
                .title(jobItemDTO.getTitle())
                .description(jobItemDTO.getDescription())
                .location(jobItemDTO.getLocation())
                .company(jobItemDTO.getCompany())
                .type(jobItemDTO.getType())
                .salary(jobItemDTO.getSalary())
                .minSalary(jobItemDTO.getMinSalary())
                .maxSalary(jobItemDTO.getMaxSalary())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        return jobItemRepository.save(job);
    }

    public Page<JobItem> searchJobs(JobItemFilterDTO jobItemFilterDTO) {
        Pageable pageable = PageRequest.of(jobItemFilterDTO.getPage(), jobItemFilterDTO.getSize(),
                Sort.by(Sort.Direction.fromString(jobItemFilterDTO.getSortDir()), jobItemFilterDTO.getSortBy())
        );
        //
        // return jobItemRepository.findAll(spec, pageable);
        return null;
    }
}
