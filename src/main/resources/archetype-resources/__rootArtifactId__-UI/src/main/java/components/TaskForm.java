package ${package}.components;

import ${package}.dto.TaskDTO;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.StringLengthValidator;
import lombok.Getter;

public class TaskForm extends FormLayout {
    @Getter
    private final Binder<TaskDTO> binder = new BeanValidationBinder<>(TaskDTO.class);

    private final TextField fachId = new TextField("Fach ID");
    private final TextField name = new TextField("Name");
    private final TextField description = new TextField("Description");
    private final DatePicker date = new DatePicker("Date");
    private final TextField ort = new TextField("Ort");
    private final TextField priority = new TextField("Priority");
    private final TextField deadline = new TextField("Deadline");

    public TaskForm() {
        add(fachId, name, description, date, ort, priority, deadline);
        configureBinding();
    }

    private void configureBinding() {
        binder.forField(fachId)
                .asRequired("Fach ID is  required")
                .withValidator(new StringLengthValidator("Fach Id must be between 0 and 150 characters", 0, 150))
                .bind(TaskDTO::getFachId, TaskDTO::setFachId);

        binder.forField(name)
                .asRequired("Name is required")
                .withValidator(new StringLengthValidator("Name must be 0-150 characters", 0, 150))
                .bind(TaskDTO::getName, TaskDTO::setName);

        binder.forField(description)
                .withValidator(new StringLengthValidator("Description is too long", 0, 999))
                .bind(TaskDTO::getDescription, TaskDTO::setDescription);

        binder.forField(priority)
                .asRequired("Priority is required")
                .withConverter(Integer::valueOf, String::valueOf, "Must be a number")
                .withValidator(value -> value >= 1 && value <= 100, "Priority must be between 1 and 100")
                .bind(TaskDTO::getPriority, TaskDTO::setPriority);

        binder.forField(date)
                .asRequired("Date is required")
                .bind(TaskDTO::getDate, TaskDTO::setDate);
        binder.forField(ort)
                .withValidator(new StringLengthValidator("Ort is too long", 0, 999))
                .bind(TaskDTO::getOrt, TaskDTO::setOrt);
        binder.forField(deadline)
                .withValidator(new StringLengthValidator("Deadline is too long", 0, 999))
                .bind(TaskDTO::getDeadline, TaskDTO::setDeadline);
    }

    public void validateAndWrite(TaskDTO taskDTO) throws ValidationException {
        binder.writeBean(taskDTO);
    }
}
