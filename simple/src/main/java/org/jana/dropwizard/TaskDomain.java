package org.jana.dropwizard;

public class TaskDomain {

    private long id;
    private String desc;
    private String date;

    public TaskDomain() {
    }

    public TaskDomain(long id, String desc, String date) {
        this.id = id;
        this.desc = desc;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
