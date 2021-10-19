/* Создать таблицу «Меню в ресторане». Колонки: название блюда, его стоимость, вес,
 * наличие скидки. Написать код для добавления записей в таблицу и их выборки по
 * критериям «стоимость от-до», «только со скидкой», выбрать набор блюд так, чтобы
 * их суммарный вес был не более 1 КГ.
 */
package com.gmail.dev.surovtsev.yaroslav;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Menu menu1 = new Menu("Pizza", 150.00, 0.450, false);
        Menu menu2 = new Menu("Burger", 250.00, 0.400, true);
        Menu menu3 = new Menu("Beer", 70.50, 0.333, false);
        Menu menu4 = new Menu("Vegetable", 50.50, 0.250, false);

        MenuDAO menuDAO = new MenuDAOImpl();
        menuDAO.add(menu1);
        menuDAO.add(menu2);
        menuDAO.add(menu3);
        menuDAO.add(menu4);

        menuDAO.delete(menu4);
        menu3.setPrice(100.10);
        menuDAO.update(menu3);

        System.out.println("=====getId");
        Optional<Menu> dishFromDB1 = menuDAO.get(menu1.getId());
        dishFromDB1.ifPresent(System.out::println);

        System.out.println("=====getId");
        Optional<Menu> dishFromDB2 = menuDAO.get(menu4.getId());
        dishFromDB2.ifPresent(System.out::println);

        System.out.println("=====getAll");
        List<Menu> list1 = menuDAO.getAll();
        list1.forEach(System.out::println);

        System.out.println("=====isDiscountAvailable");
        List<Menu> list2 = menuDAO.getAll(true);
        list2.forEach(System.out::println);

        System.out.println("=====price between");
        List<Menu> list3 = menuDAO.getAll(100, 160);
        list3.forEach(System.out::println);

        System.out.println("=====< 1 kg");
        List<Menu> list4 = menuDAO.getAll(1.000);
        list4.forEach(System.out::println);
    }
}
