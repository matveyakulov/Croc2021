package HomeWork2;

import HomeWork2.Zoo.Aviary;
import HomeWork2.Zoo.Disease;
import HomeWork2.Zoo.Feeding;
import HomeWork2.Zoo.ZooPark;

public class App {

    public static void main(String[] args) {
        ZooPark zoo = new ZooPark("Enot"){{
            add("Cat");
            add("Dog");
            add("Lion");
        }};
        zoo.print();
        zoo.remove("Dog");
        zoo.print();
        Disease disease = new Disease(){{
            add("Cat", "qwe", "20.02.21");
            add("Dog", "rty", "26.02.21");
            add("Lion", "asd", "31.02.21");
        }};
        disease.print();
        Aviary aviary = new Aviary(){{
            add(1, "28.12.21");
            add(2, "22.09.21");
        }};
        aviary.print();
        Feeding feeding = new Feeding(){{
            add("Cat", "23.09.21");
            add("Dog", "10.07.21");
        }};
        feeding.print();

    }
}
