package co.ceiba.process;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.ceiba.model.Estacionamiento;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.VehiculoService;

public class RegistrarIngresoProcess {

	static final int ESTADO_ACTIVO = 1;
	static final String MESSAGE = "Message";
	static final String STATE = "state";
	static final int STATE_OK = 00;
	static final int STATE_BAD = 99;


	public Map<Object, Object> registrarIngresoProcess(EstacionamientoService estacionamientoService,
			VehiculoService vehiculoService, Vehiculo vehiculo, Estacionamiento estacionamiento) {

		Map<Object, Object> response = new HashMap<>();
		Estacionamiento estacionamientoRegistrado = new Estacionamiento();
		ReglasParqueaderoProcess reglas = new ReglasParqueaderoProcess();

		try {
			List<Vehiculo> vehiculosActivos = vehiculoService.getByTipoVehiculoAndEstado(vehiculo.getTipoVehiculo(),
					ESTADO_ACTIVO);
			boolean disponibilidad = reglas.validarCupoTipoVehiculo(vehiculosActivos.size(),
					vehiculo.getTipoVehiculo());
			if (disponibilidad) {
				boolean validarPlaca = reglas.validarPlaca(vehiculo.getPlaca());
				if (validarPlaca) {
					int idVehiculoRegistrado = validarVehiculoRegistrado(vehiculoService, vehiculo);
					if (idVehiculoRegistrado > 0) {
						vehiculo.setIdVehiculo(idVehiculoRegistrado);
					}
					vehiculo.setEstado(ESTADO_ACTIVO);
					Vehiculo vehiculoIngresado = vehiculoService.saveVehiculo(vehiculo);
					estacionamiento.setIdVehiculo(vehiculoIngresado.getIdVehiculo());
					Date fechaIngreso = new Date();
					estacionamiento.setFechaIngreso(fechaIngreso);
					Estacionamiento estacionamientoAsignado = estacionamientoService.saveEstacionamiento(estacionamiento);
					estacionamientoRegistrado = estacionamientoAsignado;
					response.put("IdEstacionamiento", estacionamientoRegistrado.getIdEstacionamiento());
					response.put("FechaIngreso", estacionamientoRegistrado.getFechaIngreso());
					response.put("PlacaVehiculo", vehiculo.getPlaca());
					response.put(STATE, STATE_OK);
					response.put(MESSAGE, "Vehiculo registrado exitosamente");

				}else {
					response.put(STATE, STATE_BAD);
					response.put(MESSAGE, "No esta Autorizado a ingresar al estacionamiento");
				}
			}else {
				response.put(STATE, STATE_BAD);
				response.put(MESSAGE, "No hay cupos disponibles en el estacionamiento");
				
			}
			return response;
		} catch (Exception excepcion) {
			response.put(STATE, STATE_BAD);
			response.put(MESSAGE, "Error realizando proceso de registro,verifique los datos e intente de nuevo");
			return response;
		}
	}

	private int validarVehiculoRegistrado(VehiculoService vehiculoService, Vehiculo vehiculo) {
		Vehiculo vehiculoRegistrado = vehiculoService.getVehiculoByPlaca(vehiculo.getPlaca());
		if (vehiculoRegistrado != null && vehiculoRegistrado.getIdVehiculo() > 0)
			return vehiculoRegistrado.getIdVehiculo();
		else
			return 0;
	}
}
