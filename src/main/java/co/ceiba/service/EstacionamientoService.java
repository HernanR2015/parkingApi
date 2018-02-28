package co.ceiba.service;

import co.ceiba.model.Estacionamiento;

public interface EstacionamientoService {
	
	Estacionamiento getEstacionamientoByIdVehiculo(int idVehiculo);
	
	Estacionamiento saveEstacionamiento (Estacionamiento estacionamiento);
	
//	void deleteEstacionamiento(int id);
	
	

}
