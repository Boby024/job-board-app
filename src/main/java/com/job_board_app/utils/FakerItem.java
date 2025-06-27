package com.job_board_app.utils;

import com.job_board_app.model.JobItem;
import com.job_board_app.model.User;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.*;

@NoArgsConstructor
public class FakerItem {
    String[] TITLES = {
            "Software Engineer", "Data Analyst", "Product Manager", "UX Designer", "QA Tester"
    };
    String[] DESCRIPTIONS = {
            "Responsible for developing and maintaining applications.",
            "Analyze and interpret complex data sets.",
            "Oversee product development lifecycle.",
            "Design user interfaces and experiences.",
            "Test software to ensure quality and performance."
    };
    String[] LOCATIONS = {
            "New York", "San Francisco", "Chicago", "Austin", "Seattle"
    };
    String[] COMPANIES = {
            "Acme Corp", "Globex Inc.", "Initech", "Umbrella Corp", "Soylent Corp"
    };
    String[] TYPES = {
            "Full-Time", "Part-Time", "Contract"
    };
    Random random = new Random();


    public List<JobItem> generateJobItems(User user, int size) {
        List<JobItem> jobItems = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            JobItem jobItem = new JobItem();

            jobItem.setTitle(getRandom(TITLES));
            jobItem.setDescription(getRandom(DESCRIPTIONS));
            jobItem.setLocation(getRandom(LOCATIONS));
            jobItem.setCompany(getRandom(COMPANIES));
            jobItem.setType(getRandom(TYPES));

            double minSalary = 50000 + random.nextInt(50000); // $50k - $100k
            double maxSalary = minSalary + 20000 + random.nextInt(30000); // $20k - $50k more
            double salary = minSalary + (maxSalary - minSalary) / 2;

            jobItem.setMinSalary(minSalary);
            jobItem.setMaxSalary(maxSalary);
            jobItem.setSalary(salary);

            Instant now = Instant.now();
            jobItem.setCreatedAt(now);
            jobItem.setUpdatedAt(now);
            jobItem.setUser(user);
            jobItems.add(jobItem);
        }
        return jobItems;
    }

    private String getRandom(String[] array) {
        return array[random.nextInt(array.length)];
    }
}
