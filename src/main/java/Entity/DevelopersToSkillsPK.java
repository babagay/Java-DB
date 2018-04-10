package Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DevelopersToSkillsPK implements Serializable {
    private int developerId;
    private int skillId;

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

    @Column (name = "skill_id", nullable = false)
    @Id
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
        DevelopersToSkillsPK that = (DevelopersToSkillsPK) o;
        return developerId == that.developerId &&
                skillId == that.skillId;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( developerId, skillId );
    }
}

