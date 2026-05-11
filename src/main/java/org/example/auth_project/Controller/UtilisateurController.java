package org.example.auth_project.Controller;


import lombok.RequiredArgsConstructor;
import org.example.auth_project.Entity.Utilisateurs;
import org.example.auth_project.Repository.UtilisateursRepository;
import org.example.auth_project.Services.CustomuserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UtilisateurController {


    private final UserDetailsService userDetailsService;

    private  Utilisateurs utilisateurs;
    private final UtilisateursRepository utilisateursRepository;

    private final CustomuserDetailServices customuserDetailServices;

    private final  AuthenticationManager authenticationManager;

    private  final PasswordEncoder passwordEncoder;


    @PostMapping("/register") // N'oublie pas le slash /
    public ResponseEntity<?> register(@RequestBody Utilisateurs utilisateurs) {

        // CORRECTION : On utilise isPresent() sur l'Optional renvoyé par le repository
        if (utilisateursRepository.findByEmail(utilisateurs.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 est plus précis que 401 pour un doublon
                    .body("Cet email est déjà utilisé");
        }

        // Hashage du mot de passe
        utilisateurs.setPassword(passwordEncoder.encode(utilisateurs.getPassword()));

        // Sauvegarde en base de données
        Utilisateurs newUtilisateur = utilisateursRepository.save(utilisateurs);

        // Retourne l'utilisateur créé
        return ResponseEntity.ok(newUtilisateur);
    }


}
