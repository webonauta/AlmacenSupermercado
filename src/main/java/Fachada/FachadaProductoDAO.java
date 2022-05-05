
package Fachada;

import DAO.ProductosDAO;
import Model.ProductoDTO;
import javafx.collections.ObservableList;

/**
 *
 * Navarro Villa Emmanuel de Jesús
 */
public class FachadaProductoDAO {
    
    public ObservableList<ProductoDTO> obtenerProductos(){
        ProductosDAO p = new ProductosDAO();
        
        return p.getProductos();
    }
}
