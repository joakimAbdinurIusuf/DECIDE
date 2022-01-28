import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LCMTest {

    /**
     * Tests the ANDD operation for true ANDD true
     */
    @Test 
    public void givenTrueANDD_whenOperate_thenAssertTrue() {
        LCM lcm = new LCM(new Logic[][]{{ Logic.ANDD }});
        assertTrue(lcm.operate(0, 0, new boolean[]{ true }));
    }

    /**
     * Tests the ANDD operation for false ANDD false
     */
    @Test 
    public void givenFalseANDD_whenOperate_thenAssertFalse() {
        LCM lcm = new LCM(new Logic[][]{{ Logic.ANDD }});
        assertFalse(lcm.operate(0, 0, new boolean[]{ false }));
    }

    /**
     * Tests the ANDD operation for true ANDD false
     */
    @Test 
    public void givenTrueFalseANDD_whenOperate_thenAssertFalse() {
        LCM lcm = new LCM(new Logic[][]{{ Logic.NOTUSED, Logic.ANDD }, { Logic.NOTUSED, Logic.NOTUSED }});
        assertFalse(lcm.operate(0, 1, new boolean[]{ true, false }));
    }

    /**
     * Tests the ORR operation for true ORR true
     */
    @Test 
    public void givenTrueORR_whenOperate_thenAssertTrue() {
        LCM lcm = new LCM(new Logic[][]{{ Logic.ORR }});
        assertTrue(lcm.operate(0, 0, new boolean[]{ true }));
    }

    /**
     * Tests the ORR operation for false ORR false
     */
    @Test 
    public void givenFalseORR_whenOperate_thenAssertFalse() {
        LCM lcm = new LCM(new Logic[][]{{ Logic.ORR }});
        assertFalse(lcm.operate(0, 0, new boolean[]{ false }));
    }

    /**
     * Tests the ORR operation for true ORR false
     */
    @Test 
    public void givenTrueFalseORR_whenOperate_thenAssertTrue() {
        LCM lcm = new LCM(new Logic[][]{{ Logic.NOTUSED, Logic.ORR }, { Logic.NOTUSED, Logic.NOTUSED }});
        assertTrue(lcm.operate(0, 1, new boolean[]{ true, false }));
    }

    /**
     * Tests the NOTUSED operation for true
     */
    @Test 
    public void givenTrueNOTUSED_whenOperate_thenAssertTrue() {
        LCM lcm = new LCM(new Logic[][]{{ Logic.NOTUSED }});
        assertTrue(lcm.operate(0, 0, new boolean[]{ true }));
    }

    /**
     * Tests the NOTUSED operation for false
     */
    @Test 
    public void givenFalseNOTUSED_whenOperate_thenAssertTrue() {
        LCM lcm = new LCM(new Logic[][]{{ Logic.NOTUSED }});
        assertTrue(lcm.operate(0, 0, new boolean[]{ false }));
    }

}