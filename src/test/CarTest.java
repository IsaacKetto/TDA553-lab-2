import org.junit.Before;

import java.awt.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {
	
	private Car car;
	private final double delta = 0.00001; 

	private class DummyCar extends Car {
		public DummyCar() {
			super(2, 100, Color.blue, "Dummy", 1000);
		}
	}

	@Before
	public void setUp() {
		car = new DummyCar();
	}

	@Test
	public void testNumberOfDoorsInitializedAsTwo() {
		assertEquals(2, car.getNrDoors()); 
	}

	@Test 
	public void testEnginePowerInitializedAs100() {
		assertEquals(100, car.getEnginePower(), delta); 
	}

	@Test
	public void testColorInitializedAsBlue() {
		assertEquals(Color.blue, car.getColor()); 
	}

	@Test
	public void testSetColorToBlack() {
		car.setColor(Color.black);
		assertEquals(Color.black, car.getColor());
	}

	@Test
	public void testModelNameInitializedAsDummy() {
		assertEquals("Dummy", car.getModelName()); 
	}

	@Test
	public void testWeightInitializedAs1000() {
		assertEquals(1000, car.getWeight(), delta); 
	}

	@Test 
	public void testPositionInitializedAsZero() {
		assertEquals(0, car.getPosition()[0], delta);
		assertEquals(0, car.getPosition()[1], delta);
	}

	@Test
	public void testDirectionInitializedAsNorth() {
		assertEquals(Car.Direction.NORTH, car.getDirection());
	}

	@Test 
	public void testCurrentSpeedInitializedAsZero() {
		assertEquals(0, car.getCurrentSpeed(), delta);
	}

	@Test
	public void testTurnLeftIsCorrectDirection() {
		Car.Direction[] expectedDirections = new Car.Direction[] {
			Car.Direction.NORTH,
			Car.Direction.WEST,
			Car.Direction.SOUTH,
			Car.Direction.EAST,
			Car.Direction.NORTH,
			Car.Direction.WEST,
		};
		for (int i = 0; i < 6; i++) {
			assertEquals(expectedDirections[i], car.getDirection());
			car.turnLeft();
		}
	}

	@Test
	public void testTurnRightIsCorrectDirection() {
		Car.Direction[] expectedDirections = new Car.Direction[] {
			Car.Direction.NORTH,
			Car.Direction.EAST,
			Car.Direction.SOUTH,
			Car.Direction.WEST,
			Car.Direction.NORTH,
			Car.Direction.EAST,
		};
		for (int i = 0; i < 6; i++) {
			assertEquals(expectedDirections[i], car.getDirection());
			car.turnRight();
		}
	}

	@Test
	public void testMoveNorth() {
		car.gas(0.5);
		car.move();
		assertEquals(car.getCurrentSpeed(), car.getPosition()[1], delta);
	}

	@Test
	public void testMoveWest() {
		car.turnLeft();
		car.gas(0.5);
		car.move();
		assertEquals(-car.getCurrentSpeed(), car.getPosition()[0], delta);
	}

	@Test
	public void testMoveSouth() {
		car.turnLeft();
		car.turnLeft();
		car.gas(0.5);
		car.move();
		assertEquals(-car.getCurrentSpeed(), car.getPosition()[1], delta);
	}

	@Test
	public void testMoveEast() {
		car.turnRight();
		car.gas(0.5);
		car.move();
		assertEquals(car.getCurrentSpeed(), car.getPosition()[0], delta);
	}

	@Test
	public void testStartEngineSetsSpeedToPointOne() {
		car.startEngine();
		assertEquals(0.1, car.getCurrentSpeed(), delta);
	}

	@Test
	public void testStopEngineSetsSpeedToZero() {
		car.startEngine();
		car.stopEngine();
		assertEquals(0, car.getCurrentSpeed(), delta);
	}

	@Test 
	public void testSpeedFactorReturnsEnginePowerTimesPointZeroOne() {
		double enginepower = car.getEnginePower();
		double expectedSpeedFactor = enginepower * 0.01;
		assertEquals(expectedSpeedFactor, car.speedFactor(), delta);
	}

	@Test
	public void testGasAmountLessThanZeroReturnsEarly() {
		double speed = car.getCurrentSpeed();
		car.gas(-1);
		assertEquals(speed, car.getCurrentSpeed(), delta);
	}

	@Test
	public void testGasAmountGreaterThanOneReturnsEarly() {
		double speed = car.getCurrentSpeed();
		car.gas(2);
		assertEquals(speed, car.getCurrentSpeed(), delta);
	}

	@Test
	public void testBrakeAmountLessThanZeroReturnsEarly() {
		double speed = car.getCurrentSpeed();
		car.gas(-1);
		assertEquals(speed, car.getCurrentSpeed(), delta);
	}

	@Test
	public void testBrakeAmountGreaterThanOneReturnsEarly() {
		double speed = car.getCurrentSpeed();
		car.gas(2);
		assertEquals(speed, car.getCurrentSpeed(), delta);
	}

	@Test
	public void testGasPointFiveShouldReturnSpeedfactorTimesPointFive() {
		car.gas(0.5);
		double expectedSpeed =  car.speedFactor() * 0.5;
		assertEquals(expectedSpeed, car.getCurrentSpeed(), delta);
	}

	@Test
	public void testBrakePointFiveAfterGasPointFiveShouldReturnZero() {
		car.gas(0.5);
		car.brake(0.5);
		assertEquals(0, car.getCurrentSpeed(), delta);
	}
}


