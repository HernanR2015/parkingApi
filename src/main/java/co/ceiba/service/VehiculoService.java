package co.ceiba.service;

import java.util.List;

import co.ceiba.model.Vehiculo;


public interface VehiculoService {
	
//	List<Vehiculo> listAllVehiculo();
//	
//	List<Vehiculo> getVehiculoByTipoVehiculo(int tipoVehiculo);
	
	List<Vehiculo> getByEstado(int estado);
	
	List<Vehiculo> getByTipoVehiculoAndEstado(int tipoVehiculo, int estado);
	
	Vehiculo getVehiculoById(int id);

	Vehiculo saveVehiculo(Vehiculo vehiculo);
	
	Vehiculo getVehiculoByPlaca(String placa);
	
	void deleteVehiculo(int id);

}
