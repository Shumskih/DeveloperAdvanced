package developer.controller;

import developer.dao.JavaIOSkillDAOImpl;
import developer.model.Skill;

public class SkillController {
    private JavaIOSkillDAOImpl javaIOSkillDAOImpl = new JavaIOSkillDAOImpl();

    public void save(Skill skill) {
        javaIOSkillDAOImpl.save(skill);
    }

    public void update(Skill skill) {
        javaIOSkillDAOImpl.update(skill);
    }

    public void getById(int id) {
        javaIOSkillDAOImpl.getById(id);
    }

    public void showAllSkills() {
        javaIOSkillDAOImpl.showAllSkills();
    }
}
