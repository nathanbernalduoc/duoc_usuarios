package com.nathan.duoc_usuarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nathan.duoc_usuarios.dto.UsuarioDto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuasrioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarUsuarioTest() {

        UsuarioDto usuario = new UsuarioDto();
        usuario.setApellidos("Perez");
        usuario.setNombres("Juan");
        usuario.setDireccion("Su casa");
        usuario.setRol(2);
        usuario.setKeyWord("Clave de prueba");
        usuario.setPassword("Contraseña1");
        usuario.setAlias("juanperez");

        UsuarioDto result = usuarioRepository.save(usuario);

        assertNotNull(result.getId());
        assertEquals("Juan", result.getNombres());

    }
    
}
