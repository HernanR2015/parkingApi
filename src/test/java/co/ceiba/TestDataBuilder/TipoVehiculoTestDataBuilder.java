package co.ceiba.TestDataBuilder;

import co.ceiba.model.TipoVehiculo;

public class TipoVehiculoTestDataBuilder {

	int idTipoVehiculo;
	String nombreTipoVehiculo;
	double valorDia;
	double valorHora;
	
	public TipoVehiculoTestDataBuilder() {
//		this.idTipoVehiculo = 4;
		this.nombreTipoVehiculo = "carro";
		this.valorDia = 8000;
		this.valorHora = 1000;
	}
	
	public TipoVehiculoTestDataBuilder withNombreTipoVehiculo(String nombreTipoVehiculo) {
		this.nombreTipoVehiculo = nombreTipoVehiculo;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder withValorDia(double valorDia) {
		this.valorDia = valorDia;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder withValorHora(double valorHora) {
		this.valorHora = valorHora;
		return this;
	}
	
	public TipoVehiculo build() {
		return new TipoVehiculo (this.idTipoVehiculo, this.nombreTipoVehiculo, this.valorDia, this.valorHora); //
	}
}
