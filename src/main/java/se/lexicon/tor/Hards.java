package se.lexicon.tor;

public class Hards extends Products
{
    private String crunch;
    private static final String benefit = "crunchy";

    public Hards(String name, String taste, String overall, int price, String crunch){
        super(name,taste,overall,price);
        this.crunch = crunch;
    }

    public String consume(){
        StringBuilder teller = new StringBuilder("You crunch the ");
        teller.append(name).append(", it should be ").append(benefit).append(", but it crunches like ").append(crunch).append(", and ").append(super.consume());
        return teller.toString();
    }

}
