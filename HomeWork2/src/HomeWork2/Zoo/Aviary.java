package HomeWork2.Zoo;

import java.util.ArrayList;

/**
 * Вольер
 */
public class Aviary {
    /**
     * Список номеров вольеров
     */
    private ArrayList<Integer> num;
    /**
     * Список дат уборок
     */
    private ArrayList<String> date;

    public Aviary(){
        num = new ArrayList<>();
        date = new ArrayList<>();
    }

    public Aviary(Integer num, String date){
        this();
        this.num.add(num);
        this.date.add(date);
    }

    /**
     * Добавляет новую запись уборки
     * @param num номер вольера
     * @param date дата уборки
     */
    public void add(Integer num, String date){
        this.num.add(num);
        this.date.add(date);
    }

    /**
     * Выводит все даты уборок
     */
    public void print(){
        for(int i = 0; i < num.size(); i++){
            System.out.println("Номер вольера: " + num.get(i)
                    + " Дата уборки: " + date.get(i));
        }
    }

}
