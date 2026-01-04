package Session15.lt3;

import java.util.*;

public class OrderManagement implements ProductManager<Product> {
    static Map<Integer, Order> orders = new HashMap<>();
    static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderManagement orderManagement = new OrderManagement();
        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Them san pham");
            System.out.println("2. Xoa san pham");
            System.out.println("3. Hien thi san pham");
            System.out.println("4. Tao don hang");
            System.out.println("5. Them san pham vao don hang");
            System.out.println("6. Hien thi don hang");
            System.out.println("0. Thoat");
            System.out.print("Lua chon cua ban: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    orderManagement.addProduct(new Product());
                    break;
                case 2:
                    orderManagement.removeProduct(0);
                    break;
                case 3:
                    orderManagement.display();
                    break;
                case 4:
                    orderManagement.addOrder(sc);
                    break;
                case 5:
                    orderManagement.addProductToOrder(sc);
                    break;
                case 6:
                    orderManagement.displayOrder();
                    break;
                case 0:
                    System.out.println("Da thoat.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        } while (true);
    }

    @Override
    public void addProduct(Product item) {
        Scanner sc = new Scanner(System.in);
        item.input(sc);
        products.add(item);
        System.out.println("Them san pham thanh cong");
    }

    public int checkProductId(int product_id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product_id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeProduct(int index) {
        if (products.isEmpty()) {
            System.out.println("Chua co san pham.");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("=== Danh sach san pham ===");
            display();
            System.out.print("Nhap id san pham can xoa: ");
            int product_id = Integer.parseInt(sc.nextLine());
            index = checkProductId(product_id);
            if (index == -1) {
                System.err.println("Khong tim thay id.");
            } else {
                products.remove(index);
                System.out.println("Xoa san pham thanh cong.");
            }
        }
    }

    @Override
    public void display() {
        if (products.isEmpty()) {
            System.out.println("Chua co san pham.");
        } else {
            for (Product item : products) {
                System.out.println(item);
            }
        }
    }

    public void addOrder(Scanner sc) {
        Order order = new Order();
        do {
            System.out.println("=== Danh san pham ===");
            for (int i = 0; i < products.size(); i++) {
                System.out.println(products.get(i));
            }

            System.out.print("Nhap id san pham hoac nhap 0 de dung chon: ");
            int product_id = Integer.parseInt(sc.nextLine());
            if (product_id == 0) {
                break;
            }
            int check_id = checkProductId(product_id);
            if (check_id == -1) {
                System.err.println("Khong tim thay id.");
            } else {
                order.addProduct(products.get(check_id));
                order.getTotalPrice();
            }
        } while (true);

        orders.put(order.getOrderId(), order);
        System.out.println("Tao don hang thanh cong");
    }

    public int checkOrderId(int order_id) {
        for(Order order : orders.values()) {
            if(order.getOrderId() == order_id) {
                return order.getOrderId();
            }
        }
        return -1;
    }

    public void addProductToOrder(Scanner sc) {
        int check_id_order;
        int check_id_product;
        do {
            System.out.println("=== Danh sach don hang ===");
            for(Order o : orders.values()) {
                o.display();
            }
            System.out.print("Nhap id don muon them san pham hoac nhap 0 de dung chon: ");
            int order_id = Integer.parseInt(sc.nextLine());
            if (order_id == 0) {
                break;
            }
            check_id_order = checkOrderId(order_id);
            if (check_id_order == -1) {
                System.err.println("Khong tim thay id.");
            } else {
                System.out.println("=== Danh san pham ===");
                for (int i = 0; i < products.size(); i++) {
                    System.out.println(products.get(i));
                }
                System.out.print("Nhap id san pham muon them hoac nhap 0 de dung chon: ");
                int product_id = Integer.parseInt(sc.nextLine());
                if (product_id == 0) {
                    break;
                }
                check_id_product = checkProductId(product_id);
                if (check_id_product == -1) {
                    System.err.println("Khong tim thay id.");
                } else {
                    orders.get(order_id).addProduct(products.get(check_id_product));
                }
            }
        } while (true);

    }

    public void displayOrder() {
        orders.values().forEach(Order::display);
    }
}
