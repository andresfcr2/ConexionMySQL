package andres.interfazgrafica.API;

import DAO.UsuarioDAO;
import DAO.UsuarioDAOImpl;
import andres.interfazgrafica.Usuario;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioDAO dao = new UsuarioDAOImpl();

    // GET /api/usuarios
    @GetMapping
    public List<Usuario> listar() throws SQLException {
        return dao.listar();
    }

    // GET /api/usuarios/{nombre}
    @GetMapping("/{nombre}")
    public ResponseEntity<Usuario> buscar(@PathVariable String nombre) throws SQLException {
        Usuario u = dao.buscarPorNombre(nombre);
        return u == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    // POST /api/usuarios   body: {"nombre":"Ana","nota":4.5}
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) throws SQLException {
        if (dao.buscarPorNombre(usuario.getNombre()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        dao.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // PUT /api/usuarios/{nombre}   body: {"nota":3.8}
    @PutMapping("/{nombre}")
    public ResponseEntity<Usuario> actualizar(@PathVariable String nombre,
                                              @RequestBody Usuario usuario) throws SQLException {
        usuario.setNombre(nombre);
        return dao.actualizar(usuario)
                ? ResponseEntity.ok(usuario)
                : ResponseEntity.notFound().build();
    }

    // DELETE /api/usuarios/{nombre}
    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> eliminar(@PathVariable String nombre) throws SQLException {
        return dao.eliminar(nombre)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}