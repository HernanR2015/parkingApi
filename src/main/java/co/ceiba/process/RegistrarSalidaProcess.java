package co.ceiba.process;

import java.util.Date;

import co.ceiba.model.Estacionamiento;
import co.ceiba.model.TipoVehiculo;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.TipoVehiculoService;
import co.ceiba.service.VehiculoService;

public class RegistrarSalidaProcess {

	static final int ESTADO_ACTIVO = 1;
	static final int NUEVE_HORAS = 32400;
	static final int HORA = 3600;
	static final int DIA = 86400;
	static final int TIPO_VEHICULO_MOTO = 2;
	static final int COBRO_ADICIONAL_CILINDRAJE =2000;

	public Estacionamiento registrarSalidaProcess(TipoVehiculoService tipoVehiculoService,VehiculoService vehiculoService, EstacionamientoService estacionamientoService, String placa) {
		Date fechaSalida = new Date();
		Estacionamiento estacionamientoCobrado  = new Estacionamiento ();
		Vehiculo vehiculoRegistrado = vehiculoService.getVehiculoByPlaca(placa);
		if (vehiculoRegistrado != null && vehiculoRegistrado.getIdVehiculo() > 0) {
			Estacionamiento estacionamientoAsignado = estacionamientoService.getEstacionamientoByIdVehiculo(vehiculoRegistrado.getIdVehiculo());
			if (estacionamientoAsignado != null && estacionamientoAsignado.getIdEstacionamiento() > 0) {
				TipoVehiculo tipoVehiculo = tipoVehiculoService.getTipoVehiculoById(vehiculoRegistrado.getTipoVehiculo());
				double valorTiempo = calcularCobro(estacionamientoAsignado, tipoVehiculo.getValorHora(),tipoVehiculo.getValorDia(),fechaSalida);
				int cilindraje = Integer.parseInt(vehiculoRegistrado.getCilindraje());
				if (vehiculoRegistrado.getTipoVehiculo() == TIPO_VEHICULO_MOTO && cilindraje> 500) {
					valorTiempo =valorTiempo + COBRO_ADICIONAL_CILINDRAJE;
				}
				estacionamientoAsignado.setValor(valorTiempo);
				estacionamientoAsignado.setFechaSalida(fechaSalida);
				estacionamientoCobrado = estacionamientoService.saveEstacionamiento(estacionamientoAsignado);
			}
		}
	return estacionamientoCobrado;
	}

	public double calcularCobro(Estacionamiento estacionamiento, double valorHora, double valorDia ,Date fechaSalida) {
		
		double valorCobro = 0;
		int dias = 0;
		int horas = 0;
		System.out.println("fecha salida :"+fechaSalida.getTime());
		System.out.println("fecha ingreso :"+estacionamiento.getFechaIngreso().getTime());
		int tiempoTranscurrido = (int) ((fechaSalida.getTime() - estacionamiento.getFechaIngreso().getTime()) / 1000);

		if (tiempoTranscurrido >= NUEVE_HORAS && tiempoTranscurrido <= DIA) {
			valorCobro = valorDia;
		} else if (tiempoTranscurrido > DIA) {
			dias = tiempoTranscurrido / DIA;
			tiempoTranscurrido = tiempoTranscurrido - (DIA * dias);
			horas = tiempoTranscurrido / HORA;
		} else {
			System.out.println("entra menos de 9 horas");
			System.out.println("tiempo transcurrido : "+tiempoTranscurrido);
			horas = tiempoTranscurrido / HORA;
			System.out.println("horas  "+horas );
		}

		if (valorCobro == 0) {
			valorCobro = (valorDia * dias) + (valorHora * horas);
		}
		return valorCobro;
	}
}
