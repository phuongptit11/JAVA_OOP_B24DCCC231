import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ======================= PRODUCT CLASSES ==========================
abstract class Product {
    protected String id;
    protected String name;
    protected double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayInfo();
}

// ----- ElectronicProduct -----
class ElectronicProduct extends Product {
    private String imei;
    private int warrantyMonths;

    public ElectronicProduct(String id, String name, double price, String imei, int warrantyMonths) {
        super(id, name, price);
        this.imei = imei;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void displayInfo() {
        System.out.println("Điện tử: " + name + " - Giá: " + price + " VND - IMEI: " + imei + " - Bảo hành: " + warrantyMonths + " tháng");
    }
}

// ----- FoodProduct -----
class FoodProduct extends Product {
    private String expiryDate;

    public FoodProduct(String id, String name, double price, String expiryDate) {
        super(id, name, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public void displayInfo() {
        System.out.println("Thực phẩm: " + name + " - Giá: " + price + " VND - Hạn sử dụng: " + expiryDate);
    }
}

// ======================= PAYMENT CLASSES ==========================

// Interface PaymentMethod
interface PaymentMethod {
    void pay(double amount, String customerName);
}

// CashPayment
class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount, String customerName) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + " VND. Thanh toán tiền mặt thành công ");
    }
}

// CreditCardPayment
class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount, String customerName) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + " VND. Thanh toán bằng thẻ tín dụng thành công ");
    }
}

// MomoPayment
class MomoPayment implements PaymentMethod {
    @Override
    public void pay(double amount, String customerName) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + " VND. Thanh toán bằng ví Momo thành công ");
    }
}

// ======================= ORDER CLASS ==============================
class Order {
    private String customerName;
    private List<Product> productList = new ArrayList<>();
    private PaymentMethod paymentMethod;

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : productList) {
            total += p.getPrice();
        }
        return total;
    }

    public void checkout(String paymentType) {
        double totalAmount = calculateTotal();

        // Dùng if-else để chọn phương thức thanh toán
        if (paymentType.equalsIgnoreCase("cash")) {
            paymentMethod = new CashPayment();
        } else if (paymentType.equalsIgnoreCase("card")) {
            paymentMethod = new CreditCardPayment();
        } else if (paymentType.equalsIgnoreCase("momo")) {
            paymentMethod = new MomoPayment();
        } else {
            System.out.println("Phương thức thanh toán không hợp lệ ");
            return;
        }

        paymentMethod.pay(totalAmount, customerName);
    }

    public void showOrderDetails() {
        System.out.println("=== Danh sách sản phẩm của " + customerName + " ===");
        for (Product p : productList) {
            p.displayInfo();
        }
        System.out.println("Tổng tiền: " + calculateTotal() + " VND\n");
    }
}

// ======================= MAIN CLASS ==============================
public class OnlineStorePaymentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Thêm sản phẩm mẫu ---
        Product p1 = new ElectronicProduct("E01", "Laptop Dell", 15000000, "IMEI12345", 24);
        Product p2 = new FoodProduct("F01", "Bánh quy", 50000, "2025-12-30");
        Product p3 = new ElectronicProduct("E02", "Tai nghe Sony", 1200000, "IMEI67890", 12);

        // --- Tạo đơn hàng ---
        System.out.print("Nhập tên khách hàng: ");
        String customerName = sc.nextLine();

        Order order = new Order(customerName);
        order.addProduct(p1);
        order.addProduct(p2);
        order.addProduct(p3);

        order.showOrderDetails();

        // --- Chọn phương thức thanh toán ---
        System.out.print("Chọn phương thức thanh toán (cash/card/momo): ");
        String paymentType = sc.nextLine();

        order.checkout(paymentType);

        sc.close();
    }
}

