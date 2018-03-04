package co.ceiba.process;

import java.util.Calendar;
import java.util.Date;

public class ReglasParqueaderoProcess {
	static final int TOTAL_CARROS = 20;
	static final int TOTAL_MOTOS = 10;

	static final int TIPO_VEHICULO_CARRO = 3;
	static final int TIPO_VEHICULO_MOTO = 4;

	static final String LETRA_INICIAL_PLACA = "A";

	public boolean validarCupoTipoVehiculo(int totalVehiculosActivos, int tipoVehiculo) {
		boolean disponible = false;

		if (tipoVehiculo == TIPO_VEHICULO_CARRO) {
			if (totalVehiculosActivos < TOTAL_CARROS) {
				disponible = true;
			}
		} else if (tipoVehiculo == TIPO_VEHICULO_MOTO) {
			if (totalVehiculosActivos < TOTAL_MOTOS) {
				disponible = true;
			} else {
				disponible = false;
			}
		}

		return disponible;
	}

	public boolean validarPlaca(String placa) {
		boolean placaValida = false;
		String letraInicial = placa.toUpperCase().substring(0, 1);
		if (letraInicial.equals(LETRA_INICIAL_PLACA)) {
			Calendar fechaActual = Calendar.getInstance();
			fechaActual.setTime(new Date());
			if (fechaActual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || fechaActual.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				placaValida = true;
			} else {
				placaValida =  false;
			}
		} else {
			placaValida = true;
		}
		return placaValida;
	}

}
