package ${package}.client;

import ${package}.dto.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "tasks", url = "localhost:${server_port_backend}"+ TaskClient.SUB_URL )
public interface TaskClient {
    String SUB_URL = "/api/tasks";

    @PostMapping("save")
    ResponseEntity<String> save(@RequestBody TaskDTO taskDTO);

    @DeleteMapping("delete/{id}")
    ResponseEntity<String> deleteById(@PathVariable("id") UUID id);

    @GetMapping("findAll")
    ResponseEntity<List<TaskDTO>> findAll();

    @GetMapping
    ResponseEntity<TaskDTO> findById(@RequestParam("id") UUID id);

    @PatchMapping("update")
    ResponseEntity<String> update(@RequestBody TaskDTO taskDTO);
}
