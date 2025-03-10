package ${package}.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String fachId;
    private String name;
    private String description;
    private LocalDate date;
    private String ort;
    private int priority;
    private String deadline;

}
