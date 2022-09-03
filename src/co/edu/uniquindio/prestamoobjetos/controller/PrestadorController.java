package co.edu.uniquindio.prestamoobjetos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.prestamoobjetos.Aplicacion;
import co.edu.uniquindio.prestamoobjetos.exceptions.CantidadExcedidaException;
import co.edu.uniquindio.prestamoobjetos.exceptions.FechasException;
import co.edu.uniquindio.prestamoobjetos.exceptions.NoExisteException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoNoRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoYaRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.PrestamoYaEntregadoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.YaExisteException;
import co.edu.uniquindio.prestamoobjetos.model.Objeto;
import co.edu.uniquindio.prestamoobjetos.model.Prestamo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class PrestadorController {
	Aplicacion aplicacion;

	ObservableList<Objeto> listaObjetosData = FXCollections.observableArrayList();
	Objeto objetoSeleccionado;

	ObservableList<Prestamo> listaPrestamosData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    private TableColumn<Objeto, String> columnCodigoObjeto;

    @FXML
    private TableColumn<Objeto, String> columnEstadoObjeto;

    @FXML
    private Button btnEntregarPrestamo;

    @FXML
    private Button btnSalirLogin;

    @FXML
    private TableColumn<Objeto, String> columnNombreObjeto;

    @FXML
    private TableColumn<Objeto, Double> columnValorUnitario;

    @FXML
    private Button btnAgregarObjeto;

    @FXML
    private TextField txtCodigoObjeto;

    @FXML
    private DatePicker datePrestamo;

    @FXML
    private TextField txtUnidadesPrestadas;

    @FXML
    private TextField txtCodigoPrestamo;

    @FXML
    private Button btnAgregarUnidades;

    @FXML
    private TextField txtNumeroIdentificacionCliente;

    @FXML
    private DatePicker dateEntrega;

    @FXML
    private Button btnSolicitarPrestamo;

    @FXML
    private TableColumn<Objeto, Integer> columnUnidadesDisponibles;

    @FXML
    private TableView<Objeto> tableObjetos;

    @FXML
    private TableColumn<Objeto, Double> columnPrecioAlquiler;

    @FXML
    void solicitarPrestamoAction(ActionEvent event) {
    	solicitarPrestamo();
    }

	@FXML
    void agregarUnidadesAction(ActionEvent event) {
		agregarUnidades();
    }

	@FXML
    void agregarObjetoAction(ActionEvent event) {
    	agregarObjeto();
    }

	@FXML
    void entregarPrestamoAction(ActionEvent event) {
    	entregarPrestamo();
    }

	@FXML
    void salirLoginAction(ActionEvent event) {
    	mostrarLogin();
    }

    @FXML
    void initialize() {
    	this.columnNombreObjeto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCodigoObjeto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columnUnidadesDisponibles.setCellValueFactory(new PropertyValueFactory<>("unidadesDisponibles"));
		this.columnEstadoObjeto.setCellValueFactory(new PropertyValueFactory<>("estado"));
		this.columnPrecioAlquiler.setCellValueFactory(new PropertyValueFactory<>("precioAlquiler"));
		this.columnValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));

		tableObjetos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
			objetoSeleccionado = newSelection;
			ponerCodigoObjeto(objetoSeleccionado);
		});
    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

		tableObjetos.getItems().clear();
		tableObjetos.setItems(getListaObjetosData());
    }

	public ObservableList<Objeto> getListaObjetosData() {
		listaObjetosData.addAll(aplicacion.obtenerObjetos());
		return listaObjetosData;
	}

	public ObservableList<Prestamo> getListaPrestamosData() {
		listaPrestamosData.addAll(aplicacion.obtenerPrestamos());
		return listaPrestamosData;
	}

	/**
	 * Este metodo pone el codigo del objeto que se seleccione de la tabla
	 * @param objetoSeleccionado2
	 */
    private void ponerCodigoObjeto(Objeto objetoSeleccionado2) {
    	if(objetoSeleccionado != null){
    		txtCodigoObjeto.setText(objetoSeleccionado.getCodigo());
    	}
	}

    /**
     * Metodo para solicitar un prestamo
     */
    private void solicitarPrestamo() {
    	try{
    		// 1. Capturar datos de la interfaz
        	String codigo = txtCodigoPrestamo.getText();
        	String fechaPrestamo = "";
        	String fechaEntrega = "";
        	String estado = "Pendiente";
        	String codigoCliente = txtNumeroIdentificacionCliente.getText();
        	String nombreEmpleado = txtNombreEmpleado.getText();
        	String codigoEmpleado = aplicacion.obtenerCodigoEmpleado(nombreEmpleado);
        	String codigoObjeto = txtCodigoObjeto.getText();
        	String unidadesPrestadas = txtUnidadesPrestadas.getText();

    		if(datePrestamo.getValue()!=null){
    			fechaPrestamo = datePrestamo.getValue().toString();
    		}

        	// 2. Validar informacion capturada
        	if(datosValidosPrestamo(codigo, fechaPrestamo, fechaEntrega, codigoCliente, nombreEmpleado, codigoObjeto, unidadesPrestadas) == true){
        		// 3. Registrar el prestamo
        		Prestamo prestamo = null;
        		prestamo = aplicacion.crearPrestamo(codigo, fechaPrestamo, fechaEntrega, estado, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas);

        		if(prestamo != null){
        			listaPrestamosData.add(prestamo);
        			tableObjetos.refresh();
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
     * Metodo para gregar unidades al prestamo
     */
    private void agregarUnidades() {
    	try{
	    	// 1. Capturar datos de la interfaz
	    	String codigo = txtCodigoPrestamo.getText();
	    	String fechaPrestamo = "";
	    	String fechaEntrega = "";
	    	String codigoCliente = txtNumeroIdentificacionCliente.getText();
	    	String nombreEmpleado = txtNombreEmpleado.getText();
	    	String codigoEmpleado = aplicacion.obtenerCodigoEmpleado(nombreEmpleado);
	    	String codigoObjeto = txtCodigoObjeto.getText();
	    	String unidadesAgregar = txtUnidadesPrestadas.getText();
	    	Prestamo prestamo = null;

	    	if(datePrestamo.getValue()!=null){
				fechaPrestamo = datePrestamo.getValue().toString();
			}
			if(dateEntrega.getValue()!=null){
				fechaEntrega = dateEntrega.getValue().toString();
			}

			boolean flagUnidadesAgregadas = false;
			if(datosValidosPrestamo(codigo, fechaPrestamo, fechaEntrega, codigoCliente, codigoEmpleado, codigoObjeto, unidadesAgregar) == true){
				prestamo = aplicacion.obtenerPrestamoCodigo(codigo);
				if(prestamo!=null){
					flagUnidadesAgregadas = aplicacion.agregarUnidadesPrestamo(prestamo, codigoObjeto, unidadesAgregar);
					if(flagUnidadesAgregadas == true){
						tableObjetos.refresh();
						mostrarMensaje("Notificación prestamo", "Unidades agregadas", "Las unidades han sido agregadas con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación prestamo", "Unidades no agregadas", "Las unidades no han sido agregadas", AlertType.ERROR);
					}
				}else{
					mostrarMensaje("Notificación prestamo", "Unidades no agregadas", "El prestamo no existe", AlertType.ERROR);
					}
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
     * Metodo para agregar un objeto al prestamo
     */
    private void agregarObjeto() {
    	try{
	    	// 1. Capturar datos de la interfaz
	    	String codigo = txtCodigoPrestamo.getText();
	    	String fechaPrestamo = "";
	    	String fechaEntrega = "";
	    	String codigoCliente = txtNumeroIdentificacionCliente.getText();
	    	String nombreEmpleado = txtNombreEmpleado.getText();
	    	String codigoEmpleado = aplicacion.obtenerCodigoEmpleado(nombreEmpleado);
	    	String codigoObjeto = txtCodigoObjeto.getText();
	    	String unidadesPrestadas = txtUnidadesPrestadas.getText();
	    	Prestamo prestamo = null;

	    	if(datePrestamo.getValue()!=null){
				fechaPrestamo = datePrestamo.getValue().toString();
			}
			if(dateEntrega.getValue()!=null){
				fechaEntrega = dateEntrega.getValue().toString();
			}

			boolean flagUnidadesAgregadas = false;
			if(datosValidosPrestamo(codigo, fechaPrestamo, fechaEntrega, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas) == true){
				prestamo = aplicacion.obtenerPrestamoCodigo(codigo);
				if(prestamo!=null){
					flagUnidadesAgregadas = aplicacion.agregarObjetoPrestamo(prestamo, codigoObjeto, unidadesPrestadas);

					if(flagUnidadesAgregadas == true){
						tableObjetos.refresh();
						mostrarMensaje("Notificación prestamo", "Objetos agregados", "Los objetos han sido agregados con exito", AlertType.INFORMATION);
					}else{
						mostrarMensaje("Notificación prestamo", "Objetos no agregados", "Los objetos no han sido agregados", AlertType.ERROR);
					}
				}else{
					mostrarMensaje("Notificación prestamo", "Objetos no agregados", "El prestamo no existe", AlertType.ERROR);
				}
			}

	    }catch(FechasException e1){
			mostrarMensaje("Notificación prestamo", "Unidades no agregadas", e1.getMessage(), AlertType.ERROR);
		}catch(ObjetoYaRegistradoException e1){
			mostrarMensaje("Notificación prestamo", "Unidades no agregadas", e1.getMessage(), AlertType.ERROR);
		}
	}

    /**
     * Metodo para entregar un prestamo
     */
    private void entregarPrestamo() {
    	try{
    		// 1. Capturar datos de la interfaz
        	String codigo = txtCodigoPrestamo.getText();
        	String fechaPrestamo = "";
        	String fechaEntrega = "";
        	String codigoCliente = txtNumeroIdentificacionCliente.getText();
        	String nombreEmpleado = txtNombreEmpleado.getText();
        	String codigoEmpleado = aplicacion.obtenerCodigoEmpleado(nombreEmpleado);
        	String codigoObjeto = txtCodigoObjeto.getText();
        	String unidadesPrestadas = txtUnidadesPrestadas.getText();
        	Prestamo prestamo = null;

        	if(datePrestamo.getValue()!=null){
    			fechaPrestamo = datePrestamo.getValue().toString();
    		}
    		if(dateEntrega.getValue()!=null){
    			fechaEntrega = dateEntrega.getValue().toString();
    		}

    		boolean flagPrestamoEntregado = false;

    		if(datosValidosPrestamoEntregado(codigo, fechaPrestamo, fechaEntrega, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas) == true){
    			prestamo = aplicacion.obtenerPrestamoCodigo(codigo);
    			if(prestamo!=null){
    				flagPrestamoEntregado = aplicacion.entregarPrestamo(prestamo, fechaEntrega);
    				if(flagPrestamoEntregado == true){
    					tableObjetos.refresh();
    					mostrarMensaje("Notificación prestamo", "Prestamo entregado", "El prestamo ha sido entregado con exito", AlertType.INFORMATION);
    				}else{
    					mostrarMensaje("Notificación prestamo", "Prestamo no entregado", "El prestamo no ha sido entregado", AlertType.ERROR);
    				}
    			}
    		}
    	}catch(FechasException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no entregado", e1.getMessage(), AlertType.ERROR);
		}catch(PrestamoYaEntregadoException e1){
			mostrarMensaje("Notificación prestamo", "Prestamo no entregado", e1.getMessage(), AlertType.ERROR);
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
			 String codigoCliente, String nombreEmpleado, String codigoObjeto, String unidadesPrestadas) {
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
		if(nombreEmpleado == null || nombreEmpleado.equals("")){
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
	 * Metodo para validar los datos ingresados al mometno de entregar un prestamo
	 * @param codigo
	 * @param fechaPrestamo
	 * @param fechaEntrega
	 * @param codigoCliente
	 * @param codigoEmpleado
	 * @param codigoObjeto
	 * @param unidadesPrestadas
	 * @return
	 */
	private boolean datosValidosPrestamoEntregado(String codigo, String fechaPrestamo, String fechaEntrega,
			 String codigoCliente, String codigoEmpleado, String codigoObjeto, String unidadesPrestadas) {
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
			mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, AlertType.WARNING);
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
	 * metodo para volver al login
	 */
	private void mostrarLogin() {
		aplicacion.salirLogin();
	}

}
