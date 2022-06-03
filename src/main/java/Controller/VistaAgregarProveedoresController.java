package Controller;

import DAO.impl.ProveedoresDAOImpl;
import Model.ProveedorDTO;
import controller.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alberto
 * @author Ingrid Casales
 */
public class VistaAgregarProveedoresController extends ProveedoresDAOImpl implements Initializable {
    @FXML
    private Button btnAgregarProveedor;
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
    @FXML
    private TextField txtEmpresa;
    @FXML
    private TextField txtRFC;
    private boolean actualizar = false;
    @FXML
    private Label lblProveedores;
    @FXML
    private Button btnRegresar;
    
    private ProveedorDTO proveedor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(ActionEvent event) throws IOException {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Deseas regresar?", ButtonType.YES, ButtonType.NO);

              // clicking X also means no
              ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

              if (!ButtonType.NO.equals(result)) {
                    lanzarSiguienteVentana("VistaProveedores.fxml");
              }

    }
    
    @FXML
    private void registrarProveedor(ActionEvent event) throws IOException {
        Alert alert = null;
        ProveedorDTO proveedor = new ProveedorDTO(txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtTelefono.getText(), txtDireccion.getText(),txtEmpresa.getText(), txtRFC.getText());
      
        System.out.println(proveedor);
        
        
        if(actualizar == false){
             alert =  new Alert(Alert.AlertType.CONFIRMATION, "¿Deseas registrar un nuevo proveedor?", ButtonType.YES, ButtonType.NO);
            // clicking X also means no
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (!ButtonType.NO.equals(result)) {
                insertarProveedor(proveedor);
            }
       }else{
            alert =  new Alert(Alert.AlertType.CONFIRMATION, "¿Deseas aztualizar proveedor?", ButtonType.YES, ButtonType.NO);
            // clicking X also means no
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (!ButtonType.NO.equals(result)) {
                int id = getIdProveedor(this.proveedor.getRfc());
                actualizarProveedor(proveedor,id);
                lanzarSiguienteVentana("VistaProveedores.fxml");
            }
           
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
            
            ((Stage) (btnAgregarProveedor.getScene().getWindow())).close();
    }
     
    public void Actualizar(){
        
        this.actualizar = true;
    } 
    
     public void setProveedor(ProveedorDTO proveedor){
        this.proveedor = proveedor;
        txtNombre.setText(proveedor.getNombre());
        txtPaterno.setText(proveedor.getPaterno());
        txtMaterno.setText(proveedor.getMaterno());
        txtDireccion.setText(proveedor.getDireccion());
        txtEmpresa.setText(proveedor.getEmpresa());
        txtRFC.setText(proveedor.getRfc());
        txtTelefono.setText(proveedor.getTelefono());
    }
    
    public void setEtiquetaProveedor(){
        lblProveedores.setText("Actualizar proveedor");
    }
    
}
