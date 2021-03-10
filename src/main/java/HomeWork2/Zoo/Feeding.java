package HomeWork2.Zoo;

import java.util.ArrayList;

/**
 * Кормление
 */
public class Feeding {
    /**
     * Список имен животных
     */
    private ArrayList<String> name;
    /**
     * Список дат кормлений
     */
    private ArrayList<String> date;

    public Feeding(){
        name = new ArrayList<>();
        date = new ArrayList<>();
    }

    public Feeding(String name, String date){
        this();
        this.name.add(name);
        this.date.add(date);
    }

    /**
     * Добавляет новую запись кормления
     * @param name имя животного
     * @param date дата кормления
     */
    public void add(String name, String date){
        this.name.add(name);
        this.date.add(date);
    }

    /**
     * Выводит все даты кормлений
     */
    public void print(){
        for(int i = 0; i < name.size(); i++){
            System.out.println("Имя животного: " + name.get(i)
                    + " Дата кормления: " + date.get(i));
        }
    }
}
