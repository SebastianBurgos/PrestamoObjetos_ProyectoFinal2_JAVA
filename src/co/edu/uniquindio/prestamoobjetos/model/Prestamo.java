package co.edu.uniquindio.prestamoobjetos.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import co.edu.uniquindio.prestamoobjetos.exceptions.CantidadExcedidaException;
import co.edu.uniquindio.prestamoobjetos.exceptions.FechasException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoNoRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.ObjetoYaRegistradoException;
import co.edu.uniquindio.prestamoobjetos.exceptions.PrestamoYaEntregadoException;

public class Prestamo {

	private String codigo;
	private String estado;
	private double valorPrestamo;
	private String fechaPrestamo;
	private String fechaEntrega;
	private Empleado empleado;
	private Cliente cliente;
	ArrayList<DetallePrestamo> listaDetallesPrestamo = new ArrayList<>();

	/**
	 * Metodo constructor de la clase prestamo
	 * @param codigo
	 * @param estado
	 * @param valorPrestamo
	 * @param fechaPrestamo
	 * @param fechaEntrega
	 * @param empleado
	 * @param cliente
	 */
	public Prestamo(String codigo, String estado, String fechaPrestamo, String fechaEntrega,
			Empleado empleado, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.estado = estado;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaEntrega = fechaEntrega;
		this.empleado = empleado;
		this.cliente = cliente;
	}

