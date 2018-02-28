package co.ceiba.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import co.ceiba.TestDataBuilder.TipoVehiculoTestDataBuilder;
import co.ceiba.model.TipoVehiculo;
import co.ceiba.repository.TipoVehiculoRepository;

@RunWith(SpringRunner.class)
public class TipoVehiculoServiceImplTest {

	@TestConfiguration
	static class TipoVehiculoServiceImplTestContextConfiguration {

		@Bean
		public TipoVehiculoService tipovehiculoService() {
			return new TipoVehiculoServiceImpl();
		}
	}
	
	@Autowired
	private TipoVehiculoService tipoVehiculoService;

	@MockBean
	private TipoVehiculoRepository tipoVehiculoRepository;

	@Before
	public void setUp() {
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("Camion").build();
		List<TipoVehiculo> listaTipoVehiculo = new ArrayList<>();
		listaTipoVehiculo.add(tipoVehiculo);
		Mockito.when(tipoVehiculoRepository.findByNombreTipoVehiculo(tipoVehiculo.getNombreTipoVehiculo())).thenReturn(tipoVehiculo);
		Mockito.when(tipoVehiculoRepository.findAll()).thenReturn(listaTipoVehiculo);
		Mockito.when(tipoVehiculoRepository.findOne(tipoVehiculo.getIdTipoVehiculo())).thenReturn(tipoVehiculo);

		

	}

	@Test
	public void listAllTiposVehiculo() {
		List<TipoVehiculo> listaTipoVehiculo = (List<TipoVehiculo>) tipoVehiculoService.listAllTiposVehiculo();
		assertThat(listaTipoVehiculo).size().isNotZero();
	}

	@Test
	public void getTipoVehiculoById() {
		TipoVehiculo tipoVehiculoBuild = new TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("Camion").build();
		TipoVehiculo tipoVehiculoRec = tipoVehiculoService.getTipoVehiculoById(tipoVehiculoBuild.getIdTipoVehiculo());
		System.out.println("id vehiculo construido: "+tipoVehiculoBuild.getIdTipoVehiculo());
		System.out.println("id vehiculo recuperado: "+tipoVehiculoRec.getIdTipoVehiculo());
		assertThat(tipoVehiculoRec.getIdTipoVehiculo()).isEqualTo(tipoVehiculoBuild.getIdTipoVehiculo());
	}

	@Test
	public void getTipoVehiculoByNombre() {
		TipoVehiculo tipoVehiculo = tipoVehiculoService.getTipoVehiculoByNombre("Camion");
		assertThat(tipoVehiculo.getNombreTipoVehiculo()).isEqualTo("Camion");
	}

//	@Test
//	public void saveTipoVehiculo() {
//		// Arrange
//		TipoVehiculo vehiculo = new TipoVehiculoTestDataBuilder().build();
//		Mockito.when(tipoVehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
//		// Act
//		TipoVehiculo vehiculoGuardado = new TipoVehiculo();
//		vehiculoGuardado = tipoVehiculoService.saveTipoVehiculo(vehiculo);
//		// Assert
//		Assert.assertNotNull(vehiculoGuardado);
//	}

}
