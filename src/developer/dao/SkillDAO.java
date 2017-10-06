package developer.dao;

import developer.model.Skill;

public interface SkillDAO {
    void save(Skill skill);

    void update(Skill skill);

    void getById(Integer id);

    void showAllSkills();
}
