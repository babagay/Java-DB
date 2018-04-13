package Entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "developers_to_skills", schema = "public", catalog = "postgres")
@IdClass (DevelopersToSkillsPK.class)
public class DevelopersToSkills {
    private int developerId;
    private int skillId;
    private Developer developerByDeveloperId;
    private Skill skillBySkillId;

    @Id
    @Column (name = "developer_id", nullable = false )
    public int getDeveloperId()
    {
        return developerId;
    }

    public void setDeveloperId(int developerId)
    {
        this.developerId = developerId;
    }

    @Id
    @Column (name = "skill_id", nullable = false)
    public int getSkillId()
    {
        return skillId;
    }

    public void setSkillId(int skillId)
    {
        this.skillId = skillId;
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
        DevelopersToSkills that = (DevelopersToSkills) o;
        return developerId == that.developerId &&
                skillId == that.skillId;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( developerId, skillId );
    }

    @ManyToOne
    @JoinColumn (name = "developer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Developer getDeveloperByDeveloperId()
    {
        return developerByDeveloperId;
    }

    public void setDeveloperByDeveloperId(Developer dev)
    {
        developerByDeveloperId = dev;
    }

    @ManyToOne
    @JoinColumn (name = "skill_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Skill getSkillBySkillId()
    {
        return skillBySkillId;
    }

    public void setSkillBySkillId(Skill skillBySkillId)
    {
        this.skillBySkillId = skillBySkillId;
    }
}
