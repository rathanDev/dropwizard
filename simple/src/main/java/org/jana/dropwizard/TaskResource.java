package org.jana.dropwizard;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskRepo taskRepo;

    public TaskResource(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GET
    public List<TaskDomain> getAllTasks() {
        return taskRepo.findAll();
    }

    @GET
    @Path("/{id}")
    public TaskDomain getById(@PathParam("id") Long id) {
        return taskRepo
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @POST
    public TaskDomain saveTask(TaskDomain taskDomain) {
        TaskDomain saved = taskRepo.save(taskDomain);
        return saved;
    }

}

