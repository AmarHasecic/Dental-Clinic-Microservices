package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@Table(name = "records")
public class RecordEntity implements Serializable {
    @Id
    private Long id;

    private Long patientId;

    private Long appointmentId;

    private String image;
}
