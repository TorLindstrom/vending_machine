package se.lexicon.tor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MachineTest
{
    Machine machine;

    @Before
    public void setup(){
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
        machine = new Machine(products);
    }

    @Test
    public void payValid()
    {
        machine.addCurrency(10);

        assertEquals(10, machine.getBalance());
    }

    @Test
    public void requestProductValidGreens(){
        machine.addCurrency(20);
        Products product = machine.request(2);

        assertTrue(product.consume().contains("Broccoli"));
        assertTrue(product.consume().contains("eat"));
    }

    @Test
    public void requestProductValidHards(){
        machine.addCurrency(20);
        Products product = machine.request(11);

        assertTrue(product.consume().contains("Mixed nuts"));
        assertTrue(product.consume().contains("crunch"));
    }

    @Test
    public void requestProductValidWets(){
        machine.addCurrency(20);
        Products product = machine.request(8);

        assertTrue(product.consume().contains("Ecologic water"));
        assertTrue(product.consume().contains("drink"));
    }

    @Test
    public void requestProductInsufficient(){
        Products product = machine.request(2);

        assertNull(product);
    }

    @Test
    public void requestProductInvalid(){
        machine.addCurrency(20);
        Products product = machine.request(16);

        assertNull(product);
    }

    @Test
    public void endSession() throws InterruptedException
    {
        machine.addCurrency(500);
        int returned = machine.endSession();

        assertEquals(500, returned);
        assertEquals(0, machine.getBalance());
    }

    @Test
    public void endSessionMuch() throws InterruptedException
    {
        machine.addCurrency(2786);
        int returned = machine.endSession();

        assertEquals(2786, returned);
        assertEquals(0, machine.getBalance());
    }

    @Test
    public void getDescriptionValid()
    {
        assertTrue(machine.getDescription(5).contains("Lettuce"));
    }

    @Test
    public void getDescriptionInvalid()
    {
        assertNull(machine.getDescription(16));
    }

    @Test
    public void getProducts()
    {
        assertEquals(14, machine.getProducts().length);
        assertTrue(machine.getProducts()[0].contains("Kale"));
        assertTrue(machine.getProducts()[13].contains("Corn snacks"));
    }

}
