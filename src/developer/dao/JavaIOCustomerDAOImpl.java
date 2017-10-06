package developer.dao;

import developer.model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class JavaIOCustomerDAOImpl implements CustomerDAO {
    private static final String filePathCustomer = "customers.txt";

    public void save(Customer customer) {
        File customerFile = new File("customers.txt");
        String customerId = Integer.toString(customer.getCustomerId());
        String customerName = customer.getCustomerName();
        boolean customerIdExists = false;

        // Checking if file exists and if true searching ID duplicates
        if (customerFile.exists()) {
            // Search of ID duplicates
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathCustomer))) {
                String line;
                String[] customerData;

                while ((line = reader.readLine()) != null) {
                    customerData = line.split(",");
                    if (customerData[1].equals(customerName)) {
                        System.out.println("==========================================================================");
                        System.out.println("Customer \"" + customerName + "\" is already exists. Please, enter another name.");
                        System.out.println("==========================================================================");
                        System.out.println();
                        customerIdExists = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found, sorry..." + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no customer with id: " + customerId + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // End of searching of ID duplicates

        // Writing customer to a file
        if (!customerIdExists) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathCustomer, true))) {
                String str = customerId + "," +
                        customerName + "," +
                        "<" +
                        customer.getProjects() +
                        ">";
                writer.write(str);
                writer.newLine();
                writer.flush();
                writer.close();
                System.out.println("Customer " + "\"" + customerName + "\"" + " created.");
            } catch (IOException e) {
                System.out.println("I can't to save file " + filePathCustomer + ", sorry...: " + e);
            }
        }
        // End of write customer to a file
    }

    public void update(Customer customer) {
        File file = new File("customers.txt");
        String customerId = Integer.toString(customer.getCustomerId());
        ArrayList<String> customersList = new ArrayList<>();
        ArrayList<String> changedCustomer = new ArrayList<>();
        boolean IDExists = false;
        // Checking if file exists and if true searching of ID
        if (file.exists()) {
            // Searching of ID
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathCustomer))) {
                String line;
                String[] customerData;

                while ((line = reader.readLine()) != null) {
                    customerData = line.split(",");

                    if (customerData[0].equals(customerId)) {
                        IDExists = true;

                        String str = customer.getCustomerId() + "," +
                                customer.getCustomerName() + "," +
                                "<" +
                                customer.getProjects() +
                                ">";
                        System.out.println("Customer " + customer.getCustomerName() + " have updated");
                        changedCustomer.add(str);
                        for (String s : changedCustomer)
                            System.out.println(s);
                    }
                    if (!customerData[0].equals(customerId)) {
                        customersList.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e);
            } catch (NoSuchElementException e) {
                System.out.println("There is no customer with id: " + customerId + ": " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("File not found.");
        // End of searching of ID

        // If ID exists
        if (IDExists) {
            file.delete();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathCustomer, true))) {

                for (String s : customersList) {
                    writer.write(s);
                    writer.newLine();
                }
                for (String s : changedCustomer) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("It can't to save file " + filePathCustomer + ": " + e);
            }
        } else {
            System.out.println("Project not exists. Can't update");
        }
    }

    public void getById(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathCustomer)))
        {
            String intIdToString = Integer.toString(id);
            String line;
            String[] customer = null;

            while ((line = reader.readLine()) != null) {
                customer = line.split(",");

                if (customer[0].equals(intIdToString)) {
                    System.out.println();
                    System.out.println("------------");
                    System.out.println("ID: \"" + customer[0] + "\"" + " ");
                    System.out.println("Name: \"" + customer[1] + "\"" + " ");
                    System.out.println("------------");
                    System.out.println();
                    break;
                } else {
                    customer = null;
                }
            }
            if(customer == null)
                System.out.println("Customer with ID " + id + " not found. Please, try again later. )))");
        } catch(FileNotFoundException e){
            System.out.println("File customers.txt not found, sorry..." + e);
        } catch(NoSuchElementException e){
            System.out.println("There is no customers with id: " + id + ": " + e);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showAllCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathCustomer))){
            String line;
            String[] customerString;
            Integer customerID;
            String customerName;
            Set<Customer> customers = new LinkedHashSet<>();

            while ((line = reader.readLine()) != null) {
                customerString = line.split(",");

                customerID = Integer.parseInt(customerString[0]);
                customerName = customerString[1];


                Customer customer = new Customer(customerID, customerName);
                customers.add(customer);
                customer = null;
            }

            for (Customer c:customers) {
                System.out.println("------------");
                System.out.println("ID: " + c.getCustomerId());
                System.out.println("Name: " + c.getCustomerName());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File projects.txt not found, sorry..." + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
