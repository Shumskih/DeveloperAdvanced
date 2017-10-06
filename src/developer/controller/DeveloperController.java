package developer.controller;

import developer.dao.JavaIODeveloperDAOImpl;
import developer.model.Developer;

public class DeveloperController {
    private JavaIODeveloperDAOImpl javaIODeveloperDAOImpl = new JavaIODeveloperDAOImpl();

    public void save(Developer developer) {
        javaIODeveloperDAOImpl.save(developer);
    }

    public void update(Developer developer) {
        javaIODeveloperDAOImpl.update(developer);
    }

    public Developer getById(int id) {
        javaIODeveloperDAOImpl.getById(id);
        return null;
    }

    public void showAllDevelopers() {
        javaIODeveloperDAOImpl.showAllDevelopers();
    }
}
