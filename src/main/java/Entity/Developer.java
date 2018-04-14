package Entity;


import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

// Можно использовать либо optimizedSequence, либо SerialSequence для автогенерации ID
// Но для optimizedSequence хибенейт требовал секвенцию DEVELOPER_SEQ - пришлось ее создать,
// т.к. не понятно, как указать другую.
// В то время, как в аннотации  SequenceGenerator имя используемой последовательности
// сообщается через поле sequenceName
@GenericGenerator (
        name = "optimizedSequence",
        strategy = "enhanced-sequence",
        parameters = {
                @Parameter (name = "prefer_sequence_per_entity", value = "true"),
                @Parameter (name = "optimizer", value = "hilo"),
                @Parameter (name = "increment_size", value = "50"),
                @Parameter (name = "", value = "")
        })
@SequenceGenerator (name = "SerialSequence", sequenceName = "SERIAL", allocationSize = 1)
@Entity
@Table (name = "developers", schema = "public", catalog = "postgres")
@TypeDef( name = "postgres_human_sex", typeClass = PostgreSQLEnumType.class)
public class Developer {
    private int id;
    private String name;
    private int age;
    private Sex sex;
    private String address;
    private BigDecimal salary;

    private Collection<DevelopersToProjects> projectsByDeveloperId;
    private Collection<DevelopersToSkills> developersToSkills;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "human_sex")
    @Type( type = "postgres_human_sex")
    public Sex getSex()
    {
        return sex;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    @Id
    @Column (name = "id", nullable = false)
    // @GeneratedValue (generator = "optimizedSequence", strategy = GenerationType.AUTO) // OK
    @GeneratedValue (generator = "SerialSequence", strategy = GenerationType.SEQUENCE)
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
