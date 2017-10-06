package developer.view;

import developer.controller.CompanyController;
import developer.controller.ProjectController;
import developer.model.Company;
import developer.model.Project;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class CompanyView {
    private static final String filePathProjects = "projects.txt";

    ProjectController projectController = new ProjectController();
    CompanyController companyController = new CompanyController();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    private Set<Project> projects = new LinkedHashSet<>();

    private Integer companyID;
    private String companyName;
    private Integer projectID;

    public void createCompany() {
        String addProject;
        try {
            do {
                System.out.println("Enter company's ID: ");
                companyID = Integer.parseInt(br.readLine().trim());
                break;
            } while (true);

            do {
                System.out.println("Enter company's name: ");
                companyName = br.readLine().trim();

                System.out.println("Add project to company? yes or no:");
                addProject = br.readLine().trim();
                if (addProject.equals("yes")) {
                    do {
                        System.out.println("There is a list of projects: ");

                        projectController.showAllProjects();

                        System.out.println();
                        System.out.println("Please, enter ID of project you want to add: ");
                        projectID = Integer.parseInt(br.readLine().trim());

                        try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects))) {
                            String line;
                            String[] projectData;

                            Integer id;
                            String projectName;
                            String projectVersion;

                            while ((line = reader.readLine()) != null) {
                                projectData = line.split(",");

                                if (projectData[0].equals(Integer.toString(projectID))) {

                                    id = Integer.parseInt(projectData[0]);
                                    projectName = projectData[1];
                                    projectVersion = projectData[2];

                                    Project project = new Project(id, projectName, projectVersion);
                                    projects.add(project);
                                    project = null;
                                }
                            }
                            System.out.println("Add another one project? yes or no:");
                            addProject = br.readLine().trim();
                            if (addProject.equals("no")) {
                                Company company = new Company(companyID, companyName, projects);
                                companyController.save(company);
                                break;
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("File: " + filePathProjects + " not found." + e);
                        } catch (IOException e) {
                            System.out.println("Ooooops... Some error happened: " + e);
                        }
                    }while(true);
                } else {
                    Set<Project> projects = new LinkedHashSet<>();
                    Company company = new Company(companyID, companyName, projects);
                    companyController.save(company);
                    break;
                }
                break;
            } while (true) ;
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void updateCompany() {
        String addProject;

        try {
            do {
                System.out.println("Enter company's ID: ");
                companyID = Integer.parseInt(br.readLine().trim());
                break;
            } while(true);

            do {
                System.out.println("Enter company's name: ");
                companyName = br.readLine().trim();
                break;
            } while(true);

            do {
                System.out.println("This is list of projects: ");

                projectController.showAllProjects();

                System.out.println();
                System.out.println("Please, enter ID of project you want to add: ");
                projectID = Integer.parseInt(br.readLine().trim());

                try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects)))
                {
                    String line;
                    String[] projectData;

                    Integer projectId;
                    String projectName;
                    String projectVersion;

                    while((line = reader.readLine()) != null) {
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
                } catch (FileNotFoundException e) {
                    System.out.println("File: " + filePathProjects + " not found." + e);
                } catch (IOException e){
                    System.out.println("Ooooops... Some error happened: " + e);
                }

                System.out.println("Do you want to add another one project? yes or no:");
                addProject = br.readLine().trim();
                if (addProject.equals("no")) {
                    Company company = new Company(companyID, companyName, projects);
                    companyController.update(company);
                    break;
                }
            } while(true);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void getById() {
        try {
            System.out.println("Enter ID of company you want to find:");
            companyID = Integer.parseInt(br.readLine().trim());
            companyController.getById(companyID);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void showAllCompanies() {
        companyController.showAllCompanies();
    }
}
