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

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "task_id")
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "taskDesc", unique = false, nullable = true, length = 100)
    private String taskDesc;

    @Column(name = "taskDate")
    private String taskDate;

    @Column(name = "taskStatus")
    private String taskStatus;

    public Task() {
    }

    public Task(int id, String taskDesc, String taskDate, String taskStatus) {
        this.id = id;
        this.taskDesc = taskDesc;
        this.taskDate = taskDate;
        this.taskStatus = taskStatus;
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

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
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
                Objects.equals(taskDate, task.taskDate) &&
                Objects.equals(taskStatus, task.taskStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskDesc, taskDate, taskStatus);
    }

}

