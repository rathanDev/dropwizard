package org.jana.dropwizard.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.jana.dropwizard.core.TaskEntity;

import java.util.List;
import java.util.Optional;

public class TaskDao extends AbstractDAO<TaskEntity> {

    public TaskDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<TaskEntity> findAll() {
        return list(namedTypedQuery("org.jana.dropwizard.core.TaskEntity.findAll"));
    }

    public Optional<TaskEntity> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public TaskEntity create(TaskEntity taskEntity) {
        return persist(taskEntity);
    }

}

