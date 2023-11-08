import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Volvo240Test {
	private final Volvo240 volvo240 = new Volvo240();
	@Test
	public void testTrimFactorInitializedAsOnePointTwentyfive(){
		assertEquals(volvo240.getTrimFactor(),1.25);
	}

	@Test
	public void testSpeedFactor(){
		assertEquals(volvo240.getEnginePower() * 0.01 * volvo240.getTrimFactor(), volvo240.speedFactor());
	}
}
