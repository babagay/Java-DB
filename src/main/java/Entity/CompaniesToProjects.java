package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "companies_to_projects", schema = "public", catalog = "postgres")
@IdClass( CompaniesToProjectsPK.class )
public class CompaniesToProjects {

    private int companyId;
    private int projectsId;
    private Company companyByCompanyId;
    private Project projectByProjectId;

    @Id
    @Column (name = "company_id", nullable = false)
    public int getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(int companyId)
    {
        this.companyId = companyId;
    }

    @Id
    @Column (name = "projects_id", nullable = false)
    public int getProjectsId()
    {
        return projectsId;
    }

    public void setProjectsId(int projectsId)
    {
        this.projectsId = projectsId;
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
        CompaniesToProjects that = (CompaniesToProjects) o;
        return companyId == that.companyId &&
                projectsId == that.projectsId;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( companyId, projectsId );
    }

    @ManyToOne
    @JoinColumn (name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Company getCompanyByCompanyId()
    {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companiesByCompanyId)
    {
        this.companyByCompanyId = companiesByCompanyId;
    }

    @ManyToOne
    @JoinColumn (name = "projects_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Project getProjectByProjectId()
    {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectsByProjectsId)
    {
        this.projectByProjectId = projectsByProjectsId;
    }
}
