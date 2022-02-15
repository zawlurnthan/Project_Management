package com.zawthan.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zawthan.pma.models.Project;
import com.zawthan.pma.repositories.ChartData;
import com.zawthan.pma.repositories.EmployeeProject;
import com.zawthan.pma.repositories.EmployeeRepository;
import com.zawthan.pma.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository proRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/")
    public String home(Model model) throws JsonProcessingException {

        List<Project> projects = (List<Project>) proRepo.findAll();
        model.addAttribute("projects", projects);

        List<ChartData> projectData = proRepo.getProjectStatus();
        // convert project data object into a jason for chart
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        model.addAttribute("projectStatusCount", jsonString);

        List<EmployeeProject> employeeProjectCount = (List<EmployeeProject>) empRepo.employeeProjects();
        model.addAttribute("employeeList", employeeProjectCount);
        return "index";
    }
}
