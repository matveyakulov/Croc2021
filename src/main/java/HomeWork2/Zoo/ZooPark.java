package HomeWork2.Zoo;

import java.util.ArrayList;

/**
 * Зоопарк.
 */
public class ZooPark {
    /**
     * Список животных
     */
    private ArrayList<String> list;

    public ZooPark(){
        list = new ArrayList<>();
    }

    public ZooPark(String name){
        this();
        list.add(name);
    }
    /**
     * Добавляет новое животное
     * @param name имя животного
     */
    public void add(String name){
        list.add(name);
    }

    /**
     * Удаляет запись животного по его имени
     * @param name имя животного
     */
    public void remove(String name){
        int j = -1;
        for(int i = 0; i < list.size(); i++ ){
            if(list.get(i).equals(name))
                j = i;
        }
        if(j != -1){
            list.remove(name);
        }
        else System.out.println("Такого не существует");
    }

    /**
     * Выводит всех животных
     */
    public void print(){
        for(String item : list){
            System.out.println("Имя животного: " + item);
        }
    }
}
