package myjavaproject;

import java.util.Scanner;

// Item Class with OOP concepts applied
class Item {
    private String name;
    private double price;
    private int quantity;

    // 1. Basic Constructor with Constructor Chaining
    public Item() {
        this("Default Item", 0.0, 1);
    }

    // 2. Overloaded Constructor (2 Parameters) with Constructor Chaining
    public Item(String name, double price) {
        this(name, price, 1);
    }

    // 3. Overloaded Constructor (3 Parameters) using 'this' keyword
    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void displayItem() {
        System.out.printf("%-15s PHP %-8.2f x%-5d Total: PHP %.2f\n", 
            this.name, this.price, this.quantity, (this.price * this.quantity));
    }

    public double getTotalPrice() {
        return this.price * this.quantity;
    }
}

// Main Class containing Menu System
public class myjavaproject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Item[] cart = new Item[10];
        int itemCount = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n===== POINT OF SALE SYSTEM =====");
            System.out.println("1. Add Default Item (Default Constructor)");
            System.out.println("2. Add Quick Item (Overloaded 2-Params)");
            System.out.println("3. Add Custom Item (Overloaded 3-Params)");
            System.out.println("4. View Cart & Total");
            System.out.println("5. Exit");
            System.out.print("Select choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    if (itemCount < cart.length) {
                        cart[itemCount++] = new Item();
                        System.out.println("Default item added!");
                    } else {
                        System.out.println("Cart is full!");
                    }
                    break;

                case 2:
                    if (itemCount < cart.length) {
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        cart[itemCount++] = new Item(name, price);
                        System.out.println("Item added successfully!");
                    } else {
                        System.out.println("Cart is full!");
                    }
                    break;

                case 3:
                    if (itemCount < cart.length) {
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        cart[itemCount++] = new Item(name, price, qty);
                        System.out.println("Item added successfully!");
                    } else {
                        System.out.println("Cart is full!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- CURRENT CART ---");
                    if (itemCount == 0) {
                        System.out.println("Cart is empty.");
                    } else {
                        double grandTotal = 0;
                        for (int i = 0; i < itemCount; i++) {
                            cart[i].displayItem();
                            grandTotal += cart[i].getTotalPrice();
                        }
                        System.out.printf("GRAND TOTAL: PHP %.2f\n", grandTotal);
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("System closed. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}