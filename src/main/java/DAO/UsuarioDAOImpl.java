/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import andres.interfazgrafica.Usuario;
import Database.Conexion;


public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean crear(Usuario usuario) throws SQLException {
        String sql =
            "INSERT INTO usuario (nombre, nota) VALUES (?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setDouble(2, usuario.getNota());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Usuario buscarPorNombre(String nombre)
            throws SQLException {

        String sql =
            "SELECT nombre, nota FROM usuario WHERE nombre = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("nombre"),
                        rs.getDouble("nota")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public ArrayList<Usuario> listar() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String sql =
            "SELECT nombre, nota FROM usuario ORDER BY nombre";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(
                    new Usuario(
                        rs.getString("nombre"),
                        rs.getDouble("nota")
                    )
                );
            }
        }

        return usuarios;
    }

    @Override
    public boolean actualizar(Usuario usuario)
            throws SQLException {

        String sql =
            "UPDATE usuario SET nota = ? WHERE nombre = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, usuario.getNota());
            ps.setString(2, usuario.getNombre());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(String nombre) throws SQLException {
        String sql =
            "DELETE FROM usuario WHERE nombre = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            return ps.executeUpdate() > 0;
        }
    }
}