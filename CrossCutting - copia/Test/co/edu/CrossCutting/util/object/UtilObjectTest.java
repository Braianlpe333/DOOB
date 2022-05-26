package co.edu.CrossCutting.util.object;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.uco.crosscutting.util.object.UtilObject;

class UtilObjectTest {
	
	@Test
	void validateIfObjectisNull() {
		//arrange
		
		Object value = null;
		
		//act
		boolean result = UtilObject.getUtilObject().isNull(value);
		//assert
		assertTrue(result);
	
	}
	
	@Test
	void validateIfObjectisNotNull() {
		//arrange
		
		Object value = new Object();
		
		//act
		boolean result = UtilObject.getUtilObject().isNull(value);
		
		//assert
		assertTrue(result);
	
	}
	@Test
	void validateIfObjectisEqualToOriginal() {
		//arrange
		
		Object value = new Object();
		Object defaultValue = new Object();
		
		//act
		Object result = UtilObject.getUtilObject().getDefault(value, defaultValue);
		
		//assert
		assertTrue(value.equals(result));
	
	}
	
	@Test
	void validateIfObjectisEqualToDefaultObjectWhenOriginalObjectIsNull() {
		//arrange
		
		Object value = new Object();
		Object defaultValue = new Object();
		
		//act
		Object result = UtilObject.getUtilObject().getDefault(value, defaultValue);
		
		//assert
		assertTrue(value.equals(result));
	
	}

}
