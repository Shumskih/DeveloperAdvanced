package developer.dao;

import developer.model.Project;

public interface ProjectDAO {
    void save(Project project);

    void update(Project project);

    void getById(Integer id);

    void showAllProjects();
}