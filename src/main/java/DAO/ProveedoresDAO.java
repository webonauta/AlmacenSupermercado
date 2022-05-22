
package DAO;
import Model.ProveedorDTO;
import javafx.collections.ObservableList;

/**
 *
 * @author Ingrid Casales
 */
public interface ProveedoresDAO {

    
    public void insertarProveedor(ProveedorDTO proveedor);
    
    public void eliminarProveedor(ProveedorDTO proveedor);
    
    public ObservableList<ProveedorDTO> getProveedores();
    
    public void actualizarProveedor(ProveedorDTO proveedor,int id);
    
    public int getIdProveedor(String rfc);
}
