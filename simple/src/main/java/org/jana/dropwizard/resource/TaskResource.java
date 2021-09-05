package org.jana.dropwizard.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.jana.dropwizard.dao.TaskDao;
import org.jana.dropwizard.core.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    //    private final TaskRepo taskRepo;
    private final TaskDao taskDao;

    public TaskResource(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GET
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
    public Task saveTask(Task task) {
        Task saved = taskDao.create(task);
        return saved;
    }

}

