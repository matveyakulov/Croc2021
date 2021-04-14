package com.github.matveyakulov.javaschool.homework7;

import com.github.matveyakulov.javaschool.homework7.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.homework7.service.CarDiler;
import com.github.matveyakulov.javaschool.homework7.service.CarService;
import com.github.matveyakulov.javaschool.homework7.model.Car;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Menu.
 */
public class App {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // data source
        DataSourceProvider dataSourceProvider = new DataSourceProvider();

        // подключились к бд
        CarDiler carDiler = new CarDiler(dataSourceProvider.getDataSource());

        // экземпляр прикладного сервиса
        CarService carService = new CarService(carDiler);

        while (true) {
            System.out.println("Что вы хотите сделать?\n"
                    + "1 - создать машину в конец таблицы\n"
                    + "2 - создать машину в заданную строку\n"
                    + "3 - прочитать машину\n"
                    + "4 - обновить данные существующей машины\n"
                    + "5 - удалить машину\n"
                    + "6 - вывести на экран все записи\n"
                    + "7 - удалить все записи\n");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Введите количество лошадей");
                    int hp = sc.nextInt();
                    System.out.println("Введите номер машины");
                    String number = sc.next();
                    System.out.println("Введите true - машина в розыске, false - иначе");
                    boolean wanted = sc.nextBoolean();
                    System.out.println("Введите дату производства машины в формате: гг-мм-дд");
                    String date = sc.next();
                    System.out.println("Введите время производства машины в формате: ч:м:с");
                    String time = sc.next();
                    carService.create(new Car(hp, number, wanted, date, time));
                    System.out.println("Машина успешно добавлена!");
                    break;
                }
                case 2: {
                    System.out.println("Введите id строки");
                    int id = sc.nextInt();
                    System.out.println("Введите количество лошадей");
                    int hp = sc.nextInt();
                    System.out.println("Введите номер машины");
                    String number = sc.next();
                    System.out.println("Введите true - машина в розыске, false - иначе");
                    boolean wanted = sc.nextBoolean();
                    System.out.println("Введите дату производства машины в формате: гггг-мм-дд");
                    String date = sc.next();
                    System.out.println("Введите время производства машины в формате: ч:м:с");
                    String time = sc.next();

                    carService.create(id, new Car(hp, number, wanted, date, time));
                    System.out.println("Машина успешно добавлена!");
                    break;
                }
                case 3: {
                    System.out.println("Введите id машины согласно таблице");
                    int id = sc.nextInt();

                    if(carDiler.read(id) != null) {
                        System.out.println("Найденная машина:\n");
                        System.out.println(carDiler.read(id).toString());
                    }
                    else {
                        System.out.println("Такой машины нет!");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Введите id машины согласно таблице");
                    int id = sc.nextInt();
                    System.out.println("Введите количество лошадей");
                    int hp = sc.nextInt();
                    System.out.println("Введите номер машины");
                    String number = sc.next();
                    System.out.println("Введите true - машина в розыске, false - иначе");
                    boolean wanted = sc.nextBoolean();
                    System.out.println("Введите дату производства машины в формате: гг-мм-дд");
                    String date = sc.next();
                    System.out.println("Введите время производства машины в формате: ч:м:с");
                    String time = sc.next();
                    carService.update(id, new Car(hp, number, wanted, date, time));
                    System.out.println("Данные успешно обновлены!");
                    break;
                }
                case 5: {
                    System.out.println("Введите id машины согласно таблице");
                    int id = sc.nextInt();
                    carService.delete(id);
                    System.out.println("Машина успешно удалена!");
                    break;
                }
                case 6: {
                    Map<Integer, Car> carList = carService.findAll();
                    Set<Integer> keySet = carList.keySet();
                    for (Integer key : keySet) {
                        System.out.println(key + " " + carList.get(key));
                    }
                    if(carList.size() == 0){
                        System.out.println("Таблица пуста!");
                    }
                    break;
                }
                case 7: {
                    carService.deleteAll();
                    System.out.println("Таблица очищена!");
                }
                default: {
                    break;
                }

            }
            if (!(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6 || choice == 7)) {
                System.out.println("До свидания!");
                break;
            }
        }
    }
}
