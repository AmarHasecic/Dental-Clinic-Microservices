package ba.unsa.etf.nwt.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class AppointmentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2731425678149216052L;
    @Id
    private long id;
    private Date date;
    private long dentistID;
    private long patientID;
    private long serviceID;
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getDentistID() {
        return dentistID;
    }

    public void setDentistID(long dentistID) {
        this.dentistID = dentistID;
    }

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }

    public long getServiceID() {
        return serviceID;
    }

    public void setServiceID(long serviceID) {
        this.serviceID = serviceID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
