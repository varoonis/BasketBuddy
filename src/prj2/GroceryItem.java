package prj2;

/**
 * Represents an item in a BasketBuddy shopping list.
 *
 * @author Wayne
 * @version Oct 15, 2026
 */
public class GroceryItem
{
    private String name;

    /**
     * Creates a grocery item with a name.
     *
     * @param name the item name
     * @throws IllegalArgumentException if name is null or blank
     */
    public GroceryItem(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "Item name cannot be null or blank");
        }

        this.name = name.trim();
    }

    /**
     * Returns the item name.
     *
     * @return the item name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the item name as its string representation.
     *
     * @return the item name
     */
    @Override
    public String toString()
    {
        return name;
    }
}
