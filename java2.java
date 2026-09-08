package myjavaproject;

import java.util.Scanner;
import java.util.ArrayList;

// CLASS SubscriptionMember
class SubscriptionMember {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> memberNames = new ArrayList<>();

    public void addSubscriptionMember() {
        System.out.print("Enter member name: ");
        String name = sc.nextLine();
        System.out.print("Enter discounted rate: ");
        double rate = Double.parseDouble(sc.nextLine());

        memberNames.add(name);
        System.out.println("Processing membership payment: " + rate);
        System.out.println("Processing weekly payment: " + rate);
        System.out.println("Subscription member added: " + name);
    }
}

// CLASS PerSessionMember
class PerSessionMember {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> memberNames = new ArrayList<>();
    ArrayList<Double> memberRates = new ArrayList<>();

    public void addPerSessionMember() {
        System.out.print("Enter member name: ");
        String name = sc.nextLine();
        System.out.print("Enter discounted rate: ");
        double rate = Double.parseDouble(sc.nextLine());

        memberNames.add(name);
        memberRates.add(rate);
        System.out.println("Processing membership payment: " + rate);
        System.out.println("Per session member added: " + name);
    }

    public double getDiscountedRate(String name) {
        int index = memberNames.indexOf(name);
        return index >= 0 ? memberRates.get(index) : 0;
    }
}

// CLASS Attendance
class Attendance {
    Scanner sc = new Scanner(System.in);
    double dailySalesTotal = 0;
    double operationalCost = 0;

    ArrayList<String> logCustomer = new ArrayList<>();
    ArrayList<String> logTime = new ArrayList<>();
    ArrayList<Double> logPayment = new ArrayList<>();

    public void inputSales(SubscriptionMember subMember, PerSessionMember perMember) {
        System.out.print("Enter visitor name: ");
        String visitor = sc.nextLine();

        System.out.print("Member or walk in? (member/walkin): ");
        String customerType = sc.nextLine();
        double fee = 0;

        if (customerType.equalsIgnoreCase("member")) {
            System.out.print("Subscription or per session?: ");
            String planType = sc.nextLine();

            if (planType.equalsIgnoreCase("subscription")) {
                System.out.println("No fee collected");
            } else if (planType.equalsIgnoreCase("per session")) {
                fee = perMember.getDiscountedRate(visitor);
                System.out.println("Processing discounted rate: " + fee);
            }
        } else if (customerType.equalsIgnoreCase("walkin")) {
            System.out.print("Enter standard payment: ");
            fee = Double.parseDouble(sc.nextLine());
            System.out.println("Processing standard payment: " + fee);
        }

        logCustomer.add(visitor);
        logTime.add(java.time.LocalTime.now().toString());
        logPayment.add(fee);
        dailySalesTotal += fee;
    }

    public void viewWeeklySales() {
        for (int i = 0; i < logCustomer.size(); i++) {
            System.out.println(logCustomer.get(i) + " | " + logTime.get(i) + " | PHP " + logPayment.get(i) + " | paid");
        }
        System.out.println("Total sales: " + dailySalesTotal);
        System.out.println("Cost of operation: " + operationalCost);
        System.out.println("Net total: " + (dailySalesTotal - operationalCost));
    }
}

// CLASS Exit
class Exit {
    public void exitProgram() {
        System.out.println("Saving data...");
        System.out.println("Thank you! Exiting the system...");
    }
}

// CLASS Menu
class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SubscriptionMember subscriptionMember = new SubscriptionMember();
        PerSessionMember perSessionMember = new PerSessionMember();
        Attendance attendance = new Attendance();
        Exit exit = new Exit();

        boolean systemRunning = true;

        while (systemRunning) {
            System.out.println("\nGYM SYSTEM MENU");
            System.out.println("1. Subscription membership");
            System.out.println("2. Per session membership");
            System.out.println("3. Input sales");
            System.out.println("4. View weekly sales");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    subscriptionMember.addSubscriptionMember();
                    break;
                case "2":
                    perSessionMember.addPerSessionMember();
                    break;
                case "3":
                    attendance.inputSales(subscriptionMember, perSessionMember);
                    break;
                case "4":
                    attendance.viewWeeklySales();
                    break;
                case "5":
                    exit.exitProgram();
                    systemRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
