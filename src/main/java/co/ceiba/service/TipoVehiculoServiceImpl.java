package co.ceiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.model.TipoVehiculo;
import co.ceiba.repository.TipoVehiculoRepository;

@Service
public class TipoVehiculoServiceImpl implements TipoVehiculoService {
	
	@Autowired
	TipoVehiculoRepository tipoVehiculoRepository;

	@Override
	public List<TipoVehiculo> listAllTiposVehiculo() {
		return (List<TipoVehiculo>) tipoVehiculoRepository.findAll();
		
	}

	@Override
	public TipoVehiculo getTipoVehiculoById(int idTipoVehiculo) {
		return tipoVehiculoRepository.findOne(idTipoVehiculo);
	}

	@Override
	public TipoVehiculo getTipoVehiculoByNombre(String nombreTipoVehiculo) {
		return tipoVehiculoRepository.findByNombreTipoVehiculo(nombreTipoVehiculo);
	}

	@Override
	public TipoVehiculo saveTipoVehiculo(TipoVehiculo tipoVehiculo) {
		return tipoVehiculoRepository.save(tipoVehiculo);
	}

	@Override
	public void deleteTipoVehiculo(int idTipoVehiculo) {
		tipoVehiculoRepository.delete(idTipoVehiculo);	
	}
}
