package org.jana.dropwizard.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.jana.dropwizard.core.Task;
import org.jana.dropwizard.dao.TaskDao;

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
    @Path("/create")
//    @Timed
    @UnitOfWork // @Valid
    public Task create() {
        Task task = new Task(8, "desc8", "2021-01-08", "PENDING");
        return taskDao.create(task);
        // return saved;
    }

//    @POST
//    @Path("/{id}")
//    @UnitOfWork
//    public Task update(@PathParam("id") int id, Task task) {
//        Task record = taskDao.findById(id).orElseThrow(RuntimeException::new);
//        if (task.getTaskDesc() != null && !task.getTaskDesc().isEmpty()) {
//            record.setTaskDesc(task.getTaskDesc());
//        }
//        if (task.getTaskDate() != null && !task.getTaskDate().isEmpty()) {
//            record.setTaskDate(task.getTaskDate());
//        }
//        if (task.getTaskStatus() != null && !task.getTaskStatus().isEmpty()) {
//            record.setTaskStatus(task.getTaskStatus());
//        }
//        return taskDao.saveOrUpdate(record);
//    }

}

