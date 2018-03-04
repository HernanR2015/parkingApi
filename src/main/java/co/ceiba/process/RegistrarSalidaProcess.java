package co.ceiba.process;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import co.ceiba.model.Estacionamiento;
import co.ceiba.model.TipoVehiculo;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.TipoVehiculoService;
import co.ceiba.service.VehiculoService;

public class RegistrarSalidaProcess {

	static final int ESTADO_ACTIVO = 1;
	static final int ESTADO_INACTIVO = 0;
	static final int NUEVE_HORAS = 32400;
	static final int HORA = 3600;
	static final int DIA = 86400;
	static final int TIPO_VEHICULO_MOTO = 4;
	static final int COBRO_ADICIONAL_CILINDRAJE = 2000;
	static final int CILINDRAJE_COBRO_ADICIOAL = 500;
	
	static final String MESSAGE = "Message";
	static final String STATE = "state";
	static final int STATE_OK = 00;
	static final int STATE_BAD = 99;

	public Map<Object, Object> registrarSalidaProcess(TipoVehiculoService tipoVehiculoService,
			VehiculoService vehiculoService, EstacionamientoService estacionamientoService, String placa)
			throws ParseException {

		Map<Object, Object> response = new HashMap<>(); 
		Date fechaSalida = new Date();
		
		Vehiculo vehiculoRegistrado = vehiculoService.getVehiculoByPlaca(placa);
		

		if (vehiculoRegistrado != null && vehiculoRegistrado.getIdVehiculo() > 0) {
			
			if (vehiculoRegistrado.getEstado() == ESTADO_ACTIVO) {
				Estacionamiento estacionamientoAsignado = estacionamientoService
						.getEstacionamientoByIdVehiculo(vehiculoRegistrado.getIdVehiculo());
				if (estacionamientoAsignado != null && estacionamientoAsignado.getIdEstacionamiento() > 0) {
					TipoVehiculo tipoVehiculo = tipoVehiculoService
							.getTipoVehiculoById(vehiculoRegistrado.getTipoVehiculo());
					double valorTiempo = calcularCobro(estacionamientoAsignado.getFechaIngreso(), tipoVehiculo,
							vehiculoRegistrado.getCilindraje(), fechaSalida);
					vehiculoRegistrado.setEstado(0);
					vehiculoRegistrado = vehiculoService.saveVehiculo(vehiculoRegistrado);

					estacionamientoAsignado.setValor(valorTiempo);
					estacionamientoAsignado.setFechaSalida(fechaSalida);
					Estacionamiento estacionamientoCobrado = estacionamientoService.saveEstacionamiento(estacionamientoAsignado);
					if (estacionamientoCobrado != null && estacionamientoCobrado.getIdEstacionamiento()>0) {
						response.put("fechaIngreso", estacionamientoCobrado.getFechaIngreso());
						response.put("fechaSalida", estacionamientoCobrado.getFechaSalida());
						response.put("valor", estacionamientoCobrado.getValor());
						response.put("placaVehiculo", placa); 
						response.put(STATE, STATE_OK);
						response.put(MESSAGE, "Cobro realizado Exitosamente");
					}
				} else {
					response.put(STATE, STATE_BAD);
					response.put(MESSAGE, "el vehiculo no tiene un estacionamiento asignado");
				}
			} else {
				response.put(STATE, STATE_BAD);
				response.put(MESSAGE,"el vehiculo de placa: " + placa + " no esta actualmente en el estacionamiento");
			}

		} else {
			response.put(STATE, STATE_BAD);
			response.put(MESSAGE, "el vehiculo de placa: " + placa + " no esta registrado");
		}
		return response;
	}

	public double calcularCobro(Date fechaIngreso, TipoVehiculo tipoVehiculo, String cilindraje, Date fechaSalida)
			throws ParseException {

		double valorCobro = 0;
		int dias = 0;
		int horas = 0;
		
		int tiempoTranscurrido = (int) ((fechaSalida.getTime() - fechaIngreso.getTime()) / 1000);

		if (tiempoTranscurrido >= NUEVE_HORAS && tiempoTranscurrido <= DIA) {
			valorCobro = tipoVehiculo.getValorDia();
		} else if (tiempoTranscurrido > DIA) {
			dias = tiempoTranscurrido / DIA;
			tiempoTranscurrido = tiempoTranscurrido - (DIA * dias);
			horas = tiempoTranscurrido / HORA;
		} else {
			horas = tiempoTranscurrido / HORA;
		}

		if (valorCobro == 0) {
			valorCobro = (tipoVehiculo.getValorDia() * dias) + (tipoVehiculo.getValorHora() * horas);
		}
		int cilindrajeVehiulo = Integer.parseInt(cilindraje);
		if (tipoVehiculo.getIdTipoVehiculo()==4 && cilindrajeVehiulo > CILINDRAJE_COBRO_ADICIOAL) {
			valorCobro = valorCobro + COBRO_ADICIONAL_CILINDRAJE;
		}
		return valorCobro;
	}
}
