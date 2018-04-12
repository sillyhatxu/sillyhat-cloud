package com.sillyhat.cloud.groovytest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    private Long id;

    private Long uid;

    private List<Project> projectList;

    public void addProject(Project project){
        if(null == projectList){
            projectList = new ArrayList<>();
        }
        projectList.add(project);
    }

    public long getCurrentPrice(){
        if(null == projectList)
            return 0l;
        return projectList.stream().mapToLong(project -> project.getQuantity() * project.getEveryPrice()).sum();
    }

    public int getProjectQuantity(){
        if(null == projectList)
            return 0;
        return projectList.stream().mapToInt(project -> project.getQuantity()).sum();
    }

    public void clearProject(){
        projectList.clear();
    }

    public boolean isValid(){
        return uid != null && projectList != null && !projectList.isEmpty();
    }
}
