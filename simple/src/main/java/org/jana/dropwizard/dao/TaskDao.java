package org.jana.dropwizard.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.jana.dropwizard.core.Task;

import java.util.List;
import java.util.Optional;

public class TaskDao extends AbstractDAO<Task> {

    public TaskDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> findAll() {
        return list(namedTypedQuery("org.jana.dropwizard.core.Task.findAll"));
    }

    public Optional<Task> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public Task saveOrUpdate(Task task) {
        return persist(task);
    }

}

