/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import DAO.ClientesDAO;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaAgregarClientesController implements Initializable {

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
            primaryStage.show();
            
            ((Stage) (btnAgregarCliente.getScene().getWindow())).close();
    }

    @FXML
    private void agregarCliente(ActionEvent event) {
        ClientesDAO c = new ClientesDAO();
        ClienteDTO cliente = new ClienteDTO(txtNombre.getText(),txtPaterno.getText(), txtMaterno.getText(), txtTelefono.getText(), txtDireccion.getText());
        c.insertarCliente(cliente);
    }
    
}
