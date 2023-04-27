package com.apress.spring6recipes.calculator;

import org.springframework.stereotype.Component;

@Component
class StandardUnitCalculator implements UnitCalculator {

	@Override
	public double kilogramToPound(double kg) {
		var pound = kg * 2.2;
		System.out.printf("%f kilogram = %f pound%n", kg, pound);
		return pound;
	}

	@Override
	public double kilometerToMile(double km) {
		var mile = km * 0.62;
		System.out.printf("%f kilometer = %f mile%n", km, mile);
		return mile;
	}
}
