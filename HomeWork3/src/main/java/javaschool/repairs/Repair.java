package javaschool.repairs;

/**
 * Обычный ремонт для всех ТС.(Тех обслуживание)
 */
public interface Repair {

    /**
     * Ремонт ТС.
     * @param state текущее состояние обьекта.
     */
    default void repair(Integer state){   // хотел реализовать дефолтный метод, ничего лучше не придумал
        state = 2;
    }
}
