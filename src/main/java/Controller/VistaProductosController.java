package controller;

import Controller.VistaAgregarProductosController;
import DAO.impl.ProductosDAOImpl;
import Fachada.FachadaProductoDAO;
import Model.ProductoDTO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 * @author Ingrid Casales
 */
public class VistaProductosController extends ProductosDAOImpl implements Initializable  {

    
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
    //private ProductosDAO p;
    private FachadaProductoDAO f;
    private ObservableList<ProductoDTO> listaProductos;
    FilteredList<ProductoDTO> produtosFiltrados;

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
        
        //p = new ProductosDAO();
        f = new FachadaProductoDAO();
        //listaProductos = p.getProductos();
        listaProductos = f.obtenerProductos();
        tblProductos.setItems(listaProductos);
        
        
        FilteredList<ProductoDTO> filteredData = new FilteredList<>(listaProductos, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		 txtBuscarProducto.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productoSeleccionado -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (productoSeleccionado.getClave().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (productoSeleccionado.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (productoSeleccionado.getCategoria().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<ProductoDTO> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tblProductos.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tblProductos.setItems(sortedData);
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
        if(productoSeleccionado!= null){
            
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar producto seleccionado?", ButtonType.YES, ButtonType.NO);

              // clicking X also means no
              ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

              if (!ButtonType.NO.equals(result)) {
                  eliminarProducto(productoSeleccionado);
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
