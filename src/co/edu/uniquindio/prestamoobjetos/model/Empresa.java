package co.edu.uniquindio.prestamoobjetos.model;

import java.util.ArrayList;

import co.edu.uniquindio.prestamoobjetos.exceptions.CantidadExcedidaException;
import co.edu.uniquindio.prestamoobjetos.exceptions.FechasException;
import co.edu.uniquindio.prestamoobjetos.exceptions.NoEliminadoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.NoExisteException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoNoRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoYaRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.PrestamoYaEntregadoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.YaExisteException;
import javafx.scene.image.Image;


public class Empresa {

	private String nombreEmpresa;
	ArrayList<Empleado> listaEmpleados = new ArrayList<>();
	ArrayList<Cliente> listaClientes = new ArrayList<>();
	ArrayList<Objeto> listaObjetos = new ArrayList<>();
	ArrayList<Prestamo> listaPrestamos = new ArrayList<>();


	public Empresa(String nombreEmpresa) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		inicializarDatos();
	}

	private void inicializarDatos() {
		TipoDocumento td = null;
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan Barela");
		cliente.setCiudadResidencia("Armenia");
		cliente.setDepartamento("Quindio");
		cliente.setDireccion("Cra 24 2-03");
		cliente.setEmail("barelajuan@gmail.com");
		cliente.setNumeroDocumento("1005090418");
		cliente.setPais("Colombia");
		cliente.setProfesion("Estudiante");
		cliente.setTelefonoCelular("3010235466");
		cliente.setTelefonoResidencia("7450095");
		td = TipoDocumento.CEDULA;
		cliente.setTipoDocumento(td);
		getListaClientes().add(cliente);

		cliente = new Cliente();
		cliente.setNombre("Ana Cirope");
		cliente.setCiudadResidencia("Cali");
		cliente.setDepartamento("Guambique");
		cliente.setDireccion("Cl 56 Mz 2");
		cliente.setEmail("ciropedemanzana@gmail.com");
		cliente.setNumeroDocumento("1005090419");
		cliente.setPais("Colombia");
		cliente.setProfesion("Contadora");
		cliente.setTelefonoCelular("32625124215");
		cliente.setTelefonoResidencia("71211212");
		td = TipoDocumento.PASAPORTE;
		cliente.setTipoDocumento(td);
		getListaClientes().add(cliente);

		cliente = new Cliente();
		cliente.setNombre("Mario Guevara");
		cliente.setCiudadResidencia("Simbioque");
		cliente.setDepartamento("Paloquemao");
		cliente.setDireccion("Av 34 cra 23-02");
		cliente.setEmail("guevaramario@gmail.com");
		cliente.setNumeroDocumento("1005090420");
		cliente.setPais("Australia");
		cliente.setProfesion("Ladron");
		cliente.setTelefonoCelular("35651212121");
		cliente.setTelefonoResidencia("789856532");
		td = TipoDocumento.CEDULAEXTRANJERIA;
		cliente.setTipoDocumento(td);
		getListaClientes().add(cliente);

		Empleado empleado = new Empleado();
		empleado.setNombre("Daniel Mejia");
		empleado.setCiudadResidencia("Armenia");
		empleado.setDepartamento("Quindio");
		empleado.setDireccion("Cra 24 2-03");
		empleado.setEmail("danielm@gmail.com");
		empleado.setNumeroDocumento("1005090569");
		empleado.setPais("Colombia");
		empleado.setTipoEmpleado("Medio tiempo");
		empleado.setTelefonoCelular("3010235466");
		empleado.setTelefonoResidencia("7450095");
		td = TipoDocumento.CEDULA;
		empleado.setTipoDocumento(td);
		getListaEmpleados().add(empleado);

		empleado = new Empleado();
		empleado.setNombre("Maria Lopez");
		empleado.setCiudadResidencia("Cali");
		empleado.setDepartamento("Guambique");
		empleado.setDireccion("Cl 56 Mz 2");
		empleado.setEmail("marialop@gmail.com");
		empleado.setNumeroDocumento("1005090458");
		empleado.setPais("Colombia");
		empleado.setTipoEmpleado("Tiempo Completo");
		empleado.setTelefonoCelular("32625124215");
		empleado.setTelefonoResidencia("71211212");
		td = TipoDocumento.PASAPORTE;
		empleado.setTipoDocumento(td);
		getListaEmpleados().add(empleado);

		empleado = new Empleado();
		empleado.setNombre("Jenn Muriel");
		empleado.setCiudadResidencia("Simbioque");
		empleado.setDepartamento("Paloquemao");
		empleado.setDireccion("Av 34 cra 23-02");
		empleado.setEmail("jparra@gmail.com");
		empleado.setNumeroDocumento("1005090879");
		empleado.setPais("Australia");
		empleado.setTipoEmpleado("Ladron");
		empleado.setTelefonoCelular("35651212121");
		empleado.setTelefonoResidencia("789856532");
		td = TipoDocumento.CEDULAEXTRANJERIA;
		empleado.setTipoDocumento(td);
		getListaEmpleados().add(empleado);

		Objeto objeto = new Objeto();
		objeto.setNombre("Olla");
		objeto.setCodigo("1");
		objeto.setUnidadesDisponibles(241);
		objeto.setEstado("Disponible");
		objeto.setPeso(32.2);
		objeto.setPrecioAlquiler(2000);
		objeto.setTipo("Usado");
		objeto.setValorUnitario(2500);
		objeto.setValorTotal(2500*5);
		objeto.setDescripcion("Una olla de aluminio bonita");
		Image foto = new Image(getClass().getResourceAsStream("olla.jpg"));
		objeto.setFoto(foto);
		getListaObjetos().add(objeto);

		objeto = new Objeto();
		objeto.setNombre("Silla");
		objeto.setCodigo("2");
		objeto.setUnidadesDisponibles(76);
		objeto.setEstado("Disponible");
		objeto.setPeso(65.2);
		objeto.setPrecioAlquiler(1000);
		objeto.setTipo("Nuevo");
		objeto.setValorUnitario(3000);
		objeto.setValorTotal(10*3000);
		objeto.setDescripcion("Una silla de madera bonita");
		foto = new Image(getClass().getResourceAsStream("silla.jpg"));
		objeto.setFoto(foto);
		getListaObjetos().add(objeto);

		Prestamo prestamo = new Prestamo();
		prestamo.setCliente(listaClientes.get(0));
		prestamo.setEmpleado(listaEmpleados.get(1));
		prestamo.setCodigo("1");
		prestamo.setEstado("Entregado");
		prestamo.setFechaPrestamo("2021-03-01");
		prestamo.setFechaEntrega("2021-03-05");
		prestamo.crearDetallePrestamo(objeto, 3);
		prestamo.setValorPrestamo(20000);
		getListaPrestamos().add(prestamo);

		objeto = new Objeto();
		objeto.setNombre("Espada");
		objeto.setCodigo("3");
		objeto.setUnidadesDisponibles(0);
		objeto.setEstado("No Disponible");
		objeto.setPeso(23);
		objeto.setPrecioAlquiler(3500);
		objeto.setTipo("Nuevo");
		objeto.setValorUnitario(10000);
		objeto.setValorTotal(3*10000);
		objeto.setDescripcion("Una espada para asesinar reyes");
		foto = new Image(getClass().getResourceAsStream("espada.jpg"));
		objeto.setFoto(foto);
		getListaObjetos().add(objeto);

	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public ArrayList<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}


	public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}


	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}


	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}


	public ArrayList<Objeto> getListaObjetos() {
		return listaObjetos;
	}


	public void setListaObjetos(ArrayList<Objeto> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}


	public ArrayList<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}


	public void setListaPrestamos(ArrayList<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	/**
	 * Metodo para crear un cliente
	 * @param numeroIdentificacionCliente
	 * @param tipoDocumentoCliente
	 * @param nombreCliente
	 * @param generoCliente
	 * @param ciudadResidenciaCliente
	 * @return
	 * @throws CantidadExcedidaException
	 * @throws YaExisteException
	 */
	public Cliente crearCliente(String nombre, TipoDocumento tipoDocumento, String numeroDocumento, String direccion, String ciudad,
			String departamento, String pais, String profesion, String email, String telefonoCelular, String telefonoResidencia) throws YaExisteException {
		Cliente cliente = null;

		//verificar si el cliente que se va a agregar ya existe
		cliente = obtenerCliente(numeroDocumento);

		if(cliente == null){
			cliente = new Cliente(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, profesion, email, telefonoCelular, telefonoResidencia);

			getListaClientes().add(cliente);
			return cliente;
		}else{
			throw new YaExisteException("El cliente con el numero de documento ingresado ya existe");
		}

	}

	/**
	 * Metodo para obtener un cliente por el numero de documento
	 * @param numeroDocumento
	 * @return
	 */
	private Cliente obtenerCliente(String numeroDocumento) {
		for(Cliente cliente : listaClientes){
			if(cliente.getNumeroDocumento().equals(numeroDocumento)){
				return cliente;
			}
		}
		return null;
	}

	/**
	 * Metodo para eliminar un cliente por el numero de documento
	 * @param numeroDocumento
	 * @return
	 * @throws NoEliminadoException
	 */
	public boolean eliminarCliente(String numeroDocumento) throws NoEliminadoException {
		Cliente cliente = null;

		cliente = obtenerCliente(numeroDocumento);

		if(cliente != null){
			for(Prestamo prestamo : listaPrestamos){
				if(prestamo.getCliente().getNumeroDocumento().equals(numeroDocumento)){
					throw new NoEliminadoException("El cliente no se puede eliminar porque tiene asociado un prestamo");
				}else{
					getListaClientes().remove(cliente);
				}
			}
			return true;
		}else{
			return false;
		}

	}

	/**
	 * Metodo para actualizar los datos de un cliente por el numero de documento
	 * @param numeroDocumentoActual
	 * @param nombre
	 * @param numeroDocumento
	 * @param direccion
	 * @return
	 * @throws YaExisteException
	 * @throws NoExisteException
	 */
	public boolean actualizarCliente(String numeroDocumentoActual, String nombre, TipoDocumento tipoDocumento, String numeroDocumento, String direccion, String ciudad, String departamento, String pais, String profesion,
			String email, String telefonoCelular, String telefonoResidencia) throws NoExisteException {
		Cliente cliente = null;

		cliente = obtenerCliente(numeroDocumentoActual);

		if(cliente != null){
			cliente.setNombre(nombre);
			cliente.setNumeroDocumento(numeroDocumento);
			cliente.setDireccion(direccion);
			cliente.setTipoDocumento(tipoDocumento);
			cliente.setDireccion(direccion);
			cliente.setCiudadResidencia(ciudad);
			cliente.setDepartamento(departamento);
			cliente.setPais(pais);
			cliente.setProfesion(profesion);
			cliente.setEmail(email);
			cliente.setTelefonoCelular(telefonoCelular);
			cliente.setTelefonoResidencia(telefonoResidencia);
			return true;
		}else{
			throw new NoExisteException("El cliente con el numero de documento ingresado no existe");
		}
	}

	public Empleado crearEmpleado(String nombre, TipoDocumento tipoDocumento, String numeroDocumento,
			String direccion, String ciudad, String departamento, String pais, String tipoEmpleado, String email,
			String telefonoCelular, String telefonoResidencia) throws YaExisteException {
		Empleado empleado = null;

		//verificar si el empleado que se va a agregar ya existe
		empleado = obtenerEmpleado(numeroDocumento);

		if(empleado == null){
			empleado = new Empleado(nombre, tipoDocumento, numeroDocumento, direccion, ciudad, departamento, pais, tipoEmpleado, email, telefonoCelular, telefonoResidencia);

			getListaEmpleados().add(empleado);
			return empleado;
		}else{
			throw new YaExisteException("El empleado con el numero de documento ingresado ya existe");
		}
	}

	/**
	 * Metodo para obtener un empleado por el numero de documento
	 * @param numeroDocumento
	 * @return
	 */
	private Empleado obtenerEmpleado(String numeroDocumento) {
		for(Empleado empleado : listaEmpleados){
			if(empleado.getNumeroDocumento().equals(numeroDocumento)){
				return empleado;
			}
		}
		return null;
	}

	public boolean eliminarEmpleado(String numeroDocumento) throws NoEliminadoException {
		Empleado empleado = null;

		empleado = obtenerEmpleado(numeroDocumento);

		if(empleado != null){
			for(Prestamo prestamo : listaPrestamos){
				if(prestamo.getEmpleado().getNumeroDocumento().equals(numeroDocumento)){
					throw new NoEliminadoException("El empleado no se puede eliminar porque está asociado a un prestamo");
				}else{
					getListaEmpleados().remove(empleado);
				}
			}
			return true;
		}else{
			return false;
		}
	}

	public boolean actualizarEmpleado(String numeroDocumentoActual, String nombre, TipoDocumento tipoDocumento,
			String numeroDocumento, String direccion, String ciudad, String departamento, String pais,
			String tipoEmpleado, String email, String telefonoCelular, String telefonoResidencia) throws NoExisteException {
		Empleado empleado = null;

		empleado = obtenerEmpleado(numeroDocumentoActual);

		if(empleado != null){
			empleado.setNombre(nombre);
			empleado.setNumeroDocumento(numeroDocumento);
			empleado.setDireccion(direccion);
			empleado.setTipoDocumento(tipoDocumento);
			empleado.setDireccion(direccion);
			empleado.setCiudadResidencia(ciudad);
			empleado.setDepartamento(departamento);
			empleado.setPais(pais);
			empleado.setTipoEmpleado(tipoEmpleado);
			empleado.setEmail(email);
			empleado.setTelefonoCelular(telefonoCelular);
			empleado.setTelefonoResidencia(telefonoResidencia);
			return true;
		}else{
			throw new NoExisteException("El empleado con el numero de documento ingresado no existe");
		}
	}

	/**
	 * Metodo para crear un objeto
	 * @param codigoObjeto
	 * @param nombreObjeto
	 * @param unidadesDisponiblesObjeto
	 * @param estadoObjeto
	 * @param precioAlquilerObjeto
	 * @param precioAlquilerObjeto2
	 * @param valorTotalObjeto
	 * @param valorUnitarioObjeto
	 * @param fotoObjeto
	 * @param estadoObjeto2
	 * @return
	 * @throws CantidadExcedidaException
	 * @throws YaExisteException
	 */
	public Objeto crearObjeto(String codigo, String nombre, int unidadesDisponibles, String descripcion, double peso, String tipo, double valorUnitario, double valorTotal, double precioAlquiler, Image foto) throws YaExisteException{
		Objeto objeto = null;
		String estado = "";

		//verificar si el objeto que se va a agregar ya existe
		objeto = obtenerObjeto(codigo);

		if(unidadesDisponibles > 0){
			estado = "Disponible";
		}else{
			estado = "No Disponible";
		}
		if(objeto == null){
			objeto = new Objeto(codigo, nombre, unidadesDisponibles, descripcion, peso, estado, tipo, valorUnitario, valorTotal, precioAlquiler, foto);
			getListaObjetos().add(objeto);
			return objeto;
		}else{
			throw new YaExisteException("El objeto con el codigo ingresado ya existe");
		}
	}

	/**
	 * Metodo para ver si ya existe un objeto con ese codigo
	 * @param codigo
	 * @return
	 */
	private Objeto obtenerObjeto(String codigo) {
		for(Objeto objeto : listaObjetos){
			if(objeto.getCodigo().equals(codigo)){
				return objeto;
			}
		}
		return null;
	}

	/**
	 * Metodo para actualizar un objeto
	 * @param codigoActual
	 * @param codigo
	 * @param nombre
	 * @param unidadesDisponibles
	 * @param descripcion
	 * @param peso
	 * @param estado
	 * @param tipo
	 * @param valorUnitario
	 * @param valorTotal
	 * @param precioAlquiler
	 * @return
	 * @throws NoExisteException
	 */
	public boolean actualizarObjeto(String codigoActual, String codigo, String nombre, int unidadesDisponibles,
			String descripcion, double peso, String tipo, double valorUnitario, double valorTotal,
			double precioAlquiler, Image foto) throws NoExisteException {
		Objeto objeto = null;
		String estado = "";
		if(unidadesDisponibles > 0){
			estado = "Disponible";
		}else{
			estado = "No Disponible";
		}
		objeto = obtenerObjeto(codigoActual);

		if(objeto != null){
			objeto.setNombre(nombre);
			objeto.setCodigo(codigo);
			objeto.setUnidadesDisponibles(unidadesDisponibles);
			objeto.setEstado(estado);
			objeto.setPeso(peso);
			objeto.setPrecioAlquiler(precioAlquiler);
			objeto.setTipo(tipo);
			objeto.setValorUnitario(valorUnitario);
			objeto.setValorTotal(valorTotal);
			objeto.setDescripcion(descripcion);
			objeto.setFoto(foto);
			return true;
		}else{
			throw new NoExisteException("El objeto con el codigo ingresado no existe");
		}
	}

	public boolean eliminarObjeto(String codigo) throws NoEliminadoException {
		Objeto objeto = null;

		objeto = obtenerObjeto(codigo);

		if(objeto != null){
			for(Prestamo prestamo : listaPrestamos){
				if(prestamo.verificarObjetoPrestado(codigo)==true){
					throw new NoEliminadoException("El objeto no se puede eliminar porque ha sido prestado");
				}else{
					getListaObjetos().remove(objeto);
				}
			}
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Metodo para crear un prestamo
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
	 * @throws YaExisteException
	 * @throws NoExisteException
	 * @throws CantidadExcedidaException
	 * @throws FechasException
	 */
	public Prestamo crearPrestamo(String codigo, String fechaPrestamo, String fechaEntrega,
			String estado, String codigoCliente, String codigoEmpleado, String codigoObjeto, int unidadesPrestadas) throws YaExisteException, NoExisteException, CantidadExcedidaException, FechasException {
		Prestamo prestamo = null;
		Cliente cliente = null;
		Empleado empleado = null;
		Objeto objeto = null;
		double valorPrestamo = 0.0;

		//verificar si el prestamo que se va a agregar ya existe
		prestamo = obtenerPrestamo(codigo);

		//verificar si el codigo del empleado, cliente u objeto si existen
		empleado = obtenerEmpleado(codigoEmpleado);
		cliente = obtenerCliente(codigoCliente);
		objeto = obtenerObjeto(codigoObjeto);

		if(empleado != null && cliente != null && objeto != null){
			if(prestamo == null){
				if(objeto.getUnidadesDisponibles()>=unidadesPrestadas){
					prestamo = new Prestamo(codigo, estado, fechaPrestamo, fechaEntrega, empleado, cliente);
					if(estado.equals("Pendiente")){
						prestamo.setValorPrestamo(valorPrestamo);
					}else{
						valorPrestamo = prestamo.calcularValorPrestamo(objeto, unidadesPrestadas, fechaPrestamo, fechaEntrega);
						if(valorPrestamo>=0){
							prestamo.setValorPrestamo(valorPrestamo);
						}else{
							throw new FechasException("La fecha de entrega debe ser posterior a la fecha de prestamo");
						}
					}
					prestamo.setValorPrestamo(valorPrestamo);
					prestamo.crearDetallePrestamo(objeto, unidadesPrestadas);
					getListaPrestamos().add(prestamo);
					return prestamo;
				}else{
					throw new CantidadExcedidaException("No hay suficientes unidades disponibles para prestar del objeto ingresado");
				}
			}else{
				throw new YaExisteException("Ya existe un prestamo creado con el codigo ingresado");
			}
		}else{
			throw new NoExisteException("El codigo o numero de identificacion ingresados del objeto, cliente o empleado no coinciden");
		}
	}

	public boolean actualizarPrestamo(String codigoActual, String codigo, String fechaPrestamo, String fechaEntrega,
			String estado, String codigoCliente, String codigoEmpleado, String codigoObjeto, int unidadesPrestadas) throws CantidadExcedidaException, NoExisteException, FechasException, NoEliminadoException, YaExisteException, ObjetoYaRegistradoException {
		Prestamo prestamo = null;
		Cliente cliente = null;
		Empleado empleado = null;
		Objeto objeto = null;
		double valorPrestamo = 0.0;

		prestamo = obtenerPrestamo(codigoActual);
		String estadoActual = prestamo.getEstado();
		cliente = obtenerCliente(codigoCliente);
		empleado = obtenerEmpleado(codigoEmpleado);
		objeto = obtenerObjeto(codigoObjeto);

		if(cliente != null && empleado != null && objeto != null){
			prestamo.setCodigo(codigo);
			prestamo.setCliente(cliente);
			prestamo.setEmpleado(empleado);
			prestamo.setEstado(estado);
			prestamo.setFechaPrestamo(fechaPrestamo);
			prestamo.setFechaEntrega(fechaEntrega);
			prestamo.actualizarDetallesPrestamo(estadoActual, estado, objeto, unidadesPrestadas);
			if(estado.equals("Pendiente")){
				prestamo.setValorPrestamo(0.0);
			}else{
				valorPrestamo = prestamo.actualizarValorPrestamo(objeto, unidadesPrestadas);
				prestamo.setValorPrestamo(valorPrestamo);
			}

		}else{
			throw new NoExisteException("El codigo del objeto o el numero de identificacion del cliente o empleado");
		}

		return true;
	}

	public boolean eliminarPrestamo(String codigo) throws NoEliminadoException {
		for(Prestamo prestamo : listaPrestamos){
			if(prestamo.getCodigo().equals(codigo)){
				if(prestamo.getFechaEntrega().equals("") || prestamo.getFechaEntrega().isEmpty()){
					throw new NoEliminadoException("El prestamo no se puede eliminar ya que aun está pendiente");
				}else{
					getListaPrestamos().remove(prestamo);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Metodo para verificar si ya hay un prestamo con el mismo codigo
	 * @param codigo
	 * @return
	 */
	private Prestamo obtenerPrestamo(String codigo) {
		for(Prestamo prestamo : listaPrestamos){
			if(prestamo.getCodigo().equals(codigo)){
				return prestamo;
			}
		}
		return null;
	}

	public String obtenerNombresObjetos(Prestamo prestamoSeleccionado) {
		return prestamoSeleccionado.obtenerNombresObjetos();
	}

	public String obtenerUnidadesPrestadasObjetos(Prestamo prestamoSeleccionado) {
		return prestamoSeleccionado.obtenerUnidadesPrestadasObjetos();
	}

	public boolean agregarUnidadesPrestamo(Prestamo prestamoSeleccionado, String codigoObjeto, String unidadesAgregar) throws CantidadExcedidaException, ObjetoNoRegistradoException, FechasException {
		return prestamoSeleccionado.agregarUnidades(codigoObjeto, unidadesAgregar);
	}

	public boolean agregarObjetoPrestamo(Prestamo prestamoSeleccionado, String codigoObjeto, String unidadesPrestadasString) throws FechasException, ObjetoYaRegistradoException {
		Objeto objeto = obtenerObjeto(codigoObjeto);
		int unidadesPrestadas = Integer.parseInt(unidadesPrestadasString);
		return prestamoSeleccionado.agregarObjeto(objeto, unidadesPrestadas);
	}

	public boolean entregarPrestamo(Prestamo prestamoSeleccionado, String fechaEntrega) throws FechasException, PrestamoYaEntregadoException {
		return prestamoSeleccionado.entregarPrestamo(fechaEntrega);
	}

	public String obtenerObjetosDisponibles() {
		String objetosDisponibles = "";
		for(Objeto objeto : listaObjetos){
			if(objeto.getEstado().equals("Disponible")){
				objetosDisponibles += objeto.toString()+"\n";
			}
		}
		return objetosDisponibles;
	}

	public String obtenerObjetosPrestados() {
		String objetosPrestados = "";

		for(Prestamo prestamo : listaPrestamos){
			objetosPrestados += prestamo.obtenerObjetosPrestados().toString()+"\n";
		}

		return objetosPrestados;
	}

	public String obtenerObjetosAgotados() {
		String objetosAgotados = "";

		for(Objeto objeto : listaObjetos){
			if(objeto.getUnidadesDisponibles()<=5){
				objetosAgotados += objeto.toString()+"\n";
			}
		}

		return objetosAgotados;
	}

	public String obtenerCodigoEmpleado(String nombreEmpleado) {
		for(Empleado empleado : listaEmpleados){
			if(empleado.getNombre().equalsIgnoreCase(nombreEmpleado)){
				return empleado.getNumeroDocumento();
			}
		}
		return null;
	}

	public Prestamo obtenerPrestamoCodigo(String codigo) {
		for(Prestamo prestamo : listaPrestamos){
			if(prestamo.getCodigo().equals(codigo)){
				return prestamo;
			}
		}
		return null;
	}
}






