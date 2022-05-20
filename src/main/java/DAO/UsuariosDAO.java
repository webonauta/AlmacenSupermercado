package DAO;

import Model.UsuarioDTO;

/**
 *
 * @author Ingrid Casales
 */
public interface UsuariosDAO {
    
    public UsuarioDTO validarUsuario(String id_usuario,String password);
}
