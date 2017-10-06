package developer.model;

import java.util.Set;

public class Company {
    private Integer companyId;
    private String companyName;
    private Set<Project> projects;

    public Company(Integer companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Company(Integer companyId, String companyName, Set<Project> projects) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.projects = projects;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
        return "Company = " +
                companyName + ", " +
                "<" +
                getProjects() +
                ">";
    }
}
