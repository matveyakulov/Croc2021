package javaschool.model;

/**
 * Любое ТС.
 */
public abstract class Vehicle{

    /**
     * Цена за сутки.
     */
    private Integer price;

    /**
     * Статус( true - в аренде, false - нет).
      */
    private Boolean rent;

    /**
     * Имя ТС.
     */
    private String name;

    /**
     * Описывает текущее состояние ТС( 0 - в ремонте, 1 - никогда не было в ремонте, 2 - работает после ремонта).
     */
    protected Integer state;

    /**
     * Тип мотора.
     */
    private MotorType motorType;

    public Vehicle(Integer price, Boolean rent, String name, Integer state, MotorType motorType) {
        this.price = price;
        this.rent = rent;
        this.name = name;
        this.state = state;
        this.motorType = motorType;
    }

    /**
     * Изменяет статус аренды ТС( 0 - свободен, 1 - занят или в ремонте).
     */
    public void setRent(){ // так как их всего два, то параметр не нужен, просто меняем значение на противоположное

        rent = !rent;

    }

    public Integer getPrice() {
        return price;
    }

    public Integer getState() {
        return state;
    }

    public Boolean getRent() {
        return rent;
    }

    /**
     * Считает стоимость аренды.
     * @param days количество дней.
     * @return сумма аренды.
     */
    public abstract Integer getCost(Integer days);

}
