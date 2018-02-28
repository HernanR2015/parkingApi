package co.ceiba.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import co.ceiba.TestDataBuilder.VehiculoTestDataBuilder;
import co.ceiba.model.Vehiculo;
import co.ceiba.repository.VehiculoRepository;

@RunWith(SpringRunner.class)
public class VehiculoServiceImplTest {
	
	static final int TIPO_VEHICULO_CARRO = 1;
	static final int TIPO_VEHICULO_MOTO = 2;
	
	static final int ESTADO_VEHICULO_ACTIVO = 1;
	static final int TIPO_VEHICULO_INACTIVO = 0;
	
	@TestConfiguration
	static class VehiculoServiceImplTestContextConfiguration {

		@Bean
		public VehiculoService tipovehiculoService() {
			return new VehiculoServiceImpl();
		}
	}
	
	@Autowired
	private VehiculoService vehiculoService;

	@MockBean
	private VehiculoRepository vehiculoRepository;
	
	@Before
	public void setUp() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca("ABC123").withEstado(ESTADO_VEHICULO_ACTIVO).withTipoVehiculo(TIPO_VEHICULO_CARRO).build();  //   withNombre   TipoVehiculo("Camion").build();
		List<Vehiculo> listaVehiculosActivos = new ArrayList<>();
		listaVehiculosActivos.add(vehiculo);
		Mockito.when(vehiculoRepository.findByPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);
		Mockito.when(vehiculoRepository.findByEstado(vehiculo.getEstado())).thenReturn(listaVehiculosActivos);
		Mockito.when(vehiculoRepository.findByTipoVehiculoAndEstado(vehiculo.getTipoVehiculo(), vehiculo.getEstado())).thenReturn(listaVehiculosActivos);
	}
	
	@Test
	public void getByTipoVehiculoAndEstado() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca("ABC123").withEstado(ESTADO_VEHICULO_ACTIVO).withTipoVehiculo(TIPO_VEHICULO_CARRO).build();  //   withNombre   TipoVehiculo("Camion").build();
		List<Vehiculo> listaVehiculoActivos = (List<Vehiculo>) vehiculoService.getByTipoVehiculoAndEstado(vehiculo.getTipoVehiculo(), vehiculo.getEstado());
		assertThat(listaVehiculoActivos).size().isNotZero();
	}
	
	@Test
	public void getVehiculoByPlaca() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca("ABC123").build();  //   withNombre   TipoVehiculo("Camion").build();
		Vehiculo vehiculoRec =  vehiculoService.getVehiculoByPlaca(vehiculo.getPlaca());
		assertThat(vehiculoRec.getPlaca().equals(vehiculo.getPlaca()));
		}
	
	@Test
	public void getByEstado() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withEstado(ESTADO_VEHICULO_ACTIVO).build();  //   withNombre   TipoVehiculo("Camion").build();
		List<Vehiculo> listaVehiculoActivos = (List<Vehiculo>) vehiculoService.getByEstado(vehiculo.getEstado());
		assertThat(listaVehiculoActivos).size().isNotZero();
	}
	
	@Test
	public void saveVehiculo() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		Mockito.when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
		// Act
		Vehiculo vehiculoGuardado = new Vehiculo();
		vehiculoGuardado = vehiculoService.saveVehiculo(vehiculo);
		// Assert
		Assert.assertNotNull(vehiculoGuardado);
	}

	
	
	
	

}
