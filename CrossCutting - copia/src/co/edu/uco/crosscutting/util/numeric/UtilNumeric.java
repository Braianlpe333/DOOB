package co.edu.uco.crosscutting.util.numeric;

import co.edu.uco.crosscutting.util.object.UtilObject;

public class UtilNumeric {
	private static final UtilNumeric INSTANCE = new UtilNumeric();
	public static final Byte ZERO = 0;
	
	private UtilNumeric() {
		
	}
	
	public static UtilNumeric getUtilObject() {
		return INSTANCE;
	}
	
	public <T extends Number> T getDefault(T value, T defaultValue) {
		return UtilObject.getUtilObject().getDefault(value, defaultValue);
	}
	
	public <T extends Number> Number getDefault(T value) {
		return getDefault(value, ZERO);
	}
	
	
	public <T extends Number> boolean isGreatherThan(T oneValue, T twoValue) {
		return getDefault(oneValue).doubleValue() > getDefault(twoValue).doubleValue();
	}
	public <T extends Number> boolean isLessThan(T oneValue, T twoValue) {
		return getDefault(oneValue).doubleValue() < getDefault(twoValue).doubleValue();
	}
	public <T extends Number> boolean isEqualThan(T oneValue, T twoValue) {
		return getDefault(oneValue).doubleValue() == getDefault(twoValue).doubleValue();
	}
	public <T extends Number> boolean isGreatherOrEqualThan(T oneValue, T twoValue) {
		return getDefault(oneValue).doubleValue() > getDefault(twoValue).doubleValue() || getDefault(oneValue).doubleValue() == getDefault(twoValue).doubleValue();
	}
	public <T extends Number> boolean isLessOrEqualThan(T oneValue, T twoValue) {
		return getDefault(oneValue).doubleValue() < getDefault(twoValue).doubleValue() || getDefault(oneValue).doubleValue() == getDefault(twoValue).doubleValue();
	}
	
	public <T extends Number> boolean isBetween(T value, T initialRange, T finalRange, boolean incluedeInitialRange ,boolean includeFinalRnage) {
		
		return incluedeInitialRange
			   ? isGreatherOrEqualThan(value, initialRange)
			   : isGreatherThan(value, initialRange)
			   &&
			   includeFinalRnage
			   ? isLessOrEqualThan(value, finalRange)
			   : isLessThan(value,finalRange);
	}
	
	public <T extends Number> boolean isBetweenIncludeRanges(T value, T initialRange, T finalRange) {
		return isBetween(value,initialRange,finalRange, true, true);
	}
	
	public <T extends Number> boolean isPositive(T value) {
		return isGreatherOrEqualThan(value, ZERO);
	}
	
	public <T extends Number> boolean isNegative(T value) {
		return !isPositive(value);
	}
	
}
