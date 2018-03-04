package co.ceiba.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.ceiba.TestDataBuilder.EstacionamientoTestDataBuilder;
import co.ceiba.TestDataBuilder.VehiculoTestDataBuilder;
import co.ceiba.model.Estacionamiento;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.EstacionamientoServiceImpl;
import co.ceiba.service.TipoVehiculoService;
import co.ceiba.service.TipoVehiculoServiceImpl;
import co.ceiba.service.VehiculoService;
import co.ceiba.service.VehiculoServiceImpl;
import co.ceiba.web.EstacionamientoController;


@RunWith(SpringRunner.class)
@WebMvcTest(EstacionamientoController.class)

public class EstacionamientoControllerTest {
	
	@TestConfiguration
	static class EstacionamientoServiceImplTestContextConfiguration {

		@Bean
		public EstacionamientoService estacionamientoService() {
			return new EstacionamientoServiceImpl();
		}
	}
	
	@TestConfiguration
	static class TipoVehiculoServiceImplTestContextConfiguration {

		@Bean
		public TipoVehiculoService tipoVehiculoService() {
			return new TipoVehiculoServiceImpl();
		}
	}
	
	@TestConfiguration
	static class VehiculoServiceImplTestContextConfiguration {

		@Bean
		public VehiculoService vehiculoService() {
			return new VehiculoServiceImpl();
		}
	}
	
	@Autowired
	private MockMvc mockMvcc;
	
	@Autowired
	private WebApplicationContext wacc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	private TipoVehiculoService tipoVehiculoService;
	
	@MockBean
	private VehiculoService vehiculoService;
	
	@MockBean
	private EstacionamientoService estacionamientoService;
	
	@Before
	public void setup() throws Exception {
		this.mockMvcc = MockMvcBuilders.webAppContextSetup(this.wacc).build();
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca("QWE456").withCilindraje("1000").withTipoVehiculo(3).withEstado(0).build();
		List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		listaVehiculos.add(vehiculo);
		vehiculo.setIdVehiculo(6);
	
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withIdVehiculo(6).build();
		estacionamiento.setIdEstacionamiento(9);
		
		Mockito.when(vehiculoService.getByTipoVehiculoAndEstado(3, 1)).thenReturn(listaVehiculos);
		Mockito.when(vehiculoService.getVehiculoByPlaca("QWE456")).thenReturn(vehiculo);
		Mockito.when(vehiculoService.saveVehiculo(vehiculo)).thenReturn(vehiculo);
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamiento)).thenReturn(estacionamiento);
		
		Mockito.when(vehiculoService.getVehiculoByPlaca("QWE456")).thenReturn(vehiculo);
		Mockito.when(estacionamientoService.getEstacionamientoByIdVehiculo(6)).thenReturn(estacionamiento);
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamiento)).thenReturn(estacionamiento);
	}
	
	@Test
	public void asignarEstacionamientoControllerTest() throws Exception {
		// Arrange
		final String url = "/Estacionamiento/asignarEstacionamiento";
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca("QWE456").withCilindraje("1000").withTipoVehiculo(3).withEstado(0).build();
		List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		listaVehiculos.add(vehiculo);
		vehiculo.setIdVehiculo(6);
	
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withIdVehiculo(6).build();
		estacionamiento.setIdEstacionamiento(9);
		
		
		Mockito.when(vehiculoService.getByTipoVehiculoAndEstado(3, 1)).thenReturn(listaVehiculos);
		Mockito.when(vehiculoService.getVehiculoByPlaca("QWE456")).thenReturn(vehiculo);
		Mockito.when(vehiculoService.saveVehiculo(vehiculo)).thenReturn(vehiculo);
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamiento)).thenReturn(estacionamiento);
		
		// Act
		MvcResult mvcResult = (MvcResult) this.mockMvcc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo))).andDo(print())
				.andExpect(status().isOk()).andReturn();
		// Assert
		Assert.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	
	
//	@Test
//	public void liberarEstacionamientoControllerTest() throws Exception {
//		// Arrange
//		final String url = "Estacionamiento/liberarEstacionamiento";
//		String placa = "QWE456";
//		
//		// Act
//		MvcResult mvcResult = (MvcResult) this.mockMvcc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(placa)).andDo(print())
//				.andExpect(status().isOk()).andReturn();		// Assert
//		Assert.assertEquals(200, mvcResult.getResponse().getStatus());
//	}
	
	
}