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

    @Column(name = "task_desc")
    private String taskDesc;

    @Column(name = "task_date")
    private String taskDate;

    public Task() {
    }

    public Task(int id, String taskDesc, String taskDate) {
        this.id = id;
        this.taskDesc = taskDesc;
        this.taskDate = taskDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
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
                Objects.equals(taskDesc, task.taskDesc) &&
                Objects.equals(taskDate, task.taskDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskDesc, taskDate);
    }

}

