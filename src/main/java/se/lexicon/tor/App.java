package se.lexicon.tor;

import java.util.Scanner;

public class App
{

    public static Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) throws InterruptedException
    {
        run();
    }
    public static void run() throws InterruptedException
    {
        Products[] products = new Products[]{
                new Greens("Kale", "metallic", "awful", 20, "leatherface of vegetables"),
                new Greens("Broccoli", "bland", "indifferent", 20, "afro of the green giant"),
                new Greens("Avocado", "yummy", "creamy", 20, "prehistoric"),
                new Greens("Soybeans", "firm", "good", 20, "firm"),
                new Greens("Lettuce", "like water", "sad", 20, "crunchy water"),
                new Greens("Tomatillo", "sour", "small", 20, "the berry it is"),
                new Wets("Water", "like lettuce", "wet", 20, "very watery"),
                new Wets("Ecologic water", "ecological", "ecologic", 20, "even more watery"),
                new Wets("Caffinated water", "energized", "caffenating", 20, "what coffee drinks"),
                new Wets("Vitamin water", "like chemicals", "strange", 20, "not very watery"),
                new Hards("Mixed nuts", "dry", "crunchy", 20, "mixed"),
                new Hards("Paleo crackers", "like noraml crackers", "okay", 20, "paleolithic"),
                new Hards("Almonds", "like a waste of water", "delicious", 20, "very crunchy"),
                new Hards("Corn snacks", "nothing", "nothing", 20, "air"),

        };

        Machine machine = new Machine(products);
        for (String item: machine.getProducts()){
            System.out.println(item);
        }
        Menu menu = new Menu(machine);
        menu.getInput();
    }
}
