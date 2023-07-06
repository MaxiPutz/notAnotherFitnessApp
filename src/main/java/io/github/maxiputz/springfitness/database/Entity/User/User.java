package io.github.maxiputz.springfitness.database.Entity.User;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String clientId;
    
    @Column(columnDefinition = "TEXT")
    private String clientSecret;
    private String entryNumber;

    public User() {
    }

    public User(String name, String password) {

        this.username = name;
        this.password = password;
    }
}
