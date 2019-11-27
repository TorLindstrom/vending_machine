package se.lexicon.tor;

import static java.lang.String.format;

public abstract class Products {

    protected String name;
    protected String taste;
    protected String overallExperience;
    protected int price;

    protected Products(String name, String taste, String overallExperience, int price){
        this.name = name;
        this.taste = taste;
        this.overallExperience = overallExperience;
        this.price = price;
    }

    protected String examine(){
        StringBuilder teller = new StringBuilder(this.name).append(", tastes ").append(this.taste).append(", and it's ").append(this.overallExperience);
        return format("%-60s%4skr", teller.toString(), this.price);
    }
    protected String consume(){
        return "it truly tasted " + this.overallExperience;
    }
}
