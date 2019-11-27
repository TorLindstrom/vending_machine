package se.lexicon.tor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuTest
{
    public static Menu menu;
    public static Machine machine;

    @Before
    public void init(){
        machine = new Machine(new Greens("Kale", "metallic", "awful", 20, "leatherface of vegetables"),
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
                new Hards("Corn snacks", "nothing", "nothing", 20, "air"));

        menu = new Menu(machine);
    }

    @Test
    public void payValid() throws InterruptedException
    {
        int i = menu.start(new String[]{"pay", "100"});

        assertEquals(1, i);
        assertEquals(100, machine.getBalance());
    }

    @Test
    public void payInvalid() throws InterruptedException
    {
        int i = menu.start(new String[]{"pay", "a"});

        assertEquals(-1, i);
        assertEquals(0, machine.getBalance());
    }

    @Test
    public void orderInsufficient() throws InterruptedException
    {
        int i = menu.start(new String[]{"order", "2"});

        assertEquals(-1, i);
        assertEquals(0, machine.getBalance());
    }

    @Test
    public void orderSufficient() throws InterruptedException
    {
        menu.start(new String[]{"pay", "100kr"});
        int i = menu.start(new String[]{"order", "2"});

        assertEquals(1, i);
        assertEquals(80, machine.getBalance());
    }

    @Test
    public void orderNotThere() throws InterruptedException
    {
        menu.start(new String[]{"pay", "100kr"});
        int i = menu.start(new String[]{"order", "30"});

        assertEquals(-1, i);
        assertEquals(100, machine.getBalance());
    }

    @Test
    public void orderNotNumber() throws InterruptedException
    {
        menu.start(new String[]{"pay", "100kr"});
        int i = menu.start(new String[]{"order", "a"});

        assertEquals(-1, i);
        assertEquals(100, machine.getBalance());
    }

    @Test
    public void describeThere() throws InterruptedException
    {
        int i = menu.start(new String[]{"description", "3"});

        assertEquals(1, i);
    }

    @Test
    public void describeNotThere() throws InterruptedException
    {
        int i = menu.start(new String[]{"description", "30"});

        assertEquals(-1, i);
    }

    @Test
    public void describeNotNumber() throws InterruptedException
    {
        int i = menu.start(new String[]{"description", "a"});

        assertEquals(-1, i);
    }

    @Test
    public void notAnOrder() throws InterruptedException
    {
        int i = menu.start(new String[]{"purf", "100kr"});

        assertEquals(-1, i);
    }
}
