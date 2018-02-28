package co.ceiba.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import co.ceiba.model.TipoVehiculo;
import co.ceiba.service.TipoVehiculoService;

@RestController
@RequestMapping("/tipoVehiculo")
public class TipoVehiculoController {
	
	

	@Autowired
	private TipoVehiculoService tipoVehiculoService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/tiposVehiculos")
	public List<TipoVehiculo> listarTiposVehiculo() {
		return tipoVehiculoService.listAllTiposVehiculo();
		
	}
	

}
