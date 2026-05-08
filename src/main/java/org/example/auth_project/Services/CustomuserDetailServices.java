package org.example.auth_project.Services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.auth_project.Entity.Utilisateurs;
import org.example.auth_project.Repository.UtilisateursRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomuserDetailServices implements UserDetailsService {

    private final UtilisateursRepository utilisateursRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateurs utilisateurs = utilisateursRepository.findByEmail(username)

                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

    }

}
