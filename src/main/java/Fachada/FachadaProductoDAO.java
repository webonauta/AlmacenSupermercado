
package Fachada;

import DAO.ProductosDAO;
import Model.ProductoDTO;
import javafx.collections.ObservableList;

/**
 * @author Ingrid Casales
 * @author Navarro Villa Emmanuel de Jesús
 */
public class FachadaProductoDAO {
    private ProductosDAO p;
    
    public ObservableList<ProductoDTO> obtenerProductos(){
        return p.getProductos();
    }
}
