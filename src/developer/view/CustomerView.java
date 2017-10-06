package developer.view;

import developer.controller.CustomerController;
import developer.controller.ProjectController;
import developer.model.Customer;
import developer.model.Project;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomerView {
    private static final String filePathProjects = "projects.txt";

    CustomerController customerController = new CustomerController();
    ProjectController projectController = new ProjectController();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private Customer customer;
    private Integer customerID;
    private String customerName;
    Set<Project> projects = new LinkedHashSet<>();

    private Integer projectID;
    private String addProject;

    public void createCustomer() {
        try {
            do {
                System.out.println("Enter customer's ID: ");
                customerID = Integer.parseInt(br.readLine().trim());
                break;
            } while(true);

            do {
                System.out.println("Enter customer's name: ");
                customerName = br.readLine().trim();
                break;
            } while(true);

            do {
                System.out.println("Add project to customer? yes or no: ");
                addProject = br.readLine().trim();
                if (addProject.equals("yes")) {
                    do {
                        System.out.println("There is list of projects: ");

                        projectController.showAllProjects();

                        System.out.println();
                        System.out.println("Please, enter ID of project you want to add: ");
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
                                }
                            }
                            System.out.println("Add another one project? yes or no: ");
                            addProject = br.readLine().trim();
                            if (addProject.equals("no")) {
                                customer = new Customer(customerID, customerName, projects);
                                customerController.save(customer);
                                break;
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("File: " + filePathProjects + " not found." + e);
                        }
                    } while(true);
                }
                break;
            } while (true);
        } catch (FileNotFoundException e) {
            System.out.println("File: " + filePathProjects + " not found." + e);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void updateCustomer() {
        String addProject;

        try {
            do {
                System.out.println("Enter customer's ID: ");
                customerID = Integer.parseInt(br.readLine().trim());
                break;
            } while(true);

            do {
                System.out.println("Enter customer's name: ");
                customerName = br.readLine().trim();
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
                    Customer customer = new Customer(customerID, customerName, projects);
                    customerController.update(customer);
                    break;
                }
            } while(true);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void getById() {
        try {
            System.out.println("Enter ID of customer you want to find:");
            customerID = Integer.parseInt(br.readLine().trim());
            customerController.getById(customerID);
        } catch (IOException e) {
            System.out.println("Ooooops... Some error happened: " + e);
        }
    }

    public void showAllCustomers() {
        customerController.showAllCustomers();
    }
}
