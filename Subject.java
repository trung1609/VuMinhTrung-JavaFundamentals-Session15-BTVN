package Session15.lt2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Subject {
    String code;
    public static int AUTO_ID = 1;
    String name;
    int credits;
    LocalDate startDate;

    public Subject() {
    }

    public Subject(String name, int credits, LocalDate startDate) {
        this.code = String.format("S%03d", AUTO_ID++);
        this.name = name;
        this.credits = credits;
        this.startDate = startDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("Code: %s - Name: %s - Credits: %d - StartDate: %s", code, name, credits, startDate.format(formatter));
    }

    public void input(Scanner sc) {
        this.code = String.format("S%03d", AUTO_ID++);
        System.out.print("Nhap ten mon hoc: ");
        this.name = sc.nextLine();
        this.credits = inputCredits(sc);
        this.startDate = inputStartDate(sc);
    }

    public int inputCredits(Scanner sc) {
        int inputCredits;
        do {
            try {
                System.out.print("Nhap so tin chi: ");
                inputCredits = Integer.parseInt(sc.nextLine());
                if (inputCredits < 0 || inputCredits > 10) {
                    System.err.println("Vui long nhap so tin chi trong khoang [0,10].");
                    continue;
                }
                return inputCredits;
            } catch (NumberFormatException e) {
                System.err.println("Vui long nhap so tin hop le.");
            }
        } while (true);

    }

    public LocalDate inputStartDate(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        do {
            try {
                System.out.print("Nhap ngay bat dau: ");
                String date = sc.nextLine();
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Vui long nhap dung dinh dang ngay.");
            }
        } while (true);
    }
}
