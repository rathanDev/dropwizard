package org.jana.dropwizard.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task_tb")
@NamedQueries({
        @NamedQuery(
                name = "org.jana.dropwizard.core.Task.findAll",
                query = "select t from Task t"
        )
})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "task_id")
    private int id;

    @Column(name = "desc")
    private String desc;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private String status;

    public Task() {
    }

    public Task(int id, String desc, String date, String status) {
        this.id = id;
        this.desc = desc;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task task = (Task) o;

        return id == task.id &&
                Objects.equals(desc, task.desc) &&
                Objects.equals(date, task.date) &&
                Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc, date, status);
    }

}

