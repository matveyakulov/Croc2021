package javaschool.model;

import javaschool.repairs.Repair;

/**
 * Все ТС с двигателем внутреннего сгорания.
 */
public class DVS extends Vehicle implements Repair {

    /**
     * Количество посадочных мест.
     */
    private Integer  seats;

    /**
     * Разгон до 100.
     */
    private Double acceleration;

    public DVS(Integer price, Boolean rent, String name, Integer state, MotorType motorType,
               Integer seats, Double acceleration) {
        super(price, rent, name, state, motorType);
        this.seats = seats;
        this.acceleration = acceleration;
    }


    /**
     * Считает стоимость аренды.
     *
     * @param days количество дней.
     * @return сумма аренды.
     */
    @Override
    public Integer getCost(Integer days) {
        return days * getPrice() * 2;  // допустим, что какой-нибудь коэфициент аренды = 2
    }
}
