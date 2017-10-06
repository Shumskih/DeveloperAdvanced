package developer.view;

import developer.controller.SkillController;
import developer.model.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class SkillView {
    SkillController skillController = new SkillController();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private Set<Skill> skills = new LinkedHashSet<>();
    private Skill skill;
    private Integer skillId;
    private String skillName;

    public void createSkill() {
        try {
            do {
                System.out.println("Enter skill's ID: ");
                skillId = Integer.parseInt(br.readLine().trim());
                break;
            } while (true);

            do {
                System.out.println("Enter skill's name: ");
                skillName = br.readLine().trim();

                skill = new Skill(skillId, skillName);
                skillController.save(skill);
                skills.add(skill);
                break;
            } while (true);
        } catch(IOException e){
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void updateSkill() {
        try {
            do {
                System.out.println("Enter ID of skill you want to update: ");
                skillId = Integer.parseInt(br.readLine().trim());
                break;
            } while(true);

            do {
                System.out.println("Enter name of skill you want to update: ");
                skillName = br.readLine().trim();
                break;
            } while(true);
            System.out.println();

            skill = new Skill(skillId, skillName);
            skillController.update(skill);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void getById() {
        try {
            System.out.println("Enter ID of skill you want to find: ");
            skillId = Integer.parseInt(br.readLine().trim());
            skillController.getById(skillId);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void showAllSkills() {
        skillController.showAllSkills();
    }
}