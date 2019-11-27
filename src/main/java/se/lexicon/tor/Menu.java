package se.lexicon.tor;

import java.util.Scanner;

public class Menu
{
    private VendingMachine machine;

    public Menu(VendingMachine machine)
    {
        this.machine = machine;
    }

    public void getInput() throws InterruptedException
    {
        while (true) {
            System.out.println("Please enter a order, followed by a value, for example: 'pay 100kr', 'description 3'");
            String input = App.scanner.nextLine().trim().toLowerCase();
            Scanner scan = new Scanner(input);
            start(new String[]{scan.next(), scan.next()});
        }
    }

    public int start(String[] arguments) throws InterruptedException
    {
        switch (arguments[0]) {
            case "pay":
                return check(arguments[1]);
            case "order":
                return askOrder(arguments[1]);
            case "get description":
            case "description":
                return askDescription(arguments[1]);
            case "quit":
                machine.endSession();
                System.exit(0);
            default:
                System.out.println("Please enter a valid command");
                return -1;
        }
    }

    private int check(String input)
    {
        String enumChecker = "_" + input.toLowerCase();
        enumChecker = enumChecker.contains("kr") ? enumChecker : enumChecker + "kr";
        try {
            for (Cash cash : Cash.values()) {
                if (Cash.valueOf(enumChecker) == cash) {
                    machine.addCurrency(cash.value);
                    System.out.println("Added to deposit");
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid denomination, in the form of '***kr'");
            System.out.print("Used denominations are: 1kr, 5kr, 10kr, 20kr, 50kr, 100kr, 500kr, 1000kr in coins and bills respectively.");
        }
        return -1;
    }

    private int askOrder(String input)
    {
        System.out.print("Please write a product number:");
        try {
            int number = Integer.parseInt(input);
            Products item = machine.request(number);
            System.out.println("You bought a " + item.name);
            System.out.println(item.consume());
            return 1;
        } catch (NullPointerException e1) {
            System.out.println("Insufficient funds");
        } catch (Exception e2) {
            System.out.println("Please enter a valid number");
        }
        return -1;
    }

    private int askDescription(String input)
    {
        System.out.print("Please enter the number of the product:");
        try {
            int number = Integer.parseInt(input);
            String answer = machine.getDescription(number);
            if (answer != null) {
                System.out.println(answer);
                return 1;
            }
        } catch (Exception e2) {
            System.out.println("Not a valid product number");
        }
        return -1;
    }
}
