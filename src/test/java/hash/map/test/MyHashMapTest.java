package hash.map.test;

import hash.map.impl.MyHashMap;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Test;

public class MyHashMapTest {
    private static final Integer FIRST_INT = 5;
    private static final Integer SECOND_INT = -9;
    private static final Integer THIRD_INT = 25;
    private static final Long VALUE_ONE = 55L;
    private static final Long VALUE_TWO = 564L;
    private static final Long VALUE_THREE = -478455L;

    @Test
    public void getByNonExistedKey() {
        MyHashMap myHashMap = new MyHashMap();
        Assert.assertNull("Test failed! If key doesn't exist, we should return null.",
                myHashMap.getValue(FIRST_INT));
    }

    @Test
    public void putAndGetOk() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(FIRST_INT, VALUE_ONE);
        myHashMap.put(SECOND_INT, VALUE_TWO);
        myHashMap.put(THIRD_INT, VALUE_THREE);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 3 but was "
                + myHashMap.getSize(), 3, myHashMap.getSize());
        Long firstActualValue = myHashMap.getValue(FIRST_INT);
        Long secondActualValue = myHashMap.getValue(SECOND_INT);
        Long thirdActualValue = myHashMap.getValue(THIRD_INT);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_ONE)
                + firstActualValue, VALUE_ONE, firstActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_TWO)
                + secondActualValue, VALUE_TWO, secondActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_THREE)
                + thirdActualValue, VALUE_THREE, thirdActualValue);
    }

    @Test
    public void putTheSameElement() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(FIRST_INT, VALUE_ONE);
        myHashMap.put(SECOND_INT, VALUE_TWO);
        myHashMap.put(THIRD_INT, VALUE_THREE);
        myHashMap.put(FIRST_INT, VALUE_ONE);
        myHashMap.put(SECOND_INT, VALUE_TWO);
        myHashMap.put(THIRD_INT, VALUE_THREE);
        Assert.assertEquals("Test failed! We should add checking if the same element "
                + "exists in the map", 3, myHashMap.getSize());
        Long firstActualValue = myHashMap.getValue(FIRST_INT);
        Long secondActualValue = myHashMap.getValue(SECOND_INT);
        Long thirdActualValue = myHashMap.getValue(THIRD_INT);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_ONE)
                + firstActualValue, VALUE_ONE, firstActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_TWO)
                + secondActualValue, VALUE_TWO, secondActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_THREE)
                + thirdActualValue, VALUE_THREE, thirdActualValue);
    }

    @Test
    public void putAndGetByNullKey() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(null, VALUE_ONE);
        Long firstActualValue = myHashMap.getValue(null);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_ONE)
                + firstActualValue, VALUE_ONE, firstActualValue);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 1 but was "
                + myHashMap.getSize(), 1, myHashMap.getSize());
        myHashMap.put(null, VALUE_TWO);
        Long secondActualValue = myHashMap.getValue(null);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_TWO)
                + secondActualValue, VALUE_TWO, secondActualValue);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 1 but was "
                + myHashMap.getSize(), 1, myHashMap.getSize());
    }

    @Test
    public void putAndGetWithCollision() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(FIRST_INT, VALUE_ONE);
        myHashMap.put(SECOND_INT, VALUE_TWO);
        myHashMap.put(THIRD_INT, VALUE_THREE);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 3 but was "
                + myHashMap.getSize(), 3, myHashMap.getSize());
        Long firstActualValue = myHashMap.getValue(FIRST_INT);
        Long secondActualValue = myHashMap.getValue(SECOND_INT);
        Long thirdActualValue = myHashMap.getValue(THIRD_INT);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_ONE)
                + firstActualValue, VALUE_ONE, firstActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_TWO)
                + secondActualValue, VALUE_TWO, secondActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_THREE)
                + thirdActualValue, VALUE_THREE, thirdActualValue);
    }

    @Test
    public void putAndGetByNullKeyWithCollision() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(FIRST_INT, VALUE_ONE);
        myHashMap.put(null, VALUE_ONE);
        myHashMap.put(SECOND_INT, VALUE_TWO);
        myHashMap.put(null, VALUE_TWO);
        myHashMap.put(THIRD_INT, VALUE_THREE);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 4 but was "
                + myHashMap.getSize(), 4, myHashMap.getSize());
        Long firstActualValue = myHashMap.getValue(FIRST_INT);
        Long secondActualValue = myHashMap.getValue(SECOND_INT);
        Long thirdActualValue = myHashMap.getValue(THIRD_INT);
        Long fourthActualValue = myHashMap.getValue(null);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_ONE)
                + firstActualValue, VALUE_ONE, firstActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_TWO)
                + secondActualValue, VALUE_TWO, secondActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_THREE)
                + thirdActualValue, VALUE_THREE, thirdActualValue);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_TWO)
                + fourthActualValue, VALUE_TWO, fourthActualValue);
    }

    @Test
    public void putAndGetTheOverriddenValueByKey() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(FIRST_INT, VALUE_ONE);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 1 but was "
                + myHashMap.getSize(), 1, myHashMap.getSize());
        Long firstActualValue = myHashMap.getValue(FIRST_INT);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_ONE)
                + firstActualValue, VALUE_ONE, firstActualValue);
        myHashMap.put(FIRST_INT, VALUE_TWO);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 1 but was "
                + myHashMap.getSize(), 1, myHashMap.getSize());
        Long secondActualValue = myHashMap.getValue(FIRST_INT);
        Assert.assertEquals(String.format("Test failed! HashMap expects to contain %d values, but was ", VALUE_TWO)
                + secondActualValue, VALUE_TWO, secondActualValue);
    }

    @Test
    public void checkTheHashMapIncrease() {
        MyHashMap myHashMap = new MyHashMap();
        for (int i = 0; i < 1000; i++) {
            myHashMap.put(i, (long) i);
        }
        Assert.assertEquals("Test failed! The size isn't correct. Expected 1000 but was "
                + myHashMap.getSize(), 1000, myHashMap.getSize());
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(Long.valueOf(i),
                    myHashMap.getValue(i));
        }
    }

    @Test
    public void getSizeOfEmptyHashMap() {
        MyHashMap myHashMap = new MyHashMap();
        Assert.assertEquals("Test failed! The size isn't correct. Expected 0 but was "
                + myHashMap.getSize(), 0, myHashMap.getSize());
    }

    @Test
    public void existOnlyOneArrayFieldTest() {
        MyHashMap myHashMap = new MyHashMap();
        Field[] declaredFields = myHashMap.getClass().getDeclaredFields();
        int count = 0;
        for (Field field : declaredFields) {
            if (field.getType().isArray()) {
                count++;
            }
        }
        Assert.assertEquals("Class MyHashMap shouldn't consist more then one array as a field",
                1, count);
    }

    @Test
    public void checkArrayLengthAfterResizingTest() throws IllegalAccessException {
        MyHashMap myHashMap = new MyHashMap();
        for (int i = 0; i < 14; i++) {
            myHashMap.put(i, (long) i);
        }
        for (int i = 0; i < 14; i++) {
            Assert.assertEquals(Long.valueOf(i),
                    myHashMap.getValue(i));
        }
        Field[] declaredFields = myHashMap.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getType().isArray()) {
                field.setAccessible(true);
                int length = Array.getLength(field.get(myHashMap));
                Assert.assertEquals("After first resizing, length of array should be " + 32,
                        32, length);
            }
        }
    }
}
