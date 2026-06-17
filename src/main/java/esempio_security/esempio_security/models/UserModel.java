package esempio_security.esempio_security.models;

import esempio_security.esempio_security.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Length(min = 2, max = 19, message = "Username must be between 2 and 19 characters")
    @NotBlank(message = "Username is required")
    private String username;

    @Column
    @NotBlank(message = "Password is required")
    private String password;

    @Column
    @Length(min = 2, max = 19, message = "First name must be between 2 and 19 characters")
    @NotBlank(message = "First name is required")
    private String name;

    @Column
    @Length(min = 2, max = 19, message = "Last name must be between 2 and 19 characters")
    @NotBlank(message = "Last name is required")
    private String cognome;

    @Column(unique = true)
    @Email(message = "Email must be valid")
    @NotNull(message = "Email is required")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserModel() {
    }

    public UserModel(Long id, String username, String password, String name, String cognome, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.cognome = cognome;
        this.email = email;
        this.role = role;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
