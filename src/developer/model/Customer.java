package developer.model;

import java.util.Set;

public class Customer {
    private Integer customerId;
    private String customerName;
    private Set<Project> projects;

    public Customer(Integer customerId, String customerName) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public Customer(Integer customerId, String customerName, Set<Project> projects) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.projects = projects;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjects() {
        String str = "";
        for(Project project:projects) {
            if(str.equals("")) str = Integer.toString(project.getId());
            else str += "," + Integer.toString(project.getId());
        }
        return str;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Project = " +
                getCustomerId() + "," +
                getCustomerName() + "," +
                "<" +
                getProjects() +
                ">";
    }
}
