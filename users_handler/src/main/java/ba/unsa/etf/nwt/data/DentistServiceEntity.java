package ba.unsa.etf.nwt.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "dentistservice")
public class DentistServiceEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2731425678149216056L;
    @Id
    private long id;
    private long serviceID;
    private long dentistID;
}
