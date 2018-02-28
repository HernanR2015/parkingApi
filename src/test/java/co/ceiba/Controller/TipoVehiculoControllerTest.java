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
import co.ceiba.TestDataBuilder.TipoVehiculoTestDataBuilder;
import co.ceiba.model.TipoVehiculo;
import co.ceiba.repository.TipoVehiculoRepository;
import co.ceiba.service.TipoVehiculoService;
import co.ceiba.service.TipoVehiculoServiceImpl;
import co.ceiba.web.TipoVehiculoController;


@RunWith(SpringRunner.class)
@WebMvcTest(TipoVehiculoController.class)
public class TipoVehiculoControllerTest {
	
	@TestConfiguration
	static class TipoVehiculoServiceImplTestContextConfiguration {

		@Bean
		public TipoVehiculoService vehiculoService() {
			return new TipoVehiculoServiceImpl();
		}
	}
	
	@Autowired
	private MockMvc mockMvcc;
	
	@Autowired
	private WebApplicationContext wacc;
	
	@Autowired
	private TipoVehiculoService vehiculoService;
	
	@MockBean
	private TipoVehiculoRepository tipoVehiculoRepository;
	
	@Before
	public void setup() throws Exception {
		this.mockMvcc = MockMvcBuilders.webAppContextSetup(this.wacc).build();
		TipoVehiculo vehiculo = new TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("carro").build();
		List<TipoVehiculo> listaTiposVehiculos = new ArrayList<TipoVehiculo>();
		listaTiposVehiculos.add(vehiculo);
		Mockito.when(vehiculoService.listAllTiposVehiculo()).thenReturn(listaTiposVehiculos);
	}
	
	@Test
	public void obtenerTiposVehiculos() throws Exception {
		// Arrange
		final String url = "/tipoVehiculo/tiposVehiculos";
		// Act
		MvcResult mvcResult = this.mockMvcc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn();
		// Assert
		Assert.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	
	
}
