package co.edu.uniquindio.prestamoobjetos.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.prestamoobjetos.Aplicacion;
import co.edu.uniquindio.prestamoobjetos.exceptions.CantidadExcedidaException;
import co.edu.uniquindio.prestamoobjetos.exceptions.DatosInvalidosException;
import co.edu.uniquindio.prestamoobjetos.exceptions.DatosNumericosException;
import co.edu.uniquindio.prestamoobjetos.exceptions.FechasException;
import co.edu.uniquindio.prestamoobjetos.exceptions.NoEliminadoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.NoExisteException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoNoRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoYaRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.PrestamoYaEntregadoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.YaExisteException;
import co.edu.uniquindio.prestamoobjetos.model.Cliente;
import co.edu.uniquindio.prestamoobjetos.model.DetallePrestamo;
import co.edu.uniquindio.prestamoobjetos.model.Empleado;
import co.edu.uniquindio.prestamoobjetos.model.Objeto;
import co.edu.uniquindio.prestamoobjetos.model.Prestamo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class EmpresaPrestamoObjetosController {

	Aplicacion aplicacion;
	ObservableList<Cliente> listaClientesData = FXCollections.observableArrayList();
	Cliente clienteSeleccionado;

	ObservableList<Empleado> listaEmpleadosData = FXCollections.observableArrayList();
	Empleado empleadoSeleccionado;

	ObservableList<Objeto> listaObjetosData = FXCollections.observableArrayList();
	Objeto objetoSeleccionado;

	ObservableList<Prestamo> listaPrestamosData = FXCollections.observableArrayList();
	Prestamo prestamoSeleccionado;

	ObservableList<Objeto> listaObjetosPrestamoData = FXCollections.observableArrayList();
	Objeto objetoSeleccionadoPrestamo;

	DetallePrestamo detallesPrestamoSeleccionado;

	FilteredList<Cliente> filteredClienteData;
	FilteredList<Empleado> filteredEmpleadoData;
	FilteredList<Objeto> filteredObjetoData;
	FilteredList<Prestamo> filteredPrestamoData;

    @FXML
    private TextField txtPaisEmpleado;

    @FXML
    private TextField filterFieldCliente;

    @FXML
    private TextField filterFieldEmpleado;

    @FXML
    private TextField filterFieldObjeto;

    @FXML
    private TextField filterFieldPrestamo;

    @FXML
    private TableColumn<Empleado, String> columnDireccionEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnEmailEmpleado;

    @FXML
    private TextField txtNumeroDocumentoEmpleado;

    @FXML
    private SplitMenuButton optTipoDocumentoEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnPaisEmpleado;

    @FXML
    private TextField txtDepartamentoEmpleado;

    @FXML
    private TextField txtTipoEmpleado;

    @FXML
    private Button btnActualizarEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnDepartamentoEmpleado;

    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    private Button btnNuevoEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnTelefonoResidenciaEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnTelefonoCelularEmpleado;

    @FXML
    private TextField txtTelefonoResidenciaEmpleado;

    @FXML
    private TextField txtCiudadEmpleado;

    @FXML
    private TextField txtDireccionEmpleado;

    @FXML
    private Button btnEliminarEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnTipoDocumentoEmpleado;

    @FXML
    private TextField txtTelefonoCelularEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnDocumentoEmpleado;

    @FXML
    private TableView<Empleado> tableEmpleados;

    @FXML
    private Button btnAgregarEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnCiudadEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnTipoEmpleado;


    @FXML
    private TableColumn<Empleado, String> columnNombreEmpleado;

    @FXML
    private TextField txtEmailEmpleado;

    @FXML
    void nuevoEmpleadoAction(ActionEvent event) {
    	nuevoEmpleado();
    }

    @FXML
    void agregarEmpleadoAction(ActionEvent event) {
    	agregarEmpleado();
    }

    @FXML
    void actualizarEmpleadoAction(ActionEvent event) {
    	actualizarEmpleado();
    }

    @FXML
    void eliminarEmpleadoAction(ActionEvent event) {
    	eliminarEmpleado();
    }

    @FXML
    void seleccionarCedulaEmpleado(ActionEvent event) {
		optTipoDocumentoEmpleado.setText("Cedula");
    }

	@FXML
    void seleccionarPasaporteEmpleado(ActionEvent event) {
		optTipoDocumentoEmpleado.setText("Pasaporte");
    }

	@FXML
    void seleccionarCedulaExtranjeriaEmpleado(ActionEvent event) {
		optTipoDocumentoEmpleado.setText("CedulaExtranjeria");
    }

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitMenuButton optTipoDocumentoCliente;

    @FXML
    private TextField txtNumeroDocumentoCliente;

    @FXML
    private TextField txtDireccionCliente;

    @FXML
    private Button btnNuevoCliente;

    @FXML
    private TableColumn<Cliente, String> columnDocumentoCliente;

    @FXML
    private TableColumn<Cliente, String> columnTipoDocumentoCliente;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private TableColumn<Cliente, String> columnNombreCliente;

    @FXML
    private TableColumn<Cliente, String> columnTelefonoResidenciaCliente;

    @FXML
    private TableColumn<Cliente, String> columnTelefonoCelularCliente;

    @FXML
    private TableColumn<Cliente, String> columnEmailCliente;

    @FXML
    private TextField txtPaisCliente;

    @FXML
    private Button btnAgregarCliente;

    @FXML
    private TableColumn<Cliente, String> columnDireccionCliente;

    @FXML
    private TableView<Cliente> tableClientes;

    @FXML
    private TableColumn<Cliente, String> columnCiudadCliente;

    @FXML
    private TableColumn<Cliente, String> columnDepartamentoCliente;

    @FXML
    private TableColumn<Cliente, String> columnPaisCliente;

    @FXML
    private TableColumn<Cliente, String> columnProfesionCliente;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private TextField txtTelefonoResidenciaCliente;

    @FXML
    private TextField txtProfesionCliente;

    @FXML
    private TextField txtTelefonoCelularCliente;

    @FXML
    private TextField txtCiudadCliente;

    @FXML
    private TextField txtDepartamentoCliente;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    void nuevoClienteAction(ActionEvent event) {
    	nuevoCliente();
    }

	@FXML
    void agregarClienteAction(ActionEvent event) {
		agregarCliente();
    }

	@FXML
    void actualizarClienteAction(ActionEvent event) {
		actualizarCliente();
    }

	@FXML
    void eliminarClienteAction(ActionEvent event) {
    	eliminarCliente();
    }

	@FXML
    void seleccionarCedulaCliente(ActionEvent event) {
		optTipoDocumentoCliente.setText("Cedula");
    }

	@FXML
    void seleccionarPasaporteCliente(ActionEvent event) {
		optTipoDocumentoCliente.setText("Pasaporte");
    }

	@FXML
    void seleccionarCedulaExtranjeriaCliente(ActionEvent event) {
		optTipoDocumentoCliente.setText("CedulaExtranjeria");
    }


	 @FXML
    private TextField txtTipoObjeto;

    @FXML
    private TextField txtNombreObjeto;

    @FXML
    private TableColumn<Objeto, String> columnNombreObjeto;

    @FXML
    private TextField txtValorUnitarioObjeto;

    @FXML
    private TableColumn<Objeto, Double> columnPesoObjeto;

    @FXML
    private TableColumn<Objeto, Double> columnValorTotalObjeto;

    @FXML
    private Button btnActualizarObjeto;

    @FXML
    private Button btnAgregarObjeto;

    @FXML
    private Button btnSeleccionarImagenObjeto;

    @FXML
    private TextField txtCodigoObjeto;

    @FXML
    private TableColumn<Objeto, String> columnDescripcionObjeto;

    @FXML
    private Button btnNuevoObjeto;

    @FXML
    private TextArea txtDescripcionObjeto;

    @FXML
    private TableColumn<Objeto, Double> columnPrecioAlquilerObjeto;

    @FXML
    private TableColumn<Objeto, Integer> columnUnidadesDisponiblesObjeto;

    @FXML
    private TextField txtPesoObjeto;

    @FXML
    private TextField txtPrecioAlquilerObjeto;

    @FXML
    private TableView<Objeto> tableObjetos;

    @FXML
    private TableColumn<Objeto, String> columnTipoObjeto;

    @FXML
    private Button btnEliminarObjeto;

    @FXML
    private TextField txtUnidadesDisponiblesObjeto;

    @FXML
    private TableColumn<Objeto, String> columnCodigoObjeto;

    @FXML
    private TableColumn<Objeto, String> columnEstadoObjeto;

    @FXML
    private TableColumn<Objeto, Double> columnValorUnitarioObjeto;

    @FXML
    private ImageView ivImagenObjeto;

    @FXML
    void actualizarObjetoAction(ActionEvent event) {
    	actualizarObjeto();
    }

    @FXML
    void agregarObjetoAction(ActionEvent event) {
    	agregarObjeto();
    }

    @FXML
    void nuevoObjetoAction(ActionEvent event) {
    	nuevoObjeto();
    }

    @FXML
    void eliminarObjetoAction(ActionEvent event) {
    	eliminarObjeto();
    }

    @FXML
    void seleccionarImagenAction(ActionEvent event) {
    	seleccionarImagen();
    }

    @FXML
    private TextField txtEmpleado;

    @FXML
    private TextField txtCliente;

    @FXML
    private Button btnEliminarPrestamo;

    @FXML
    private TableColumn<Prestamo, Double> columnValorPrestamo;

    @FXML
    private Button btnActualizarPrestamo;

    @FXML
    private DatePicker dateEntrega;

    @FXML
    private Button btnAgregarPrestamo;

    @FXML
    private TextField txtCodigoPrestamo;

    @FXML
    private TextField txtObjeto;

    @FXML
    private TextField txtUnidadesPrestadas;

    @FXML
    private TableColumn<Prestamo, String> columnCodigoPrestamo;

    @FXML
    private TableColumn<Prestamo, String> columnFechaEntrega;

    @FXML
    private TableView<Prestamo> tablePrestamos;

    @FXML
    private SplitMenuButton optEstadoPrestamo;

    @FXML
    private DatePicker datePrestamo;

    @FXML
    private Button btnNuevoPrestamo;

    @FXML
    private Button btnAgregarUnidadesPrestadas;

    @FXML
    private Button btnAgregarObjetoPrestamo;

    @FXML
    private Button btnEntregarPrestamo;

    @FXML
    private TableColumn<Prestamo, String> columnEmpleado;

    @FXML
    private TableColumn<Prestamo, String> columnEstadoPrestamo;

    @FXML
    private TableColumn<Prestamo, String> columnCliente;

    @FXML
    private TableColumn<Prestamo, String> columnObjeto;

    @FXML
    private TableColumn<Prestamo, String> columnFechaPrestamo;

    @FXML
    private Button btnSalir;

    @FXML
    void salirLoginAction(ActionEvent event) {
    	mostrarLogin();
    }

	@FXML
    void nuevoPrestamoAction(ActionEvent event) {
    	nuevoPrestamo();
    }

    @FXML
    void agregarPrestamoAction(ActionEvent event) {
    	agregarPrestamo();
    }

    @FXML
    void actualizarPrestamoAction(ActionEvent event) {
    	actualizarPrestamo();
    }

    @FXML
    void eliminarPrestamoAction(ActionEvent event) {
    	eliminarPrestamo();
    }

    @FXML
    void agregarUnidadesAction(ActionEvent event) {
    	agregarUnidades();
    }

    @FXML
    void agregarObjetoPrestamoAction(ActionEvent event) {
    	agregarObjetoPrestamo();
    }

    @FXML
    void entregarPrestamoAction(ActionEvent event) {
    	entregarPrestamo();
    }

    @FXML
    void initialize() {
		this.columnNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnDocumentoCliente.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
		this.columnCiudadCliente.setCellValueFactory(new PropertyValueFactory<>("ciudadResidencia"));
		this.columnTelefonoResidenciaCliente.setCellValueFactory(new PropertyValueFactory<>("telefonoResidencia"));
		this.columnEmailCliente.setCellValueFactory(new PropertyValueFactory<>("email"));
		this.columnDepartamentoCliente.setCellValueFactory(new PropertyValueFactory<>("departamento"));
		this.columnPaisCliente.setCellValueFactory(new PropertyValueFactory<>("pais"));
		this.columnTipoDocumentoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
		this.columnTelefonoCelularCliente.setCellValueFactory(new PropertyValueFactory<>("telefonoCelular"));
		this.columnDireccionCliente.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		this.columnProfesionCliente.setCellValueFactory(new PropertyValueFactory<>("profesion"));

		tableClientes.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
			clienteSeleccionado = newSelection;
			mostrarInformacionCliente(clienteSeleccionado);
		});

		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
    	filteredClienteData = new FilteredList<>(listaClientesData, p -> true);


    	// 2. Set the filter Predicate whenever the filter changes.
    	filterFieldCliente.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredClienteData.setPredicate(cliente-> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (cliente.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (cliente.getNumeroDocumento().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				} else if (cliente.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

		this.columnNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnDocumentoEmpleado.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
		this.columnCiudadEmpleado.setCellValueFactory(new PropertyValueFactory<>("ciudadResidencia"));
		this.columnTelefonoResidenciaEmpleado.setCellValueFactory(new PropertyValueFactory<>("telefonoResidencia"));
		this.columnEmailEmpleado.setCellValueFactory(new PropertyValueFactory<>("email"));
		this.columnDepartamentoEmpleado.setCellValueFactory(new PropertyValueFactory<>("departamento"));
		this.columnPaisEmpleado.setCellValueFactory(new PropertyValueFactory<>("pais"));
		this.columnTipoDocumentoEmpleado.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
		this.columnTelefonoCelularEmpleado.setCellValueFactory(new PropertyValueFactory<>("telefonoCelular"));
		this.columnDireccionEmpleado.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		this.columnTipoEmpleado.setCellValueFactory(new PropertyValueFactory<>("tipoEmpleado"));

		tableEmpleados.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
			empleadoSeleccionado = newSelection;
			mostrarInformacionEmpleado(empleadoSeleccionado);
		});

		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
    	filteredEmpleadoData = new FilteredList<>(listaEmpleadosData, p -> true);


    	// 2. Set the filter Predicate whenever the filter changes.
    	filterFieldEmpleado.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredEmpleadoData.setPredicate(empleado-> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (empleado.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (empleado.getNumeroDocumento().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				} else if (empleado.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

		this.columnNombreObjeto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCodigoObjeto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columnUnidadesDisponiblesObjeto.setCellValueFactory(new PropertyValueFactory<>("unidadesDisponibles"));
		this.columnPesoObjeto.setCellValueFactory(new PropertyValueFactory<>("peso"));
		this.columnEstadoObjeto.setCellValueFactory(new PropertyValueFactory<>("estado"));
		this.columnPrecioAlquilerObjeto.setCellValueFactory(new PropertyValueFactory<>("precioAlquiler"));
		this.columnValorUnitarioObjeto.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		this.columnValorTotalObjeto.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		this.columnTipoObjeto.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		this.columnDescripcionObjeto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

		tableObjetos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
			objetoSeleccionado = newSelection;
			mostrarInformacionObjeto(objetoSeleccionado);
		});

		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
    	filteredObjetoData = new FilteredList<>(listaObjetosData, p -> true);


    	// 2. Set the filter Predicate whenever the filter changes.
    	filterFieldObjeto.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredObjetoData.setPredicate(objeto-> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (objeto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (objeto.getCodigo().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				} else if (objeto.getTipo().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

		this.columnCodigoPrestamo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columnEstadoPrestamo.setCellValueFactory(new PropertyValueFactory<>("estado"));
		this.columnValorPrestamo.setCellValueFactory(new PropertyValueFactory<>("valorPrestamo"));
		this.columnFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
		this.columnFechaEntrega.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
		this.columnEmpleado.setCellValueFactory(new PropertyValueFactory<>("empleado"));
		this.columnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		this.columnObjeto.setCellValueFactory(new PropertyValueFactory<>("listaDetallesPrestamo"));

		tablePrestamos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
			prestamoSeleccionado = newSelection;
			mostrarInformacionPrestamo(prestamoSeleccionado);
		});

		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
    	filteredPrestamoData = new FilteredList<>(listaPrestamosData, p -> true);


    	// 2. Set the filter Predicate whenever the filter changes.
    	filterFieldPrestamo.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredPrestamoData.setPredicate(prestamo-> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (prestamo.getCodigo().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});

    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		tableClientes.getItems().clear();
		tableClientes.setItems(getListaClientesData());
		tableEmpleados.getItems().clear();
		tableEmpleados.setItems(getListaEmpleadosData());
		tableObjetos.getItems().clear();
		tableObjetos.setItems(getListaObjetosData());
		tablePrestamos.getItems().clear();
		tablePrestamos.setItems(getListaPrestamosData());

		// 3. Wrap the FilteredList in a SortedList.
    	SortedList<Cliente> sortedClienteData = new SortedList<>(filteredClienteData);
    	SortedList<Empleado> sortedEmpleadoData = new SortedList<>(filteredEmpleadoData);
    	SortedList<Objeto> sortedObjetoData = new SortedList<>(filteredObjetoData);
    	SortedList<Prestamo> sortedPrestamoData = new SortedList<>(filteredPrestamoData);

    	// 4. Bind the SortedList comparator to the TableView comparator.
    	sortedClienteData.comparatorProperty().bind(tableClientes.comparatorProperty());
    	sortedEmpleadoData.comparatorProperty().bind(tableEmpleados.comparatorProperty());
    	sortedObjetoData.comparatorProperty().bind(tableObjetos.comparatorProperty());
    	sortedPrestamoData.comparatorProperty().bind(tablePrestamos.comparatorProperty());

    	// 5. Add sorted (and filtered) data to the table.
    	tableClientes.setItems(sortedClienteData);
    	tableEmpleados.setItems(sortedEmpleadoData);
    	tableObjetos.setItems(sortedObjetoData);
    	tablePrestamos.setItems(sortedPrestamoData);
	}

	public ObservableList<Cliente> getListaClientesData() {
		listaClientesData.addAll(aplicacion.obtenerClientes());
		return listaClientesData;
	}

	public ObservableList<Empleado> getListaEmpleadosData() {
		listaEmpleadosData.addAll(aplicacion.obtenerEmpleados());
		return listaEmpleadosData;
	}

	public ObservableList<Objeto> getListaObjetosData() {
		listaObjetosData.addAll(aplicacion.obtenerObjetos());
		return listaObjetosData;
	}

	public ObservableList<Prestamo> getListaPrestamosData() {
		listaPrestamosData.addAll(aplicacion.obtenerPrestamos());
		return listaPrestamosData;
	}

	public ObservableList<Objeto> getListaObjetosPrestamoData() {
		listaObjetosPrestamoData.addAll(aplicacion.obtenerObjetos());
		return listaObjetosPrestamoData;
	}

	/**
	 * Metodo para seleccionar la imagen del objeto
	 */
	private void seleccionarImagen() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(aplicacion.getPrimaryStage());

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            ivImagenObjeto.setImage(image);
        }
	}

	/**
	 * Metodo para mostrar la informacion del cliente seleccionado en los campos de texto
	 * @param clienteSeleccionado
	 */
    private void mostrarInformacionCliente(Cliente clienteSeleccionado) {
    	if(clienteSeleccionado != null){
    		txtNombreCliente.setText(clienteSeleccionado.getNombre());
    		optTipoDocumentoCliente.setText(clienteSeleccionado.getTipoDocumento().toString());
        	txtNumeroDocumentoCliente.setText(clienteSeleccionado.getNumeroDocumento());
        	txtDireccionCliente.setText(clienteSeleccionado.getDireccion());
        	txtCiudadCliente.setText(clienteSeleccionado.getCiudadResidencia());
        	txtDepartamentoCliente.setText(clienteSeleccionado.getDepartamento());
        	txtPaisCliente.setText(clienteSeleccionado.getPais());
        	txtProfesionCliente.setText(clienteSeleccionado.getProfesion());
        	txtEmailCliente.setText(clienteSeleccionado.getEmail());
        	txtTelefonoCelularCliente.setText(clienteSeleccionado.getTelefonoCelular());
        	txtTelefonoResidenciaCliente.setText(clienteSeleccionado.getTelefonoResidencia());
    	}
	}

    /**
     * Metodo para mostrar instrucciones al undir el boton de nuevo
     */
    private void nuevoCliente() {
    	limpiarCamposTextoCliente();
    	tableClientes.getSelectionModel().clearSelection();
	}

    /**
     * Metodo para agregar un cliente
     */
    private void agregarCliente() {
    	try{
			// 1. Capturar datos de la Stringerfaz
	    	String nombre = txtNombreCliente.getText();
	    	String tipoDocumento = optTipoDocumentoCliente.getText();
	    	String numeroDocumento = txtNumeroDocumentoCliente.getText();
	    	String direccion = txtDireccionCliente.getText();
	    	String ciudad = txtCiudadCliente.getText();
	    	String departamento = txtDepartamentoCliente.getText();
	    	String pais = txtPaisCliente.getText();
	    	String profesion = txtProfesionCliente.getText();
	    	String email = txtEmailCliente.getText();
	    	String telefonoCelular = txtTelefonoCelularCliente.getText();
	    	String telefonoResidencia = txtTelefonoResidenciaCliente.getText();

	    	// 2. Validar informacion capturada
	    	if(datosValidosCliente(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, profesion, email, telefonoCelular, telefonoResidencia) == true){
	    		// 3. Registrar el cliente
	    		Cliente cliente = null;
	    		cliente = aplicacion.crearCliente(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, profesion, email, telefonoCelular, telefonoResidencia);

	    		if(cliente != null){
	    			listaClientesData.add(cliente);
	    			limpiarCamposTextoCliente();
	    			mostrarMensaje("Notificación cliente", "Cliente registrado", "El cliente se ha registrado con exito", AlertType.INFORMATION);
	    		}else{
	    			mostrarMensaje("Notificación cliente", "Cliente no registrado", "El cliente no se ha registrado con exito", AlertType.ERROR);
	    		}
	    	}

    	}catch(YaExisteException e1){
    		mostrarMensaje("Notificación cliente", "Cliente no registrado", e1.getMessage(), AlertType.ERROR);
        }catch(DatosInvalidosException e1){
    		mostrarMensaje("Notificación cliente", "Cliente no registrado", e1.getMessage(), AlertType.ERROR);
        }
	}

    /**
     * Metodo para actualizar un cliente seleccionado
     */
    private void actualizarCliente() {
    	try{
    	String nombre = txtNombreCliente.getText();
    	String tipoDocumento = optTipoDocumentoCliente.getText();
    	String numeroDocumento = txtNumeroDocumentoCliente.getText();
    	String direccion = txtDireccionCliente.getText();
    	String ciudad = txtCiudadCliente.getText();
    	String departamento = txtDepartamentoCliente.getText();
    	String pais = txtPaisCliente.getText();
    	String profesion = txtProfesionCliente.getText();
    	String email = txtEmailCliente.getText();
    	String telefonoCelular = txtTelefonoCelularCliente.getText();
    	String telefonoResidencia = txtTelefonoResidenciaCliente.getText();

    	boolean flagClienteActualizado = false;
		if(clienteSeleccionado != null){

			if(datosValidosCliente(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, profesion, email, telefonoCelular, telefonoResidencia) == true){
				flagClienteActualizado = aplicacion.actualizarCliente(clienteSeleccionado.getNumeroDocumento(), nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, profesion, email, telefonoCelular, telefonoResidencia);

				if(flagClienteActualizado == true){
					tableClientes.refresh();
					mostrarMensaje("Notificación cliente", "Cliente actualizado", "El cliente ha sido actualizado con exito", AlertType.INFORMATION);
				}else{
					mostrarMensaje("Notificación cliente", "Cliente no actualizado", "El cliente no ha sido actualizado con exito", AlertType.ERROR);
				}
			}
		}else{
			mostrarMensaje("Notificación cliente", "Cliente no seleccionado", "No se ha seleccionado ningun cliente", AlertType.WARNING);
		}

	    }catch(NoExisteException e1){
			mostrarMensaje("Notificación cliente", "Cliente no actualizado", e1.getMessage(), AlertType.ERROR);
	    }catch(DatosInvalidosException e1){
    		mostrarMensaje("Notificación cliente", "Cliente no registrado", e1.getMessage(), AlertType.ERROR);
        }
	}

    /**
     * Metodo para eliminar un cliente seleccionado
     */
	private void eliminarCliente() {
		try{
			boolean flagClienteEliminado = false;
			if(clienteSeleccionado != null){
				if(mostrarMensajeConfirmacion("Estas seguro que quieres eliminar el cliente seleccionado?") == true){

					flagClienteEliminado = aplicacion.eliminarCliente(clienteSeleccionado.getNumeroDocumento());

					if(flagClienteEliminado == true){
						listaClientesData.remove(clienteSeleccionado);
						clienteSeleccionado = null;
						tableClientes.getSelectionModel().clearSelection();
						limpiarCamposTextoCliente();
						mostrarMensaje("Notificación cliente", "Cliente eliminado", "El cliente ha sido eliminado con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación cliente", "Cliente no eliminado", "El cliente no ha sido eliminado", AlertType.ERROR);
					}
				}else{
					mostrarMensaje("Notificación cliente", "Cliente no eliminado", "El cliente no ha sido eliminado", AlertType.INFORMATION);
				}
			}else{
				mostrarMensaje("Notificación cliente", "Cliente no seleccionado", "No se ha seleccionado ningun cliente", AlertType.WARNING);
			}
		}catch(NoEliminadoException e1){
			mostrarMensaje("Notificación cliente", "Cliente no eliminado", e1.getMessage(), AlertType.ERROR);
	    }
	}

    /**
     * Metodo para mostrar la imformacion del empleado seleccionado en los campos de texto
     * @param empleadoSeleccionado
     */
    private void mostrarInformacionEmpleado(Empleado empleadoSeleccionado) {
    	if(empleadoSeleccionado != null){
    		txtNombreEmpleado.setText(empleadoSeleccionado.getNombre());
    		optTipoDocumentoEmpleado.setText(empleadoSeleccionado.getTipoDocumento().toString());
        	txtNumeroDocumentoEmpleado.setText(empleadoSeleccionado.getNumeroDocumento());
        	txtDireccionEmpleado.setText(empleadoSeleccionado.getDireccion());
        	txtCiudadEmpleado.setText(empleadoSeleccionado.getCiudadResidencia());
        	txtDepartamentoEmpleado.setText(empleadoSeleccionado.getDepartamento());
        	txtPaisEmpleado.setText(empleadoSeleccionado.getPais());
        	txtTipoEmpleado.setText(empleadoSeleccionado.getTipoEmpleado());
        	txtEmailEmpleado.setText(empleadoSeleccionado.getEmail());
        	txtTelefonoCelularEmpleado.setText(empleadoSeleccionado.getTelefonoCelular());
        	txtTelefonoResidenciaEmpleado.setText(empleadoSeleccionado.getTelefonoResidencia());
    	}
	}

    /**
     * Metodo para mostrar instrucciones al undir el boton de nuevo
     */
    private void nuevoEmpleado() {
    	limpiarCamposTextoEmpleado();
    	tableEmpleados.getSelectionModel().clearSelection();
	}

    /**
     * Metodo para agregar un cliente
     */
    private void agregarEmpleado() {
		try{
    		// 1. Capturar datos de la Stringerfaz
	    	String nombre = txtNombreEmpleado.getText();
	    	String tipoDocumento = optTipoDocumentoEmpleado.getText();
	    	String numeroDocumento = txtNumeroDocumentoEmpleado.getText();
	    	String direccion = txtDireccionEmpleado.getText();
	    	String ciudad = txtCiudadEmpleado.getText();
	    	String departamento = txtDepartamentoEmpleado.getText();
	    	String pais = txtPaisEmpleado.getText();
	    	String tipoEmpleado = txtTipoEmpleado.getText();
	    	String email = txtEmailEmpleado.getText();
	    	String telefonoCelular = txtTelefonoCelularEmpleado.getText();
	    	String telefonoResidencia = txtTelefonoResidenciaEmpleado.getText();

	    	// 2. Validar informacion capturada
	    	if(datosValidosEmpleado(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, tipoEmpleado, email, telefonoCelular, telefonoResidencia) == true){
	    		// 3. Registrar el cliente
	    		Empleado cliente = null;
	    		cliente = aplicacion.crearEmpleado(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, tipoEmpleado, email, telefonoCelular, telefonoResidencia);

	    		if(cliente != null){
	    			listaEmpleadosData.add(cliente);
	    			limpiarCamposTextoEmpleado();
	    			mostrarMensaje("Notificación empleado", "Empleado registrado", "El empleado se ha registrado con exito", AlertType.INFORMATION);
	    		}else{
	    			mostrarMensaje("Notificación empleado", "Empleado no registrado", "El empleado no se ha registrado con exito", AlertType.ERROR);
	    		}
	    	}
	    }catch(YaExisteException e1){
			mostrarMensaje("Notificación empleado", "Empleado no registrado", e1.getMessage(), AlertType.ERROR);
	    }catch(DatosInvalidosException e1){
    		mostrarMensaje("Notificación cliente", "Cliente no registrado", e1.getMessage(), AlertType.ERROR);
        }
	}

	/**
     * Metodo para actualizar un empleado seleccionado
     */
    private void actualizarEmpleado() {
    	try{
	    	String nombre = txtNombreEmpleado.getText();
	    	String tipoDocumento = optTipoDocumentoEmpleado.getText();
	    	String numeroDocumento = txtNumeroDocumentoEmpleado.getText();
	    	String direccion = txtDireccionEmpleado.getText();
	    	String ciudad = txtCiudadEmpleado.getText();
	    	String departamento = txtDepartamentoEmpleado.getText();
	    	String pais = txtPaisEmpleado.getText();
	    	String tipoEmpleado = txtTipoEmpleado.getText();
	    	String email = txtEmailEmpleado.getText();
	    	String telefonoCelular = txtTelefonoCelularEmpleado.getText();
	    	String telefonoResidencia = txtTelefonoResidenciaEmpleado.getText();

	    	boolean flagEmpleadoActualizado = false;
			if(empleadoSeleccionado != null){

				if(datosValidosEmpleado(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, tipoEmpleado, email, telefonoCelular, telefonoResidencia) == true){
					flagEmpleadoActualizado = aplicacion.actualizarEmpleado(empleadoSeleccionado.getNumeroDocumento(), nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, tipoEmpleado, email, telefonoCelular, telefonoResidencia);

					if(flagEmpleadoActualizado == true){
						tableEmpleados.refresh();
						mostrarMensaje("Notificación empleado", "Empleado actualizado", "El empleado ha sido actualizado con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación empleado", "Empleado no actualizado", "El empleado no ha sido actualizado con exito", AlertType.ERROR);
					}
				}
			}else{
				mostrarMensaje("Notificación empleado", "Empleado no seleccionado", "No se ha seleccionado ningun empleado", AlertType.WARNING);
			}
	    }catch(NoExisteException e1){
			mostrarMensaje("Notificación empleado", "Empleado no actualizado", e1.getMessage(), AlertType.ERROR);
	    }catch(DatosInvalidosException e1){
    		mostrarMensaje("Notificación cliente", "Cliente no registrado", e1.getMessage(), AlertType.ERROR);
        }
	}

    /**
     * Metodo para eliminar un empleado seleccionado
     */
	private void eliminarEmpleado() {
		try{
			boolean flagEmpleadoEliminado = false;
			if(empleadoSeleccionado != null){
				if(mostrarMensajeConfirmacion("Estas seguro que quieres eliminar el empleado seleccionado?") == true){

					flagEmpleadoEliminado = aplicacion.eliminarEmpleado(empleadoSeleccionado.getNumeroDocumento());

					if(flagEmpleadoEliminado == true){
						listaEmpleadosData.remove(empleadoSeleccionado);
						empleadoSeleccionado = null;
						tableEmpleados.getSelectionModel().clearSelection();
						limpiarCamposTextoEmpleado();
						mostrarMensaje("Notificación empleado", "Empleado eliminado", "El empleado ha sido eliminado con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación empleado", "Empleado no eliminado", "El empleado no ha sido eliminado con exito", AlertType.ERROR);
					}
				}else{
					mostrarMensaje("Notificación empleado", "Empleado no eliminado", "El empleado no ha sido eliminado", AlertType.INFORMATION);
				}
			}else{
				mostrarMensaje("Notificación empleado", "Empleado no seleccionado", "No se ha seleccionado ningun empleado", AlertType.WARNING);
			}
		}catch(NoEliminadoException e1){
			mostrarMensaje("Notificación empleado", "Empleado no eliminado", e1.getMessage(), AlertType.ERROR);
	    }
	}

	/**
	 * Metodo para mostrar la informacion del objeto seleccionado en los campos de texto
	 * @param objetoSeleccionado
	 */
    private void mostrarInformacionObjeto(Objeto objetoSeleccionado) {
    	if(objetoSeleccionado != null){
    		txtNombreObjeto.setText(objetoSeleccionado.getNombre());
    		txtCodigoObjeto.setText(objetoSeleccionado.getCodigo());
    		txtUnidadesDisponiblesObjeto.setText(String.valueOf(objetoSeleccionado.getUnidadesDisponibles()));
    		txtDescripcionObjeto.setText(objetoSeleccionado.getDescripcion());
    		txtPesoObjeto.setText(String.valueOf(objetoSeleccionado.getPeso()));
    		txtTipoObjeto.setText(objetoSeleccionado.getTipo());
    		txtValorUnitarioObjeto.setText(String.valueOf(objetoSeleccionado.getValorUnitario()));
    		txtPrecioAlquilerObjeto.setText(String.valueOf(objetoSeleccionado.getPrecioAlquiler()));
            ivImagenObjeto.setImage(objetoSeleccionado.getFoto());
    	}
	}

    /**
     * Metodo para mostrar instrucciones al undir el boton de nuevo
     */
    private void nuevoObjeto() {
    	limpiarCamposTextoObjeto();
    	tableObjetos.getSelectionModel().clearSelection();
	}

    /**
     * Metodo para agregar un objeto
     */
    private void agregarObjeto() {
    	try{
    		// 1. Capturar datos de la Stringerfaz
        	String codigo = txtCodigoObjeto.getText();
        	String nombre = txtNombreObjeto.getText();
        	String unidadesDisponiblesString = txtUnidadesDisponiblesObjeto.getText();
        	String descripcion = txtDescripcionObjeto.getText();
        	String pesoString = txtPesoObjeto.getText();
        	String tipo = txtTipoObjeto.getText();
        	String valorUnitarioString = txtValorUnitarioObjeto.getText();
        	String precioAlquilerString = txtPrecioAlquilerObjeto.getText();
        	Image foto = ivImagenObjeto.getImage();

        	// 2. Validar informacion capturada
        	if(datosValidosObjeto(codigo, nombre, unidadesDisponiblesString, descripcion, pesoString, tipo, valorUnitarioString, precioAlquilerString) == true){
        		// 3. Registrar el objeto
        		Objeto objeto = null;
        		objeto = aplicacion.crearObjeto(codigo, nombre, unidadesDisponiblesString, descripcion, pesoString, tipo, valorUnitarioString, precioAlquilerString, foto);

        		if(objeto != null){
        			listaObjetosData.add(objeto);
        			limpiarCamposTextoObjeto();
        			mostrarMensaje("Notificación objeto", "Objeto registrado", "El objeto se ha registrado con exito", AlertType.INFORMATION);
        		}else{
        			mostrarMensaje("Notificación objeto", "Objeto no registrado", "El objeto no se ha registrado con exito", AlertType.ERROR);
        		}
        	}
	    }catch(NumberFormatException e1){
			mostrarMensaje("Notificación objeto", "Objeto no registrado", "Los datos numericos ingresados no son validos", AlertType.ERROR);
	    }catch(YaExisteException e1){
			mostrarMensaje("Notificación objeto", "Objeto no registrado", e1.getMessage(), AlertType.ERROR);
	    }catch(DatosInvalidosException e1){
    		mostrarMensaje("Notificación cliente", "Cliente no registrado", e1.getMessage(), AlertType.ERROR);
        }
	}

    /**
     * Metodo para actualizar un objeto seleccionado
     */
    private void actualizarObjeto() {
	    try{
	    	// 1. Capturar datos de la Stringerfaz
	    	String codigo = txtCodigoObjeto.getText();
	    	String nombre = txtNombreObjeto.getText();
	    	String unidadesDisponiblesString = txtUnidadesDisponiblesObjeto.getText();
	    	String descripcion = txtDescripcionObjeto.getText();
	    	String pesoString = txtPesoObjeto.getText();
	    	String tipo = txtTipoObjeto.getText();
	    	String valorUnitarioString = txtValorUnitarioObjeto.getText();
	    	String precioAlquilerString = txtPrecioAlquilerObjeto.getText();
	    	Image foto = ivImagenObjeto.getImage();

	    	boolean flagObjetoActualizado = false;
			if(objetoSeleccionado != null){

				if(datosValidosObjeto(codigo, nombre, unidadesDisponiblesString, descripcion, pesoString, tipo, valorUnitarioString, precioAlquilerString) == true){
					flagObjetoActualizado = aplicacion.actualizarObjeto(objetoSeleccionado.getCodigo(), codigo, nombre, unidadesDisponiblesString, descripcion, pesoString, tipo, valorUnitarioString, precioAlquilerString, foto);

					if(flagObjetoActualizado == true){
						tableObjetos.refresh();
						mostrarMensaje("Notificación objeto", "Objeto actualizado", "El objeto ha sido actualizado con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación objeto", "Objeto no actualizado", "El objeto no ha sido actualizado con exito", AlertType.ERROR);
					}
				}
			}else{
				mostrarMensaje("Notificación objeto", "Objeto no seleccionado", "No se ha seleccionado ningun objeto", AlertType.WARNING);
			}
	    }catch(NumberFormatException e1){
			mostrarMensaje("Notificación objeto", "Objeto no actualizado", "Los datos numericos ingresados no son validos", AlertType.ERROR);
	    }catch(NoExisteException e1){
			mostrarMensaje("Notificación objeto", "Objeto no actualizado", e1.getMessage(), AlertType.ERROR);
	    }catch(DatosInvalidosException e1){
    		mostrarMensaje("Notificación cliente", "Cliente no registrado", e1.getMessage(), AlertType.ERROR);
        }
	}

    /**
     * Metodo para eliminar un objeto seleccionado
     */
	private void eliminarObjeto() {
		try{
			boolean flagObjetoEliminado = false;
			if(objetoSeleccionado != null){
				if(mostrarMensajeConfirmacion("Estas seguro que quieres eliminar el objeto seleccionado?") == true){

					flagObjetoEliminado = aplicacion.eliminarObjeto(objetoSeleccionado.getCodigo());

					if(flagObjetoEliminado == true){
						listaObjetosData.remove(objetoSeleccionado);
						objetoSeleccionado = null;
						tableObjetos.getSelectionModel().clearSelection();
						limpiarCamposTextoObjeto();
						mostrarMensaje("Notificación objeto", "Objeto eliminado", "El objeto ha sido eliminado con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación objeto", "Objeto no eliminado", "El objeto no ha sido eliminado con exito", AlertType.ERROR);
					}
				}else{
					mostrarMensaje("Notificación objeto", "Objeto no eliminado", "El objeto no ha sido eliminado", AlertType.INFORMATION);
				}
			}else{
				mostrarMensaje("Notificación objeto", "Objeto no seleccionado", "No se ha seleccionado ningun objeto", AlertType.WARNING);
			}
	    }catch(NoEliminadoException e1){
			mostrarMensaje("Notificación objeto", "Objeto no eliminado", e1.getMessage(), AlertType.ERROR);
	    }
	}


	/**
	 * Metodo para mostrar la informacion del prestamo seleccionado en los campos de texto
	 * @param prestamoSeleccionado
	 */
	private void mostrarInformacionPrestamo(Prestamo prestamoSeleccionado) {
    	if(prestamoSeleccionado != null){
    		txtCodigoPrestamo.setText(prestamoSeleccionado.getCodigo());
    		datePrestamo.setValue(LocalDate.parse(prestamoSeleccionado.getFechaPrestamo(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    		if(prestamoSeleccionado.getFechaEntrega().isEmpty()){
    			dateEntrega.setValue(null);
    		}else{
    			dateEntrega.setValue(LocalDate.parse(prestamoSeleccionado.getFechaEntrega(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    		}
    		txtEmpleado.setText(prestamoSeleccionado.getEmpleado().getNumeroDocumento());
    		txtCliente.setText(prestamoSeleccionado.getCliente().getNumeroDocumento());
    		txtObjeto.setText(aplicacion.obtenerNombresObjetos(prestamoSeleccionado));
    		txtUnidadesPrestadas.setText(aplicacion.obtenerUnidadesPrestadasObjetos(prestamoSeleccionado));

    	}
	}

	 /**
     * Metodo para mostrar instrucciones al undir el boton de nuevo
     */
    private void nuevoPrestamo() {
    	limpiarCamposTextoPrestamo();
    	tablePrestamos.getSelectionModel().clearSelection();
	}

    /**
     * Metodo para agregar un prestamo
     * @throws FechasException
     * @throws CantidadExcedidaException
     * @throws NoExisteException
     */
    private void agregarPrestamo(){
    	try{
    		// 1. Capturar datos de la interfaz
        	String codigo = txtCodigoPrestamo.getText();
        	String fechaPrestamo = "";
        	String fechaEntrega = "";
        	String estado = "";
        	String codigoCliente = txtCliente.getText();
        	String codigoEmpleado = txtEmpleado.getText();
        	String codigoObjeto = txtObjeto.getText();
        	String unidadesPrestadas = txtUnidadesPrestadas.getText();

    		if(datePrestamo.getValue()!=null){
    			fechaPrestamo = datePrestamo.getValue().toString();
    		}
    		if(dateEntrega.getValue()!=null){
    			fechaEntrega = dateEntrega.getValue().toString();
    			estado = "Entregado";
    		}else{
    			estado = "Pendiente";
    		}

        	// 2. Validar informacion capturada
        	if(datosValidosPrestamo(codigo, fechaPrestamo, fechaEntrega, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas) == true){
        		// 3. Registrar el prestamo
        		Prestamo prestamo = null;
        		prestamo = aplicacion.crearPrestamo(codigo, fechaPrestamo, fechaEntrega, estado, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas);

        		if(prestamo != null){
        			listaPrestamosData.add(prestamo);
        			tableObjetos.refresh();
        			limpiarCamposTextoPrestamo();
        			mostrarMensaje("Notificación prestamo", "Prestamo registrado", "El prestamo se ha registrado con exito", AlertType.INFORMATION);
        		}else{
        			mostrarMensaje("Notificación prestamo", "Prestamo no registrado", "El prestamo no se ha registrado con exito", AlertType.ERROR);
        		}
        	}
    	}catch(NumberFormatException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no registrado", "Los datos numericos ingresados no son validos", AlertType.ERROR);
		}catch(YaExisteException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no registrado", e1.getMessage(), AlertType.ERROR);
		}catch(FechasException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no registrado", e1.getMessage(), AlertType.ERROR);
		}catch(NoExisteException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no registrado", e1.getMessage(), AlertType.ERROR);
		}catch(CantidadExcedidaException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no registrado", e1.getMessage(), AlertType.ERROR);
		}
	}

    /**
     * Metodo para actualizar un prestamo
     */
    private void actualizarPrestamo() {
    	try{
    		// 1. Capturar datos de la interfaz
        	String codigo = txtCodigoPrestamo.getText();
        	String fechaPrestamo = "";
        	String fechaEntrega = "";
        	String estado = "";
        	String codigoCliente = txtCliente.getText();
        	String codigoEmpleado = txtEmpleado.getText();
        	String codigoObjeto = txtObjeto.getText();
        	String unidadesPrestadas = txtUnidadesPrestadas.getText();

    		if(datePrestamo.getValue()!=null){
    			fechaPrestamo = datePrestamo.getValue().toString();
    		}
    		if(dateEntrega.getValue()!=null){
    			if(dateEntrega.getValue().toString().equals("")){
    				estado = "Pendiente";
    			}else{
    				fechaEntrega = dateEntrega.getValue().toString();
    				estado = "Entregado";
    			}
    		}else{
    			estado = "Pendiente";
    		}

    		boolean flagPrestamoActualizado = false;
    		if(prestamoSeleccionado != null){

    			if(datosValidosPrestamo(codigo, fechaPrestamo, fechaEntrega, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas) == true){
    				flagPrestamoActualizado = aplicacion.actualizarPrestamo(prestamoSeleccionado.getCodigo(), codigo, fechaPrestamo, fechaEntrega, estado, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas);

    				if(flagPrestamoActualizado == true){
    					tablePrestamos.refresh();
    					limpiarCamposTextoPrestamo();
    					tableObjetos.refresh();
    					mostrarMensaje("Notificación prestamo", "Prestamo actualizado", "El prestamo ha sido actualizado con exito", AlertType.INFORMATION);
    				}else{
    					mostrarMensaje("Notificación prestamo", "Prestamo no actualizado", "El prestamo no ha sido actualizado con exito", AlertType.ERROR);
    				}
    			}
    		}else{
    			mostrarMensaje("Notificación prestamo", "Prestamo no seleccionado", "No se ha seleccionado ningun prestamo", AlertType.WARNING);
    		}
    	}catch(NumberFormatException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no actualizado", "Los datos numericos ingresados no son validos", AlertType.ERROR);
		}catch(FechasException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no actualizado", e1.getMessage(), AlertType.ERROR);
		}catch(NoExisteException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no actualizado", e1.getMessage(), AlertType.ERROR);
		}catch(CantidadExcedidaException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no actualizado", e1.getMessage(), AlertType.ERROR);
		}catch(YaExisteException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no actualizado", e1.getMessage(), AlertType.ERROR);
		}catch(NoEliminadoException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no actualizado", e1.getMessage(), AlertType.ERROR);
		}catch(ObjetoYaRegistradoException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no actualizado", e1.getMessage(), AlertType.ERROR);
		}
	}

    /**
     * Metodo para eliminar un prestamo seleccionado
     */
	private void eliminarPrestamo() {
		try{
			boolean flagPrestamoEliminado = false;
			if(prestamoSeleccionado != null){
				if(mostrarMensajeConfirmacion("Estas seguro que quieres eliminar el prestamo seleccionado?") == true){

					flagPrestamoEliminado = aplicacion.eliminarPrestamo(prestamoSeleccionado.getCodigo());

					if(flagPrestamoEliminado == true){
						listaPrestamosData.remove(prestamoSeleccionado);
						prestamoSeleccionado = null;
						tablePrestamos.getSelectionModel().clearSelection();
						limpiarCamposTextoPrestamo();
						mostrarMensaje("Notificación prestamo", "Prestamo eliminado", "El prestamo ha sido eliminado con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación prestamo", "Prestamo no eliminado", "El prestamo no ha sido eliminado con exito", AlertType.ERROR);
					}
				}else{
					mostrarMensaje("Notificación prestamo", "Prestamo no eliminado", "El prestamo no ha sido eliminado", AlertType.INFORMATION);
				}
			}else{
				mostrarMensaje("Notificación prestamo", "Prestamo no seleccionado", "No se ha seleccionado ningun prestamo", AlertType.WARNING);
			}
		}catch(NoEliminadoException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no eliminado", e1.getMessage(), AlertType.ERROR);
	    }
	}

	private void agregarUnidades(){
		try{
			// 1. Capturar datos de la interfaz
	    	String codigo = txtCodigoPrestamo.getText();
	    	String fechaPrestamo = "";
	    	String fechaEntrega = "";
	    	String codigoCliente = txtCliente.getText();
	    	String codigoEmpleado = txtEmpleado.getText();
	    	String codigoObjeto = txtObjeto.getText();
	    	String unidadesAgregar = txtUnidadesPrestadas.getText();

	    	if(datePrestamo.getValue()!=null){
				fechaPrestamo = datePrestamo.getValue().toString();
			}
			if(dateEntrega.getValue()!=null){
				fechaEntrega = dateEntrega.getValue().toString();
			}

			boolean flagUnidadesAgregadas = false;
			if(prestamoSeleccionado != null){

				if(datosValidosPrestamo(codigo, fechaPrestamo, fechaEntrega, codigoCliente, codigoEmpleado, codigoObjeto, unidadesAgregar) == true){
					flagUnidadesAgregadas = aplicacion.agregarUnidadesPrestamo(prestamoSeleccionado, codigoObjeto, unidadesAgregar);

					if(flagUnidadesAgregadas == true){
						tablePrestamos.refresh();
						tableObjetos.refresh();
						mostrarInformacionPrestamo(prestamoSeleccionado);
						//tablePrestamos.getSelectionModel().clearSelection();
						mostrarMensaje("Notificación prestamo", "Unidades agregadas", "Las unidades han sido agregadas con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación prestamo", "Unidades no agregadas", "Las unidades no han sido agregadas", AlertType.ERROR);
					}
				}
			}else{
				mostrarMensaje("Notificación prestamo", "Prestamo no seleccionado", "No se ha seleccionado ningun prestamo", AlertType.WARNING);
			}
		}catch(NumberFormatException e1){
			mostrarMensaje("Notificación prestamo", "Unidades no agregadas", "Los datos numericos ingresados no son validos", AlertType.ERROR);
		}catch(CantidadExcedidaException e1){
			mostrarMensaje("Notificación prestamo", "Unidades no agregadas", e1.getMessage(), AlertType.ERROR);
		}catch(ObjetoNoRegistradoException e1){
			mostrarMensaje("Notificación prestamo", "Unidades no agregadas", e1.getMessage(), AlertType.ERROR);
		}catch(FechasException e1){
			mostrarMensaje("Notificación prestamo", "Unidades no agregadas", e1.getMessage(), AlertType.ERROR);
		}
	}

	/**
	 * Metodo para gregar un objeto a un prestamo selecionado
	 */
	private void agregarObjetoPrestamo(){
		try{
			// 1. Capturar datos de la interfaz
	    	String codigo = txtCodigoPrestamo.getText();
	    	String fechaPrestamo = "";
	    	String fechaEntrega = "";
	    	String codigoCliente = txtCliente.getText();
	    	String codigoEmpleado = txtEmpleado.getText();
	    	String codigoObjeto = txtObjeto.getText();
	    	String unidadesPrestadas = txtUnidadesPrestadas.getText();

	    	if(datePrestamo.getValue()!=null){
				fechaPrestamo = datePrestamo.getValue().toString();
			}
			if(dateEntrega.getValue()!=null){
				fechaEntrega = dateEntrega.getValue().toString();
			}

			boolean flagUnidadesAgregadas = false;
			if(prestamoSeleccionado != null){

				if(datosValidosPrestamo(codigo, fechaPrestamo, fechaEntrega, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas) == true){
					flagUnidadesAgregadas = aplicacion.agregarObjetoPrestamo(prestamoSeleccionado, codigoObjeto, unidadesPrestadas);

					if(flagUnidadesAgregadas == true){
						tablePrestamos.refresh();
						tableObjetos.refresh();
						mostrarInformacionPrestamo(prestamoSeleccionado);
						//tablePrestamos.getSelectionModel().clearSelection();
						mostrarMensaje("Notificación prestamo", "Objetos agregados", "Los objetos han sido agregados con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación prestamo", "Objetos no agregados", "Los objetos no han sido agregados", AlertType.ERROR);
					}
				}
			}else{
				mostrarMensaje("Notificación prestamo", "Prestamo no seleccionado", "No se ha seleccionado ningun prestamo", AlertType.WARNING);
			}
		}catch(FechasException e1){
			mostrarMensaje("Notificación prestamo", "Objeto no agregado", e1.getMessage(), AlertType.ERROR);
		}catch(NumberFormatException e1){
			mostrarMensaje("Notificación prestamo", "Objeto no agregado", "Los datos numericos ingresados no son validos", AlertType.ERROR);
		}catch(ObjetoYaRegistradoException e1){
			mostrarMensaje("Notificación prestamo", "Objeto no agregado", e1.getMessage(), AlertType.ERROR);
		}
	}

	/**
	 * Metodo para entregar un prestamo
	 */
	private void entregarPrestamo(){
		try{
			// 1. Capturar datos de la interfaz
	    	String codigo = txtCodigoPrestamo.getText();
	    	String fechaPrestamo = "";
	    	String fechaEntrega = "";
	    	String codigoCliente = txtCliente.getText();
	    	String codigoEmpleado = txtEmpleado.getText();
	    	String codigoObjeto = txtObjeto.getText();
	    	String unidadesPrestadas = txtUnidadesPrestadas.getText();

	    	if(datePrestamo.getValue()!=null){
				fechaPrestamo = datePrestamo.getValue().toString();
			}
			if(dateEntrega.getValue()!=null){
				fechaEntrega = dateEntrega.getValue().toString();
			}

			boolean flagPrestamoEntregado = false;
			if(prestamoSeleccionado != null){

				if(datosValidosPrestamoEntregado(codigo, fechaPrestamo, fechaEntrega, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas) == true){
					flagPrestamoEntregado = aplicacion.entregarPrestamo(prestamoSeleccionado, fechaEntrega);

					if(flagPrestamoEntregado == true){
						tablePrestamos.refresh();
						tableObjetos.refresh();
						mostrarInformacionPrestamo(prestamoSeleccionado);
						//tablePrestamos.getSelectionModel().clearSelection();
						mostrarMensaje("Notificación prestamo", "Prestamo entregado", "El prestamo ha sido entregado con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación prestamo", "Prestamo no entregado", "El prestamo no ha sido entregado", AlertType.ERROR);
					}
				}
			}else{
				mostrarMensaje("Notificación prestamo", "Prestamo no seleccionado", "No se ha seleccionado ningun prestamo", AlertType.WARNING);
			}
		}catch(FechasException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no entregado", e1.getMessage(), AlertType.ERROR);
		}catch(PrestamoYaEntregadoException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no entregado", e1.getMessage(), AlertType.ERROR);
		}catch(DatosInvalidosException e1){
    		mostrarMensaje("Notificación cliente", "Cliente no registrado", e1.getMessage(), AlertType.ERROR);
        }
	}

	/**
	 *	Metodo para mostrar el mensaje de confimacion al undir un boton como eliminar (se puede mandar un parametro String como mensaje)
	 * @param mensaje
	 * @return
	 */
	private boolean mostrarMensajeConfirmacion(String mensaje) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmacion");
		alert.setContentText(mensaje);
		Optional <ButtonType> action = alert.showAndWait();

		if(action.get() == ButtonType.OK){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Metodo para mostrar un mensaje de tipo AlertType
	 * @param titulo
	 * @param header
	 * @param contenido
	 * @param alertType
	 */
	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	/**
	 * Metodo para verificar si los datos enviados son validos
	 * @param nombre
	 * @param numeroDocumento
	 * @param direccion
	 * @return
	 * @throws DatosInvalidosException
	 */
	private boolean datosValidosCliente(String nombre, String tipoDocumentoString, String numeroDocumento, String direccion, String ciudad, String departamento, String pais, String profesion,
			String email, String telefonoCelular, String telefonoResidencia) throws DatosInvalidosException {
		String mensaje = "";

		if(nombre == null || nombre.equals("")){
			mensaje += "El nombre es invalido. \n";
		}
		if(tipoDocumentoString == "Elija una opcion"){
			mensaje += "El tipo de documento es invalido. \n";
		}
		if(numeroDocumento == null || numeroDocumento.equals("")){
			mensaje += "El numero de documento es invalido. \n";
		}
		if(direccion == null || direccion.equals("")){
			mensaje += "La direccion es invalida \n";
		}
		if(departamento == null || departamento.equals("")){
			mensaje += "El departamento es invalido \n";
		}
		if(pais == null || pais.equals("")){
			mensaje += "El pais es invalido \n";
		}
		if(profesion == null || profesion.equals("")){
			mensaje += "La profesion es invalida \n";
		}
		if(email == null || email.equals("")){
			mensaje += "El email es invalido \n";
		}
		if(telefonoCelular == null || telefonoCelular.equals("")){
			mensaje += "El telefono celular es invalido \n";
		}
		if(telefonoResidencia == null || telefonoResidencia.equals("")){
			mensaje += "El telefono de residencia es invalido \n";
		}

		if(mensaje.equals("")){
			return true;
		}else{
			throw new DatosInvalidosException(mensaje);
		}
	}

	/**
	 * Metodo para verificar si los datos enviados son validos
	 * @param nombre
	 * @param numeroDocumento
	 * @param direccion
	 * @return
	 * @throws DatosInvalidosException
	 */
	private boolean datosValidosEmpleado(String nombre, String tipoDocumentoString, String numeroDocumento, String direccion, String ciudad, String departamento, String pais, String tipoEmpleado,
			String email, String telefonoCelular, String telefonoResidencia) throws DatosInvalidosException {
		String mensaje = "";

		if(nombre == null || nombre.equals("")){
			mensaje += "El nombre es invalido. \n";
		}
		if(tipoDocumentoString == "Elija una opcion"){
			mensaje += "El tipo de documento es invalido. \n";
		}
		if(numeroDocumento == null || numeroDocumento.equals("")){
			mensaje += "El numero de documento es invalido. \n";
		}
		if(direccion == null || direccion.equals("")){
			mensaje += "La direccion es invalida \n";
		}
		if(departamento == null || departamento.equals("")){
			mensaje += "El departamento es invalido \n";
		}
		if(pais == null || pais.equals("")){
			mensaje += "El pais es invalido \n";
		}
		if(tipoEmpleado == null || tipoEmpleado.equals("")){
			mensaje += "La profesion es invalida \n";
		}
		if(email == null || email.equals("")){
			mensaje += "El email es invalido \n";
		}
		if(telefonoCelular == null || telefonoCelular.equals("")){
			mensaje += "El telefono celular es invalido \n";
		}
		if(telefonoResidencia == null || telefonoResidencia.equals("")){
			mensaje += "El telefono de residencia es invalido \n";
		}

		if(mensaje.equals("")){
			return true;
		}else{
			throw new DatosInvalidosException(mensaje);
		}
	}

	/**
	 * Metodo para verificar si los datos enviados son validos
	 * @param precioAlquiler
	 * @param valorTotal
	 * @param valorUnitario
	 * @param tipo
	 * @param estado
	 * @param peso
	 * @param descripcion
	 * @param unidadesDisponibles
	 * @param nombre
	 * @param codigo
	 * @param nombre
	 * @param numeroDocumento
	 * @param direccion
	 * @return
	 * @throws DatosInvalidosException
	 */
	private boolean datosValidosObjeto(String codigo, String nombre, String unidadesDisponibles, String descripcion, String peso, String tipo, String valorUnitario, String precioAlquiler) throws DatosInvalidosException {
		String mensaje = "";

		if(codigo == null || codigo.equals("")){
			mensaje += "El codigo es invalido. \n";
		}
		if(nombre == null || nombre.equals("")){
			mensaje += "El nombre es invalido. \n";
		}
		if(unidadesDisponibles == null || unidadesDisponibles.equals("")){
			mensaje += "Las unidades disponibles no son validas. \n";
		}
		if(descripcion == null || descripcion.equals("")){
			mensaje += "La descripcion es invalida. \n";
		}
		if(peso == null || peso.equals("")){
			mensaje += "El peso es invalido. \n";
		}
		if(tipo == null || tipo.equals("")){
			mensaje += "El tipo es invalido. \n";
		}
		if(valorUnitario == null || valorUnitario.equals("")){
			mensaje += "El valorUnitario es invalido. \n";
		}
		if(precioAlquiler == null || precioAlquiler.equals("")){
			mensaje += "El precioAlquiler es invalido. \n";
		}

		if(mensaje.equals("")){
			return true;
		}else{
			throw new DatosInvalidosException(mensaje);
		}
	}

	/**
	 * Metodo mpara verificar los datos de un prestamo ingresao
	 * @param codigo
	 * @param valorPrestamo
	 * @param fechaPrestamo
	 * @param fechaEntrega
	 * @param estado
	 * @param codigoCliente
	 * @param codigoEmpleado
	 * @param codigoObjeto
	 * @param unidadesPrestadas
	 * @return
	 */
	private boolean datosValidosPrestamo(String codigo, String fechaPrestamo, String fechaEntrega,
			 String codigoCliente, String codigoEmpleado, String codigoObjeto, String unidadesPrestadas) {
		String mensaje = "";

		if(codigo == null || codigo.equals("")){
			mensaje += "El codigo es invalido. \n";
		}

		if(fechaPrestamo == null || fechaPrestamo.equals("")){
			mensaje += "La fecha de prestamo es invalida. \n";
		}
		if(codigo == null || codigo.equals("")){
			mensaje += "El codigo es invalido. \n";
		}
		if(codigoCliente == null || codigoCliente.equals("")){
			mensaje += "El codigo de cliente es invalido. \n";
		}
		if(codigoEmpleado == null || codigoEmpleado.equals("")){
			mensaje += "El codigo de empleado es invalido. \n";
		}
		if(codigoObjeto == null || codigoObjeto.equals("")){
			mensaje += "El codigo de objeto es invalido. \n";
		}
		if(unidadesPrestadas == null || unidadesPrestadas.equals("")){
			mensaje += "Las unidades prestadas son invalidas. \n";
		}

		if(mensaje.equals("")){
			return true;
		}else{
			mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, AlertType.WARNING);
			return false;
		}
	}

	/**
	 * metodo para validar datos de prestamo entregao
	 * @param codigo
	 * @param fechaPrestamo
	 * @param fechaEntrega
	 * @param codigoCliente
	 * @param codigoEmpleado
	 * @param codigoObjeto
	 * @param unidadesPrestadas
	 * @return
	 * @throws DatosInvalidosException
	 */
	private boolean datosValidosPrestamoEntregado(String codigo, String fechaPrestamo, String fechaEntrega,
			 String codigoCliente, String codigoEmpleado, String codigoObjeto, String unidadesPrestadas) throws DatosInvalidosException {
		String mensaje = "";

		if(codigo == null || codigo.equals("")){
			mensaje += "El codigo es invalido. \n";
		}

		if(fechaPrestamo == null || fechaPrestamo.equals("")){
			mensaje += "La fecha de prestamo es invalida. \n";
		}

		if(fechaEntrega == null || fechaEntrega.equals("")){
			mensaje += "La fecha de entrega es invalida. \n";
		}
		if(codigo == null || codigo.equals("")){
			mensaje += "El codigo es invalido. \n";
		}
		if(codigoCliente == null || codigoCliente.equals("")){
			mensaje += "El codigo de cliente es invalido. \n";
		}
		if(codigoEmpleado == null || codigoEmpleado.equals("")){
			mensaje += "El codigo de empleado es invalido. \n";
		}
		if(codigoObjeto == null || codigoObjeto.equals("")){
			mensaje += "El codigo de objeto es invalido. \n";
		}
		if(unidadesPrestadas == null || unidadesPrestadas.equals("")){
			mensaje += "Las unidades prestadas son invalidas. \n";
		}

		if(mensaje.equals("")){
			return true;
		}else{
			throw new DatosInvalidosException(mensaje);
		}
	}


	/**
	 * Metodo para limpiar los campos de texto de cliente y dejarlos en blanco
	 */
	private void limpiarCamposTextoCliente() {
		txtNombreCliente.setText("");
    	optTipoDocumentoCliente.setText("Elija una opcion");
    	txtNumeroDocumentoCliente.setText("");
    	txtDireccionCliente.setText("");
    	txtCiudadCliente.setText("");
    	txtDepartamentoCliente.setText("");
    	txtPaisCliente.setText("");
    	txtProfesionCliente.setText("");
    	txtEmailCliente.setText("");
    	txtTelefonoCelularCliente.setText("");
    	txtTelefonoResidenciaCliente.setText("");
	}

	/**
	 * Metodo para limpiar los campos de texto de empleado y dejarlos en blanco
	 */
	private void limpiarCamposTextoEmpleado() {
		txtNombreEmpleado.setText("");
    	optTipoDocumentoEmpleado.setText("Elija una opcion");
    	txtNumeroDocumentoEmpleado.setText("");
    	txtDireccionEmpleado.setText("");
    	txtCiudadEmpleado.setText("");
    	txtDepartamentoEmpleado.setText("");
    	txtPaisEmpleado.setText("");
    	txtTipoEmpleado.setText("");
    	txtEmailEmpleado.setText("");
    	txtTelefonoCelularEmpleado.setText("");
    	txtTelefonoResidenciaEmpleado.setText("");
	}

	/**
	 * Metodo para limpiar los campos de texto de objeto y dejarlos en blanco
	 */
	private void limpiarCamposTextoObjeto() {
		txtNombreObjeto.setText("");
		txtCodigoObjeto.setText("");
		txtUnidadesDisponiblesObjeto.setText("");
		txtDescripcionObjeto.setText("");
		txtPesoObjeto.setText("");
		txtTipoObjeto.setText("");
		txtValorUnitarioObjeto.setText("");
		txtPrecioAlquilerObjeto.setText("");
		Image imagenDefecto = new Image(getClass().getResourceAsStream("../model/simboloimagen.jpg"));
		ivImagenObjeto.setImage(imagenDefecto);
	}

	/**
	 * Metodo para limpiar los campos de texto de prestamos y dejarlos en blanco
	 */
	private void limpiarCamposTextoPrestamo() {
		txtCodigoPrestamo.setText("");
		datePrestamo.setValue(null);
		dateEntrega.setValue(null);
		txtEmpleado.setText("");
		txtCliente.setText("");
		txtObjeto.setText("");
		txtUnidadesPrestadas.setText("");
	}

	/**
	 * metodo para volver al login
	 */
	private void mostrarLogin() {
		aplicacion.salirLogin();
	}
}

