package by.teachmeskills.penkovsky.homework15.blrpassport;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class BlrPassport {
    private String surname;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String passportNumber;
    private String idNumber;
    private LocalDate issueDate;
    private LocalDate expirationDate;

    public BlrPassport(String surname, String name, String gender, String birthDateString,
                       String passportNumber, String idNumber, String issueDateString, String expirationDateString)
            throws IllegalArgumentException {
        validateSurname(surname);
        validateName(name);
        validateGender(gender);
        validateBirthDate(birthDateString);
        validatePassportNumber(passportNumber);
        validateIdNumber(idNumber);
        validateIssueAndExpirationDates(issueDateString, expirationDateString);

        this.surname = surname;
        this.name = name;
        this.gender = gender;
        this.birthDate = LocalDate.parse(birthDateString);
        this.passportNumber = passportNumber;
        this.idNumber = idNumber;
        this.issueDate = LocalDate.parse(issueDateString);
        this.expirationDate = LocalDate.parse(expirationDateString);
    }

    public String toString() {
        return String.format("Фамилия: %s\nИмя: %s\nПол: %s\nДата рождения: %s\nНомер паспорта: %s\n" +
                        "Идентификационный номер: %s\nДата выдачи: %s\nСрок действия: %s", surname, name, gender,
                birthDate.toString(), passportNumber, idNumber, issueDate.toString(), expirationDate.toString());
    }

    public boolean isExpired(LocalDate now) {
        return now.isAfter(expirationDate);
    }

    private void validateSurname(String surname) throws IllegalArgumentException {
        if (!Pattern.matches("[A-Za-z]{2,}", surname)) {
            throw new IllegalArgumentException("Фамилия должна содержать только латинские буквы и быть длиной не менее двух символов");
        }
    }

    private void validateName(String name) throws IllegalArgumentException {
        if (!Pattern.matches("[A-Za-z]{2,}", name)) {
            throw new IllegalArgumentException("Имя должно содержать только латинские буквы и быть длиной не менее двух символов");
        }
    }

    private void validateGender(String gender) throws IllegalArgumentException {
        if (!gender.equals("M") && !gender.equals("F")) {
            throw new IllegalArgumentException("Пол должен быть 'M' или 'F'");
        }
    }

    private void validateBirthDate(String birthDateString) throws IllegalArgumentException {
        try {
            LocalDate birthDate = LocalDate.parse(birthDateString);
            if (birthDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Дата рождения не может быть в будущем");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты рождения");
        }
    }

    private void validatePassportNumber(String passportNumber) throws IllegalArgumentException {
        if (!Pattern.matches("(AB|BM|HB|KH|MP|MC|KB|PP|SP|DP)\\d{7}", passportNumber)) {
            throw new IllegalArgumentException("Номер паспорта должен состоять из двух букв и семи цифр");
        }
    }

    private void validateIdNumber(String idNumber) throws IllegalArgumentException {
        if (!Pattern.matches("^\\d{7}[ABCHEKM]\\d{3}[PBAI]{2}\\d$", idNumber)) {
            throw new IllegalArgumentException("Идентификационный номер должен соответствовать формату: буква (A, B, C, K, E, M, H), 3 цифры, 2 буквы (PB, BA, BI), 1 цифра");
        }
    }
    private void validateIssueAndExpirationDates(String issueDateString, String expirationDateString)
            throws IllegalArgumentException {
        LocalDate issueDate;
        LocalDate expirationDate;
        try {
            issueDate = LocalDate.parse(issueDateString);
            expirationDate = LocalDate.parse(expirationDateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты");
        }
        LocalDate now = LocalDate.now(ZoneId.of("Europe/Minsk"));
        if (issueDate.isAfter(now)) {
            throw new IllegalArgumentException("Дата выдачи не может быть в будущем");
        }
        if (expirationDate.isBefore(now)) {
            throw new IllegalArgumentException("Срок действия паспорта уже истек");
        }
        if (issueDate.isAfter(expirationDate)) {
            throw new IllegalArgumentException("Дата выдачи не может быть позже даты окончания действия паспорта");
        }
    }
}

