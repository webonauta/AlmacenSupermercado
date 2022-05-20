package DAO;

import Model.ProductoDTO;
import javafx.collections.ObservableList;


/**
 *
 * @author Ingrid Casales
 */
public interface ProductosDAO {

    
    public void insertarProducto(ProductoDTO producto);
    
    public void actualizarProducto(ProductoDTO producto,int id);
    
    public void eliminarProducto(ProductoDTO producto);
    
    public ObservableList<ProductoDTO> getProductos();
    
    public ObservableList<String> getCategorias();
     
    public int getIdProducto(String clave);
}
