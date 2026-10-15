package andres.interfazgrafica;

import java.util.ArrayList;

import DAO.UsuarioDAOImpl;

public class Registros {

    UsuarioDAOImpl dao = new UsuarioDAOImpl();

    public String agregarUsuario(Usuario user) {
        try {
            dao.crear(user);
            return "Usuario creado correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Usuario pudo ser creado";
        }

    }

    public String editarUsuario(String nombre, double nota) {

        try {
            Usuario u = new Usuario(nombre, nota);
            dao.actualizar(u);
            return "Usuario actualizado con exito";
        } catch (Exception e) {
            e.printStackTrace();
            return "Usuario no encontrado";
        }
    }

    public String eliminarUsuario(String nombre) {
        try {
            boolean eliminado = dao.eliminar(nombre);
            if (eliminado) {
                return "Usuario eliminado con exito";
            }
            return "Usuario no encontrado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Algo salió mal" + e.getMessage();
        }

    }

    public ArrayList<Usuario> obtenerUsuarios() {
        try {
            return dao.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
