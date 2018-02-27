package co.ceiba.process;


import co.ceiba.model.Estacionamiento;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.VehiculoService;

public class RegistrarIngresoProcess {

	static final int ESTADO_ACTIVO = 1;

	public Estacionamiento registrarIngresoProcess(EstacionamientoService estacionamientoService,
		VehiculoService vehiculoService, Vehiculo vehiculo) {
		Estacionamiento estacionamientoRegistrado = new Estacionamiento();
		ReglasParqueaderoProcess reglas = new ReglasParqueaderoProcess();
		try {
			ConsultasProcess consultaDisponibilidad = new ConsultasProcess();
			int vehiculosActivos = consultaDisponibilidad.consultarVehiculosActivosPorTipo(vehiculoService,
					vehiculo.getTipoVehiculo(), ESTADO_ACTIVO);
			boolean disponibilidad = reglas.validarCupoTipoVehiculo(vehiculosActivos, vehiculo.getTipoVehiculo());
			if (disponibilidad) {
				boolean validarPlaca = reglas.validarPlaca(vehiculo.getPlaca());
				if (validarPlaca) {
					vehiculo.setEstado(ESTADO_ACTIVO);
					Vehiculo vehiculoIngresado = vehiculoService.saveVehiculo(vehiculo);
					Estacionamiento estacionamiento = new Estacionamiento();
					estacionamiento.setIdVehiculo(vehiculoIngresado.getIdVehiculo());
					Estacionamiento estacionamientoAsignado = estacionamientoService
							.saveEstacionamiento(estacionamiento);
					estacionamientoRegistrado = estacionamientoAsignado;
				}
			}
			return estacionamientoRegistrado;
		} catch (Exception excepcion) {
			System.out.println("error realizando ingreso");
			return null;
		}
	}
}
