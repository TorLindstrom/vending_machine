package se.lexicon.tor;

public class Greens extends Products{

    private String texture;
    private static final String benefit = "healthy";


    public Greens(String name, String taste, String overall, int price, String texture){
        super(name,taste,overall,price);
        this.texture = texture;
    }

    public String consume(){
        StringBuilder teller = new StringBuilder("You eat the ");
        teller.append(name).append(", it should be ").append(benefit).append(", but it feels like ").append(texture).append(", and ").append(super.consume());
        return teller.toString();
    }
}
