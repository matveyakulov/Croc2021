package com.github.matveyakulov.javaschool.homework4.components;


import java.util.Comparator;
import java.util.List;

/**
 * Класс для хранения компонент.
 */
public class Components {

    /**
     * Компоненты.
     */
    List<String> list;

    public Components(List<String> list) {
        this.list = list;
        list.sort(Comparator.comparing(String::length));  //сортировка по длине
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for(String item : list){
            stringBuilder.append(item);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
