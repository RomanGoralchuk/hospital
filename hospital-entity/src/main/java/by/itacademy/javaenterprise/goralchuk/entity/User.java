package by.itacademy.javaenterprise.goralchuk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Data
@Entity
@OptimisticLocking
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    private Integer enabled = 1;
}
