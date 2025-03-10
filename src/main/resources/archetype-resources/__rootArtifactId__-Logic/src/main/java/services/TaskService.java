package ${package}.services;

import ${package}.entity.TaskEntity;
import ${package}.repo.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void save(TaskEntity task) {
        taskRepository.save(task);
    }

    @Transactional
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<TaskEntity> findById(UUID id) {
        return taskRepository.findById(id);
    }

}
