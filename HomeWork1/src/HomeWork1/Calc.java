package HomeWork1;

import java.util.ArrayList;

public class Calc {

    public static void printBytes(double numberOfBits){
        ArrayList<String> dimension = new ArrayList<>(){{       //коллекция для степени байта
            add("B");
            add("KB");
            add("MB");
            add("GB");
            add("TB");
            add("PB");
            add("EB");  // экзабайт
            add("ZB");  // зеттабайт
            add("YB");  // йоттабайт
        }};
        int count = 0;
        if(numberOfBits >= 0) {
            while (numberOfBits >= 1024 && count < dimension.size() - 1) {      // ограничил count, чтобы не выйти за пределы коллекции
                count++;
                numberOfBits /= 1024;
            }
            System.out.print(String.format("%.1f", numberOfBits) + " " + dimension.get(count));
        }else {
            System.out.println("Число должно быть положительным!");
        }
        if(numberOfBits >=52 && count < dimension.size() - 1)
            System.out.println(" или приблизительно" + " " + String.format("%.1f", numberOfBits / 1024) + " " + dimension.get(count + 1));

    }
}
