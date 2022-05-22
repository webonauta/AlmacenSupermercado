package controller;

import Controller.VistaAgregarProductosController;
import Controller.VistaAgregarProveedoresController;
import DAO.impl.ProveedoresDAOImpl;
import Model.ProveedorDTO;
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
public class VistaProveedoresController extends ProveedoresDAOImpl implements Initializable {

  
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
    @FXML
    private TextField txtBuscar;
    
    
    private ProveedorDTO proveedorSeleccionado = null;
    private ObservableList<ProveedorDTO> listaProveedores;

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
        
        listaProveedores = getProveedores();
        tblProveedores.setItems(listaProveedores);
        
        FilteredList<ProveedorDTO> filteredData = new FilteredList<>(listaProveedores, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		 txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productoSeleccionado -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (productoSeleccionado.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (productoSeleccionado.getRfc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (productoSeleccionado.getTelefono().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<ProveedorDTO> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tblProveedores.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tblProveedores.setItems(sortedData);
    }    
    
    
    //realizando
    @FXML
    private void actualizarProveedor(ActionEvent event) throws IOException {
        Stage ventanaAgregarProveedor = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane)loader.load(getClass().getResource("/View/VistaAgregarProveedores.fxml").openStream());
        //creo instancia del controlador de la ventana VistaAgregarProveedor
        VistaAgregarProveedoresController controladorProveedor = (VistaAgregarProveedoresController)loader.getController();
        
        if(proveedorSeleccionado != null){
            
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Actualizar proveedor seleccionado?", ButtonType.YES, ButtonType.NO);

              // clicking X also means no
              ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

              if (!ButtonType.NO.equals(result)) {
                  //invoco metodos publicos del controlador instanciado
                  controladorProveedor.Actualizar();
                  controladorProveedor.setEtiquetaProveedor();
                  controladorProveedor.setProveedor(proveedorSeleccionado);
                  
                  Scene scene = new Scene(root);
                  ventanaAgregarProveedor.setScene(scene);
                  ventanaAgregarProveedor.alwaysOnTopProperty();
                  ventanaAgregarProveedor.initModality(Modality.APPLICATION_MODAL);
                  ventanaAgregarProveedor.show();
                  
                   ((Stage) (btnAgregarProveedor.getScene().getWindow())).close();
                 
                }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Debes seleccionar proveedor");
            alert.show();
        }
    
    
    }

    @FXML
    private void eliminarProveedor(ActionEvent event) {
        
        
        if(proveedorSeleccionado != null){
            
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar proveedor seleccionado?", ButtonType.YES, ButtonType.NO);

              // clicking X also means no
              ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

              if (!ButtonType.NO.equals(result)) {
                  eliminarProveedor(proveedorSeleccionado);
                  listaProveedores.remove(proveedorSeleccionado);
                  tblProveedores.refresh();
                }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Debes seleccionar proveedor");
            alert.show();
        }
    }

    @FXML
    private void agregarProveedor(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaAgregarProveedores.fxml");
    }

    @FXML
    private void regresarMenu(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaMenu.fxml");
    }
    
    
     @FXML
    private void seleccionarProveedor(MouseEvent event) {
        proveedorSeleccionado = this.tblProveedores.getSelectionModel().getSelectedItem();
        System.out.println(proveedorSeleccionado);
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
