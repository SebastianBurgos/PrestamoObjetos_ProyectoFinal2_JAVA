package co.edu.uniquindio.prestamoobjetos;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import co.edu.uniquindio.prestamoobjetos.controller.EmpresaPrestamoObjetosController;
import co.edu.uniquindio.prestamoobjetos.controller.InventarioController;
import co.edu.uniquindio.prestamoobjetos.controller.LoginController;
import co.edu.uniquindio.prestamoobjetos.controller.PrestadorController;
import co.edu.uniquindio.prestamoobjetos.exceptions.CantidadExcedidaException;
import co.edu.uniquindio.prestamoobjetos.exceptions.DatosNumericosException;
import co.edu.uniquindio.prestamoobjetos.exceptions.FechasException;
import co.edu.uniquindio.prestamoobjetos.exceptions.NoEliminadoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.NoExisteException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoNoRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoYaRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.PrestamoYaEntregadoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.YaExisteException;
import co.edu.uniquindio.prestamoobjetos.model.Cliente;
import co.edu.uniquindio.prestamoobjetos.model.Empleado;
import co.edu.uniquindio.prestamoobjetos.model.Empresa;
import co.edu.uniquindio.prestamoobjetos.model.Objeto;
import co.edu.uniquindio.prestamoobjetos.model.Prestamo;
import co.edu.uniquindio.prestamoobjetos.model.TipoDocumento;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Aplicacion extends Application {

	private Stage primaryStage;
	Empresa empresa = new Empresa("Almacen Quindío");

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Aplicacion Prestamo Objetos");
		mostrarLogin();
		//mostrarVentanaPrincipal();
		//mostrarInventario();
		//mostrarVentanaPrestador();
	}

	public void ingresoVentanaPrincipal() {
		mostrarVentanaPrincipal();
	}

	public void ingresoInventario() {
		mostrarInventario();
	}

	public void ingresoPrestador() {
		mostrarVentanaPrestador();
	}

	public void salirLogin() {
		mostrarLogin();
	}

	/**
	 * Metodo para mostrar la ventana de login
	 */
	private void mostrarLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/LoginView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			LoginController loginController = loader.getController();
			loginController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para mostrar la ventana de inventario
	 */
	private void mostrarInventario() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/InventarioView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			InventarioController inventarioController = loader.getController();
			inventarioController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para mostrar la ventana de prestador
	 */
	private void mostrarVentanaPrestador() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/PrestadorView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			PrestadorController prestadorController = loader.getController();
			prestadorController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para mostrar la ventana de admninistrador
	 */
	private void mostrarVentanaPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/EmpresaPrestamoObjetosView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			EmpresaPrestamoObjetosController empresaPrestamoObjetosController = loader.getController();
			empresaPrestamoObjetosController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public ArrayList<Cliente> obtenerClientes() {
		return empresa.getListaClientes();
	}

	public ArrayList<Empleado> obtenerEmpleados() {
		return empresa.getListaEmpleados();
	}

	public ArrayList<Objeto> obtenerObjetos() {
		return empresa.getListaObjetos();
	}

	public ArrayList<Prestamo> obtenerPrestamos() {
		return empresa.getListaPrestamos();
	}

	/**
	 * Metodo para crear un cliente
	 * @param nombre
	 * @param tipoDocumentoString
	 * @param numeroDocumento
	 * @param direccion
	 * @param ciudad
	 * @param departamento
	 * @param pais
	 * @param profesion
	 * @param email
	 * @param telefonoCelular
	 * @param telefonoResidencia
	 * @return
	 * @throws YaExisteException
	 */
	public Cliente crearCliente(String nombre, String tipoDocumentoString, String numeroDocumento, String direccion, String ciudad, String departamento, String pais, String profesion,
			String email, String telefonoCelular, String telefonoResidencia) throws YaExisteException {
		Cliente cliente = null;
		TipoDocumento tipoDocumento = null;

		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.CEDULA.toString())){
			tipoDocumento = TipoDocumento.CEDULA;
		}
		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.PASAPORTE.toString())){
			tipoDocumento = TipoDocumento.PASAPORTE;
		}
		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.CEDULAEXTRANJERIA.toString())){
			tipoDocumento = TipoDocumento.CEDULAEXTRANJERIA;
		}
		cliente = empresa.crearCliente(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, profesion, email, telefonoCelular, telefonoResidencia);

		return cliente;
	}

	/**
	 * Metodo para eliminar un cliente
	 * @param numeroDocumento
	 * @return
	 * @throws NoEliminadoException
	 */
	public boolean eliminarCliente(String numeroDocumento) throws NoEliminadoException {
		return empresa.eliminarCliente(numeroDocumento);
	}

	/**
	 * Metodo para actualizar un cliente
	 * @param numeroDocumentoActual
	 * @param nombre
	 * @param tipoDocumentoString
	 * @param numeroDocumento
	 * @param direccion
	 * @param ciudad
	 * @param departamento
	 * @param pais
	 * @param profesion
	 * @param email
	 * @param telefonoCelular
	 * @param telefonoResidencia
	 * @return
	 * @throws NoExisteException
	 */
	public boolean actualizarCliente(String numeroDocumentoActual, String nombre, String tipoDocumentoString, String numeroDocumento, String direccion, String ciudad, String departamento, String pais, String profesion,
			String email, String telefonoCelular, String telefonoResidencia) throws NoExisteException {
		TipoDocumento tipoDocumentoCliente = null;

		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.CEDULA.toString())){
			tipoDocumentoCliente = TipoDocumento.CEDULA;
		}
		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.PASAPORTE.toString())){
			tipoDocumentoCliente = TipoDocumento.PASAPORTE;
		}
		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.CEDULAEXTRANJERIA.toString())){
			tipoDocumentoCliente = TipoDocumento.CEDULAEXTRANJERIA;
		}
		return empresa.actualizarCliente(numeroDocumentoActual, nombre, tipoDocumentoCliente, numeroDocumento, direccion, ciudad, departamento, pais, profesion, email, telefonoCelular, telefonoResidencia);
	}

	/**
	 * Metodo para crear un empleado
	 * @param nombre
	 * @param tipoDocumentoString
	 * @param numeroDocumento
	 * @param direccion
	 * @param ciudad
	 * @param departamento
	 * @param pais
	 * @param tipoEmpleado
	 * @param email
	 * @param telefonoCelular
	 * @param telefonoResidencia
	 * @return
	 * @throws YaExisteException
	 */
	public Empleado crearEmpleado(String nombre, String tipoDocumentoString, String numeroDocumento, String direccion,
			String ciudad, String departamento, String pais, String tipoEmpleado, String email, String telefonoCelular,
			String telefonoResidencia) throws YaExisteException {
		Empleado empleado = null;
		TipoDocumento tipoDocumentoEmpleado = null;

		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.CEDULA.toString())){
			tipoDocumentoEmpleado = TipoDocumento.CEDULA;
		}
		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.PASAPORTE.toString())){
			tipoDocumentoEmpleado = TipoDocumento.PASAPORTE;
		}
		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.CEDULAEXTRANJERIA.toString())){
			tipoDocumentoEmpleado = TipoDocumento.CEDULAEXTRANJERIA;
		}
		empleado = empresa.crearEmpleado(nombre, tipoDocumentoEmpleado, numeroDocumento, direccion, ciudad, departamento, pais, tipoEmpleado, email, telefonoCelular, telefonoResidencia);

		return empleado;
	}


	/**
	 * Metodo para actualizar un empleado
	 * @param numeroDocumentoActual
	 * @param nombre
	 * @param tipoDocumentoString
	 * @param numeroDocumento
	 * @param direccion
	 * @param ciudad
	 * @param departamento
	 * @param pais
	 * @param tipoEmpleado
	 * @param email
	 * @param telefonoCelular
	 * @param telefonoResidencia
	 * @return
	 * @throws NoExisteException
	 */
	public boolean actualizarEmpleado(String numeroDocumentoActual, String nombre, String tipoDocumentoString,
			String numeroDocumento, String direccion, String ciudad, String departamento, String pais,
			String tipoEmpleado, String email, String telefonoCelular, String telefonoResidencia) throws NoExisteException {
		TipoDocumento tipoDocumentoEmpleado = null;

		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.CEDULA.toString())){
			tipoDocumentoEmpleado = TipoDocumento.CEDULA;
		}
		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.PASAPORTE.toString())){
			tipoDocumentoEmpleado = TipoDocumento.PASAPORTE;
		}
		if(tipoDocumentoString.toUpperCase().equals(TipoDocumento.CEDULAEXTRANJERIA.toString())){
			tipoDocumentoEmpleado = TipoDocumento.CEDULAEXTRANJERIA;
		}
		return empresa.actualizarEmpleado(numeroDocumentoActual, nombre, tipoDocumentoEmpleado, numeroDocumento, direccion, ciudad, departamento, pais, tipoEmpleado, email, telefonoCelular, telefonoResidencia);
	}

	/**
	 * Metodo para eliminar un empleado
	 * @param numeroDocumento
	 * @return
	 * @throws NoEliminadoException
	 */
	public boolean eliminarEmpleado(String numeroDocumento) throws NoEliminadoException {
		return empresa.eliminarEmpleado(numeroDocumento);
	}

	/**
	 * Metodo para crear un objeto
	 * @param codigo
	 * @param nombre
	 * @param unidadesDisponiblesString
	 * @param descripcion
	 * @param pesoString
	 * @param estado
	 * @param tipo
	 * @param valorUnitarioString
	 * @param valorTotalString
	 * @param precioAlquilerString
	 * @return
	 * @throws YaExisteException
	 * @throws DatosNumericosException
	 */
	public Objeto crearObjeto(String codigo, String nombre, String unidadesDisponiblesString, String descripcion,
			String pesoString, String tipo, String valorUnitarioString,
			String precioAlquilerString, Image foto) throws YaExisteException {
		Objeto objeto = null;
		int unidadesDisponibles = Integer.parseInt(unidadesDisponiblesString);
		double peso = Double.parseDouble(pesoString);
		double valorUnitario = Double.parseDouble(valorUnitarioString);
		double valorTotal = valorUnitario*unidadesDisponibles;
		double precioAlquiler = Double.parseDouble(precioAlquilerString);
		objeto = empresa.crearObjeto(codigo, nombre, unidadesDisponibles, descripcion, peso, tipo, valorUnitario, valorTotal, precioAlquiler, foto);
		return objeto;
	}

	/**
	 * Metodo para actualizar un objeto
	 * @param codigoActual
	 * @param codigo
	 * @param nombre
	 * @param unidadesDisponiblesString
	 * @param descripcion
	 * @param pesoString
	 * @param tipo
	 * @param valorUnitarioString
	 * @param precioAlquilerString
	 * @param foto
	 * @return
	 * @throws NoExisteException
	 */
	public boolean actualizarObjeto(String codigoActual, String codigo, String nombre, String unidadesDisponiblesString,
			String descripcion, String pesoString, String tipo, String valorUnitarioString,
			String precioAlquilerString, Image foto) throws NoExisteException {

		int unidadesDisponibles = Integer.parseInt(unidadesDisponiblesString);
		double peso = Double.parseDouble(pesoString);
		double valorUnitario = Double.parseDouble(valorUnitarioString);
		double valorTotal = valorUnitario*unidadesDisponibles;
		double precioAlquiler = Double.parseDouble(precioAlquilerString);

		return empresa.actualizarObjeto(codigoActual, codigo, nombre, unidadesDisponibles, descripcion, peso, tipo, valorUnitario, valorTotal, precioAlquiler, foto);
	}

	/**
	 * Metodo para eliminar un objeto
	 * @param codigo
	 * @return
	 * @throws NoEliminadoException
	 */
	public boolean eliminarObjeto(String codigo) throws NoEliminadoException {
		return empresa.eliminarObjeto(codigo);
	}

	/**
	 * Metodo para crear un prestamo
	 * @param codigo
	 * @param fechaPrestamo
	 * @param fechaEntrega
	 * @param estado
	 * @param codigoCliente
	 * @param codigoEmpleado
	 * @param codigoObjeto
	 * @param unidadesPrestadasString
	 * @return
	 * @throws FechasException
	 * @throws CantidadExcedidaException
	 * @throws NoExisteException
	 * @throws YaExisteException
	 */
	public Prestamo crearPrestamo(String codigo, String fechaPrestamo, String fechaEntrega,
			String estado, String codigoCliente, String codigoEmpleado, String codigoObjeto, String unidadesPrestadasString) throws YaExisteException, NoExisteException, CantidadExcedidaException, FechasException {
		Prestamo prestamo = null;
		int unidadesPrestadas = Integer.parseInt(unidadesPrestadasString);

		prestamo = empresa.crearPrestamo(codigo, fechaPrestamo, fechaEntrega, estado, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas);
		return prestamo;
	}

	/**
	 * Metodo para obtener los nombres de los objetos de un prestamo
	 * @param prestamoSeleccionado
	 * @return
	 */
	public String obtenerNombresObjetos(Prestamo prestamoSeleccionado) {
		return empresa.obtenerNombresObjetos(prestamoSeleccionado);
	}

	/**
	 * Metodo para obtener las unidades prestadas de los objetos de un prestamos seleccionado
	 * @param prestamoSeleccionado
	 * @return
	 */
	public String obtenerUnidadesPrestadasObjetos(Prestamo prestamoSeleccionado) {
		return empresa.obtenerUnidadesPrestadasObjetos(prestamoSeleccionado);
	}

	/**
	 * Metodo apra actualizar un prestamo
	 * @param codigoActual
	 * @param codigo
	 * @param fechaPrestamo
	 * @param fechaEntrega
	 * @param estado
	 * @param codigoCliente
	 * @param codigoEmpleado
	 * @param codigoObjeto
	 * @param unidadesPrestadasString
	 * @return
	 * @throws FechasException
	 * @throws NoExisteException
	 * @throws CantidadExcedidaException
	 * @throws NoEliminadoException
	 * @throws YaExisteException
	 * @throws ObjetoYaRegistradoException
	 */
	public boolean actualizarPrestamo(String codigoActual, String codigo, String fechaPrestamo, String fechaEntrega,
			String estado, String codigoCliente, String codigoEmpleado, String codigoObjeto, String unidadesPrestadasString) throws CantidadExcedidaException, NoExisteException, FechasException, NoEliminadoException, YaExisteException, ObjetoYaRegistradoException {
		int unidadesPrestadas = Integer.parseInt(unidadesPrestadasString);
		return empresa.actualizarPrestamo(codigoActual, codigo, fechaPrestamo, fechaEntrega, estado, codigoCliente, codigoEmpleado, codigoObjeto, unidadesPrestadas);
	}

	/**
	 * Metodo para eliminar un prestamo
	 * @param codigo
	 * @return
	 * @throws NoEliminadoException
	 */
	public boolean eliminarPrestamo(String codigo) throws NoEliminadoException {
		return empresa.eliminarPrestamo(codigo);
	}

	/**
	 * Metodo para agregar unidades a un prestamo
	 * @param prestamoSeleccionado
	 * @param codigoObjeto
	 * @param unidadesAgregar
	 * @return
	 * @throws ObjetoNoRegistradoException
	 * @throws CantidadExcedidaException
	 * @throws FechasException
	 */
	public boolean agregarUnidadesPrestamo(Prestamo prestamoSeleccionado, String codigoObjeto, String unidadesAgregar) throws CantidadExcedidaException, ObjetoNoRegistradoException, FechasException {
		return empresa.agregarUnidadesPrestamo(prestamoSeleccionado, codigoObjeto, unidadesAgregar);
	}

	/**
	 * Metodo para agregar objetos a un prestamo
	 * @param prestamoSeleccionado
	 * @param codigoObjeto
	 * @param unidadesPrestadas
	 * @return
	 * @throws FechasException
	 * @throws ObjetoYaRegistradoException
	 */
	public boolean agregarObjetoPrestamo(Prestamo prestamoSeleccionado, String codigoObjeto, String unidadesPrestadas) throws FechasException, ObjetoYaRegistradoException {
		return empresa.agregarObjetoPrestamo(prestamoSeleccionado, codigoObjeto, unidadesPrestadas);
	}

	/**
	 * MEtodo para entregar un prestamo
	 * @param prestamoSeleccionado
	 * @param fechaEntrega
	 * @return
	 * @throws FechasException
	 * @throws PrestamoYaEntregadoException
	 */
	public boolean entregarPrestamo(Prestamo prestamoSeleccionado, String fechaEntrega) throws FechasException, PrestamoYaEntregadoException {
		return empresa.entregarPrestamo(prestamoSeleccionado, fechaEntrega);
	}

	/**
	 * MEtodo para obtener los objetos disponibles
	 * @return
	 */
	public String obtenerObjetosDisponibles() {
		return empresa.obtenerObjetosDisponibles();
	}

	/**
	 * Metodo para obtener los objetos prestamdos
	 * @return
	 */
	public String obtenerObjetosPrestados() {
		return empresa.obtenerObjetosPrestados();
	}

	/**
	 * Metodo para obtener los objetos agotados
	 * @return
	 */
	public String obtenerObjetosAgotados() {
		return empresa.obtenerObjetosAgotados();
	}

	/**
	 * Metodo para obtener el codigo de un empleado segun el nomnbre
	 * @param nombreEmpleado
	 * @return
	 */
	public String obtenerCodigoEmpleado(String nombreEmpleado) {
		return empresa.obtenerCodigoEmpleado(nombreEmpleado);
	}

	public Prestamo obtenerPrestamoCodigo(String codigo) {
		return empresa.obtenerPrestamoCodigo(codigo);
	}

}
