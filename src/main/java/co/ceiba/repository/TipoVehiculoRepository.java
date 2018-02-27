package co.ceiba.repository;

import org.springframework.data.repository.CrudRepository;
import co.ceiba.model.TipoVehiculo;

public interface TipoVehiculoRepository extends CrudRepository<TipoVehiculo, Integer> {
	
	TipoVehiculo findByNombreTipoVehiculo(String nombreTipoVehiculo);

}



