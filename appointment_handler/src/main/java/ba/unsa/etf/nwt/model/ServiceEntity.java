package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "services")
public class ServiceEntity implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private int price;

    public ServiceEntity(long id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public ServiceEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
