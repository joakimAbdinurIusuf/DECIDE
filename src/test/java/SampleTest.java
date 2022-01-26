import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SampleTest {

    @Test
    public void testSuccess() {
        assertEquals(2, 1+1);
    }

    @Test
    public void testFail() {
        assertEquals(1, 2+2);
    }

}