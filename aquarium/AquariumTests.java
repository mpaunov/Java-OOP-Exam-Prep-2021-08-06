package aquarium;

import org.junit.Test;
import static org.junit.Assert.*;

public class AquariumTests {

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailWhenNameIsNull() {
        new Aquarium(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailWhenNameIsWhitespaces() {
        new Aquarium("      ", 10);
    }

    @Test
    public void testSetNameShouldSetCorrectName() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        assertEquals("test_name", aquarium.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrowWithLessThanZeroCapacity() {
        new Aquarium("test_name",-1);
    }

    @Test
    public void testSetCapacityShouldSetCorrectCapacity() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        assertEquals(10, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFishShouldFailWhenMaxCapacityIsReached() {
        Aquarium aquarium = new Aquarium("test_name", 0);
        aquarium.add(new Fish("test_fish"));
    }

    @Test
    public void testAddFishShouldIncreaseFishCount() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        aquarium.add(new Fish("test_fish"));
        assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFishShouldFailIfNoSuchFishAdded() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        aquarium.remove("test_fish");
    }

    @Test
    public void testRemoveFishShouldDecreaseCount() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        aquarium.add(new Fish("test_fish"));
        aquarium.remove("test_fish");
        assertEquals(0, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishShouldFailIfNoSuchFishAdded() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        aquarium.sellFish("test_fish");
    }

    @Test
    public void testSellFishShouldSetFishAsSold() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        Fish fish = new Fish("test_fish");
        aquarium.add(fish);
        aquarium.sellFish("test_fish");
        assertFalse(fish.isAvailable());
    }

    @Test
    public void testGetInfo() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        aquarium.add(new Fish("test_fish_1"));
        aquarium.add(new Fish("test_fish_2"));
        aquarium.add(new Fish("test_fish_3"));

        String expected = "Fish available at test_name: test_fish_1, test_fish_2, test_fish_3";

        assertEquals(expected, aquarium.report());
    }
}

