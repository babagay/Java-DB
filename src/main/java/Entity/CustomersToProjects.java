package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "customers_to_projects", schema = "public", catalog = "postgres")
@IdClass (CustomersToProjectsPK.class)
public class CustomersToProjects {
    private int customerId;
    private int projectId;
    private Customer customersByCustomerId;
    private Project projectsByProjectId;

    @Id
    @Column (name = "customer_id", nullable = false)
    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
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
        CustomersToProjects that = (CustomersToProjects) o;
        return customerId == that.customerId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( customerId, projectId );
    }

    @ManyToOne
    @JoinColumn (name = "customer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Customer getCustomersByCustomerId()
    {
        return customersByCustomerId;
    }

    public void setCustomersByCustomerId(Customer customer)
    {
        customersByCustomerId = customer;
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