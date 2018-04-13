package Entity;


import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * [issues]
 * В запросе, возвращающем rate, получается rate=null
 *
 * https://vladmihalcea.com/the-best-way-to-map-an-enum-type-with-jpa-and-hibernate/
 * Mapping a Java Enum to a database-specific Enumarated column type
 */

// @TypeDef( name = "skillRate", defaultForType = Rate.class, typeClass = Rate.class)
@TypeDef( name = "skillRate",  typeClass = PostgreSQLEnumType.class)

@Entity
@Table (name = "skills", schema = "public", catalog = "postgres")
public class Skill {
    private int id;
    private String title;
    private Rate rate;
    private Collection<DevelopersToSkills> developersToSkillsById;

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
    @Column (name = "title", nullable = false, length = 50)
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Enumerated(EnumType.STRING)
    // @Column (name = "rate", nullable = false)
    @Column (columnDefinition = "PROGRAMMING_SKILL")
    @Type( type = "skillRate")
    public Rate getRate()
    {
        return rate;
    }

    public void setRate(Rate rate)
    {
        this.rate = rate;
    }

    public void setRate(String rate)
    {
        this.rate = Rate.valueOf( rate );
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
        Skill that = (Skill) o;
        return id == that.id &&
                Objects.equals( title, that.title );
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( id, title );
    }

    @OneToMany (mappedBy = "skillBySkillId")
    public Collection<DevelopersToSkills> getDevelopersToSkillsById()
    {
        return developersToSkillsById;
    }

    public void setDevelopersToSkillsById(Collection<DevelopersToSkills> developersToSkillsById)
    {
        this.developersToSkillsById = developersToSkillsById;
    }

    @Override
    public String toString()
    {
        return "Skill{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                '}';
    }
}