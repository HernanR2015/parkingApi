package co.ceiba.process;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class ReglasParqueaderoProcessTest {
	
		
	@Test
	public void validarCupoTipoVehiculo() {
		ReglasParqueaderoProcess reglas = new ReglasParqueaderoProcess();
		boolean cupo = reglas.validarCupoTipoVehiculo(19, 1);
		
		Assert.assertTrue(cupo);
	}
	
	@Test
	public void validarPlaca() {
		ReglasParqueaderoProcess reglas = new ReglasParqueaderoProcess();
		boolean placaValida = reglas.validarPlaca("bcd456");
		
		Assert.assertTrue(placaValida);
	}
	

	
	

}
