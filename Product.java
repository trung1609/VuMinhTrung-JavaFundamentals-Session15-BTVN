package Session15.lt3;

import java.util.Scanner;

public class Product {
    int id;
    String name;
    double price;
    public static int AUTO_ID = 1;

    public Product() {
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Price: %.1f", id, name, price);
    }

    public void input(Scanner sc) {
        this.id = AUTO_ID++;
        System.out.print("Nhap ten san pham: ");
        this.name = sc.nextLine();
        this.price = inputPrice(sc);
    }

    public double inputPrice(Scanner sc) {
        double inputPrice;
        do {
            try {
                System.out.print("Nhap gia san pham: ");
                inputPrice = Double.parseDouble(sc.nextLine());
                if (inputPrice <= 0) {
                    System.err.println("Vui long nhap gia tien lon hon 0.");
                    continue;
                }
                return inputPrice;
            } catch (NumberFormatException e) {
                System.err.println("Vui long nhap dung dinh dang gia tien.");
            }
        } while (true);
    }
}
