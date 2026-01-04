package Session15.lt3;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    public static int AUTO_ID = 1;
    private List<Product> products = new ArrayList<>();
    public Order() {
        this.orderId = AUTO_ID++;
    }

    public int getOrderId() {
        return orderId;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
    public void display() {
        System.out.println("Don hang " + orderId);
        products.forEach(System.out::println);
        System.out.println("Tong tien: " + getTotalPrice());
    }

}
