package model;

public class ClubInfo {
    private String id;
    private String link;
    private String intro;
    private int member;
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
