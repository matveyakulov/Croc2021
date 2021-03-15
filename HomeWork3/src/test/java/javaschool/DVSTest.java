package javaschool;

import javaschool.model.DVS;
import javaschool.model.MotorType;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса DVS.
 */
public class DVSTest extends TestCase {

    private DVS auto;

    public void setUp(){
        auto = new DVS(3000, false, "Mercedes", 1,
                MotorType.PETROL, 4, 3.2);
    }

    /**
     * Тест для расчета стоимости аренды.
     */
    public void testGetCostDVS() {
        Assertions.assertEquals(24_000, auto.getCost(4));
    }

    /**
     * Тест для возможности взятия в аренду.
     */
    public void testGetRentDVS(){
        auto.setRent();
        Assertions.assertEquals(true, auto.getRent());
    }

    /**
     * Тест для текущего состояния.
     */
    public void testGetStateDVS(){
        auto.repair(auto.getState());
        Assertions.assertEquals(1, auto.getState());
    }
}
