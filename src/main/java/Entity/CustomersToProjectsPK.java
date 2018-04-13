package Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CustomersToProjectsPK implements Serializable {
    private int customerId;
    private int projectId;

    @Column (name = "customer_id", nullable = false)
    @Id
    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
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
        CustomersToProjectsPK that = (CustomersToProjectsPK) o;
        return customerId == that.customerId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( customerId, projectId );
    }
}
