/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import DAO.ProductosDAO;
import Model.ProductoDTO;
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
public class VistaProductosController implements Initializable {

    
    @FXML
    private TableView<ProductoDTO> tblProductos;
    @FXML
    private TableColumn colClave;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colDescripcion;
    @FXML
    private TableColumn colCategoria;
    @FXML
    private TableColumn colFechaAlta;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colPrecioUnitario;
    @FXML
    private TableColumn colPrecioVenta;
    @FXML
    private TextField txtBuscarProducto;
    @FXML
    private Button btnBuscarProducto;
    @FXML
    private Button btnActualizarProducto;
    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Button btnNuevoProducto;
    @FXML
    private Button btnMenu;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.colClave.setCellValueFactory(new PropertyValueFactory("clave"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        this.colCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));
        this.colFechaAlta.setCellValueFactory(new PropertyValueFactory("fechaAlta"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colPrecioUnitario.setCellValueFactory(new PropertyValueFactory("precioUnitario"));
        this.colPrecioVenta.setCellValueFactory(new PropertyValueFactory("precioVenta"));
        
        ProductosDAO p = new ProductosDAO();
        tblProductos.setItems(p.getProductos());
        
    }    
    
    @FXML
    private void buscarProdcuto(ActionEvent event) {
    }

    @FXML
    private void actualizarProducto(ActionEvent event) {
    }

    @FXML
    private void eliminarProdcuto(ActionEvent event) {
    }

    @FXML
    private void agregarProducto(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaAgregarProductos.fxml");
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
            primaryStage.show();
            
            ((Stage) (btnNuevoProducto.getScene().getWindow())).close();
    }

}
