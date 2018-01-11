package cn.krim.gp.front.test;

import java.util.Arrays;
import java.util.List;

public class WithMain {

	public static void main(String[] args) {
		Car car = Car.create(Car::new);
		List<Car> cars = Arrays.asList(car);
		cars.forEach(Car::collide);
		cars.forEach(Car::repair);
		cars.forEach(car::follow);
	
	}

}
