package Model;

/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class ProductoDTO {
    private String clave;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String fechaAlta;
    private int cantidad;
    private float precioUnitario;
    private float precioVenta;
    private float descuento;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "clave=" + clave + ", nombre=" + nombre + ", descripcion=" + descripcion + ", categoria=" + categoria + ", fechaAlta=" + fechaAlta + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", precioVenta=" + precioVenta + ", descuento=" + descuento + '}';
    }
    
    
}
