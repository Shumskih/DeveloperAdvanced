package developer.dao;

import developer.model.Company;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class JavaIOCompanyDAOImpl implements CompanyDAO {
    private static final String filePathCompany = "companies.txt";

    public void save(Company company) {
        File file = new File("companies.txt");
        String companyId = Integer.toString(company.getCompanyId());
        boolean nameExists = false;

        // Checking if file exists and if true searching name duplicates
        if (file.exists()) {
            // Search of name duplicates
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathCompany))) {
                String line;
                String[] companyData;

                while ((line = reader.readLine()) != null) {
                    companyData = line.split(",");
                    if (companyData[0].equals(companyId)) {
                        System.out.println("==========================================================================");
                        System.out.println("Company with id \"" + companyId + "\" is already exists. Please, choose another name.");
                        System.out.println("==========================================================================");
                        System.out.println();
                        nameExists = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry..." + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no company with id " + companyId + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // End of searching of name duplicates


        // Writing company to a file
        if (!nameExists) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathCompany, true))) {
                String str = company.getCompanyId() + "," +
                        company.getCompanyName() + "," +
                        "<" +
                        company.getProjects() +
                        ">";
                writer.write(str);
                writer.newLine();
                writer.flush();
                writer.close();
                System.out.println("Company \"" + company.getCompanyName() + "\" created.");
            } catch (IOException e) {
                System.out.println("I can't to save file " + filePathCompany + ", sorry...: " + e);
            }
        }
        // End of writing company to a file
    }

    public void update(Company company) {
        File file = new File("companies.txt");
        String companyID = Integer.toString(company.getCompanyId());
        ArrayList<String> companiesList = new ArrayList<>();
        ArrayList<String> changedCompany = new ArrayList<>();
        boolean nameExists = false;
        // Checking if file exists and if true searching of ID
        if (file.exists()) {
            // Searching of ID
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathCompany))) {
                String line;
                String[] companyData;

                while ((line = reader.readLine()) != null) {
                    companyData = line.split(",");

                    if (companyData[0].equals(companyID)) {
                        nameExists = true;

                        String str = company.getCompanyId() + "," +
                                company.getCompanyName() + "," +
                                "<" +
                                company.getProjects() +
                                ">";
                        System.out.println("Company have updated!");
                        changedCompany.add(str);
                        for (String s : changedCompany)
                            System.out.println(s);
                    }
                    if (!companyData[0].equals(companyID)) {
                        companiesList.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no company with ID\": " + companyID + "\": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("File not found.");
        // End of searching of ID

        // If ID exists
        if (nameExists) {
            file.delete();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathCompany, true))) {

                for (String s : companiesList) {
                    writer.write(s);
                    writer.newLine();
                }
                for (String s : changedCompany) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("It can't to save file " + filePathCompany + ": " + e);
            }
        } else {
            System.out.println("Company not exists. Can't update");
        }
    }

    public void getById(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathCompany)))
        {
            String line;
            String[] company = null;
            String companyId = Integer.toString(id);

            while ((line = reader.readLine()) != null) {
                company = line.split(",");

                if (company[0].equals(companyId)) {
                    System.out.println();
                    System.out.println("------------");
                    System.out.println("ID: " + company[0] + " ");
                    System.out.println("Name: \"" + company[1] + "\"" + " ");
                    System.out.println("------------");
                    System.out.println();
                    break;
                } else {
                    company = null;
                }
            }
            if(company == null)
                System.out.println("Company with ID " + companyId + " not found. Please, try again later. )))");
        } catch(FileNotFoundException e){
            System.out.println("File companies.txt not found, sorry..." + e);
        } catch(NoSuchElementException e){
            System.out.println("There is no company with ID: " + id + ": " + e);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showAllCompanies() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathCompany))){
            String line;
            String[] companyString;
            Integer companyID;
            String companyName;
            Set<Company> companies = new LinkedHashSet<>();

            while ((line = reader.readLine()) != null) {
                companyString = line.split(",");

                companyID = Integer.parseInt(companyString[0]);
                companyName = companyString[1];


                Company company = new Company(companyID, companyName);
                companies.add(company);
                company = null;
            }

            for (Company c:companies) {
                System.out.println("------------");
                System.out.println("ID: " + c.getCompanyId());
                System.out.println("Name: " + c.getCompanyName());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File projects.txt not found, sorry..." + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}