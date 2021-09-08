package org.jana.dropwizard.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.jana.dropwizard.dao.TaskDao;
import org.jana.dropwizard.domain.TaskDomain;
import org.jana.dropwizard.domain.TaskStatus;
import org.jana.dropwizard.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
    private final Logger log = LoggerFactory.getLogger(TaskResource.class);

    private final TaskDao taskDao;

    public TaskResource(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GET
    @UnitOfWork
    public List<TaskDomain> getAll() {
        log.debug("Get all tasks");
        return taskDao.findAll()
                .stream()
                .map(e -> new TaskDomain(e.getId(), e.getTaskDesc(), e.getTaskDate(), TaskStatus.valueOf(e.getTaskStatus())))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public TaskDomain getById(@PathParam("id") String id) {
        log.debug("Get task by id:{}", id);
        Optional<TaskEntity> entityOpt = taskDao.findById(id);
        if (!entityOpt.isPresent()) {
            return new TaskDomain();
        }
        TaskEntity entity = entityOpt.get();
        return new TaskDomain(entity.getId(), entity.getTaskDesc(), entity.getTaskDate(), TaskStatus.valueOf(entity.getTaskStatus()));
    }

    @POST
    @UnitOfWork
    public TaskDomain create(TaskDomain req) {
        log.debug("Create task: {}", req);
        TaskEntity entity = new TaskEntity(
                UUID.randomUUID().toString(),
                req.getTaskDesc(),
                req.getTaskDate(),
                TaskStatus.PENDING.toString()
        );
        taskDao.saveOrUpdate(entity);
        return new TaskDomain(entity.getId(), entity.getTaskDesc(), entity.getTaskDate(), TaskStatus.valueOf(entity.getTaskStatus()));
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public String update(@PathParam("id") String id, TaskDomain req) {
        log.debug("Update task id:{} req:{}", id, req);
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

