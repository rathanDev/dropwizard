package org.jana.dropwizard.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.jana.dropwizard.core.Task;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class TaskDao extends AbstractDAO<Task> {

    public TaskDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> findAll() {
        return list((CriteriaQuery<Task>) namedQuery("org.jana.dropwizard.core.Task.findAll"));
    }

    public Optional<Task> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public Task create(Task task) {
        return persist(task);
    }


}

