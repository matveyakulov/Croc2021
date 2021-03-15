package javaschool;

import javaschool.model.AeroFly;
import javaschool.model.MotorType;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса AeroFly.
 */
public class AeroFlyTest extends TestCase {

    private AeroFly airplane;

    public void setUp(){
        airplane = new AeroFly(3000, false, "Boing 747", 1,
                MotorType.GAS_TURBINE, 2, 1000);
    }

    /**
     * Тест для расчета стоимости аренды.
     */
    public void testGetCostAeroFly() {
        Assertions.assertEquals(24_000, airplane.getCost(4));
    }

    /**
     * Тест для возможности взятия в аренду.
     */
    public void testGetRentAeroFly(){
        airplane.setRent();
        Assertions.assertEquals(true, airplane.getRent());
    }

    /**
     * Тест для текущего состояния.
     */
    public void testGetStateAeroFly(){
        airplane.repair(airplane.getState());
        Assertions.assertEquals(1, airplane.getState());
    }
}
