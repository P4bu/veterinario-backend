package com.veterinaria.veterinario.service;

import com.veterinaria.auth.model.Erole;
import com.veterinaria.auth.model.Role;
import com.veterinaria.auth.model.Usuario;
import com.veterinaria.auth.repository.RoleRepository;
import com.veterinaria.auth.repository.UsuarioRepository;
import com.veterinaria.veterinario.dto.VeterinarioDTO;
import com.veterinaria.veterinario.dto.VeterinarioResponseDTO;
import com.veterinaria.veterinario.mapper.VeterinarioMapper;
import com.veterinaria.veterinario.model.Veterinario;
import com.veterinaria.veterinario.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{

    private final VeterinarioRepository veterinarioRepository;
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;

    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository, RoleRepository roleRepository, UsuarioRepository usuarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Veterinario crearVeterinario(VeterinarioDTO veterinarioDTO) {
        if(veterinarioRepository.existsByUsuarioEmail(veterinarioDTO.getEmail())){
            throw new IllegalArgumentException("EL EMAIL YA EXISTE EN LA BASE DE DATOS");
        }

        Role roleUsuario = roleRepository.findByNombre(Erole.VETERINARIO);

        Usuario usuario = new Usuario();
        usuario.setNombre(veterinarioDTO.getNombre());
        usuario.setApellidoPaterno(veterinarioDTO.getApellidoPaterno());
        usuario.setApellidoMaterno(veterinarioDTO.getApellidoMaterno());
        usuario.setEmail(veterinarioDTO.getEmail());

        if(veterinarioDTO.getPassword() == null || veterinarioDTO.getPassword().isBlank()) {
            throw new IllegalArgumentException("LA CONTRASEÑA NO PUEDE ESTAR VACIA");
        }
        usuario.setPassword(veterinarioDTO.getPassword());

        usuario.getRoles().add(roleUsuario);
        usuario.setRol(veterinarioDTO.getRol());
        usuario.setTelefono(veterinarioDTO.getTelefono());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        Veterinario veterinario = new Veterinario();
        veterinario.setEspecialidad(veterinarioDTO.getEspecialidad());
        veterinario.setUsuario(usuarioGuardado);

        return veterinarioRepository.save(veterinario);
    }

    @Override
    public Optional<VeterinarioResponseDTO> obtenerVeterinarioPorId(Long id) {
        Optional<Veterinario> veterinario = veterinarioRepository.findById(id);

        return veterinario.map(VeterinarioMapper::toResponseDTO);
    }

    @Override
    public List<VeterinarioResponseDTO> obtenerVeterinarios() {
        List<Veterinario> veterinarios = veterinarioRepository.findAll();

        return veterinarios.stream().map(VeterinarioMapper::toResponseDTO).collect(Collectors.toList());
    }
}
