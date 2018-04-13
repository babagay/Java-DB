package Entity;


import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table (name = "customers", schema = "public", catalog = "postgres")
public class Customer {

    private int id;
    private String name;
    private String biz;

    private Collection<CustomersToProjects> customersToProjects;

    @Id
    @Column (name = "id", nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    @Basic
    @Column (name = "biz", nullable = false, length = -1)
    public String getBiz()
    {
        return biz;
    }

    public void setBiz(String biz)
    {
        this.biz = biz;
    }

    @OneToMany (mappedBy = "customersByCustomerId")
    public Collection<CustomersToProjects> getCustomersToProjects()
    {
        return customersToProjects;
    }

    public void setCustomersToProjects(Collection<CustomersToProjects> customersToProjects)
    {
        this.customersToProjects = customersToProjects;
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
        Customer that = (Customer) o;
        return id == that.id &&
                Objects.equals( name, that.name ) &&
                Objects.equals( biz, that.biz );
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( id, name, biz );
    }
}