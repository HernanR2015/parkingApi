package co.ceiba.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import co.ceiba.TestDataBuilder.VehiculoTestDataBuilder;
import co.ceiba.model.Vehiculo;
import co.ceiba.repository.VehiculoRepository;
import co.ceiba.service.VehiculoService;
import co.ceiba.service.VehiculoServiceImpl;
import co.ceiba.web.VehiculoController;

@RunWith(SpringRunner.class)
@WebMvcTest(VehiculoController.class)
public class VehiculoControllerTest {
	
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
	private VehiculoService vehiculoService;
	
	@MockBean
	private VehiculoRepository vehiculoRepository;
	
	@Before
	public void setup() throws Exception {
		this.mockMvcc = MockMvcBuilders.webAppContextSetup(this.wacc).build();
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca("XYZ123").withEstado(1).withCilindraje("1200").withTipoVehiculo(1).build();// with   withNombreTipoVehiculo("carro").build();
		List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		listaVehiculos.add(vehiculo);
		Mockito.when(vehiculoService.getByEstado(1)).thenReturn(listaVehiculos);
	}
	
	@Test
	public void obtenerVehiculosActivos() throws Exception {
		// Arrange
		final String url = "/Vehiculo/Vehiculos";
		// Act
		MvcResult mvcResult = this.mockMvcc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn();
		// Assert
		Assert.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	
	
	
	
	

}
