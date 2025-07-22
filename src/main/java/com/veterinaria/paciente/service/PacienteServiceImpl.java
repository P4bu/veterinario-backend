package com.veterinaria.paciente.service;

import com.veterinaria.auth.model.Erole;
import com.veterinaria.auth.model.Role;
import com.veterinaria.auth.model.Usuario;
import com.veterinaria.auth.repository.RoleRepository;
import com.veterinaria.auth.repository.UsuarioRepository;
import com.veterinaria.paciente.dto.PacienteDTO;
import com.veterinaria.paciente.dto.PacienteResponseDTO;
import com.veterinaria.paciente.mapper.PacienteMapper;
import com.veterinaria.paciente.model.Paciente;
import com.veterinaria.paciente.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService{

    private final PacienteRepository pacienteRepository;
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository, RoleRepository roleRepository, UsuarioRepository usuarioRepository){
        this.pacienteRepository = pacienteRepository;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Paciente crearPaciente(PacienteDTO pacienteDTO) {
        if(pacienteRepository.existsByUsuarioEmail(pacienteDTO.getEmail())){
            throw new IllegalArgumentException("EL EMAIL YA EXISTE EN LA BASE DE DATOS");
        }

        Role roleUsuario = roleRepository.findByNombre(Erole.PACIENTE);

        Usuario usuario = new Usuario();
        usuario.setNombre(pacienteDTO.getNombre());
        usuario.setApellidoPaterno(pacienteDTO.getApellidoPaterno());
        usuario.setApellidoMaterno(pacienteDTO.getApellidoMaterno());
        usuario.setEmail(pacienteDTO.getEmail());
        if(pacienteDTO.getPassword() == null || pacienteDTO.getPassword().isBlank()) {
            throw new IllegalArgumentException("LA CONTRASEÑA NO PUEDE ESTAR VACIA");
        }
        usuario.setPassword(pacienteDTO.getPassword());
        usuario.getRoles().add(roleUsuario);
        usuario.setRol(pacienteDTO.getRol());
        usuario.setTelefono(pacienteDTO.getTelefono());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        Paciente paciente = new Paciente();
        paciente.setUsuario(usuarioGuardado);

        return pacienteRepository.save(paciente);

    }

    @Override
    public Optional<PacienteResponseDTO> obtenerPacientePorId(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        return paciente.map(PacienteMapper::toResponseDTO);
    }

    @Override
    public List<PacienteResponseDTO> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        return pacientes.stream().map(PacienteMapper::toResponseDTO).collect(Collectors.toList());
    }
}
