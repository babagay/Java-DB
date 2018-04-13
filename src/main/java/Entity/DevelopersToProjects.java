package Entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "developers_to_projects", schema = "public", catalog = "postgres")
@IdClass (DevelopersToProjectsPK.class)
public class DevelopersToProjects {
    private int developerId;
    private int projectId;
    private Developer developerByDeveloperId;
    private Project projectsByProjectId;

    @Id
    @Column (name = "developer_id", nullable = false)
    public int getDeveloperId()
    {
        return developerId;
    }

    public void setDeveloperId(int developerId)
    {
        this.developerId = developerId;
    }

    @Id
    @Column (name = "project_id", nullable = false)
    public int getProjectId()
    {
        return projectId;
    }

    public void setProjectId(int projectId)
    {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o)
    {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        DevelopersToProjects that = (DevelopersToProjects) o;
        return developerId == that.developerId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( developerId, projectId );
    }

    @ManyToOne
    @JoinColumn (name = "developer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Developer getDeveloperByDeveloperId()
    {
        return developerByDeveloperId;
    }

    public void setDeveloperByDeveloperId(Developer developersByDeveloperId)
    {
        this.developerByDeveloperId = developersByDeveloperId;
    }

    @ManyToOne
    @JoinColumn (name = "project_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Project getProjectsByProjectId()
    {
        return projectsByProjectId;
    }

    public void setProjectsByProjectId(Project projectsByProjectId)
    {
        this.projectsByProjectId = projectsByProjectId;
    }
}

