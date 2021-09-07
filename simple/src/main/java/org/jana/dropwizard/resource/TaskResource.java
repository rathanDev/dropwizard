package org.jana.dropwizard.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.jana.dropwizard.dao.TaskDao;
import org.jana.dropwizard.domain.TaskDomain;
import org.jana.dropwizard.domain.TaskStatus;
import org.jana.dropwizard.entity.TaskEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskDao taskDao;

    public TaskResource(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GET
    @UnitOfWork
    public List<TaskEntity> getAllTasks() {
        return taskDao.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public TaskEntity getById(@PathParam("id") int id) {
        return taskDao
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @POST
    @UnitOfWork
    public String create(TaskDomain req) {
        TaskEntity entity = new TaskEntity(
                UUID.randomUUID().toString(),
                req.getTaskDesc(),
                req.getTaskDate(),
                TaskStatus.PENDING.toString()
        );
        return taskDao.saveOrUpdate(entity).getId();
    }

    @POST
    @Path("/{id}")
    @UnitOfWork
    public String update(@PathParam("id") int id, TaskDomain req) {
        TaskEntity entity = taskDao.findById(id).orElseThrow(RuntimeException::new);
        if (req.getTaskDesc() != null && !req.getTaskDesc().trim().isEmpty()) {
            entity.setTaskDesc(req.getTaskDesc());
        }
        if (req.getTaskDate() != null) {
            entity.setTaskDate(req.getTaskDate());
        }
        if (req.getTaskStatus() != null) {
            entity.setTaskStatus(req.getTaskStatus().toString());
        }
        taskDao.saveOrUpdate(entity);
        return entity.getId();
    }

}

