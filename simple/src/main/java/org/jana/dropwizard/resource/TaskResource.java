package org.jana.dropwizard.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.jana.dropwizard.core.TaskEntity;
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
    public int create(TaskEntity taskEntity) {
        return taskDao.create(taskEntity).getId();
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

