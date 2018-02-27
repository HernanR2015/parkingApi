//package co.ceiba.Controller;
//
//import org.aspectj.lang.annotation.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import co.ceiba.process.ParqueaderoProcess;
//import co.ceiba.repository.TipoVehiculoRepository;
//import co.ceiba.service.TipoVehiculoService;
//import co.ceiba.service.TipoVehiculoServiceImpl;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(TipoVehiculoControllerTest.class)
//public class TipoVehiculoControllerTest {
//	
//	@TestConfiguration
//	static class VehiculoServiceImplTestContextConfiguration {
//
//		@Bean
//		public TipoVehiculoService tipoVehiculoService() {
//			return new TipoVehiculoServiceImpl();
//		}
//	}
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired
//	private WebApplicationContext wac;
//	
//	@Autowired
//	private TipoVehiculoService tipoVehiculoService;
//	
//	@MockBean
//	private ParqueaderoProcess parqueaderoProcess;
//	
//	@MockBean
//	private TipoVehiculoRepository tipoVehiculoRepository;
//	
//	@Before
//	public void setup() throws Exception {
//		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
////		TipoVehiculo tvehiculo = new TipoVehiculoTestDataBuilder().build();
////		List<TipoVehiculo> listaTiposVehiculos = new ArrayList<TipoVehiculo>();
////		listaTiposVehiculos.add(tvehiculo);
////		Mockito.when(tipoVehiculoRepository.findAll()).thenReturn(listaTiposVehiculos);
////		Mockito.when(tipoVehiculoRepository.findBytipoVehiculoAndActivo("carro", 1)).thenReturn(listaVehiculos);
//	}
//	
////	@Test
////	public void listarTiposVehiculo () throws Exception{
////		
////		
////		
////	}
//	
//	
//
//}
