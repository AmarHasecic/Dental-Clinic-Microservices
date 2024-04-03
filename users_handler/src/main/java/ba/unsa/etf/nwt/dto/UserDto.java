package ba.unsa.etf.nwt.dto;

import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.UserEntity;

import java.io.Serializable;

public class UserDto implements Serializable {

    private Long id;
    private String mail;
    private String password;
    private String accessLevel;
    private UserEntity user;
    private DentistEntity dentist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public DentistEntity getDentist() {
        return dentist;
    }

    public void setDentist(DentistEntity dentist) {
        this.dentist = dentist;
    }
}
