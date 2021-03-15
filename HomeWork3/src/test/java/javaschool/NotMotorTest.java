package javaschool;

import javaschool.model.MotorType;
import javaschool.model.NotMotor;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса NotMotor.
 */
public class NotMotorTest extends TestCase {

    private NotMotor bike;

    public void setUp(){
        bike = new NotMotor(3000, false, "Stels", 1,
                MotorType.ELECTRO, false, false);
    }
    /**
     * Тест для расчета стоимости аренды.
     */
    public void testGetCostDVS() {
        Assertions.assertEquals(12_000, bike.getCost(4));
    }

    /**
     * Тест для возможности взятия в аренду.
     */
    public void testGetRentDVS(){
        bike.setRent();
        Assertions.assertEquals(true, bike.getRent());
    }

    /**
     * Тест для текущего состояния.
     */
    public void testGetStateDVS(){
        bike.fastRepair();
        Assertions.assertEquals(2, bike.getState());
    }

}
