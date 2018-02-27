package co.ceiba.web;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import co.ceiba.process.ParqueaderoProcess;
import co.ceiba.service.TipoVehiculoService;

@RestController
@RequestMapping("/tipoVehiculo")
public class TipoVehiculoController {

	@Autowired
	private TipoVehiculoService tipoVehiculoService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/tiposVehiculos")
	public String listarTiposVehiculo() throws JSONException {
		ParqueaderoProcess parqueaderoProcess = new ParqueaderoProcess();
		return parqueaderoProcess.listarTiposVehiculos (tipoVehiculoService);
		
	}
	
//	@CrossOrigin(origins = "*")
//	@RequestMapping(method = RequestMethod.POST, value = "/guardar-tipovehiculo", produces = { "application/json" })
//	public String guardarTipoVehiculo(@RequestBody TipoVehiculo tipoVehiculo) throws JSONException {
//		ParqueaderoProcess parqueaderoProcess = new ParqueaderoProcess();
//		return parqueaderoProcess.agregarTipoVehiculo(tipoVehiculo, tipoVehiculoService); 
//
//	}
}
