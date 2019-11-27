package se.lexicon.tor;

import static java.lang.String.format;

public class Machine implements VendingMachine
{
    private int depositPool;
    private Products[] availableProducts;

    public Machine(Products... products)
    {
        availableProducts = products;
    }

    public void addCurrency(int amount)
    {
        depositPool += amount;
    }

    public Products request(int number)
    {
        try {
            Products theProduct = availableProducts[number - 1];
            if (theProduct.price > depositPool) {
                return null;
            } else {
                depositPool -= theProduct.price;
                return theProduct;
            }
        } catch (Exception e){
            System.out.println("You didn't enter a valid product number");
        }
        return null;
    }

    public int endSession() throws InterruptedException
    {
        int rest = depositPool;
        while (rest > 0) {
            String amount;
            if (rest / 1000 >= 1) {
                amount = "thousand bill";
                rest -= 1000;
            } else if (rest / 500 >= 1) {
                amount = "fivehundred bill";
                rest -= 500;
            } else if (rest / 100 >= 1) {
                amount = "hundred bill";
                rest -= 100;
            } else if (rest / 50 >= 1) {
                amount = "fifty bill";
                rest -= 50;
            } else if (rest / 20 >= 1) {
                amount = "twenty bill";
                rest -= 20;
            } else if (rest / 10 >= 1) {
                amount = "ten crown";
                rest -= 10;
            } else if (rest / 5 >= 1) {
                amount = "five crown";
                rest -= 5;
            } else {
                amount = "one crown";
                rest -= 1;
            }
            Thread.sleep(500L);
            System.out.println("Returning a " + amount);
        }
        System.out.println("No more change");
        int toBeReturned = depositPool;
        depositPool = 0;
        return toBeReturned;
    }

    @Override
    public String getDescription(int number)
    {
        try {
            return availableProducts[number - 1].examine();
        } catch (Exception e){
            System.out.println("You didn't enter a valid product number");
            return null;
        }

    }

    @Override
    public int getBalance()
    {
        return depositPool;
    }

    @Override
    public String[] getProducts()
    {
        String[] list = new String[availableProducts.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = format("# %-4s", (i + 1)) + availableProducts[i].examine();
        }
        return list;
    }

    public void menu() throws InterruptedException
    {
        while (true) {
            System.out.print("Pay, order, get description, or quit: ");
            String argument = App.scanner.nextLine().trim().toLowerCase();
            switch (argument) {
                case "pay":
                    check();
                    break;
                case "order":
                    askOrder();
                    break;
                case "get description":
                case "description":
                    askDescription();
                    break;
                case "quit":
                    endSession();
                    depositPool = 0;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid command");
            }
        }
    }

    private void check()
    {
        System.out.print("Used denominations are: 1kr, 5kr, 10kr, 20kr, 50kr, 100kr, 500kr, 1000kr in coins and bills respectively.\nPlease enter an amount:");
        String enumChecker = "_" + App.scanner.nextLine().trim().toLowerCase();
        enumChecker = enumChecker.contains("kr") ? enumChecker : enumChecker + "kr";
        try {
            for (Cash cash : Cash.values()) {
                if (Cash.valueOf(enumChecker) == cash) {
                    addCurrency(cash.value);
                    System.out.println("Added to deposit");
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid denomination, in the form of '***kr'");
        }
    }

    private void askOrder()
    {
        System.out.print("Please write a product number:");
        try {
            int input = Integer.parseInt(App.scanner.nextLine());
            Products item = request(input);
            System.out.println("You bought a " + item.name);
            System.out.println(item.consume());
        } catch (NullPointerException e1) {
            System.out.println("Insufficient funds");
        } catch (Exception e2) {
            System.out.println("Please enter a valid number");
        }
    }

    private void askDescription()
    {
        System.out.print("Please enter the number of the product:");
        try {
            int input = Integer.parseInt(App.scanner.nextLine());
            System.out.println(getDescription(input));
        } catch (ArrayIndexOutOfBoundsException e1) {
            System.out.println("Not a valid product number");
        }
    }
}
