package ${package}.utility;

import ${package}.dto.TaskDTO;
import ${package}.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskEntity toTaskEntity(TaskDTO taskDTO) {
        return TaskEntity.builder()
                .id(taskDTO.getId())
                .fachId(taskDTO.getFachId())
                .name(taskDTO.getName())
                .description(taskDTO.getDescription())
                .date(taskDTO.getDate())
                .ort(taskDTO.getOrt())
                .priority(taskDTO.getPriority())
                .deadline(taskDTO.getDeadline())
                .build();
    }

    public TaskDTO toTaskDTO(TaskEntity taskEntity) {
        return TaskDTO.builder()
                .id(taskEntity.getId())
                .fachId(taskEntity.getFachId())
                .name(taskEntity.getName())
                .description(taskEntity.getDescription())
                .date(taskEntity.getDate())
                .ort(taskEntity.getOrt())
                .priority(taskEntity.getPriority())
                .deadline(taskEntity.getDeadline())
                .build();
    }
}

