package prj2;

import student.TestCase;

/**
 * Tests the GroceryItem class.
 *
 * @author Wayne
 * @version Oct 15, 2026
 */
public class GroceryItemTest extends TestCase
{
    /**
     * Tests creating an item and retrieving its name.
     */
    public void testConstructorAndGetName()
    {
        GroceryItem item = new GroceryItem("Milk");

        assertEquals("Milk", item.getName());
    }

    /**
     * Tests that whitespace around a name is removed.
     */
    public void testNameIsTrimmed()
    {
        GroceryItem item = new GroceryItem("  Bread  ");

        assertEquals("Bread", item.getName());
    }

    /**
     * Tests that null and blank names are rejected.
     */
    public void testInvalidNames()
    {
        Exception exception = null;

        try
        {
            new GroceryItem(null);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        exception = null;

        try
        {
            new GroceryItem("   ");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }

    /**
     * Tests the string representation.
     */
    public void testToString()
    {
        GroceryItem item = new GroceryItem("Eggs");

        assertEquals("Eggs", item.toString());
    }
}
