package co.ceiba.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.model.Vehiculo;
import co.ceiba.service.VehiculoService;

@RestController
@RequestMapping("/Vehiculo")
public class VehiculoController {

	@Autowired
	private VehiculoService vehiculoService;

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/Vehiculos")
	public List<Vehiculo> listarVehiculosActivos()  {
		return vehiculoService.getByEstado(1);	
	}
	


}
