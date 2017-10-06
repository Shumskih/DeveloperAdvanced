package developer.dao;

import developer.model.Skill;

import java.io.*;
import java.util.*;

public class JavaIOSkillDAOImpl implements SkillDAO {
    private static final String filePathSkills = "skills.txt";

    public void save(Skill skill) {
        File fileSkills = new File("skills.txt");
        String idSkills = Integer.toString(skill.getId());
        String skillName = skill.getSkill();
        boolean IDSkillExists = false;

        // Checking if file exists and if true searching ID duplicates
        if (fileSkills.exists()) {
            // Search of ID duplicates
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathSkills))) {
                String line;
                String[] developerData;

                while ((line = reader.readLine()) != null) {
                    developerData = line.split(",");
                    if (developerData[0].equals(idSkills)) {
                        System.out.println("==========================================================================");
                        System.out.println("ID \"" + idSkills + "\" already exists.Please, enter another ID.");
                        System.out.println("==========================================================================");
                        System.out.println();
                        IDSkillExists = true;
                        break;
                    }
                    if (developerData[1].equals(skillName)) {
                        System.out.println("==========================================================================");
                        System.out.println("\"" + skillName + "\" skill already exists.Please, enter another skill.");
                        System.out.println("==========================================================================");
                        System.out.println();
                        IDSkillExists = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry..." + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no developer with id: " + idSkills + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // End of searching of ID duplicates

        // Writing skill to a file
        if (!IDSkillExists) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathSkills, true))) {
                String str = skill.getId() + "," + skill.getSkill();
                writer.write(str);
                writer.newLine();
                writer.flush();
                writer.close();
                System.out.println("Skill " + "\"" + skill.getSkill() + "\"" + " created.");
            } catch (IOException e) {
                System.out.println("I can't to save file " + filePathSkills + ", sorry...: " + e);
            }
        }
        // End of write skill to a file
    }

    public void update(Skill skill) {
        File file = new File("skills.txt");
        String skillID = Integer.toString(skill.getId());
        List<String> skillsList = new ArrayList<>();
        List<String> changedSkill = new ArrayList<>();
        boolean IDExists = false;
        // Checking if file exists and if true searching of ID
        if (file.exists()) {
            // Searching of ID
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathSkills))) {
                String line;
                String[] skillData;

                while ((line = reader.readLine()) != null) {
                    skillData = line.split(",");

                    if (skillData[0].equals(skillID)) {
                        IDExists = true;

                        String str = skill.getId() + "," +
                                skill.getSkill();
                        System.out.println("Skill have updated!");
                        changedSkill.add(str);
                    }
                    if (!skillData[0].equals(skillID)) {
                        skillsList.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no skill with id: " + skillID + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("File not found.");
        // End of searching of ID

        // If ID exists
        if (IDExists) {
            file.delete();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathSkills, true))) {

                for (String s : skillsList) {
                    writer.write(s);
                    writer.newLine();
                }
                for (String s : changedSkill) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Can't to save file " + filePathSkills + ": " + e);
            }
        } else {
            System.out.println("Skill not exists. Can't update");
        }
    }

    public void getById(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathSkills)))
        {
            String skillID = Integer.toString(id);
            String line;
            String[] skill = null;

            while ((line = reader.readLine()) != null) {
                skill = line.split(",");

                if (skill[0].equals(skillID)) {
                    System.out.println("------------");
                    System.out.println("ID: " + skill[0]);
                    System.out.println("Name: \"" + skill[1] + "\"");
                    System.out.println("------------");
                    System.out.println();
                    break;
                } else {
                    skill = null;
                }
            }
            if(skill == null)
                System.out.println("Skill with ID " + id + " not found. Please, try again later. )))");
        } catch(FileNotFoundException e){
            System.out.println("File skills.txt not found, sorry..." + e);
        } catch(NoSuchElementException e){
            System.out.println("There is no skills with id: " + id + ": " + e);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showAllSkills() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathSkills)))
        {
            String line;
            String[] skillString;
            Set<Skill> skillsSet = new LinkedHashSet<>();
            Integer id;
            String skill;

            while ((line = reader.readLine()) != null) {
                skillString = line.split(",");

                id = Integer.parseInt(skillString[0]);
                skill = skillString[1];

                Skill skills = new Skill(id, skill);
                skillsSet.add(skills);
                skills = null;
            }

            for (Skill s:skillsSet) {
                System.out.print(s.getId() + ", ");
                System.out.println(s.getSkill());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File skills.txt not found, sorry..." + e);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
