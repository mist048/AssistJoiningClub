package model;

public class Club {
    private String id;
    private String password;
    private String name;
    private String mail;
    private String recogn;
    private String clubInfoId;

    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getMail() {
        return mail;
    }

    protected void setMail(String mail) {
        this.mail = mail;
    }

    protected String getRecogn() {
        return recogn;
    }

    protected void setRecogn(String recogn) {
        this.recogn = recogn;
    }

    protected String getClubInfoId() {
        return clubInfoId;
    }

    protected void setClubInfoId(String clubInfoId) {
        this.clubInfoId = clubInfoId;
    }
}