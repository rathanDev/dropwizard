package org.jana.dropwizard.other;

import org.jana.dropwizard.other.TaskDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepo {
    private final List<TaskDomain> taskDomains = new ArrayList<>();

    public List<TaskDomain> findAll() {
        return taskDomains;
    }

    public Optional<TaskDomain> findById(Long id) {
        return taskDomains.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public TaskDomain save(TaskDomain taskDomain) {
        taskDomains.add(taskDomain);
        return taskDomain;
    }

}
