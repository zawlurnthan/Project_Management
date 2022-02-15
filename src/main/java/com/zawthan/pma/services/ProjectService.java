package com.zawthan.pma.services;

import com.zawthan.pma.models.Project;
import com.zawthan.pma.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projRepo;

    public Project save(Project project) {
        return projRepo.save(project);
    }

    public Iterable<Project> getAll(){
        return projRepo.findAll();
    }

    public Project findByPorjectId(long id){
        return projRepo.findByProjectId(id);
    }

    public void delete(Project project){
        projRepo.delete(project);
    }

}
