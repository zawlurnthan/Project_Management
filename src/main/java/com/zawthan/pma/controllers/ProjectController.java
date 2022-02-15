package com.zawthan.pma.controllers;
import com.zawthan.pma.models.Employee;
import com.zawthan.pma.models.Project;
import com.zawthan.pma.repositories.EmployeeRepository;
import com.zawthan.pma.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public String displayProjects(Model model){
        Iterable<Project> projects = projRepo.findAll();
        model.addAttribute("projects", projects);
        return "list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model){
        Iterable<Employee> employees = empRepo.findAll();
        model.addAttribute("project", new Project());
        model.addAttribute("employees", employees);
        return "new-project";
    }

    @PostMapping("/save")
    public String createProject(@Valid Project project, BindingResult result){
        if(result.hasErrors())
            return "new-project";

        // save project to Database.
        projRepo.save(project);
        // use redirect to prevent duplicated submission.
        return "redirect:/projects";
    }

    @GetMapping("/update")
    public String updateProject(@RequestParam("id") long id, Model model){
        Project project = projRepo.findByProjectId(id);
        model.addAttribute("project", project);

        // get all employees
        Iterable<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);

        return "/new-project";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("id") long id){
        Project project = projRepo.findByProjectId(id);
        projRepo.delete(project);
        return "redirect:/projects";
    }

}
