package Model;

import java.util.Date;



/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class ProductoDTO {
    private String nombre;
    private float cantidad;
    private Date fechaIngreso;
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    
    
}
