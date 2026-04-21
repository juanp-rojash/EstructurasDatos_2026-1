package com.example.demo.Servicio;

import com.example.demo.Model.Dto.User.UsuarioRequest;
import com.example.demo.Model.Dto.User.UsuarioResponse;
import com.example.demo.Model.Entity.Usuario;
import com.example.demo.Model.Entity.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse crearUsuario(UsuarioRequest usuarioRequest){

        try {

            Usuario cliente = Usuario.builder()
                    .Nombre(usuarioRequest.Nombre())
                    .Apellido(usuarioRequest.Apellido())
                    .Edad(usuarioRequest.Edad())
                    .Email(usuarioRequest.Email())
                    .Password(usuarioRequest.Password())
                    .build();

            Usuario alamcenado = usuarioRepository.save(cliente);

            return new UsuarioResponse(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail());

        } catch (Exception e) {
            return null;
        }
    }

    public List<UsuarioResponse> obtenerUsuarios(){

        try {

            return usuarioRepository.findAll()
                    .stream()
                    .map(usuario -> new UsuarioResponse(
                            usuario.getId(),
                            usuario.getNombre(),
                            usuario.getApellido(),
                            usuario.getEmail()
                    )).toList();

        }
        catch (Exception e){
            return null;
        }

    }

}
