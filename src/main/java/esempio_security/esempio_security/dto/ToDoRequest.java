package esempio_security.esempio_security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public class ToDoRequest {
    @NotBlank(message = "Todo is required")
    private String todo;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;

    private boolean done = false;

    public ToDoRequest() {
    }

    public ToDoRequest(String todo, LocalDate expiryDate, boolean done) {
        this.todo = todo;
        this.expiryDate = expiryDate;
        this.done = done;
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
