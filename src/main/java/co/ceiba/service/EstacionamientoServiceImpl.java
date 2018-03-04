package co.ceiba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.model.Estacionamiento;
import co.ceiba.repository.EstacionamientoRepository;

@Service
public class EstacionamientoServiceImpl implements EstacionamientoService {

	@Autowired
	EstacionamientoRepository estacionamientoRepository;
	
	@Override
	public Estacionamiento getEstacionamientoByIdVehiculo(int idVehiculo) {
		return estacionamientoRepository.findByIdVehiculo(idVehiculo);
	}

	@Override
	public Estacionamiento saveEstacionamiento(Estacionamiento estacionamiento) {
		return estacionamientoRepository.save(estacionamiento);
	}
}
