package developer.dao;

import developer.model.Developer;
import developer.model.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class JavaIOProjectDAOImpl implements ProjectDAO {
    private static final String filePathProjects = "projects.txt";

    public void save(Project project) {
        File projectFile = new File("projects.txt");
        String projectId = Integer.toString(project.getId());
        boolean projectIdExists = false;

        // Checking if file exists and if true searching ID duplicates
        if (projectFile.exists()) {
            // Search of ID duplicates
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects))) {
                String line;
                String[] projectData;

                while ((line = reader.readLine()) != null) {
                    projectData = line.split(",");
                    if (projectData[0].equals(projectId)) {
                        System.out.println("==========================================================================");
                        System.out.println("Project with ID " + projectId + " is already exists. Please, enter another ID.");
                        System.out.println("==========================================================================");
                        System.out.println();
                        projectIdExists = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry..." + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no project with id: " + projectId + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // End of searching of ID duplicates

        // Writing project to a file
        if (!projectIdExists) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathProjects, true))) {
                String str = project.getId() + "," +
                        project.getProjectName() + "," +
                        project.getProjectVersion() + "," +
                        "<" +
                        project.getDevelopers() +
                        ">";
                writer.write(str);
                writer.newLine();
                writer.flush();
                writer.close();
                System.out.println("Project " + "\"" + project.getProjectName() + "\"" + " created.");
            } catch (IOException e) {
                System.out.println("I can't to save file " + filePathProjects + ", sorry...: " + e);
            }
        }
        // End of write project to a file
    }

    public void update(Project project) {
        File file = new File("projects.txt");
        String projectID = Integer.toString(project.getId());
        ArrayList<String> projectsList = new ArrayList<>();
        ArrayList<String> changedProject = new ArrayList<>();
        boolean IDExists = false;
        // Checking if file exists and if true searching of ID
        if (file.exists()) {
            // Searching of ID
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects))) {
                String line;
                String[] projectData;

                while ((line = reader.readLine()) != null) {
                    projectData = line.split(",");

                    if (projectData[0].equals(projectID)) {
                        IDExists = true;

                        String str = project.getId() + "," +
                                project.getProjectName() + "," +
                                project.getProjectVersion() + "," +
                                "<" +
                                project.getDevelopers() +
                                ">"
                                ;
                        System.out.println("Project Updated!");
                        changedProject.add(str);
                    }
                    if (!projectData[0].equals(projectID)) {
                        projectsList.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no project with id: " + projectID + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("File not found.");
        // End of searching of ID

        // If ID exists
        if (IDExists) {
            file.delete();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathProjects, true))) {

                for (String s : projectsList) {
                    writer.write(s);
                    writer.newLine();
                }
                for (String s : changedProject) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("It can't to save file " + filePathProjects + ": " + e);
            }
        } else {
            System.out.println("Project not exists. Can't update");
        }
    }

    public void getById(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects)))
        {
            String intIdToString = Integer.toString(id);
            String line;
            String[] project = null;

            while ((line = reader.readLine()) != null) {
                project = line.split(",");

                if (project[0].equals(intIdToString)) {
                    System.out.println("------------");
                    System.out.println("ID: \"" + project[0] + "\"" + " ");
                    System.out.println("Name: \"" + project[1] + "\"" + " ");
                    System.out.println("Version: \"" + project[2] + "\"" + " ");
                    System.out.println("------------");
                    break;
                } else {
                    project = null;
                }
            }
            if(project == null)
                System.out.println("Project with ID " + id + " not found. Please, try again later. )))");
        } catch(FileNotFoundException e){
            System.out.println("File projects.txt not found, sorry..." + e);
        } catch(NoSuchElementException e){
            System.out.println("There is no projects with id: " + id + ": " + e);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showAllProjects() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathProjects))){
            String line;
            String[] projectString;
            Integer projectID;
            String projectName;
            String projectVersion;
            Set<Project> projects = new LinkedHashSet<>();
            Set<Developer> developers = new LinkedHashSet<>();

            while ((line = reader.readLine()) != null) {
                projectString = line.split(",");

                projectID = Integer.parseInt(projectString[0]);
                projectName = projectString[1];
                projectVersion = projectString[2];


                Project project = new Project(projectID, projectName, projectVersion, developers);
                projects.add(project);
                project = null;
            }

            for (Project p:projects) {
                System.out.println("------------");
                System.out.println("ID: " + p.getId());
                System.out.println("Name: " + p.getProjectName());
                System.out.println("Project Version: " + p.getProjectVersion());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File projects.txt not found, sorry..." + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
