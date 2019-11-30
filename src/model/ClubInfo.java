package model;

public class ClubInfo {
    private String id;
    private String link;
    private String recogn;
    private String intro;
    private int member;
    private int submember;
    private String icon;
    private String home;

    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected String getLink() {
        return link;
    }

    protected void setLink(String link) {
        this.link = link;
    }

    protected String getRecogn() {
        return recogn;
    }

    protected void setRecogn(String recogn) {
        this.recogn = recogn;
    }

    protected String getIntro() {
        return intro;
    }

    protected void setIntro(String intro) {
        this.intro = intro;
    }

    protected int getMember() {
        return member;
    }

    protected void setMember(int member) {
        this.member = member;
    }

    protected int getSubmember() {
        return submember;
    }

    protected void setSubmember(int submember) {
        this.submember = submember;
    }

    protected String getIcon() {
        return icon;
    }

    protected void setIcon(String icon) {
        this.icon = icon;
    }

    protected String getHome() {
        return home;
    }

    protected void setHome(String home) {
        this.home = home;
    }
}
