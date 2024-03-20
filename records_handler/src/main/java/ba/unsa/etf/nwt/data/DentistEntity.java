package ba.unsa.etf.nwt.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "dentists")
public class DentistEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2731425678149216052L;
    @Id
    private long id;
    private String workHours;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
}
