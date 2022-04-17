package org.generation.gamestory.security;

import java.util.Optional;

import org.generation.gamestory.model.Usuario;
import org.generation.gamestory.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	public UsuarioRepository userRepository;
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + "Este usuario não foi encontrado"));
		
		return usuario.map(UserDetailsImpl::new).get();
		
	}

}
