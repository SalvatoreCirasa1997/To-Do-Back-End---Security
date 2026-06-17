package esempio_security.esempio_security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ToDoResponse {
    @NotBlank(message = "Todo is required")
    private String todo;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    private boolean done;

    private Long id;

    public ToDoResponse() {
    }

    public ToDoResponse(String todo, LocalDate expiryDate, boolean done, Long id) {
        this.todo = todo;
        this.expiryDate = expiryDate;
        this.done = done;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
