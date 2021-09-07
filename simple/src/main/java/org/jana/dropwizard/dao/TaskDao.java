package org.jana.dropwizard.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.jana.dropwizard.core.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDao extends AbstractDAO<Task> {

    public TaskDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> findAll() {
        return new ArrayList<>(); //list(namedTypedQuery("org.jana.dropwizard.core.Task.findAll"));
    }

    public Optional<Task> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public Task create(Task task) {
        Task task1 = get(task.getId());
        if (task1 == null) {
            task1 = new Task();
        }
        task1.setId(12);
        task1.setTaskDesc(task.getTaskDesc());
        task1.setTaskDate(task.getTaskDate());
        task1.setTaskStatus(task.getTaskStatus());
        return persist(task1);
    }

}

