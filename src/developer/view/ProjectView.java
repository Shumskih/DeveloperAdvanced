package developer.view;

import developer.controller.DeveloperController;
import developer.controller.ProjectController;
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
    DeveloperController developerController = new DeveloperController();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Project project;
    Integer projectIdUserInput;
    String projectNameUserInput;
    String projectVersionUserInput;

    Integer projectIDParsed;
    String projectNameParsed;
    String projectVersionParsed;

    Skill skill;
    Integer skillID;
    String skillName;

    Developer developer;
    Integer developerID;

    String addDeveloper;

    Set<Project> projects = new LinkedHashSet<>();
    Set<Skill> skills = new LinkedHashSet<>();
    Set<Developer> developers = new LinkedHashSet<>();


    public void createProject() {
        try {
            do {
                System.out.println("Enter project's ID: ");
                projectIdUserInput = Integer.parseInt(br.readLine().trim());
                break;
            } while (true);

            do {
                System.out.println("Enter project's name: ");
                projectNameUserInput = br.readLine().trim();
                break;
            } while (true);

            do {
                System.out.println("Enter project's version: ");
                projectVersionUserInput = br.readLine().trim();

                System.out.println("Add developer to the project? \"yes\" or \"no\"");
                addDeveloper = br.readLine().trim();
                if (addDeveloper.equals("yes")) {

                    System.out.println("There is a list of developers: ");

                    developerController.showAllDevelopers();

                    System.out.println();
                    System.out.println("Please, enter ID of developer you want to add: ");
                    developerID = Integer.parseInt(br.readLine().trim());

                    // If user enter ID of developer, ID of project add to the developer in file developer.txt automatically
                    String[] skillData = null;
                    String[] projectData = null;

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
                        String[] skillsData;

                        while ((line = reader.readLine()) != null) {
                            skillsData = line.split(",");

                            for (String s:skillData) {
                                if (skillsData[0].equals(s)) {
                                    skillID = Integer.parseInt(skillsData[0]);
                                    skillName = skillsData[1];

                                    skill = new Skill(skillID, skillName);
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
                        String[] projData;

                        while ((line = reader.readLine()) != null) {
                            projData = line.split(",");

                            try {
                                for (String p : projectData) {
                                    if (projData[0].equals(p)) {
                                        projectIDParsed = Integer.parseInt(projData[0]);
                                        projectNameParsed = projData[1];
                                        projectVersionParsed = projData[2];

                                        Project project = new Project(projectIDParsed, projectNameParsed, projectVersionParsed);
                                        projects.add(project);
                                    }
                                }
                            } catch (NullPointerException e) {
                                if (projData[0].equals(Integer.toString(projectIdUserInput))) {
                                    projectIDParsed = Integer.parseInt(projData[0]);
                                    projectNameParsed = projData[1];
                                    projectVersionParsed = projData[2];

                                    Project project = new Project(projectIDParsed, projectNameParsed, projectVersionParsed);
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
                    project = new Project(projectIdUserInput, projectNameUserInput, projectVersionUserInput, developers);
                    projects.add(project);
                    projectController.save(project);
                    developerController.update(developer);
                    break;
                } else {
                    project = new Project(projectIdUserInput, projectNameUserInput, projectVersionUserInput, developers);
                    projectController.save(project);
                    projects.add(project);
                    break;
                }
            } while (true);


        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void updateProject() {
        try {
            do {
                System.out.println("Enter project's ID: ");
                projectIdUserInput = Integer.parseInt(br.readLine().trim());
                break;
            } while (true);

            do {
                System.out.println("Enter project's name: ");
                projectNameUserInput = br.readLine().trim();
                break;
            } while (true);

            do {
                System.out.println("Enter project's version: ");
                projectVersionUserInput = br.readLine().trim();
                break;
            } while (true);

            do {
                System.out.println("There is a list of developers: ");

                developerController.showAllDevelopers();

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

                            developer = new Developer(id, developerName, developerSurname, developerSpecialization, developerExperience, developerSalary, skills, projects);
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
                    project = new Project(projectIdUserInput, projectNameUserInput, projectVersionUserInput, developers);
                    projectController.update(project);
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
            projectIdUserInput = Integer.parseInt(br.readLine().trim());
            projectController.getById(projectIdUserInput);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void showAllProjects() {
        projectController.showAllProjects();
    }
}
