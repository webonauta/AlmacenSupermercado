package Model;

/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class UsuarioDTO {
    private int idUsuario;
    private String password;
    private String nombre;
    
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "id= " + idUsuario + " password= " + password + "nombre= " + nombre;
    }
    
    
}

