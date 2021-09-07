package org.jana.dropwizard.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task_tb")
@NamedQueries({
        @NamedQuery(
                name = "org.jana.dropwizard.core.TaskEntity.findAll",
                query = "select t from TaskEntity t"
        )
})
public class TaskEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "taskDesc", unique = false, nullable = true, length = 100)
    private String taskDesc;

    @Column(name = "taskDate", unique = false, nullable = true, length = 20)
    private String taskDate;

    @Column(name = "taskStatus", unique = false, nullable = true, length = 20)
    private String taskStatus;

    public TaskEntity() {
    }

    public TaskEntity(int id, String taskDesc, String taskDate, String taskStatus) {
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

        if (!(o instanceof TaskEntity)) {
            return false;
        }

        TaskEntity taskEntity = (TaskEntity) o;

        return id == taskEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

