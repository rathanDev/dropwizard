package org.jana.dropwizard.core;

import javax.persistence.*;

@Entity
@Table(name = "task_tb")
@NamedQueries({
        @NamedQuery(
                name = "org.jana.dropwizard.core.Task.findAll",
                query = "select t from Task t"
        )
})
/*
,
        @NamedQuery(
                name = "org.jana.dropwizard.core.Task.findByDesc",
                query = "select t from Task t " +
                        "where t.task_desc like :desc ")
* */

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "task_desc")
    private String taskDesc;

    @Column(name = "task_date")
    private String taskDate;

    public Task() {
    }

    public Task(int taskId, String taskDesc, String taskDate) {
        this.taskId = taskId;
        this.taskDesc = taskDesc;
        this.taskDate = taskDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

}

