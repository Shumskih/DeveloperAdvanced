package developer.view;

import developer.controller.DeveloperController;
import developer.model.Developer;
import developer.model.Project;
import developer.model.Skill;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class DeveloperView {
    DeveloperController developerController = new DeveloperController();
    SkillView skillView = new SkillView();
    ProjectView projectView = new ProjectView();

    private static final String filePathSkills = "skills.txt";
    private static final String filePathProjects = "projects.txt";

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private Developer developer;
    private Integer developerId;
    private String developerName;
    private String developerSurname;
    private String developerSpecialization;
    private Integer developerExperience;
    private Integer developerSalary;
    private Set<Skill> skills = new LinkedHashSet<>();
    private Set<Project> projects = new LinkedHashSet<>();

    String str;

    public void createDeveloper() {
        String addSkill;
        String addProject;
        Integer skillID;
        Integer projectID;
        try {
            do {
                do {
                    System.out.println("Enter developer's ID:");
                    developerId = Integer.parseInt(br.readLine().trim());
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's name:");
                    developerName = br.readLine().trim();
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's surname:");
                    developerSurname = br.readLine().trim();
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's specialization:");
                    developerSpecialization = br.readLine().trim();
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's experience:");
                    developerExperience = Integer.parseInt(br.readLine().trim());
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's salary:");
                    developerSalary = Integer.parseInt(br.readLine().trim());
                    break;
                } while (true);
                System.out.println();

                do {
                    System.out.println("Add skill? \"yes\" or \"no\"");
                    addSkill = br.readLine().trim();
                    if (addSkill.equals("yes")) {
                        do {
                            System.out.println("There is list of skills: ");
                            skillView.showAllSkills();

                            System.out.println();
                            System.out.println("Please, enter ID of skill you want to add to developer");
                            skillID = Integer.parseInt(br.readLine().trim());

                            try (BufferedReader reader = new BufferedReader(new FileReader(filePathSkills))) {
                                String line;
                                String[] skillData;

                                Integer skillId;
                                String skillName;

                                while ((line = reader.readLine()) != null) {
                                    skillData = line.split(",");

                                    if (skillData[0].equals(Integer.toString(skillID))) {

                                        skillId = Integer.parseInt(skillData[0]);
                                        skillName = skillData[1];

                                        Skill skill = new Skill(skillId, skillName);
                                        skills.add(skill);
                                        skill = null;
                                    }
                                }
                                System.out.println("Add another one skill? Yes or no:");
                                addSkill = br.readLine().trim();
                                if (addSkill.equals("no")) {
                                    break;
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("File: " + filePathSkills + " not found." + e);
                            } catch (IOException e) {
                                System.out.println("Ooooops... Some error happened: " + e);
                            }
                        } while (true);
                    } else {
                        break;
                    }
                    break;
                } while (true);

                do {
                    System.out.println("Add project? \"yes\" or \"no\"");
                    addProject = br.readLine().trim();
                    if (addProject.equals("yes")) {
                        do {
                            System.out.println("There is list of projects: ");
                            projectView.showAllProjects();

                            System.out.println();
                            System.out.println("Please, enter ID of project you want to add to developer");
                            projectID = Integer.parseInt(br.readLine().trim());

                            try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects))) {
                                String line;
                                String[] projectData;

                                Integer projectId;
                                String projectName;
                                String projectVersion;

                                while ((line = reader.readLine()) != null) {
                                    projectData = line.split(",");

                                    if (projectData[0].equals(Integer.toString(projectID))) {

                                        projectId = Integer.parseInt(projectData[0]);
                                        projectName = projectData[1];
                                        projectVersion = projectData[2];

                                        Project project = new Project(projectId, projectName, projectVersion);
                                        projects.add(project);
                                        project = null;
                                    }
                                }
                                System.out.println("Add another one project? Yes or no:");
                                addProject = br.readLine().trim();
                                if (addProject.equals("no")) {
                                    developer = new Developer(developerId, developerName, developerSurname, developerSpecialization, developerExperience, developerSalary, skills, projects);
                                    developerController.save(developer);
                                    break;
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("File: " + filePathSkills + " not found." + e);
                            } catch (IOException e) {
                                System.out.println("Ooooops... Some error happened: " + e);
                            }
                        } while (true);
                    } else {
                        developer = new Developer(developerId, developerName, developerSurname, developerSpecialization, developerExperience, developerSalary, skills, projects);
                        developerController.save(developer);
                        break;
                    }
                    break;
                } while(true);
                break;
            } while (true);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void updateDeveloper() {
        String addSkill;
        String addProject;
        Integer skillID;
        Integer projectID;
        try {
            do {
                do {
                    System.out.println("Enter developer's ID:");
                    developerId = Integer.parseInt(br.readLine().trim());
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's name:");
                    developerName = br.readLine().trim();
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's surname:");
                    developerSurname = br.readLine().trim();
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's specialization:");
                    developerSpecialization = br.readLine().trim();
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's experience:");
                    developerExperience = Integer.parseInt(br.readLine().trim());
                    break;
                } while (true);

                do {
                    System.out.println("Enter developer's salary:");
                    developerSalary = Integer.parseInt(br.readLine().trim());
                    break;
                } while (true);
                System.out.println();

                do {
                    System.out.println("Add skill? \"yes\" or \"no\"");
                    addSkill = br.readLine().trim();
                    if (addSkill.equals("yes")) {
                        do {
                            System.out.println("There is list of skills: ");
                            skillView.showAllSkills();

                            System.out.println();
                            System.out.println("Please, enter ID of skill you want to add to developer");
                            skillID = Integer.parseInt(br.readLine().trim());

                            try (BufferedReader reader = new BufferedReader(new FileReader(filePathSkills))) {
                                String line;
                                String[] skillData;

                                Integer skillId;
                                String skillName;

                                while ((line = reader.readLine()) != null) {
                                    skillData = line.split(",");

                                    if (skillData[0].equals(Integer.toString(skillID))) {

                                        skillId = Integer.parseInt(skillData[0]);
                                        skillName = skillData[1];

                                        Skill skill = new Skill(skillId, skillName);
                                        skills.add(skill);
                                        skill = null;
                                    }
                                }
                                System.out.println("Add another one skill? Yes or no:");
                                addSkill = br.readLine().trim();
                                if (addSkill.equals("no")) {
                                    break;
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("File: " + filePathSkills + " not found." + e);
                            } catch (IOException e) {
                                System.out.println("Ooooops... Some error happened: " + e);
                            }
                        } while (true);
                    } else {
                        break;
                    }
                    break;
                } while (true);

                do {
                    System.out.println("Add project? \"yes\" or \"no\"");
                    addProject = br.readLine().trim();
                    if (addProject.equals("yes")) {
                        do {
                            System.out.println("There is list of projects: ");
                            projectView.showAllProjects();

                            System.out.println();
                            System.out.println("Please, enter ID of project you want to add to developer");
                            projectID = Integer.parseInt(br.readLine().trim());

                            try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects))) {
                                String line;
                                String[] projectData;

                                Integer projectId;
                                String projectName;
                                String projectVersion;

                                while ((line = reader.readLine()) != null) {
                                    projectData = line.split(",");

                                    if (projectData[0].equals(Integer.toString(projectID))) {

                                        projectId = Integer.parseInt(projectData[0]);
                                        projectName = projectData[1];
                                        projectVersion = projectData[2];

                                        Project project = new Project(projectId, projectName, projectVersion);
                                        projects.add(project);
                                        project = null;
                                    }
                                }
                                System.out.println("Add another one project? Yes or no:");
                                addProject = br.readLine().trim();
                                if (addProject.equals("no")) {
                                    developer = new Developer(developerId, developerName, developerSurname, developerSpecialization, developerExperience, developerSalary, skills, projects);
                                    developerController.update(developer);
                                    break;
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("File: " + filePathSkills + " not found." + e);
                            } catch (IOException e) {
                                System.out.println("Ooooops... Some error happened: " + e);
                            }
                        } while (true);
                    } else {
                        developer = new Developer(developerId, developerName, developerSurname, developerSpecialization, developerExperience, developerSalary, skills, projects);
                        developerController.update(developer);
                        break;
                    }
                    break;
                } while(true);
                break;
            } while (true);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void getById() {
        try {
            System.out.println("Enter ID of developer you want to find:");
            developerId = Integer.parseInt(br.readLine().trim());
            developerController.getById(developerId);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void showAllDevelopers() {
        developerController.showAllDevelopers();
    }
}
