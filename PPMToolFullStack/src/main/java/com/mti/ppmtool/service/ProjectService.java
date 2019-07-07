package com.mti.ppmtool.service;

import com.mti.ppmtool.domain.Project;
import com.mti.ppmtool.exception.ProjectIdException;
import com.mti.ppmtool.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo m_projectRepo;

    public Project saveOrUpdateProject(Project project) {
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase() ); //coverting to uppercase so that comparison will have no issue
            return m_projectRepo.save(project);
        }catch (Exception e){ throw new ProjectIdException("ProjectId " + project.getProjectIdentifier().toUpperCase() + " already exists"); //it will throw a Exception of kind ProjectIdException

        }

    }

    public Project findProjectByIdentifier(String projectId) {
        Project project =m_projectRepo.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException("ProjectId " + projectId + " does not exists");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        List<Project> projects = (List<Project>) m_projectRepo.findAll();
        if(projects.size() ==0 ){
            throw new ProjectIdException("No Product exists");
        }
        return   m_projectRepo.findAll() ;
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = m_projectRepo.findByProjectIdentifier(projectId.toUpperCase());

        if(project==null){
            throw new ProjectIdException("ProjectId " + projectId + " does not exists");
        }

        m_projectRepo.delete(project);
    }

    public void updateProjectByIdentifier(String projectId){
        Project project = m_projectRepo.findByProjectIdentifier(projectId.toUpperCase());

        if(project==null){
            throw new ProjectIdException("ProjectId " + projectId + " does not exists");
        }


    }

}
