package com.job_board_app.service;

import com.job_board_app.model.JobItem;
import com.job_board_app.model.User;
import com.job_board_app.payload.request.JobItemDTO;
import com.job_board_app.payload.request.JobItemFilterDTO;
import com.job_board_app.repository.JobItemRepository;
import com.job_board_app.repository.UserRepository;
import com.job_board_app.utils.FakerItem;
import com.job_board_app.utils.JobItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class JobItemService {
    @Autowired
    private JobItemRepository jobItemRepository;

    @Autowired
    private UserRepository userRepository;


    public JobItem createJob(String email, JobItemDTO jobItemDTO) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

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
                    .user(user)
                    .build();
            return jobItemRepository.save(job);
        }
        return null;
    }


    public JobItem updateJob(String id, JobItemDTO jobItemDTO) {
        Optional<JobItem> jobItemOptional = jobItemRepository.findById(UUID.fromString(id));
        if (jobItemOptional.isPresent()) {
            JobItem jobItem = jobItemOptional.get();
            jobItem.setTitle(jobItemDTO.getTitle());
            jobItem.setDescription(jobItemDTO.getDescription());
            jobItem.setLocation(jobItemDTO.getLocation());
            jobItem.setCompany(jobItemDTO.getCompany());
            jobItem.setType(jobItemDTO.getType());
            jobItem.setSalary(jobItemDTO.getSalary());
            return jobItemRepository.save(jobItem);
        }
        return null;
    }


    public void deleteJob(String id) {
        jobItemRepository.deleteById(UUID.fromString(id));
    }


    public JobItem getJob(String id) {
        return jobItemRepository.findById(UUID.fromString(id)).orElseThrow();
    }

    public List<JobItem> getAllJobs() {
        return jobItemRepository.findAll();
    }

    public Page<JobItem> filterJobs(JobItemFilterDTO jobItemFilterDTO) {
        Pageable pageable = PageRequest.of(jobItemFilterDTO.getPage(), jobItemFilterDTO.getSize(),
                Sort.by(Sort.Direction.fromString(jobItemFilterDTO.getSortDir()), jobItemFilterDTO.getSortBy())
        );
        Specification<JobItem> spec = Specification
                .where(JobItemSpecification.hasKeyword(jobItemFilterDTO.getKeyword()))
                .and(JobItemSpecification.hasLocation(jobItemFilterDTO.getLocation()))
                .and(JobItemSpecification.hasType(jobItemFilterDTO.getType()))
                .and(JobItemSpecification.hasCompany(jobItemFilterDTO.getCompany()))
                .and(JobItemSpecification.salaryOverlaps(jobItemFilterDTO.getMinSalary(), jobItemFilterDTO.getMinSalary()));

         return jobItemRepository.findAll(spec, pageable);
    }


    public void generateJobItems(String email, int size) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            FakerItem fakerItem = new FakerItem();
            List<JobItem> jobItems = fakerItem.generateJobItems(user, size);
            jobItemRepository.saveAll(jobItems);
        }
    }
}
