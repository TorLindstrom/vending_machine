package se.lexicon.tor;

public interface VendingMachine
{
    void addCurrency(int amount);
    Products request(int number);
    int endSession() throws InterruptedException;
    String getDescription(int number);
    int getBalance();
    String[] getProducts();
}
