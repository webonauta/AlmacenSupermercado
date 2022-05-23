package Controller;

import DAO.ClientesDAO;
import DAO.impl.ClientesDAOImpl;
import Model.ClienteDTO;
import controller.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaAgregarClientesController extends ClientesDAOImpl implements Initializable {

    @FXML
    private Button btnAgregarCliente;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPaterno;
    @FXML
    private TextField txtMaterno;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @Inject
    private ClientesDAO c;
    private boolean actualizar = false;
    private ClienteDTO cliente;
    @FXML
    private Label lblClientes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaClientes.fxml");
    }
    
 
    @FXML
    private void agregarCliente(ActionEvent event) throws IOException {
        int id = getIdCliente(this.cliente.getApellidoPaterno(),this.cliente.getApellidoMaterno(),this.cliente.getNombre());
        ClienteDTO cliente = new ClienteDTO(txtNombre.getText(),txtPaterno.getText(), txtMaterno.getText(), txtTelefono.getText(), txtDireccion.getText());
        
        
        if(actualizar == false){
           insertarCliente(cliente);
           //System.out.println("Insertando");
       }else{
           actualizarCliente(cliente,id);
           lanzarSiguienteVentana("VistaClientes.fxml");
           
       }
    }
    
     private void lanzarSiguienteVentana(String vista) throws IOException{
            FXMLLoader loader = new FXMLLoader();//cardo la vista
            loader.setLocation(Main.class.getResource("/View/"+vista));
            //cargo la ventana
            AnchorPane ventana = (AnchorPane) loader.load();
            Scene scene = new Scene(ventana);//panel
            //seteo ka scene y la muestro
            Stage primaryStage = new Stage();//jframe
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
            ((Stage) (btnAgregarCliente.getScene().getWindow())).close();
    }
     
      public void Actualizar(){
        
        this.actualizar = true;
    } 
    
    public void setProducto(ClienteDTO cliente){
        this.cliente = cliente;
        txtNombre.setText(cliente.getNombre());
        txtPaterno.setText(cliente.getApellidoPaterno());
        txtMaterno.setText(cliente.getApellidoMaterno());
        txtTelefono.setText(cliente.getTelefono());
        txtDireccion.setText(cliente.getDireccion());
    }
    
    public void setEtiquetaProducto(){
        lblClientes.setText("Actualizar cliente");
    }
    
}
