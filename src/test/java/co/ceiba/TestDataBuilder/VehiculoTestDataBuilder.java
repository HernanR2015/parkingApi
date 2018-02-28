package co.ceiba.TestDataBuilder;

import co.ceiba.model.Vehiculo;

public class VehiculoTestDataBuilder {
	
	int idVehiculo;
	String placa;
	String cilindraje;
	int estado;
	int tipoVehiculo;
	
	public VehiculoTestDataBuilder() {
//		this.idVehiculo= 1;
		this.placa = "ABC123";
		this.cilindraje = "1200";
		this.estado=1;
		this.tipoVehiculo = 1;
	}
	
//	public VehiculoTestDataBuilder withIdVehiculo(int idVehiculo) {
//		this.idVehiculo = idVehiculo;
//		return this;
//	}
	
	public VehiculoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	public VehiculoTestDataBuilder withCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public VehiculoTestDataBuilder withEstado(int estado) {
		this.estado = estado;
		return this;
	}
	
	public VehiculoTestDataBuilder withTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public Vehiculo build() {
		return new Vehiculo (this.idVehiculo, this.placa, this.cilindraje, this.estado, this.tipoVehiculo);
	}
	
	

}
