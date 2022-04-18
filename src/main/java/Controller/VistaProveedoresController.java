/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import DAO.ProveedoresDAO;
import Model.ProveedorDTO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaProveedoresController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscarProveedor;
    @FXML
    private Button btnActualizarProveedor;
    @FXML
    private Button btnEliminarProveedor;
   
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnAgregarProveedor;
    @FXML
    private TableView<ProveedorDTO> tblProveedores;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colPaterno;
    @FXML
    private TableColumn colMaterno;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private TableColumn colEmpresa;
    @FXML
    private TableColumn colRFC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPaterno.setCellValueFactory(new PropertyValueFactory("paterno"));
        this.colMaterno.setCellValueFactory(new PropertyValueFactory("materno"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        this.colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.colEmpresa.setCellValueFactory(new PropertyValueFactory("empresa"));
        this.colRFC.setCellValueFactory(new PropertyValueFactory("rfc"));
        
        ProveedoresDAO p = new ProveedoresDAO();
        tblProveedores.setItems(p.getProveedores());
    }    
    
    @FXML
    private void buscarProveedor(ActionEvent event) {
    }

    @FXML
    private void actualizarProveedor(ActionEvent event) {
    }

    @FXML
    private void eliminarProveedor(ActionEvent event) {
    }

    @FXML
    private void agregarProveedor(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaAgregarProveedores.fxml");
    }

    @FXML
    private void regresarMenu(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaMenu.fxml");
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
    
}
