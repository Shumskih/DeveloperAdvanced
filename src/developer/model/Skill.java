package developer.model;

public class Skill {
    private Integer id;
    private String skill;

    public Skill() {
    }

    public Skill(Integer id, String skill) {
        this.id = id;
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

}
