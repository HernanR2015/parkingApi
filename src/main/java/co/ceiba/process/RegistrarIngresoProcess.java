package co.ceiba.process;


import java.util.List;

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
			List<Vehiculo> vehiculosActivos = vehiculoService.getByTipoVehiculoAndEstado(vehiculo.getTipoVehiculo(), ESTADO_ACTIVO);
			System.out.println("obtiene vehiculos Activos --"+vehiculosActivos.size());
			boolean disponibilidad = reglas.validarCupoTipoVehiculo(vehiculosActivos.size(), vehiculo.getTipoVehiculo());
			System.out.println("valida disponibilidad ---"+disponibilidad);
			if (disponibilidad) {
				boolean validarPlaca = reglas.validarPlaca(vehiculo.getPlaca());
				System.out.println("valida placa ---"+disponibilidad);

				if (validarPlaca) {
					vehiculo.setEstado(ESTADO_ACTIVO);
					System.out.println(" asigna estado vehiculo ---"+disponibilidad);
					System.out.println(vehiculo.getPlaca());
					Vehiculo vehiculoIngresado = vehiculoService.saveVehiculo(vehiculo);
					System.out.println("guarda vehiculo ---"+vehiculoIngresado.getIdVehiculo());

					Estacionamiento estacionamiento = new Estacionamiento();
					estacionamiento.setIdVehiculo(vehiculoIngresado.getIdVehiculo());
					Estacionamiento estacionamientoAsignado = estacionamientoService.saveEstacionamiento(estacionamiento);
					System.out.println("guarda estacionamiento ---"+vehiculoIngresado.getIdVehiculo());
					estacionamientoRegistrado = estacionamientoAsignado;
				}
			}
			return estacionamientoRegistrado;
		} catch (Exception excepcion) {
			System.out.println("error realizando ingreso_ ++++"+excepcion);
			return null;
		}
	}
}
