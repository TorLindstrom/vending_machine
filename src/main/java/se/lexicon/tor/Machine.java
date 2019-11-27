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

}
