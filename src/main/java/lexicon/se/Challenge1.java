package lexicon.se;

import java.util.Scanner;

public class Challenge1 {
        static double discountPercent;
        static double priceMenu;
        static  String item;
        static int itemNumber;
        static void main() {
            Scanner scanner = new Scanner(System.in);

            String orderId = "1";
            int quantity=0;
            double price = 0;
            //double discountPercent= 15.0;
            String customerName;
            boolean isMember= false;

            int customerServed = 0;
            double totalRevenue = 0;

            while (true){
                System.out.println("Wellcome! what is your name? ");
                customerName = scanner.nextLine();
                if(customerName.equalsIgnoreCase("done")){
                    break;
                }
                customerServed++;


                System.out.println("Hi " + customerName + "! Here is our menu:");
                System.out.println("======================================");
                System.out.printf("%20s%n", "Lexicon Cafe");
                System.out.println("======================================");
                System.out.printf("%-20s %10s%n", "1. Espresso  ", "25.00 SEK");
                System.out.printf("%-20s %10s%n", "2. Cappuccino ", "35.00 SEK");
                System.out.printf("%-20s %10s%n", "3. Latte ", "40.00 SEK");
                System.out.printf("%-20s %10s%n", "4. Croissant", "30.00 SEK");
                System.out.printf("%-20s %10s%n", "5. Sandwich ", "55.00 SEK");
                System.out.println("======================================");
                System.out.println();
                System.out.println();

                System.out.println("Enter item number (1-5): ");
                orderId = scanner.nextLine();
                System.out.println("How many? ");
                quantity = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Loyalty member? (yes/no): ");
                String membership = scanner.nextLine();

                isMember = membership.equalsIgnoreCase("yes");
                //shows in console
                System.out.printf("Customer : %.10s %n", customerName);
                chooseItem(orderId);
                printInConsole(quantity, isMember);
                System.out.println();
                System.out.println("======================================");
                System.out.printf("%15s %1s%n", "Thank you,", customerName);
                System.out.printf("%22s %n", "See you next time");
                System.out.println("======================================");
                System.out.println("Enter next customer: ");
                calculateTotalRevenue(price,quantity, isMember);


            }
            System.out.printf("Customer Served      : %.2s SEK %n",customerServed);

            totalRevenue= calculateTotalRevenue(price,quantity, isMember);
            System.out.printf("Total Revenue      : %.2s SEK %n",totalRevenue);


        }

        public static double calculateSubtotal(double price, int quantity){
            return price * quantity;
        }

        public static double calculateDiscount(double price, int quantity, boolean isMember){
            if(isMember){
                discountPercent=15.0;
                return calculateSubtotal(price,quantity) * (discountPercent/100);
            }else if(calculateSubtotal(price,quantity)>= 150){
                discountPercent = 10.0;
                return calculateSubtotal(price,quantity) * (discountPercent/100);
            }
            return 0;
        }

        public static double calculateVat(double price, int quantity, boolean isMember){
            double subtotal= calculateSubtotal(price,quantity);
            double discount = calculateDiscount(price,quantity,isMember);
            double amountAfterDiscount = subtotal -discount;

            return (double) Math.round(amountAfterDiscount * (0.12) * 100) /100;
        }


        public static double calculateTotalAmount(double price,int quantity, boolean isMember) {
            double subTotal = calculateSubtotal(price,quantity);
            double discount =calculateDiscount(price,quantity,isMember);
            double vat = calculateVat(price,quantity,isMember);

            return subTotal - discount + vat;
        }

        public static void chooseItem(String orderId) {

            switch (orderId) {
                case "1":
                    itemNumber = 1;
                    item = "Espresso";
                    priceMenu = 25.00;
                    break;
                case "2":
                    itemNumber = 2;
                    item = "Cappuccino";
                    priceMenu = 35.00;
                    break;
                case "3":
                    itemNumber = 3;
                    item = "Latte";
                    priceMenu = 40.00;
                    break;
                case "4":
                    itemNumber = 4;
                    item = "Crossant";
                    priceMenu = 30.00;
                    break;
                case "5":
                    itemNumber = 5;
                    item = "Sandwich";
                    priceMenu = 55.00;
                    break;
                default:
                    System.out.println("Invalid orderId");
            }
        }

        public static void printInConsole(int quantity, boolean isMember){
            System.out.printf("Item     : %.10s * %.3s %n", item,quantity);
            double subTotal = calculateSubtotal(priceMenu,quantity);
            System.out.printf("SubTotal : %.2f SEK%n",subTotal);
            double discount = calculateDiscount(priceMenu,quantity,isMember);
            System.out.printf("Discount : -%.2f SEK %n",discount);
            double vat = calculateVat(priceMenu,quantity,isMember);
            System.out.printf("Vat      : %.2f SEK %n",vat);
            System.out.println("-----------------------------");
            System.out.println();

            double total = calculateTotalAmount(priceMenu,quantity,isMember);
            System.out.printf("TOTAL    : %.2f SEK %n",total);
        }

        public static double calculateTotalRevenue(double pris, int quantity, boolean isMember){
            double totalAmountEveryCustomer = calculateTotalAmount(pris,quantity,isMember);
            return + totalAmountEveryCustomer;
        }
    }



