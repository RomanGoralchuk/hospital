package by.itacademy.javaenterprise.goralchuk.entity.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@OptimisticLocking
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @NotNull(message = "Login cannot be null")
    @Max(value = 10, message = "Login should not be greater than 10")
    private String username;
    @NotNull(message = "Password cannot be null")
    @Size(min = 3, max = 15, message = "Password must be between 3 and 15 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private int enabled = 1;
}