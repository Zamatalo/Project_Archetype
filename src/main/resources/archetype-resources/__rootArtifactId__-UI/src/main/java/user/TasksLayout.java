package ${package}.layouts.user;

import ${package}.components.TaskForm;
import ${package}.dto.TaskDTO;
import ${package}.services.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AccessDeniedErrorRouter;

import java.util.List;

@Route(value = "user")
@RouteAlias("/")
@AccessDeniedErrorRouter(rerouteToError = NotFoundException.class)
public class TasksLayout extends VerticalLayout {
    private final TaskService taskService;
    private final Grid<TaskDTO> tasks = new Grid<>(TaskDTO.class, true);
    private final Dialog dialog = new Dialog();
    private final TaskForm formLayout = new TaskForm();

    public TasksLayout(TaskService taskService) {
        this.taskService = taskService;
        tasks.removeColumnByKey("id");
        updateGrid();

        tasks.addItemClickListener(event -> dialogOpen(event.getItem()));
        Button addButton = new Button("Add new entry", _ -> dialogOpen(new  TaskDTO()));
        add(addButton, tasks);
        setAlignItems(Alignment.CENTER);
    }

    private void updateGrid() {
        List<TaskDTO> taskDTOS = taskService.findAll();
        tasks.setItems(taskDTOS);
    }

    private void dialogOpen(TaskDTO taskDTO) {
        dialog.removeAll();
        formLayout.getBinder().setBean(taskDTO);

        Button saveButton = new Button("Save", _ -> {
            try {
                formLayout.validateAndWrite(taskDTO);
                saveBean(taskDTO);
            } catch (Exception e) {
                Notification.show("Validation failed: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            }
        });

        Button deleteButton = new Button("Delete", _ -> deleteBean(taskDTO));
        deleteButton.setVisible(taskDTO.getId() != null);

        dialog.add(formLayout, new HorizontalLayout(saveButton, deleteButton));
        dialog.open();
    }

    private void saveBean(TaskDTO taskDTO) {
        boolean result = taskService.save(taskDTO);
        if (result) {
            Notification.show("Saved successfully", 3000, Notification.Position.BOTTOM_START);
            updateGrid();
            dialog.close();
        } else {
            Notification.show("Saving failed", 3000, Notification.Position.MIDDLE);
        }
    }


    private void deleteBean(TaskDTO taskDTO) {
        if (taskService.delete(taskDTO)) {
            updateGrid();
            Notification.show("Deleted successfully", 3000, Notification.Position.BOTTOM_START);
            dialog.close();
        } else {
            Notification.show("Deletion failed", 3000, Notification.Position.MIDDLE);
        }
    }
}
