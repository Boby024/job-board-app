package com.job_board_app.utils;

import com.job_board_app.model.JobItem;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class JobItemSpecification {

//    public static Specification<JobItem> hasLocation(String location) {
//        return (root, query, cb) ->
//                location == null ? null : cb.equal(root.get("location"), location);
//    }

    public static Specification<JobItem> hasLocation(String location) {
        return (root, query, cb) -> {
            if (location == null || location.trim().isEmpty()) return null;

            String pattern = "%" + location.trim().toLowerCase() + "%";
            return cb.like(cb.lower(root.get("location")), pattern);
        };
    }

    public static Specification<JobItem> hasType(String type) {
        return (root, query, cb) -> {
            if (type == null || type.trim().isEmpty()) return null;

            String pattern = "%" + type.trim().toLowerCase() + "%";
            return cb.like(cb.lower(root.get("type")), pattern);
        };
    }

    public static Specification<JobItem> hasCompany(String company) {
        return (root, query, cb) -> {
            if (company == null || company.trim().isEmpty()) return null;

            String pattern = "%" + company.trim().toLowerCase() + "%";
            return cb.like(cb.lower(root.get("company")), pattern);
        };
    }

    public static Specification<JobItem> hasKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return null;

            String pattern = "%" + keyword.trim().toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("title")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern)
            );
        };
    }


    public static Specification<JobItem> salaryOverlaps(Double filterMin, Double filterMax) {
        return (root, query, cb) -> {
            if (filterMin == null && filterMax == null) return null;

            Predicate salaryFixed = null;
            Predicate salaryRange = null;

            if (filterMin != null && filterMax != null) {
                // salary between filterMin and filterMax
                salaryFixed = cb.between(root.get("salary"), filterMin, filterMax);

                // range overlap: jobMax >= filterMin AND jobMin <= filterMax
                salaryRange = cb.and(
                        cb.greaterThanOrEqualTo(root.get("maxSalary"), filterMin),
                        cb.lessThanOrEqualTo(root.get("minSalary"), filterMax)
                );

            } else if (filterMin != null) {
                salaryFixed = cb.greaterThanOrEqualTo(root.get("salary"), filterMin);
                salaryRange = cb.greaterThanOrEqualTo(root.get("maxSalary"), filterMin);
            } else {
                salaryFixed = cb.lessThanOrEqualTo(root.get("salary"), filterMax);
                salaryRange = cb.lessThanOrEqualTo(root.get("minSalary"), filterMax);
            }

            // Return: (fixed salary matches) OR (range overlaps)
            return cb.or(
                    salaryFixed,
                    salaryRange
            );
        };
    }
}

