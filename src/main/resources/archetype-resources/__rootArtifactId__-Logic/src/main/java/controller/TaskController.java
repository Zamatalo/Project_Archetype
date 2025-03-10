package ${package}.controller;

import ${package}.client.TaskClient;
import ${package}.dto.TaskDTO;
import ${package}.entity.TaskEntity;
import ${package}.services.TaskService;
import ${package}.utility.TaskMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(TaskClient.SUB_URL)
public class TaskController implements TaskClient {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService,
                          TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @Override
    public ResponseEntity<String> save(@RequestBody TaskDTO taskDTO) {
        try {
            TaskEntity taskEntity = taskMapper.toTaskEntity(taskDTO);

            taskService.save(taskEntity);

            return new ResponseEntity<>("Task saved successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error saving task: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteById(UUID userID) {
        taskService.deleteById(userID);

        if (taskService.findById(userID).isEmpty()) {
            return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);

        }
        return new ResponseEntity<>("Error deleting task: ", HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<List<TaskDTO>> findAll() {
        List<TaskDTO> taskDTOS = taskService.findAll()
                .stream()
                .map(taskMapper::toTaskDTO)
                .toList();
        return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDTO> findById(UUID id) {
        Optional<TaskEntity> taskEntity = taskService.findById(id);
        if (taskEntity.isPresent()) {
            return new ResponseEntity<>(taskMapper.toTaskDTO(taskEntity.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody TaskDTO taskDTO) {
        Optional<TaskEntity> optionalTaskEntity = taskService.findById(taskDTO.getId());
        if (optionalTaskEntity.isPresent()) {
            TaskEntity existingTask = optionalTaskEntity.get();

            existingTask.setName(taskDTO.getName());
            existingTask.setDate(taskDTO.getDate());
            existingTask.setOrt(taskDTO.getOrt());
            existingTask.setPriority(taskDTO.getPriority());
            existingTask.setDeadline(taskDTO.getDeadline());

            taskService.save(existingTask);

            return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }
}
