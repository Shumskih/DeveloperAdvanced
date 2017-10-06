package developer.model;

import java.util.Set;

public class Project {
    private Integer id;
    private String projectName;
    private String projectVersion;

    Set<Developer> developers;

    public Project() {
    }

    public Project(Integer id, String projectName, String projectVersion) {
        this.id = id;
        this.projectName = projectName;
        this.projectVersion = projectVersion;
    }

    public Project(Integer id, String projectName, String projectVersion, Set<Developer> developers) {
        this.id = id;
        this.projectName = projectName;
        this.projectVersion = projectVersion;
        this.developers = developers;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevelopers() {
        String str = "";
        for (Developer developer:developers) {
            if(str.equals("")) str = Integer.toString(developer.getId());
            else str += "," + Integer.toString(developer.getId());
        }
        return str;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Project = " +
                getId() + "," +
                getProjectName() + "," +
                getProjectVersion() +
                "{" +
                getDevelopers() +
                "}";
    }
}
