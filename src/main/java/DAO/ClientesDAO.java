package DAO;

import Model.ClienteDTO;
import javafx.collections.ObservableList;


/**
 *
 * @author Ingrid Casales 
 */
public interface ClientesDAO {
    
    public void insertarCliente(ClienteDTO cliente);
    
    public void eliminarCliente(ClienteDTO cliente);
    
    public ObservableList<ClienteDTO> getClientes();
    
    public int getIdCliente(String paterno,String materno,String nombre);
    
    public void actualizarCliente(ClienteDTO cliente , int id);
}
