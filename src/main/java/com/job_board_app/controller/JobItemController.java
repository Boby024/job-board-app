package com.job_board_app.controller;

import com.job_board_app.model.JobItem;
import com.job_board_app.payload.request.JobItemDTO;
import com.job_board_app.payload.request.JobItemFilterDTO;
import com.job_board_app.payload.response.JobItemResponse;
import com.job_board_app.service.JobItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/job")
@RestController
@RequiredArgsConstructor
public class JobItemController {

    @Autowired
    private JobItemService jobItemService;


    @PostMapping("/create")
    public ResponseEntity<JobItemResponse> create(@RequestBody JobItemDTO jobItemDTO,
                                                  Principal principal
    ) {
        try {
            JobItem jobItem = jobItemService.createJob(principal.getName(), jobItemDTO);
            if (jobItem != null) {
                return new ResponseEntity<>(
                        new JobItemResponse(String.valueOf(jobItem.getId()), null),
                        HttpStatus.CREATED
                );
            } else {
                return new ResponseEntity<>(
                        new JobItemResponse(null, "Missing Attributes!"),
                        HttpStatus.CREATED
                );            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<JobItem> update(@RequestParam String id, @RequestBody JobItemDTO job) {
        try {
            JobItem jobItem = jobItemService.updateJob(id, job);
            return new ResponseEntity<>(jobItem, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/single")
    public ResponseEntity<JobItem> getById(@RequestParam String id) {
        try {
            JobItem jobItem = jobItemService.getJob(id);
            return new ResponseEntity<>(jobItem, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String id) {
        try {
            jobItemService.deleteJob(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<JobItem>> getAllJobs() {
        try {
            return ResponseEntity.ok(jobItemService.getAllJobs());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/filter")
    public Page<JobItem> getJobs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String company,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "created_at") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ) {
        JobItemFilterDTO filter = new JobItemFilterDTO();
        filter.setKeyword(keyword);
        filter.setLocation(location);
        filter.setType(type);
        filter.setCompany(company);
        filter.setPage(page);
        filter.setSize(size);
        filter.setSortBy(sortBy);
        filter.setSortDir(sortDir);
        return jobItemService.filterJobs(filter);
    }


    @GetMapping("/faker-job-items")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> generateJobItems(
                                              @RequestParam(defaultValue = "100") Integer size,
                                              Principal principal
    ) {
        try {
            jobItemService.generateJobItems(principal.getName(), size);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
