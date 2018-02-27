package co.ceiba.service;

import java.util.List;

import co.ceiba.model.TipoVehiculo;

public interface TipoVehiculoService {
	
	List<TipoVehiculo> listAllTiposVehiculo();
	
	public TipoVehiculo getTipoVehiculoById(int idTipoVehiculo);
	
	public TipoVehiculo getTipoVehiculoByNombre(String nombreTipoVehiculo);
	
	TipoVehiculo saveTipoVehiculo(TipoVehiculo tipoVehiculo);
	
	void deleteTipoVehiculo(int idTipoVehiculo);

}
