package br.edu.ifto.pwebII.model.entity;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifto.pwebII.model.jdbc.repository.UsuarioRepository;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public UsuarioDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(login) //bucando pelo login
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Perfil perfil : usuario.getPerfis()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + perfil.getNome()));
        }

        return User.withUsername(usuario.getLogin())
                .password(usuario.getPassword())
                .authorities(authorities)
                .disabled(!usuario.isAtivo())
                .build();
    }
}
