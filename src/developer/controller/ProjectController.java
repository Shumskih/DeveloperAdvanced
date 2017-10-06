package developer.controller;

import developer.dao.JavaIOProjectDAOImpl;
import developer.model.Project;

public class ProjectController {
    private JavaIOProjectDAOImpl javaIOProjectDAOImpl = new JavaIOProjectDAOImpl();

    public void save(Project project) {
        javaIOProjectDAOImpl.save(project);
    }

    public void update(Project project) {
        javaIOProjectDAOImpl.update(project);
    }

    public void getById(int id) {
        javaIOProjectDAOImpl.getById(id);
    }

    public void showAllProjects() {
        javaIOProjectDAOImpl.showAllProjects();
    }
}
