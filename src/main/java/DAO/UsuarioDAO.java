/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import andres.interfazgrafica.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {

    // Crear un usuario
    boolean crear(Usuario usuario) throws SQLException;

    // Buscar un usuario por su llave primaria
    Usuario buscarPorNombre(String nombre) throws SQLException;

    // Listar todos los usuarios
    List<Usuario> listar() throws SQLException;

    // Actualizar la nota del usuario
    boolean actualizar(Usuario usuario) throws SQLException;

    // Eliminar un usuario por su nombre
    boolean eliminar(String nombre) throws SQLException;
}