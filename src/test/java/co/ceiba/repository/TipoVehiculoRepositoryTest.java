package co.ceiba.repository;

import java.util.List;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;




import co.ceiba.TestDataBuilder.TipoVehiculoTestDataBuilder;
import co.ceiba.model.TipoVehiculo;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TipoVehiculoRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TipoVehiculoRepository tipoVehiculoRepository;
	
		
	@Test
	public void buscarTiposVehiculos() {
		//Arrange
		TipoVehiculo tvehiculoBuild = new TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("camion").build();
		entityManager.persist(tvehiculoBuild);
		entityManager.flush();
		// Act
		List<TipoVehiculo> tipoVehiculoRec = (List<TipoVehiculo>) tipoVehiculoRepository.findAll();
		System.out.println("lista --->"+tipoVehiculoRec.size());
		boolean flag= false;
		if (tipoVehiculoRec.size()>0) {
			flag= true;
		}
		// Assert
		Assert.assertTrue(flag);
	}
	
	@Test
	public void buscarTiposVehiculosPorNombre() {
		//Arrange
		TipoVehiculo tvehiculoBuild = new TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("camion").build();
		entityManager.persist(tvehiculoBuild);
		entityManager.flush();
		// Act
		TipoVehiculo tipoVehiculoRec = tipoVehiculoRepository.findByNombreTipoVehiculo(tvehiculoBuild.getNombreTipoVehiculo());
		
		boolean flag= false;
		if (tipoVehiculoRec.getNombreTipoVehiculo().equals(tvehiculoBuild.getNombreTipoVehiculo())) {
			flag= true;
		}
		// Assert
		Assert.assertTrue(flag);
	}
	
	
}
