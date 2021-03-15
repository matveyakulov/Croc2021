package javaschool.model;

import javaschool.repairs.FastRepair;
import javaschool.repairs.Repair;

/**
 * ТС, у которых нет мотора или он очень маленький.
 */
public class NotMotor extends Vehicle implements FastRepair, Repair {

    /**
     * Возможность ездить ночью( 0 - нельзя, 1 - можно ).
     */
    private Boolean night;

    /**
     * Возможность ездить по дорогам общего пользования( 0 - нельзя, 1 - можно ).
     */
    private Boolean road;

    public NotMotor(Integer price, Boolean rent, String name, Integer state, MotorType motorType,
                    Boolean night, Boolean road) {
        super(price, rent, name, state, motorType);
        this.night = night;
        this.road = road;
    }


    /**
     * Быстрый ремонт.
     */
    @Override
    public void fastRepair() {
        state = 2;
    }  // допустим этот метод принципиально отличается от Repair

    /**
     * Считает стоимость аренды.
     *
     * @param days количество дней.
     * @return сумма аренды.
     */
    @Override
    public Integer getCost(Integer days) {
        return days * getPrice();
    }
}
