
package prj2;

/**
 * Represents a grocery shopping list in BasketBuddy.
 * Stores grocery items using a LinkedChain.
 *
 * @author Randy
 * @version Oct 15, 2026
 */
public class ShoppingList
{
    private String name;
    private LinkedChain<GroceryItem> items;

    /**
     * Creates an empty shopping list with a name.
     *
     * @param name the name of the shopping list
     * @throws IllegalArgumentException if name is null or blank
     */
    public ShoppingList(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "List name cannot be null or blank");
        }

        this.name = name.trim();
        items = new LinkedChain<>();
    }

    /**
     * Returns the name of the shopping list.
     *
     * @return the list name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Adds a grocery item to the end of the list.
     *
     * @param item the grocery item to add
     * @throws IllegalArgumentException if item is null
     */
    public void addItem(GroceryItem item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException(
                "Item cannot be null");
        }

        items.addToEnd(item);
    }

    /**
     * Removes the first item with a matching name.
     *
     * @param item the grocery item to remove
     * @return true if an item was removed, false otherwise
     */
    public boolean removeItem(GroceryItem item)
    {
        if (item == null)
        {
            return false;
        }

        for (int i = 0; i < items.size(); i++)
        {
            GroceryItem current = items.get(i);

            if (current.getName().equalsIgnoreCase(item.getName()))
            {
                return items.remove(current);
            }
        }

        return false;
    }

    /**
     * Gets an item at a specified position.
     *
     * @param position the zero-based index
     * @return the grocery item at that position
     * @throws IndexOutOfBoundsException if position is invalid
     */
    public GroceryItem getItem(int position)
    {
        return items.get(position);
    }

    /**
     * Returns the number of items in the list.
     *
     * @return the item count
     */
    public int getItemCount()
    {
        return items.size();
    }

    /**
     * Checks whether the shopping list is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    /**
     * Returns a string representation of the shopping list.
     *
     * @return the list name and its items
     */
    @Override
    public String toString()
    {
        return name + ": " + items.toString();
    }
}
