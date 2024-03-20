package ba.unsa.etf.nwt.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "services")
public class ServiceEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2731425678149216056L;
    @Id
    private long id;
    private String name;
    private String type;
    private int price;

}
