package Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DevelopersToProjectsPK implements Serializable {
    private int developerId;
    private int projectId;

    @Column (name = "developer_id", nullable = false)
    @Id
    public int getDeveloperId()
    {
        return developerId;
    }

    public void setDeveloperId(int developerId)
    {
        this.developerId = developerId;
    }

    @Column (name = "project_id", nullable = false)
    @Id
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
        DevelopersToProjectsPK that = (DevelopersToProjectsPK) o;
        return developerId == that.developerId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( developerId, projectId );
    }
}
