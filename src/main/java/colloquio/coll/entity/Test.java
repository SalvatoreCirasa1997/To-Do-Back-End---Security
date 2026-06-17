package colloquio.coll.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "test",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_test_email", columnNames = "email")
        },
        indexes = {
                @Index(name = "idx_test_name", columnList = "name"),
                @Index(name = "idx_test_email", columnList = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(min = 2, max = 100, message = "Il nome deve essere tra 2 e 100 caratteri")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Email(message = "L'email deve essere valida")
    @NotBlank(message = "L'email è obbligatoria")
    @Size(max = 150, message = "L'email non può superare i 150 caratteri")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 8, max = 255, message = "La password deve essere di almeno 8 caratteri")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Il numero di telefono non è valido")
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 255, message = "L'indirizzo non può superare i 255 caratteri")
    @Column(name = "address", length = 255)
    private String address;

    @Size(max = 100, message = "La città non può superare i 100 caratteri")
    @Column(name = "city", length = 100)
    private String city;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    @Builder.Default
    private Boolean active = true;

    @Version
    @Column(name = "version")
    private Integer version;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (active == null) active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}