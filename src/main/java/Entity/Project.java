package Entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "projects", schema = "public", catalog = "postgres")
public class Project {

    private long id;
    private String title;
    private BigInteger cost;

    private Set<CompaniesToProjects> companiesToProjectsById;

    private Collection<CustomersToProjects> customersToProjectsById;
    private Collection<DevelopersToProjects> developersToProjectsById;

    @Id
    @Column (name = "id", nullable = false)
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Basic
    @Column (name = "title", nullable = false, length = -1)
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Basic
    @Column (name = "cost", nullable = true, precision = 0)
    public BigInteger getCost()
    {
        return cost;
    }

    public void setCost(BigInteger cost)
    {
        this.cost = cost;
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
        Project that = (Project) o;
        return id == that.id &&
                Objects.equals( title, that.title ) &&
                Objects.equals( cost, that.cost );
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( id, title, cost );
    }

    @OneToMany (mappedBy = "projectByProjectId")
    public Set<CompaniesToProjects> getCompaniesToProjectsById()
    {
        return companiesToProjectsById;
    }

    public void setCompaniesToProjectsById(Set<CompaniesToProjects> companiesToProjectsById)
    {
        this.companiesToProjectsById = companiesToProjectsById;
    }

    @OneToMany (mappedBy = "projectsByProjectId")
    public Collection<CustomersToProjects> getCustomersToProjectsById()
    {
        return customersToProjectsById;
    }

    public void setCustomersToProjectsById(Collection<CustomersToProjects> customersToProjectsById)
    {
        this.customersToProjectsById = customersToProjectsById;
    }

    @OneToMany (mappedBy = "projectsByProjectId")
    public Collection<DevelopersToProjects> getDevelopersToProjectsById()
    {
        return developersToProjectsById;
    }

    public void setDevelopersToProjectsById(Collection<DevelopersToProjects> developersToProjectsById)
    {
        this.developersToProjectsById = developersToProjectsById;
    }
}
