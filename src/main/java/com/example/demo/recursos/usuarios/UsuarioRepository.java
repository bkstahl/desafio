package com.example.demo.recursos.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.recursos.usuarios.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);
	Usuario findByEmailAndSenha(String email, String senha);
	
	@Query(value="SELECT u FROM Usuario u WHERE id = ?1")
	Usuario findUsuario(Long id);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE Usuario u SET u.nome = :nome WHERE u.id = :id",nativeQuery = true)
	int updateUsuarioNameById(Long id, String nome);
}