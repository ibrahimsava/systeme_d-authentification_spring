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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Utilisateurs> utilisateurs = utilisateursRepository.findByEmail(email);

          if(utilisateurs == null){

              throw new UsernameNotFoundException("User not found" + email);
          }

          return org.springframework.security.core.userdetails.User.builder()
                  .username(utilisateurs.get().getEmail())
                  .password(utilisateurs.get().getPassword())
                  .roles(utilisateurs.get().getRole())
                  .build();

    }

}
