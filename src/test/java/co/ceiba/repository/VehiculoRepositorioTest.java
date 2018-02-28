package co.ceiba.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import co.ceiba.TestDataBuilder.VehiculoTestDataBuilder;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.EstacionamientoServiceImpl;
import co.ceiba.service.VehiculoService;
import co.ceiba.service.VehiculoServiceImpl;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VehiculoRepositorioTest {
	
	static final int TIPO_VEHICULO_CARRO = 1;
	static final int TIPO_VEHICULO_MOTO = 2;
	
	static final int ESTADO_VEHICULO_ACTIVO = 1;
	static final int TIPO_VEHICULO_INACTIVO = 0;
	
//	@TestConfiguration
//	static class VehiculoServiceImplTestContextConfiguration {
//
//		@Bean
//		public VehiculoService vehiculoService() {
//			return new VehiculoServiceImpl();
//		}
//	}

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private VehiculoRepository VehiculoRepository;
//	
//	@Before 
//	public void setUp() {
//		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withPlaca("ABC123").build();
//		vehiculoBuild.setIdVehiculo(1);
//		
//		Mockito.when(VehiculoRepository.findOne(vehiculoBuild.getIdVehiculo())).thenReturn(vehiculoBuild);
//		
//	}
	
//	@Test
//	public void buscarVehiculosPorIdVehiculo() {
//		// Arrange
//		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withPlaca("ABC123").build();
//		vehiculoBuild.setIdVehiculo(1);
//		
//		//act
//		Vehiculo vehiculoRec = VehiculoRepository.findOne(vehiculoBuild.getIdVehiculo());     
//		// Assert
//		Assert.assertEquals(vehiculoBuild, vehiculoRec);
//	}

	@Test
	public void buscarVehiculosPorPlaca() {
		// Arrange
		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withPlaca("ABC123").build();
		entityManager.persist(vehiculoBuild);
		entityManager.flush();
		
		//act
		Vehiculo vehiculoRec = VehiculoRepository.findByPlaca(vehiculoBuild.getPlaca());     

		
		// Assert
		Assert.assertEquals(vehiculoBuild, vehiculoRec);
	}
	
	
	@Test
	public void buscarVehiculosPorTipoVehiculoYEstado() {
		// Arrange
		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withTipoVehiculo(TIPO_VEHICULO_CARRO).withEstado(ESTADO_VEHICULO_ACTIVO).build();
		entityManager.persist(vehiculoBuild);
		entityManager.flush();

		List<Vehiculo> VehiculoRec = VehiculoRepository.findByTipoVehiculoAndEstado(vehiculoBuild.getTipoVehiculo(),vehiculoBuild.getEstado());

		// Assert
		Assert.assertNotNull(VehiculoRec);
	}
	
	@Test
	public void buscarVehiculosPorEstado() {
		// Arrange
		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withTipoVehiculo(TIPO_VEHICULO_CARRO).withEstado(ESTADO_VEHICULO_ACTIVO).build();
		entityManager.persist(vehiculoBuild);
		entityManager.flush();

		List<Vehiculo> VehiculoRec = VehiculoRepository.findByEstado(vehiculoBuild.getEstado());

		// Assert
		Assert.assertNotNull(VehiculoRec);
	}
	
	
	
	
	

}
