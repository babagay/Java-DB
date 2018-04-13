package Entity;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public enum Rate implements Serializable {

    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior");

    private String rate;

    private static final long serialVersionUID = 1L;

    Rate(String rate)
    {
        this.rate = rate;
    }

    @Override
    public String toString()
    {
        return rate;
    }
}
