/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import DAO.ProductosDAO;
import Model.ProductoDTO;
import controller.Main;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaAgregarProductosController implements Initializable {
    @FXML
    private Button btnAgregarProductos;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtClave;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDescripcion;
   
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtPrecioUnitario;
    @FXML
    private TextField txtPrecioVenta;
    @FXML
    private ComboBox<String> cmbCategoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(ActionEvent event) throws IOException {
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
            
            ((Stage) (btnAgregarProductos.getScene().getWindow())).close();
    }

    @FXML
    private void agregarProducto(ActionEvent event) {
       ProductosDAO p =  new ProductosDAO();
       String date = txtDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
       ProductoDTO producto = new ProductoDTO(txtClave.getText(),txtNombre.getText(),txtDescripcion.getText(),
        cmbCategoria.getValue(),date,Integer.parseInt(txtCantidad.getText()),Float.valueOf(txtPrecioUnitario.getText()),Float.valueOf(txtPrecioVenta.getText()));
       
       p.insertarProducto(producto);
    }

    @FXML
    private void llenarComBoxCategoria(MouseEvent event) {
        ProductosDAO p = new ProductosDAO();
        ObservableList<String> lista;
        lista = p.getCategorias();
        Collections.sort(lista);//ordeno la lista las categorias alfabeticamente
        cmbCategoria.setItems(lista);
    }
}
