package HomeWork2.Zoo;

import java.util.ArrayList;

/**
 * Болезни.
 */
public class Disease {
    /**
     * Список имен животных
     */
    private ArrayList<String> name;
    /**
     * Список болезней животных
     */
    private ArrayList<String> disease;
    /**
     * Список дат
     */
    private ArrayList<String> date;

    public Disease(){
        name = new ArrayList<>();
        disease = new ArrayList<>();
        date = new ArrayList<>();
    }

    public Disease(String name, String disease, String date){
        this();
        this.name.add(name);
        this.disease.add(disease);
        this.date.add(date);
    }
    /**
     * Добавляет новую болезнь
     * @param name имя животного
     * @param disease имя болезни
     * @param date дата
     */
    public void add(String name, String disease, String date){
        this.name.add(name);
        this.disease.add(disease);
        this.date.add(date);
    }


    /**
     * Выводит все болезни
     */
    public void print(){
        for(int i = 0; i < name.size(); i++){
            System.out.println("Имя животного: " + name.get(i)
                    + " Болезнь: " + disease.get(i)
                    + " Дата заболевания: " + date.get(i));
        }
    }
}
