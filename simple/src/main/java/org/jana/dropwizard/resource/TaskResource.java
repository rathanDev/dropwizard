package org.jana.dropwizard.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.jana.dropwizard.core.Task;
import org.jana.dropwizard.dao.TaskDao;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskDao taskDao;

    public TaskResource(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GET
    @UnitOfWork
    public List<Task> getAllTasks() {
        return taskDao.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Task getById(@PathParam("id") int id) {
        return taskDao
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @POST
    @UnitOfWork
    public Task create(@Valid Task task) {
        return taskDao.saveOrUpdate(task);
        // return saved;
    }

    @POST
    @Path("/{id}")
    @UnitOfWork
    public Task update(@PathParam("id") int id, Task task) {
        Task record = taskDao.findById(id).orElseThrow(RuntimeException::new);
        if (task.getDesc() != null && !task.getDesc().isBlank()) {
            record.setDesc(task.getDesc());
        }
        if (task.getDate() != null && !task.getDate().isBlank()) {
            record.setDate(task.getDate());
        }
        if (task.getStatus() != null && !task.getStatus().isBlank()) {
            record.setStatus(task.getStatus());
        }
        return taskDao.saveOrUpdate(record);
    }

}

