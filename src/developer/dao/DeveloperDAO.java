package developer.dao;

import developer.model.Developer;

public interface DeveloperDAO {
    void save(Developer developer);

    void update(Developer developer);

    void getById(Integer id);

    void showAllDevelopers();
}
