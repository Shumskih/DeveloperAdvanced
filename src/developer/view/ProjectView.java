package developer.view;

import developer.controller.ProjectController;
import developer.dao.JavaIODeveloperDAOImpl;
import developer.dao.JavaIOProjectDAOImpl;
import developer.model.Developer;
import developer.model.Project;
import developer.model.Skill;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProjectView {
    private static final String filePathDevelopers = "developers.txt";
    private static final String filePathProjects = "projects.txt";
    private static final String filePathSkills = "skills.txt";

    ProjectController projectController = new ProjectController();
    JavaIOProjectDAOImpl javaIOProjectDAOImpl = new JavaIOProjectDAOImpl();
    JavaIODeveloperDAOImpl javaIODeveloperDAOImpl = new JavaIODeveloperDAOImpl();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Project project;
    Developer developer;
    Integer projectId;
    String projectName;
    String projectVersion;
    Set<Project> projects = new LinkedHashSet<>();
    Set<Skill> skills = new LinkedHashSet<>();
    Set<Developer> developers = new LinkedHashSet<>();
    Integer developerID;

    public void createProject() {
        String addDeveloper;
        try {
            do {
                System.out.println("Enter project's ID: ");
                projectId = Integer.parseInt(br.readLine().trim());
                break;
            } while (true);

            do {
                System.out.println("Enter project's name: ");
                projectName = br.readLine().trim();
                break;
            } while (true);

            do {
                System.out.println("Enter project's version: ");
                projectVersion = br.readLine().trim();

                System.out.println("Add developer to the project? \"yes\" or \"no\"");
                addDeveloper = br.readLine().trim();
                if (addDeveloper.equals("yes")) {

                    System.out.println("There is a list of developers: ");

                    javaIODeveloperDAOImpl.showAllDevelopers();

                    System.out.println();
                    System.out.println("Please, enter ID of developer you want to add: ");
                    developerID = Integer.parseInt(br.readLine().trim());

                    // If user enter ID of developer, ID of project add to the developer in file developer.txt automatically
                    String[] skillData = null;
                    String[] projectData = null;

                    Integer skillID;
                    String skillName;

                    Integer projID;
                    String projName;
                    String projVersion;

                    try (BufferedReader reader = new BufferedReader(new FileReader(filePathDevelopers))) {
                        String line;
                        String[] skillDataParseByBrackets;
                        String[] projectDataParseByBrackets;

                        while ((line = reader.readLine()) != null) {
                            try {
                                skillDataParseByBrackets = line.split("[<>]");
                                skillData = skillDataParseByBrackets[1].split(",");
                                projectDataParseByBrackets = line.split("[{}]");
                                projectData = projectDataParseByBrackets[1].split(",");
                            } catch (IndexOutOfBoundsException e) {
                                continue;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Ooooops... Some error happened: " + e);
                    }

                    try (BufferedReader reader = new BufferedReader(new FileReader(filePathSkills)))
                    {
                        String line;
                        String[] skillData1;

                        while ((line = reader.readLine()) != null) {
                            skillData1 = line.split(",");

                            for (String s:skillData) {
                                if (skillData1[0].equals(s)) {
                                    skillID = Integer.parseInt(skillData1[0]);
                                    skillName = skillData1[1];

                                    Skill skill = new Skill(skillID, skillName);
                                    skills.add(skill);
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found" + e);
                    }

                    try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects)))
                    {
                        String line;
                        String[] projectData1;

                        while ((line = reader.readLine()) != null) {
                            projectData1 = line.split(",");

                            try {
                                for (String p : projectData) {
                                    if (projectData1[0].equals(p)) {
                                        projID = Integer.parseInt(projectData1[0]);
                                        projName = projectData1[1];
                                        projVersion = projectData1[2];

                                        Project project = new Project(projID, projName, projVersion);
                                        projects.add(project);
                                    }
                                }
                            } catch (NullPointerException e) {
                                if (projectData1[0].equals(Integer.toString(projectId))) {
                                    projID = Integer.parseInt(projectData1[0]);
                                    projName = projectData1[1];
                                    projVersion = projectData1[2];

                                    Project project = new Project(projID, projName, projVersion);
                                    projects.add(project);
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found" + e);
                    }
                    //End of if user enter ID of developer, ID of project add to the developer in file developer.txt automatically

                    Integer id = null;
                    String developerName = null;
                    String developerSurname = null;
                    String developerSpecialization = null;
                    Integer developerExperience = null;
                    Integer developerSalary = null;

                    try (BufferedReader reader = new BufferedReader(new FileReader(filePathDevelopers)))
                    {
                        String line;
                        String[] developerData;
                        Set<Developer> developers = new LinkedHashSet<>();

                        while((line = reader.readLine()) != null) {
                            developerData = line.split(",");

                            if (developerData[0].equals(Integer.toString(developerID))) {

                                id = Integer.parseInt(developerData[0]);
                                developerName = developerData[1];
                                developerSurname = developerData[2];
                                developerSpecialization = developerData[3];
                                developerExperience = Integer.parseInt(developerData[4]);
                                developerSalary = Integer.parseInt(developerData[5]);



                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File: " + filePathDevelopers + " not found." + e);
                    } catch (IOException e){
                        System.out.println("Ooooops... Some error happened: " + e);
                    }
                    developer = new Developer(id, developerName, developerSurname, developerSpecialization, developerExperience, developerSalary, skills, projects);
                    developers.add(developer);
                    project = new Project(projectId, projectName, projectVersion, developers);
                    projects.add(project);
                    javaIOProjectDAOImpl.save(project);
                    javaIODeveloperDAOImpl.update(developer);
                    break;
                } else {
                    Set<Developer> developers = new LinkedHashSet<>();
                    project = new Project(projectId, projectName, projectVersion, developers);
                    javaIOProjectDAOImpl.save(project);
                    projects.add(project);
                    break;
                }
            } while (true);


        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void updateProject() {
        String addDeveloper;
        try {
            do {
                System.out.println("Enter project's ID: ");
                projectId = Integer.parseInt(br.readLine().trim());
                break;
            } while (true);

            do {
                System.out.println("Enter project's name: ");
                projectName = br.readLine().trim();
                break;
            } while (true);

            do {
                System.out.println("Enter project's version: ");
                projectVersion = br.readLine().trim();
                break;
            } while (true);

            do {
                System.out.println("There is a list of developers: ");

                javaIODeveloperDAOImpl.showAllDevelopers();

                System.out.println();
                System.out.println("Please, enter ID of developer you want to add: ");
                developerID = Integer.parseInt(br.readLine().trim());

                try (BufferedReader reader = new BufferedReader(new FileReader(filePathDevelopers)))
                {
                    String line;
                    String[] developerData;

                    Integer id;
                    String developerName;
                    String developerSurname;
                    String developerSpecialization;
                    Integer developerExperience;
                    Integer developerSalary;

                    while((line = reader.readLine()) != null) {
                        developerData = line.split(",");

                        if (developerData[0].equals(Integer.toString(developerID))) {

                            id = Integer.parseInt(developerData[0]);
                            developerName = developerData[1];
                            developerSurname = developerData[2];
                            developerSpecialization = developerData[3];
                            developerExperience = Integer.parseInt(developerData[4]);
                            developerSalary = Integer.parseInt(developerData[5]);

                            Developer developer = new Developer(id, developerName, developerSurname, developerSpecialization, developerExperience, developerSalary, skills, projects);
                            developers.add(developer);
                            developer = null;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File: " + filePathDevelopers + " not found." + e);
                } catch (IOException e){
                    System.out.println("Ooooops... Some error happened: " + e);
                }

                System.out.println("Do you want to add another one developer? yes or no: ");
                addDeveloper = br.readLine().trim();
                if (addDeveloper.equals("no")) {
                    project = new Project(projectId, projectName, projectVersion, developers);
                    javaIOProjectDAOImpl.update(project);
                    break;
                }
            } while(true);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void getById() {
        try {
            System.out.println("Enter ID of project you want to find: ");
            projectId = Integer.parseInt(br.readLine().trim());
            projectController.getById(projectId);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void showAllProjects() {
        projectController.showAllProjects();
    }
}
