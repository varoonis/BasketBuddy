package prj2;

import java.io.PrintStream;
import java.util.Scanner;
// -------------------------------------------------------------------------
/**
 *  main class
 * 
 *  @author varoonisawant
 *  @version Oct 15, 2026
 */
public class BasketBuddy
{
    private final LinkedChain<ShoppingList> shoppingLists;
    
    /**
     * creates basket buddy with no shopping lists
     */
    public BasketBuddy()
    {
        shoppingLists = new LinkedChain<>();
    }
    
    /**
     * creates a new shopping list and stores it 
     * @param name the list name
     * @return the new lsit
     * @throws IllegalArgumentException if name is null or blank
     */
    public ShoppingList createList(String name)
    {
        ShoppingList list = new ShoppingList(name);
        shoppingLists.add(list);
        return list;
    }
    
    /**
     * gets the shopping list 
     * @param position the 0-based position
     * @return the list
     * @throws IndexOutOfBoundsException if position is invalid
     */
    public ShoppingList getList(int position)
    {
        return shoppingLists.get(position);
    }
    
    /**
     * removes the shopping list 
     * @param position the 0-based position
     * @return the removed list
     * @throws IndexOutOfBoundsException if position is invalid
     */
    public ShoppingList removeList(int position)
    {
        ShoppingList list = shoppingLists.get(position);
        shoppingLists.remove(list);
        return list;
    }
    
    /**
     * @return the number of shopping lists.
     */
    public int getListCount()
    {
        return shoppingLists.size();
    }
    
    /**
     * Runs the menu loop until the user exits or input ends.
     *
     * @param in  where user input is read from
     * @param out where prompts and messages are written
     */
    public void run(Scanner in, PrintStream out) {
        out.println("Welcome to BasketBuddy!");
        boolean running = true;
        while (running) {
            printMenu(out);
            String choice = readLine(in, out, "Choose an option: ");
            if (choice == null) {
                break; // input ended
            }
            switch (choice.trim()) {
                case "1": handleCreateList(in, out); break;
                case "2": handleViewLists(out); break;
                case "3": handleRemoveList(in, out); break;
                case "4": handleAddItem(in, out); break;
                case "5": handleRemoveItem(in, out); break;
                case "6": handleViewItems(in, out); break;
                case "7": handleGetItem(in, out); break;
                case "0": running = false; break;
                default:
                    out.println("Invalid choice. Please enter a number from 0 to 7.");
            }
        }
        out.println("Goodbye!");
    }

    /**
     * Program entry point.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        new BasketBuddy().run(new Scanner(System.in), System.out);
    }

    private void printMenu(PrintStream out) {
        out.println();
        out.println("1. Create a shopping list");
        out.println("2. View shopping lists");
        out.println("3. Remove a shopping list");
        out.println("4. Add an item to a list");
        out.println("5. Remove an item from a list");
        out.println("6. View items in a list");
        out.println("7. Get an item by position");
        out.println("0. Exit");
    }

    private void handleCreateList(Scanner in, PrintStream out) {
        String name = readLine(in, out, "List name: ");
        if (name == null) {
            return;
        }
        try {
            ShoppingList list = createList(name);
            out.println("Created list \"" + list.getName() + "\".");
        }
        catch (IllegalArgumentException e) {
            out.println("Please enter a valid list name (it cannot be blank).");
        }
    }

    private void handleViewLists(PrintStream out) {
        if (shoppingLists.isEmpty()) {
            out.println("You have no shopping lists yet.");
            return;
        }
        for (int i = 0; i < shoppingLists.size(); i++) {
            ShoppingList list = shoppingLists.get(i);
            out.println((i + 1) + ". " + list.getName()
                + " (" + list.getItemCount() + " items)");
        }
    }

    private void handleRemoveList(Scanner in, PrintStream out) {
        Integer position = readPosition(in, out, "List number to remove: ");
        if (position == null) {
            return;
        }
        try {
            ShoppingList removed = removeList(position);
            out.println("Removed list \"" + removed.getName() + "\".");
        }
        catch (IndexOutOfBoundsException e) {
            out.println("Invalid position. Please enter a valid list number.");
        }
    }

    private void handleAddItem(Scanner in, PrintStream out) {
        ShoppingList list = selectList(in, out);
        if (list == null) {
            return;
        }
        String name = readLine(in, out, "Item name: ");
        if (name == null) {
            return;
        }
        try {
            GroceryItem item = new GroceryItem(name);
            list.addItem(item);
            out.println("Added \"" + item.getName() + "\" to " + list.getName() + ".");
        }
        catch (IllegalArgumentException e) {
            out.println("Please enter a valid item name (it cannot be blank).");
        }
    }

    private void handleRemoveItem(Scanner in, PrintStream out) {
        ShoppingList list = selectList(in, out);
        if (list == null) {
            return;
        }
        String name = readLine(in, out, "Item name to remove: ");
        if (name == null) {
            return;
        }
        try {
            GroceryItem item = new GroceryItem(name);
            boolean wasEmpty = list.isEmpty();
            if (list.removeItem(item)) {
                out.println("Removed \"" + item.getName() + "\".");
            }
            else if (wasEmpty) {
                out.println("The list is empty.");
            }
            else {
                out.println("Item not found.");
            }
        }
        catch (IllegalArgumentException e) {
            out.println("Please enter a valid item name (it cannot be blank).");
        }
    }

    private void handleViewItems(Scanner in, PrintStream out) {
        ShoppingList list = selectList(in, out);
        if (list == null) {
            return;
        }
        if (list.isEmpty()) {
            out.println("The list is empty.");
            return;
        }
        out.println(list.getName() + ":");
        for (int i = 0; i < list.getItemCount(); i++) {
            out.println("  " + (i + 1) + ". " + list.getItem(i));
        }
    }

    private void handleGetItem(Scanner in, PrintStream out) {
        ShoppingList list = selectList(in, out);
        if (list == null) {
            return;
        }
        Integer position = readPosition(in, out, "Item number: ");
        if (position == null) {
            return;
        }
        try {
            out.println("Item " + (position + 1) + ": " + list.getItem(position));
        }
        catch (IndexOutOfBoundsException e) {
            out.println("Invalid position. Please enter a valid item number.");
        }
    }

 
    private ShoppingList selectList(Scanner in, PrintStream out) {
        Integer position = readPosition(in, out, "List number: ");
        if (position == null) {
            return null;
        }
        try {
            return getList(position);
        }
        catch (IndexOutOfBoundsException e) {
            out.println("Invalid position. Please enter a valid list number.");
            return null;
        }
    }

    /** Reads a 1-based number and returns it 0-based, or null if not a number. */
    private Integer readPosition(Scanner in, PrintStream out, String prompt) {
        String text = readLine(in, out, prompt);
        if (text == null) {
            return null;
        }
        try {
            return Integer.parseInt(text.trim()) - 1;
        }
        catch (NumberFormatException e) {
            out.println("Please enter a number.");
            return null;
        }
    }

    /** Prints a prompt and reads one line, or returns null if input ended. */
    private String readLine(Scanner in, PrintStream out, String prompt) {
        out.print(prompt);
        if (!in.hasNextLine()) {
            return null;
        }
        return in.nextLine();
    }
}
    






















