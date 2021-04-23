package com.github.matveyakulov.javaschool.homework7;

import com.github.matveyakulov.javaschool.homework7.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.homework7.service.CarDiler;
import com.github.matveyakulov.javaschool.homework7.service.CarService;
import com.github.matveyakulov.javaschool.homework7.model.Car;

import java.io.IOException;
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
        DataSourceProvider dataSourceProvider = new DataSourceProvider("application.properties");

        // Запросы к бд
        CarDiler carDiler = new CarDiler(dataSourceProvider.getDataSource());

        // Прикладной сервис
        CarService carService = new CarService(carDiler);

        while (true) {
            System.out.println("Что вы хотите сделать?\n"
                    + "1 - Добавить новую запись в конец\n"
                    + "2 - Добавить новую запись в конкретную строку\n"
                    + "3 - Прочитать запись\n"
                    + "4 - Обновить существующую запись\n"
                    + "5 - Удалить запись\n"
                    + "6 - Вывести на экран все записи\n"
                    + "7 - Очистить таблицу\n");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Введите количество лошадей");
                    int hp = sc.nextInt();
                    System.out.println("Введите гос номер");
                    String number = sc.next();
                    System.out.println("Введите true - машина в розыске, false - иначе");
                    boolean wanted = sc.nextBoolean();
                    System.out.println("Введите дату производства в формате: гггг-мм-дд");
                    String date = sc.next();
                    System.out.println("Введите время производства в формате: ч:м:с");
                    String time = sc.next();
                    carService.create(new Car(hp, number, wanted, date, time));
                    System.out.println("Запись успешно добавлена!");
                    break;
                }
                case 2: {
                    System.out.println("Введите id строки");
                    int id = sc.nextInt();
                    System.out.println("Введите количество лошадей");
                    int hp = sc.nextInt();
                    System.out.println("Введите гос номер");
                    String number = sc.next();
                    System.out.println("Введите true - машина в розыске, false - иначе");
                    boolean wanted = sc.nextBoolean();
                    System.out.println("Введите дату производства в формате: гггг-мм-дд");
                    String date = sc.next();
                    System.out.println("Введите время производства в формате: ч:м:с");
                    String time = sc.next();

                    carService.create(id, new Car(hp, number, wanted, date, time));
                    System.out.println("Запись успешно добавлена!");
                    break;
                }
                case 3: {
                    System.out.println("Введите id строки");
                    int id = sc.nextInt();

                    if (carDiler.read(id) != null) {
                        System.out.println("Найденная строка:\n");
                        System.out.println(carDiler.read(id).toString());
                    } else {
                        System.out.println("Такой записи нет!");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Введите id строки");
                    int id = sc.nextInt();
                    System.out.println("Введите количество лошадей");
                    int hp = sc.nextInt();
                    System.out.println("Введите гос номер");
                    String number = sc.next();
                    System.out.println("Введите true - машина в розыске, false - иначе");
                    boolean wanted = sc.nextBoolean();
                    System.out.println("Введите дату производства в формате: гггг-мм-дд");
                    String date = sc.next();
                    System.out.println("Введите время производства в формате: ч:м:с");
                    String time = sc.next();
                    carService.update(id, new Car(hp, number, wanted, date, time));
                    System.out.println("Запись обновлена!");
                    break;
                }
                case 5: {
                    System.out.println("Введите id строки");
                    int id = sc.nextInt();
                    carService.delete(id);
                    System.out.println("Запись удалена!");
                    break;
                }
                case 6: {
                    Map<Integer, Car> carList = carService.findAll();
                    Set<Integer> keySet = carList.keySet();
                    for (Integer key : keySet) {
                        System.out.println(key + " " + carList.get(key));
                    }
                    if (carList.size() == 0) {
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
            if (!(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5
                    || choice == 6 || choice == 7)) {
                System.out.println("До свидания!");
                break;
            }
        }
    }
}
