/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Controller.VistaAgregarProductosController;
import DAO.ProductosDAO;
import Model.ProductoDTO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private ProductoDTO productoSeleccionado = null;
    private ProductosDAO p;
    private ObservableList<ProductoDTO> listaProductos;

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
        
        p = new ProductosDAO();
        listaProductos = p.getProductos();
        tblProductos.setItems(listaProductos);
        
    }    
    
    @FXML
    private void buscarProdcuto(ActionEvent event) {
    }

    @FXML
    private void actualizarProducto(ActionEvent event) throws IOException {
        Stage ventanaAgregarProducto = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane)loader.load(getClass().getResource("/View/VistaAgregarProductos.fxml").openStream());
        //creo instancia del controlador de la ventana VistaAgregarProducto
        VistaAgregarProductosController controladorProducto = (VistaAgregarProductosController)loader.getController();
        
        
        
        
        if(productoSeleccionado!= null){
            
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Actualizar producto seleccionado?", ButtonType.YES, ButtonType.NO);

              // clicking X also means no
              ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

              if (!ButtonType.NO.equals(result)) {
                  //invoco metodos publicos del controlador instanciado
                  controladorProducto.Actualizar();
                  controladorProducto.setEtiquetaProducto();
                  controladorProducto.setProducto(productoSeleccionado);
                  
                  Scene scene = new Scene(root);
                  ventanaAgregarProducto.setScene(scene);
                  ventanaAgregarProducto.alwaysOnTopProperty();
                  ventanaAgregarProducto.initModality(Modality.APPLICATION_MODAL);
                  ventanaAgregarProducto.show();
                  
                   ((Stage) (btnActualizarProducto.getScene().getWindow())).close();
                 
                }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Debes seleccionar producto");
            alert.show();
        }
        
    }

     @FXML
    private void eliminarProducto(ActionEvent event) {
        ProductosDAO p = new ProductosDAO();
        if(productoSeleccionado!= null){
            
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar producto seleccionado?", ButtonType.YES, ButtonType.NO);

              // clicking X also means no
              ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

              if (!ButtonType.NO.equals(result)) {
                  p.eliminarProducto(productoSeleccionado);
                  listaProductos.remove(productoSeleccionado);
                  tblProductos.refresh();
                }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Debes seleccionar producto");
            alert.show();
        }
    }

    @FXML
    private void agregarProducto(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaAgregarProductos.fxml");
    }
    
    @FXML
    private void regresarMenu(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaMenu.fxml");
    }
    
    @FXML
    private void seleccionarProducto(MouseEvent event) {
        productoSeleccionado = this.tblProductos.getSelectionModel().getSelectedItem();
        
        
        System.out.println(productoSeleccionado);
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
            
            ((Stage) (btnNuevoProducto.getScene().getWindow())).close();
    }

   

    

  

}