	public Prestamo() {
		// TODO Auto-generated constructor stub
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getValorPrestamo() {
		return valorPrestamo;
	}

	public void setValorPrestamo(double valorPrestamo) {
		this.valorPrestamo = valorPrestamo;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<DetallePrestamo> getListaDetallesPrestamo() {
		return listaDetallesPrestamo;
	}

	public void setListaDetallesPrestamo(ArrayList<DetallePrestamo> listaDetallesPrestamo) {
		this.listaDetallesPrestamo = listaDetallesPrestamo;
	}

	@Override
	public String toString() {
		return "Prestamo [codigo=" + codigo + ", estado=" + estado + ", valorPrestamo=" + valorPrestamo
				+ ", fechaPrestamo=" + fechaPrestamo + ", fechaEntrega=" + fechaEntrega + ", empleado=" + empleado
				+ ", cliente=" + cliente + ", listaDetallesPrestamo=" + listaDetallesPrestamo + "]";
	}

	public void crearDetallePrestamo(Objeto objeto, int unidadesPrestadas) {
		double subTotal = 0.0;
		subTotal = objeto.getValorUnitario()*unidadesPrestadas;
		DetallePrestamo detallePrestamo = new DetallePrestamo(unidadesPrestadas, subTotal);
		detallePrestamo.setObjeto(objeto);
		if(getEstado().equals("Pendiente")){
			detallePrestamo.getObjeto().disminuirExistencias(unidadesPrestadas);
		}
		getListaDetallesPrestamo().add(detallePrestamo);
	}

	public String obtenerNombresObjetos() {
		Objeto objeto = null;
		String listaNombres = "";
		for(DetallePrestamo detallePrestamo : listaDetallesPrestamo){
			objeto = detallePrestamo.getObjeto();
			listaNombres += objeto.getNombre()+". \n";
		}
		return listaNombres;
	}

	public String obtenerUnidadesPrestadasObjetos() {
		Objeto objeto = null;
		String nombreObjeto = "";
		String listaUnidadesPrestadas = "";
		for(DetallePrestamo detallePrestamo : listaDetallesPrestamo){
			objeto = detallePrestamo.getObjeto();
			nombreObjeto = objeto.getNombre();
			listaUnidadesPrestadas += nombreObjeto +": "+detallePrestamo.getUnidadesPrestadas()+" \n";
		}
		return listaUnidadesPrestadas;
	}

	public double calcularValorPrestamo(Objeto objeto, int unidadesPrestadas, String fechaPrestamo, String fechaEntrega) throws FechasException {
		double valorPrestamo = 0.0;
		long numeroDiasTranscurridos = 0;

		Calendar fechaP = convertirFecha(fechaPrestamo);
		Calendar fechaE = convertirFecha(fechaEntrega);

		//Hallar la cantidad de dias entre las dos fechas
		long milisec = fechaE.getTimeInMillis()-fechaP.getTimeInMillis();
		numeroDiasTranscurridos = milisec/1000/60/60/24;
		if(numeroDiasTranscurridos<0){
			throw new FechasException("La fecha de entrega debe ser posterior a la fecha de prestamo");
		}
		valorPrestamo = (objeto.getPrecioAlquiler()*numeroDiasTranscurridos)+(unidadesPrestadas*objeto.getValorUnitario());

		return valorPrestamo;
	}

	/**
	 * Metodo para convertir las fechas String a Calendar
	 * @param fecha
	 * @return
	 */
	private Calendar convertirFecha(String fecha) {
		String[] fechaPrestamoArray = fecha.split("-");
		int diaPrestamo = Integer.valueOf(fechaPrestamoArray[2]);
		int mesPrestamo = Integer.valueOf(fechaPrestamoArray[1]) - 1;
		int añoPrestamo = Integer.valueOf(fechaPrestamoArray[0]);
		Calendar fechaConvertida = new GregorianCalendar(añoPrestamo, mesPrestamo, diaPrestamo);
		return fechaConvertida;
	}



	/**
	 * Metodo para agregar unidades prestadas a un prestamo
	 * @param codigoObjeto
	 * @param unidadesAgregarString
	 * @return
	 * @throws CantidadExcedidaException
	 * @throws ObjetoNoRegistradoException
	 * @throws FechasException
	 */
	public boolean agregarUnidades(String codigoObjeto, String unidadesAgregarString) throws CantidadExcedidaException, ObjetoNoRegistradoException, FechasException {
		int unidadesAgregar = Integer.parseInt(unidadesAgregarString);
		int unidadesPrestadasActual = 0;
		int unidadesPrestadasNueva = 0;
		double valorPrestamoNuevo = 0;

		for(DetallePrestamo detallePrestamo : listaDetallesPrestamo){
			Objeto objeto = detallePrestamo.getObjeto();
			if(objeto.getCodigo().equals(codigoObjeto)){
				if(objeto.getUnidadesDisponibles()>=unidadesAgregar){
					unidadesPrestadasActual = detallePrestamo.getUnidadesPrestadas();
					unidadesPrestadasNueva = unidadesPrestadasActual + unidadesAgregar;
					if(objeto.getUnidadesDisponibles()==0){
						objeto.setEstado("No Disponible");
					}
					if(getEstado().equals("Entregado")){
						valorPrestamoNuevo = actualizarValorPrestamo(objeto, unidadesPrestadasNueva);
						setValorPrestamo(valorPrestamoNuevo);
						if(unidadesPrestadasNueva>objeto.getUnidadesDisponibles()){
							throw new CantidadExcedidaException("Las unidades registradas del prestamo excedieron las unidades disponibles del objeto");
						}
					}else{
						objeto.setUnidadesDisponibles(objeto.getUnidadesDisponibles()-unidadesAgregar);
					}
					detallePrestamo.setUnidadesPrestadas(unidadesPrestadasNueva);
					return true;
				}else{
					throw new CantidadExcedidaException("No hay suficientes unidades disponibles para prestar de este objeto");
				}
			}else{
				//throw new ObjetoNoRegistradoException("No hay objetos registrados con ese codigo en el prestamo");
			}
		}
		return false;
	}

	public double actualizarValorPrestamo(Objeto objeto, int unidadesPrestadasNueva) throws FechasException {
		double valorPrestamo = 0.0;
		long numeroDiasTranscurridos = 0;

		Calendar fechaP = convertirFecha(fechaPrestamo);
		Calendar fechaE = convertirFecha(fechaEntrega);

		//Hallar la cantidad de dias entre las dos fechas
		long milisec = fechaE.getTimeInMillis()-fechaP.getTimeInMillis();
		numeroDiasTranscurridos = milisec/1000/60/60/24;

		if(numeroDiasTranscurridos < 0){
			throw new FechasException("La fecha de entrega debe ser posterior a la fecha de prestamo");
		}

		valorPrestamo = (objeto.getPrecioAlquiler()*numeroDiasTranscurridos)+(unidadesPrestadasNueva*objeto.getValorUnitario());

		return valorPrestamo;
	}

	public boolean agregarObjeto(Objeto objeto, int unidadesPrestadas) throws FechasException, ObjetoYaRegistradoException {
		boolean objetoYaAgregado = false;
		for(DetallePrestamo detallePrestamo : listaDetallesPrestamo){
			if(detallePrestamo.getObjeto()==objeto){
				objetoYaAgregado = true;
			}
		}
		if(objetoYaAgregado != true){
			if(getEstado().equals("Entregado")){
				double nuevoValorPrestamo = getValorPrestamo()+actualizarValorPrestamo(objeto, unidadesPrestadas);
				setValorPrestamo(nuevoValorPrestamo);
			}
			crearDetallePrestamo(objeto, unidadesPrestadas);
			return true;
		}else{
			throw new ObjetoYaRegistradoException("El objeto que intenta agregar al prestamo ya está agregado");
		}
	}

	public boolean entregarPrestamo(String fechaEntrega) throws FechasException, PrestamoYaEntregadoException {
		if(getEstado().equals("Pendiente")){
			setFechaEntrega(fechaEntrega);
			for(DetallePrestamo detallePrestamo : listaDetallesPrestamo){
				valorPrestamo += calcularValorPrestamo(detallePrestamo.getObjeto(), detallePrestamo.getUnidadesPrestadas(), getFechaPrestamo(), getFechaEntrega());
				detallePrestamo.aumentarExistencias(detallePrestamo.getUnidadesPrestadas());;
			}
			setEstado("Entregado");
			setValorPrestamo(valorPrestamo);
			return true;
		}else{
			throw new PrestamoYaEntregadoException("El prestamo ya ha sido entregado");
		}
	}

	public boolean verificarObjetoPrestado(String codigo) {
		for(DetallePrestamo detallePrestamo : listaDetallesPrestamo){
			if(detallePrestamo.getObjeto().getCodigo().equals(codigo)){
				return true;
			}
		}
		return false;
	}

	public Objeto obtenerObjetosPrestados() {
		for(DetallePrestamo detallePrestamo : listaDetallesPrestamo){
			return detallePrestamo.getObjeto();
		}
		return null;
	}

	public void actualizarDetallesPrestamo(String estadoActual, String estado, Objeto objeto,
			int unidadesPrestadas) throws FechasException, ObjetoYaRegistradoException {

		for(int i=0; i<listaDetallesPrestamo.size(); i++){
			if(listaDetallesPrestamo.get(i).getObjeto() == objeto){
				listaDetallesPrestamo.get(i).actualizarDetallePrestamo(estadoActual, estado, unidadesPrestadas);
			}else{
				agregarObjeto(objeto, unidadesPrestadas);
				if(estadoActual.equals("Entregado") && estado.equals("Pendiente")){
					listaDetallesPrestamo.get(i).disminuirExistencias(unidadesPrestadas);
				}
				if(estadoActual.equals("Pendiente") && estado.equals("Entregado")){
					listaDetallesPrestamo.get(i).aumentarExistencias(unidadesPrestadas);
				}
			}
		}
	}

}
