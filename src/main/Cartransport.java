import java.awt.*;
import java.util.ArrayList;

public class Cartransport extends Car {
    
    private boolean bedDown;
    private ArrayList<Car> currentCarsOnBed;
    
    public Cartransport() {
		super(
			2, 			// nrDoors
			125,		// enginePower
			Color.red,  // color
			"Biltransport1337"	// modelName
		);
        currentCarsOnBed = new ArrayList<Car>();
	    bedDown = false;
    }

    public void lowerbed(){
        if (currentSpeed == 0);
        bedDown = true;
    }

    public void raisebed(){
        if (currentSpeed == 0);
        bedDown = false;
    }

    @Override
    public void gas(double amount) {
		if (bedDown == false) return;
        if (amount > 1 || amount < 0) return;
		incrementSpeed(amount);
    }

    @Override
    public void brake(double amount) {	
		if (amount > 1 || amount < 0) return;
        decrementSpeed(amount);
    }

    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        for (Car car : currentCarsOnBed){
            car.setCurrentSpeed(currentSpeed);
        }
    }

    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        for (Car car : currentCarsOnBed){
            car.setCurrentSpeed(currentSpeed);
        }
    }

    @Override
    public void move() {
		super.move();
        for (Car car : currentCarsOnBed){
            car.move();
        }
	}


    public void loadCar(Car car){
        if (car instanceof Cartransport) return;
        if (bedDown) {
            if (car.getPosition()[0] > position[0]-5 && car.getPosition()[0] < position[0]+5 && car.getPosition()[1] > position[1]-5 && car.getPosition()[1] < position[1]+5);{
                currentCarsOnBed.add(car);
            }
        }
    }

    public void unloadCar(int numberOfCarsToUnload){
        int unloadAmount = numberOfCarsToUnload;
        int numberOfCarsOnBed = currentCarsOnBed.size();
        if (numberOfCarsToUnload > numberOfCarsOnBed);{
            unloadAmount = numberOfCarsOnBed;}
         // implementera ett sätt att se till så att an inte ladar av för många
        if (bedDown){
            for (int i = 1; i <= unloadAmount; i++){
                Car car  = currentCarsOnBed.get(currentCarsOnBed.size() - 1);
                double[] currentPosition = {position[0]-i, position[1]-i};
                car.setPosition(currentPosition);
                currentCarsOnBed.remove(currentCarsOnBed.size() - 1);
            }
        }
    }
}
