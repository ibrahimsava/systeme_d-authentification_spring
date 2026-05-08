package org.example.auth_project.Entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class Utilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

}
