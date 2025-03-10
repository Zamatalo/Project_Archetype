package ${package}.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO implements Serializable {
    private UUID id;
    @NotEmpty
    private String fachId;
    private String name;
    private String description;
    private LocalDate date;
    private String ort;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int priority;
    private String deadline;
}
