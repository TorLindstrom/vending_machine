package se.lexicon.tor;

public class Wets extends Products
{
    private String wateriness;
    private static final String benefit = "replenishing";

    public Wets(String name, String taste, String overall, int price, String wateriness){
        super(name,taste,overall,price);
        this.wateriness = wateriness;
    }

    public String consume(){
        StringBuilder teller = new StringBuilder("You drink the ");
        teller.append(name).append(", it should be ").append(benefit).append(", but it splashes like ").append(wateriness).append(", and ").append(super.consume());
        return teller.toString();
    }

}
