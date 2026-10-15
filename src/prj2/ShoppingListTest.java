
package prj2;

import student.TestCase;

/**
 * Tests the ShoppingList class for BasketBuddy.
 *
 * @author Randy
 * @version Oct 15, 2026
 */
public class ShoppingListTest extends TestCase
{
    private ShoppingList list;
    private GroceryItem milk;
    private GroceryItem bread;
    private GroceryItem eggs;

    /**
     * Sets up the test objects before each test.
     */
    public void setUp()
    {
        list = new ShoppingList("Weekly Groceries");
        milk = new GroceryItem("Milk");
        bread = new GroceryItem("Bread");
        eggs = new GroceryItem("Eggs");
    }

    /**
     * Tests the constructor and getName.
     */
    public void testConstructor()
    {
        assertEquals("Weekly Groceries", list.getName());
        assertEquals(0, list.getItemCount());
        assertTrue(list.isEmpty());
    }

    /**
     * Tests invalid shopping list names.
     */
    public void testInvalidName()
    {
        Exception exception = null;

        try
        {
            new ShoppingList(null);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        exception = null;

        try
        {
            new ShoppingList("   ");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }

    /**
     * Tests adding grocery items.
     */
    public void testAddItem()
    {
        list.addItem(milk);
        list.addItem(bread);

        assertEquals(2, list.getItemCount());
        assertFalse(list.isEmpty());
        assertEquals(milk, list.getItem(0));
        assertEquals(bread, list.getItem(1));
    }

    /**
     * Tests adding a null item.
     */
    public void testAddNullItem()
    {
        Exception exception = null;

        try
        {
            list.addItem(null);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals(0, list.getItemCount());
    }

    /**
     * Tests removing an existing item.
     */
    public void testRemoveItem()
    {
        list.addItem(milk);
        list.addItem(bread);
        list.addItem(eggs);

        assertTrue(list.removeItem(bread));
        assertEquals(2, list.getItemCount());
        assertEquals(milk, list.getItem(0));
        assertEquals(eggs, list.getItem(1));
    }

    /**
     * Tests removing an item using a matching name.
     */
    public void testRemoveMatchingName()
    {
        list.addItem(milk);

        GroceryItem anotherMilk = new GroceryItem("milk");

        assertTrue(list.removeItem(anotherMilk));
        assertTrue(list.isEmpty());
    }

    /**
     * Tests removing an item that does not exist.
     */
    public void testRemoveMissingItem()
    {
        list.addItem(milk);

        assertFalse(list.removeItem(bread));
        assertEquals(1, list.getItemCount());
    }

    /**
     * Tests removing from an empty list.
     */
    public void testRemoveEmptyList()
    {
        assertFalse(list.removeItem(milk));
        assertFalse(list.removeItem(null));
        assertTrue(list.isEmpty());
    }

    /**
     * Tests retrieving grocery items.
     */
    public void testGetItem()
    {
        list.addItem(milk);
        list.addItem(bread);

        assertEquals(milk, list.getItem(0));
        assertEquals(bread, list.getItem(1));
    }

    /**
     * Tests an invalid item position.
     */
    public void testInvalidPosition()
    {
        Exception exception = null;

        try
        {
            list.getItem(-1);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        exception = null;

        try
        {
            list.getItem(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }

    /**
     * Tests the item count.
     */
    public void testGetItemCount()
    {
        assertEquals(0, list.getItemCount());

        list.addItem(milk);
        assertEquals(1, list.getItemCount());

        list.addItem(bread);
        assertEquals(2, list.getItemCount());

        list.removeItem(milk);
        assertEquals(1, list.getItemCount());
    }

    /**
     * Tests the isEmpty method.
     */
    public void testIsEmpty()
    {
        assertTrue(list.isEmpty());

        list.addItem(milk);
        assertFalse(list.isEmpty());

        list.removeItem(milk);
        assertTrue(list.isEmpty());
    }

    /**
     * Tests the string representation.
     */
    public void testToString()
    {
        assertEquals("Weekly Groceries: []", list.toString());

        list.addItem(milk);
        assertEquals("Weekly Groceries: [Milk]",
            list.toString());
    }
}
