import java.awt.*;

public class Car implements Movable {

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    public String modelName; // The car model name
  	protected int[] position;
	protected Direction direction;

	enum Direction {
		NORTH,
		EAST,
		SOUTH,
		WEST;

		private static final Direction[] vals = values();

		public Direction left() {
			return vals[(this.ordinal() - 1) % 4];
		}

		public Direction right() {
			return vals[(this.ordinal() + 1) % 4];
		}
	}

	public void Car() {
        stopEngine();
		position = new int[2];
		direction = Direction.NORTH;
	}

	public void move() {
		switch(direction) {
			case Direction.NORTH:
				position[1] += currentSpeed;
				break;

			case Direction.EAST:
				position[0] += currentSpeed;
				break;

			case Direction.SOUTH:
				position[1] -= currentSpeed;
				break;

			case Direction.WEST:
				position[0] -= currentSpeed;
				break;
		}	
	}

	public void turnLeft() {
		direction = direction.left(); 
	}

	public void turnRight() {
		direction = direction.right();
	}

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
	    color = clr;
    }

    public void startEngine(){
	    currentSpeed = 0.1;
    }

    public void stopEngine(){
	    currentSpeed = 0;
    }

    public double speedFactor(){
        return enginePower * 0.01;
    }

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

	private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
    
    public void gas(double amount) {
		if (amount > 1 || amount < 0) { return; }
		incrementSpeed(amount);
    }

    public void brake(double amount){	
		if (amount > 1 || amount < 0) { return; }
        decrementSpeed(amount);
    }
}
