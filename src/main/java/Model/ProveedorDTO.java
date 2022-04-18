
package Model;

/**
 *
 * @author Navarro Villa Emmanuel de Jesús
 */
public class ProveedorDTO {
    private String nombre;
    private String paterno;
    private String materno;
    private String telefono;
    private String direccion;
    private String empresa;
    private String rfc;

    public ProveedorDTO() {
    }
    
    

    public ProveedorDTO(String nombre, String paterno, String materno, String telefono, String direccion, String empresa, String rfc) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.telefono = telefono;
        this.direccion = direccion;
        this.empresa = empresa;
        this.rfc = rfc;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return "ProveedorDTO{" + "nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno + ", telefono=" + telefono + ", direccion=" + direccion + ", empresa=" + empresa + ", rfc=" + rfc + '}';
    }
    
    
}
