import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Saab95Test {
	private final Saab95 saab95 = new Saab95();

	@Test
	public void testTurboOnInitializedAsFalse(){
		assertFalse(saab95.getTurboOn());
	}

	@Test
	public void testSetTurboOn(){
		saab95.setTurboOn();
		assertTrue(saab95.getTurboOn());
	}
	@Test
	public void testSetTurboOff(){
		saab95.setTurboOn();
		saab95.setTurboOff();
		assertFalse(saab95.getTurboOn());
	}

	@Test
	public void testSpeedFactorWithNoTurbo(){
		saab95.setTurboOff();
		assertEquals(saab95.getEnginePower()*0.01*1, saab95.speedFactor());
	}

	@Test
	public void testSpeedFactorWithTurbo(){
		saab95.setTurboOn();
		assertEquals(saab95.getEnginePower()*0.01*1.3, saab95.speedFactor());
	}
}
