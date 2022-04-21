package com.atm;

import java.sql.*;
import java.util.Scanner;

public class MainATM {


    public static void main(String args[]) throws SQLException {
        Account user = new Account();
        BankOperation operation = new BankOperation();

        System.out.println("    _________________________");
        System.out.println("   ДОБРО ПОЖАЛОВАТЬ В БАНКОМАТ!\n    _________________________");
        System.out.println("Выберете операцию:");
        System.out.println("Нажмите 1 для регистрации пользователя.");
        System.out.println("Нажмите 2 для входа в аккаунт.");
        System.out.println("    _________________________");
        int numberOperation;

        do {
            System.out.print("Введите цифру: ");
            Scanner scanner = new Scanner(System.in);
            numberOperation = scanner.nextInt();
            if (numberOperation > 2) {
                System.out.println("НЕВЕРНЫЙ КОД ОПЕРАЦИИ! ПОПРОБУЙТЕ СНОВА!");
                System.out.println("    _________________________");
            }
        }while (numberOperation > 2);


        switch (numberOperation){
            case 1:
                Scanner scanner = new Scanner(System.in);
                System.out.println("    _________________________");
                System.out.println("   РЕГИСТРАЦИЯ ПОЛЗОВАТЕЛЯ");
                System.out.print("Введите Ваше имя: ");
                user.setFirstname(scanner.nextLine());
                System.out.print("Введите Вашу фамилию: ");
                user.setLastname(scanner.nextLine());
                user.createUser();
                System.out.println("    _________________________");
                System.out.println("Пользователь успешно зарегестрирован!");
                System.out.println("Ваш номер карты: " + user.getNowNumberCard());
                System.out.println("Ваш пин-код: " + user.getNowPin_code());
                break;
            case 2:
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("    _________________________");
                System.out.println("   ВХОД В АККАУНТ");
                System.out.print("Введите номер карты: ");
                user.setNumberCard(scanner1.nextLine());
                System.out.print("Введите пин-код: ");
                user.setPin_code(scanner1.nextLine());
                System.out.println("    _________________________");
                System.out.println("Вы вошли в свой аккаунт! Здравствуйте, " + user.getFirstname() + " " + user.getLastname() + "!");
                int numberOperation1;

                do{
                    System.out.println();
                    System.out.println("Выберете операцию:");
                    System.out.println("Нажмите 1 для вывода баланса на экран.");
                    System.out.println("Нажмите 2 для пополнения баланса.");
                    System.out.println("Нажмите 3 для снятия денежных средств.");
                    System.out.println("Нажмите 4 для перевода денежных средств на другой счет.");
                    System.out.println("Нажмите 0 для завершения работы.");
                    System.out.println("    _________________________");
                    Scanner scanner2 = new Scanner(System.in);

                    do {
                        System.out.print("Введите цифру: ");
                        numberOperation1 = scanner2.nextInt();
                        if (numberOperation1 > 4){
                            System.out.println("НЕВЕРНЫЙ КОД ОПЕРАЦИИ! ПОПРОБУЙТЕ СНОВА!");
                            System.out.println("    _________________________");
                        }
                    }while (numberOperation1 > 4);

                    switch (numberOperation1){
                        case 0:
                            System.out.println("    _________________________");
                            System.out.println("Досвидания!");
                            break;
                        case 1:
                            System.out.println("    _________________________");
                            System.out.println("   ВЫВОД БАЛАНСА");
                            System.out.println("Ваш баланс: " + user.getBalance() + " ₽");

                            System.out.println("    _________________________");
                            System.out.println("Продолжить работу?\n1 - Да\n2 - Нет");
                            System.out.print("Введите цифру: ");
                            int numberOperation2 = scanner2.nextInt();
                            if (numberOperation2 == 2) numberOperation1 = 0;
                            System.out.println("    _________________________");
                            break;
                        case 2:
                            System.out.println("    _________________________");
                            System.out.println("   ПОПОЛНЕНИЕ БАЛАНСА");
                            System.out.print("Введите сумму пополнения: ");
                            operation.depositing_money(user.getNumberCard(),user.getPinCode(),
                                    user.getBalance(), scanner2.nextDouble());
                            System.out.println("Баланс успешно пополнен!");

                            System.out.println("    _________________________");
                            System.out.println("Продолжить работу?\n1 - Да\n2 - Нет");
                            System.out.print("Введите цифру: ");
                            int numberOperation3 = scanner2.nextInt();
                            if (numberOperation3 == 2) numberOperation1 = 0;
                            System.out.println("    _________________________");
                            break;
                        case 3:
                            Scanner scanner3 = new Scanner(System.in);
                            System.out.println("    _________________________");
                            System.out.println("   СНЯТИЕ ДЕНЕЖНЫХ СРЕДСТВ");
                            System.out.print("Введите сумму снятия: ");
                            double debit_money = scanner3.nextDouble();
                            if (debit_money > user.getBalance()){
                                System.out.println("На вашем счету недостаточно средств!");
                            }else {
                                operation.debit_money(user.getNumberCard(), user.getPinCode(), user.getBalance(), debit_money);
                            }
                            System.out.println("Вы сняли " + debit_money + " ₽!");
                            System.out.println();
                            System.out.println("Ваш баланс: " + user.getBalance() + " ₽");

                            System.out.println("    _________________________");
                            System.out.println("Продолжить работу?\n1 - Да\n2 - Нет");
                            System.out.print("Введите цифру: ");
                            int numberOperation4 = scanner2.nextInt();
                            if (numberOperation4 == 2) numberOperation1 = 0;
                            System.out.println("    _________________________");
                            break;
                        case 4:
                            Scanner scanner4 = new Scanner(System.in);
                            System.out.println("    _________________________");
                            System.out.println("   ПЕРЕВОД ДЕНЕЖНЫХ СРЕДСТВ");
                            System.out.print("Введите номер карты получателя: ");
                            String card_number = scanner4.nextLine();
                            System.out.print("Введите сумму перевода в рублях: ");
                            double transaction_money = scanner4.nextDouble();
                            if (transaction_money > user.getBalance()){
                                System.out.println("На вашем счету недостаточно средств!");
                            }else {
                                operation.transaction_money(user.getNumberCard(), user.getPinCode(),user.getBalance(),
                                        card_number,user.getBalance(card_number),transaction_money);
                            }
                            System.out.println("Перевод выполнен успешно!");
                            System.out.println();
                            System.out.println("Ваш баланс: " + user.getBalance() + " ₽");

                            System.out.println("    _________________________");
                            System.out.println("Продолжить работу?\n1 - Да\n2 - Нет");
                            System.out.print("Введите цифру: ");
                            int numberOperation5 = scanner2.nextInt();
                            if (numberOperation5 == 2) numberOperation1 = 0;
                            System.out.println("    _________________________");
                            break;
                    }

                }while (numberOperation1 != 0);

                break;
        }

    }

}
