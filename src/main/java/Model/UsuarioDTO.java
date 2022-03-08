package Model;

/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class UsuarioDTO {
    private String idUsuario;
    private String password;
    private String nombre;
    private String paterno;
    private String materno;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
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

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }
    
    

  

    @Override
    public String toString() {
        return "id= " + idUsuario + " password= " + password + "nombre= " + nombre + "paterno= " + paterno + "materno= " + materno;
    }
    
    
}

