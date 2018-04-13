package Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "companies", schema = "public", catalog = "postgres")
public class Company {

    private long id;
    private String name;
    Set<CompaniesToProjects> companyToProjects;

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
    @Column (name = "name", nullable = false, length = -1)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @OneToMany (mappedBy = "companyByCompanyId")
    public Set<CompaniesToProjects> getCompaniesToProjectsById()
    {
        return companyToProjects;
    }

    public void setCompaniesToProjectsById(Set<CompaniesToProjects> c2p)
    {
        companyToProjects = c2p;
    }

//    @OneToMany (mappedBy = "companiesByCompanyId")
//    public Collection<CompaniesToProjectsEntity> getCompaniesToProjectsById()
//    {
//        return companiesToProjectsById;
//    }

    @Override
    public boolean equals(Object o)
    {
        if ( this == o ) {
            return true;
        }

        if ( !(o instanceof Company) ) {
            return false;
        }

        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Company company = (Company) o;
        return getId() == company.getId() &&
                Objects.equals( getName(), company.getName() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( getId(), getName() );
    }
}
