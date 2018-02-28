package co.ceiba.TestDataBuilder;

import java.util.Date;

import co.ceiba.model.Estacionamiento;

public class EstacionamientoTestDataBuilder {
	
	int idEstacionamiento;
	double valor;
	Date fechaIngreso;
	Date fechaRetiro;
	int idVehiculo;
	
	public EstacionamientoTestDataBuilder () {
		Date date = new Date();
//		this.idEstacionamiento =1;
		this.valor = 7800;
		this.fechaIngreso = date;
		this.fechaRetiro = date;
		this.idVehiculo =1;
	}
	
//	public EstacionamientoTestDataBuilder withIdEstacionamiento(int idEstacionamiento) {
//		this.idEstacionamiento = idEstacionamiento;
//		return this;
//	}
	
	public EstacionamientoTestDataBuilder withValor(double valor) {
		this.valor = valor;
		return this;
	}
	
	public EstacionamientoTestDataBuilder withFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public EstacionamientoTestDataBuilder withFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
		return this;
	}
	
	public EstacionamientoTestDataBuilder withIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
		return this;
	}
	
	public Estacionamiento build() {
		return new Estacionamiento (this.idEstacionamiento, this.valor, this.fechaIngreso, this.fechaRetiro, this.idVehiculo);
	}

}
