package com.zawthan.pma.api.controllers;

import com.zawthan.pma.models.Project;
import com.zawthan.pma.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository projRepo;

    @GetMapping
    public Iterable<Project> getProjects() {
        return projRepo.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long id) {
        return projRepo.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody @Valid Project project){
        return projRepo.save(project);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody @Valid Project project){
        return projRepo.save(project);
    }


    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Project partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Project patchProject){
        Project project = projRepo.findById(id).get();

        if(patchProject.getName() != null){
            project.setName(patchProject.getName());
        }
        if(patchProject.getStage() != null){
            project.setStage(patchProject.getStage());
        }
        if(patchProject.getDescription() != null){
            project.setDescription(patchProject.getDescription());
        }
        return projRepo.save(project);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        try {
            projRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){}
    }

}
