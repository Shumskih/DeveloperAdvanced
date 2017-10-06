package developer.dao;

import developer.model.Developer;
import developer.model.Project;
import developer.model.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class JavaIODeveloperDAOImpl implements DeveloperDAO {
    private static final String filePathDevelopers = "developers.txt";

    public void save(Developer developer) {
        File file = new File("developers.txt");
        String id = Integer.toString(developer.getId());
        boolean IDExists = false;

        // Checking if file exists and if true searching ID duplicates
        if (file.exists()) {
            // Search of ID duplicates
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathDevelopers))) {
                String line;
                String[] developerData;

                while ((line = reader.readLine()) != null) {
                    developerData = line.split(",");
                    if (developerData[0].equals(id)) {
                        System.out.println("==========================================================================");
                        System.out.println("Developer with ID " + id + " is already exists. Please, choose another ID.");
                        System.out.println("==========================================================================");
                        System.out.println();
                        IDExists = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry..." + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no developer with id: " + id + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // End of searching of ID duplicates


        // Writing developer to a file
        if (!IDExists) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathDevelopers, true))) {
                String str = developer.getId() + "," +
                        developer.getName() + "," +
                        developer.getSurname() + "," +
                        developer.getSpecialization() + "," +
                        developer.getExperience() + "," +
                        developer.getSalary() + "," +
                        "<" +
                        developer.getSkills() +
                        ">," +
                        "{" +
                        developer.getProjects() +
                        "}";
                writer.write(str);
                writer.newLine();
                writer.flush();
                writer.close();
                System.out.println("Developer " + developer.getName() + " with ID " + id + " created.");
            } catch (IOException e) {
                System.out.println("I can't to save file " + filePathDevelopers + ", sorry...: " + e);
            }
        }
        // End of writing developer to a file
    }

    public void update(Developer developer) {
        File file = new File("developers.txt");
        String idToString = Integer.toString(developer.getId());
        ArrayList<String> developersList = new ArrayList<>();
        ArrayList<String> changedDeveloper = new ArrayList<>();
        boolean IDExists = false;
        // Checking if file exists and if true searching of ID
        if (file.exists()) {
            // Searching of ID
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathDevelopers))) {
                String line;
                String[] developerData;

                while ((line = reader.readLine()) != null) {
                    developerData = line.split(",");

                    if (developerData[0].equals(idToString)) {
                        IDExists = true;

                        String str = developer.getId() + "," +
                                developer.getName() + "," +
                                developer.getSurname() + "," +
                                developer.getSpecialization() + "," +
                                developer.getExperience() + "," +
                                developer.getSalary() + "," +
                                "<" +
                                developer.getSkills() +
                                ">" + "," +
                                "{" +
                                developer.getProjects() +
                                "}";
                        System.out.println("Developer " + developer.getName() + " have updated");
                        changedDeveloper.add(str);
                    }
                    if (!developerData[0].equals(idToString)) {
                        developersList.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no developer with id: " + idToString + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("File not found.");
        // End of searching of ID

        // If ID exists
        if (IDExists) {
            file.delete();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathDevelopers, true))) {

                for (String s : developersList) {
                    writer.write(s);
                    writer.newLine();
                }
                for (String s : changedDeveloper) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("I can't to save file " + filePathDevelopers + ": " + e);
            }
        } else {
            System.out.println("Developer not exists. Can't update");
        }
    }

    public void getById(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathDevelopers)))
        {
            String intIdToString = Integer.toString(id);
            String line;
            String[] developer = null;
            String[] splitSkills;
            String[] skill;
            String[] splitProjects;
            String[] project;

            while ((line = reader.readLine()) != null) {
                developer = line.split(",");
                splitSkills = line.split("[<>]");
                skill = splitSkills[1].split(",");

                splitProjects = line.split("[{}]");
                project = splitProjects[1].split(",");

                if (developer[0].equals(intIdToString)) {

                    // Show developer
                    System.out.println("================");
                    System.out.println("Developer found: ");
                    System.out.println("================");
                    System.out.println();
                    System.out.println("ID: " + developer[0]);
                    System.out.println("Name: " + developer[1]);
                    System.out.println("Surname: " + developer[2]);
                    System.out.println("Specialization: " + developer[3]);
                    System.out.println("Experience: " + developer[4] + " year");
                    System.out.println("Salary: $" + developer[5]);

                    // Show developer's skills
                    System.out.println("----------");
                    System.out.print("Skills: ");
                    JavaIOSkillDAOImpl javaIOSkillDAOImpl = new JavaIOSkillDAOImpl();

                    for (String skillId: skill) {
                        Integer index = Integer.parseInt(skillId);
                        javaIOSkillDAOImpl.getById(index);
                    }

                    // Show developer's projects
                    System.out.println("\n----------");
                    System.out.print("Projects: ");
                    JavaIOProjectDAOImpl javaIOProjectDAOImpl = new JavaIOProjectDAOImpl();

                    for (String projectId: project) {
                        Integer index = Integer.parseInt(projectId);
                        javaIOProjectDAOImpl.getById(index);
                    }
                    break;
                } else
                    developer = null;
            }
            if(developer == null)
                System.out.println("Developer with ID " + id + " not found. Please, try again later. )))");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, sorry..." + e);
        } catch (NoSuchElementException e) {
            System.out.println("There is no developer with id: " + id + ": " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllDevelopers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathDevelopers)))
        {
            String line;
            String[] developerString;
            Set<Developer> developersSet = new LinkedHashSet<>();
            Integer developerId;
            String developerName;
            String developerSurname;
            String developerSpecialization;
            Integer developerExperience;
            Integer developerSalary;
            Set<Skill> skills = new LinkedHashSet<>();
            Set<Project> projects = new LinkedHashSet<>();

            while ((line = reader.readLine()) != null) {
                developerString = line.split(",");

                developerId = Integer.parseInt(developerString[0]);
                developerName = developerString[1];
                developerSurname = developerString[2];
                developerSpecialization = developerString[3];
                developerExperience = Integer.parseInt(developerString[4]);
                developerSalary = Integer.parseInt(developerString[5]);


                Developer developer = new Developer(developerId, developerName, developerSurname, developerSpecialization, developerExperience, developerSalary, skills, projects);
                developersSet.add(developer);
            }

            for (Developer s:developersSet) {
                System.out.println("--------------");
                System.out.println("ID: " + s.getId());
                System.out.println("Name: " +s.getName() + "\n" +
                        "Surname: " + s.getSurname() + "\n" +
                        "Specialization: " + s.getSpecialization() + "\n" +
                        "Experience: " + s.getExperience() + " year\n" +
                        "Salary: $" + s.getSalary());
                System.out.println("--------------");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File developers.txt not found, sorry..." + e);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

