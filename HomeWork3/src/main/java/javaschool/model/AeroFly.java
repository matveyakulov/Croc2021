package javaschool.model;

import javaschool.repairs.Repair;
import javaschool.repairs.RepairPlane;

/**
 * Летающие ТС.
 */
public class AeroFly extends Vehicle implements Repair, RepairPlane {

    /**
     * Количество двигателей.
     */
    private Integer countMotor;

    /**
     * Грузоподьемность.
     */
    private Integer carrying;

    public AeroFly(Integer price, Boolean rent, String name, Integer state, MotorType motorType,
                   Integer countMotor, Integer carrying) {
        super(price, rent, name, state, motorType);
        this.countMotor = countMotor;
        this.carrying = carrying;
    }

    /**
     * Считает стоимость аренды.
     *
     * @param days количество дней.
     * @return сумма аренды.
     */
    @Override
    public Integer getCost(Integer days) {
        return days * getPrice() * 5;  // допустим, что какой-нибудь коэфициент аренды = 5
    }

    /**
     * Долгий ремонт.
     */
    @Override
    public void longRepair() {
        state = 2;
    }
}
