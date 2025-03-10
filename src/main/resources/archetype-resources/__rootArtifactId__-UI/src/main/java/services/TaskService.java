package ${package}.services;

import ${package}.client.TaskClient;
import ${package}.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskClient client;

    public TaskService(TaskClient client) {
        this.client = client;
    }

    public List<TaskDTO> findAll()  {
        ResponseEntity<?> response = client.findAll();
        if (response.getStatusCode() == HttpStatus.OK) {
            return (List<TaskDTO>) response.getBody();
        }
        return new ArrayList<>();
    }

    public boolean save(TaskDTO task) {
        ResponseEntity<?> response = client.save(task);
        return response.getStatusCode() == HttpStatus.OK;
    }

    public boolean delete(TaskDTO task) {
        ResponseEntity<?> response = client.deleteById(task.getId());
        return response.getStatusCode() == HttpStatus.OK;
    }

}
