package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "services")
public class ServiceEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2731425678149216056L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    private int price;

}
