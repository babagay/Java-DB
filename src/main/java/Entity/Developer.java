package Entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
@Table (name = "developers", schema = "public", catalog = "postgres")
public class Developer {
    private int id;
    private String name;
    private int age;
    private String address;
    private BigDecimal salary;

    private Collection<DevelopersToProjects> projectsByDeveloperId;
    private Collection<DevelopersToSkills> developersToSkills;

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
    @Column (name = "age", nullable = false)
    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Basic
    @Column (name = "address", nullable = true, length = 100)
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Basic
    @Column (name = "salary", nullable = true, precision = 3)
    public BigDecimal getSalary()
    {
        return salary;
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    // [!] название метода не адекватное - надо уточнить его назначение
    @OneToMany (mappedBy = "developerByDeveloperId")
    public Collection<DevelopersToProjects> getDevelopersToProjectsById()
    {
        return projectsByDeveloperId;
    }

    public void setDevelopersToProjectsById(Collection<DevelopersToProjects> projectsByDeveloperId)
    {
        this.projectsByDeveloperId = projectsByDeveloperId;
    }

    // [!] уточнить название метода исходя из функции
    @OneToMany (mappedBy = "developerByDeveloperId")
    public Collection<DevelopersToSkills> getDevelopersToSkillsById()
    {
        return developersToSkills;
    }

    public void setDevelopersToSkillsById(Collection<DevelopersToSkills> developersToSkills)
    {
        this.developersToSkills = developersToSkills;
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
        Developer that = (Developer) o;
        return id == that.id &&
                age == that.age &&
                Objects.equals( name, that.name ) &&
                Objects.equals( address, that.address ) &&
                Objects.equals( salary, that.salary );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, age, address, salary );
    }
}
