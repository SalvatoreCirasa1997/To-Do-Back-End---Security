package esempio_security.esempio_security.models;
import esempio_security.esempio_security.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;
import org.hibernate.mapping.Constraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

//una classe che implementa l'interfaccia UserDetails ereditando diversi metodi
@Entity
@Table(name = "Users")
public class UserModel implements UserDetails {
    //attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    @NotBlank
    private String username;

    @Column
    @NotBlank
    private String password;
    @Column
    @NotBlank
    @Size(max = 19)
    private String name;

    @Column
    @NotBlank
    private String cognome;

    @Column(unique = true)
    @NotBlank
    @Email
    private String email;

    @Enumerated(EnumType.STRING) //enum
    private Role role;

    //costruttore vuoto e pieno
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //settare i seguenti metodi ereditati a return true
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

    public void setUsername(String username) {
        this.username = username;
    }

    //cambiare il tipo di return per restituire una lista di ruoli
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
