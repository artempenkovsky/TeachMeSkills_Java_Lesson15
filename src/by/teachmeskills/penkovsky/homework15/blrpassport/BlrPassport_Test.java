package by.teachmeskills.penkovsky.homework15.blrpassport;

import by.teachmeskills.penkovsky.homework15.blrpassport.BlrPassport;

import java.time.LocalDate;
import java.util.Scanner;

public class BlrPassport_Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите фамилию на латинице: ");
            String surname = scanner.nextLine();
            System.out.print("Введите имя на латинице: ");
            String name = scanner.nextLine();
            System.out.print("Введите пол (M/F): ");
            String gender = scanner.nextLine();
            System.out.print("Введите дату рождения (гггг-мм-дд): ");
            String birthDate = scanner.nextLine();
            System.out.print("Введите серию и номер паспорта: ");
            String passportNumber = scanner.nextLine();
            System.out.print("Введите идентификационный номер паспорта: ");
            String passportSeries = scanner.nextLine();
            System.out.print("Введите дату выдачи паспорта (гггг-мм-дд): ");
            String issueDate = scanner.nextLine();
            System.out.print("Введите дату окончания срока действия паспорта (гггг-мм-дд): ");
            String expirationDate = scanner.nextLine();

            BlrPassport passport = new BlrPassport(surname, name, gender, birthDate, passportNumber,
                    passportSeries, issueDate, expirationDate);
            System.out.println(passport.toString());
            System.out.println("Просрочен ли паспорт: " + passport.isExpired(LocalDate.now()));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}


