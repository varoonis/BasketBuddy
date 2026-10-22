package prj2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author varoonisawant
 * @version Oct 22, 2026
 */
public class BasketBuddyGUI
    extends JFrame
{
    private final BasketBuddy app;

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final DefaultListModel<String> itemModel = new DefaultListModel<>();
    private final JList<String> listView = new JList<>(listModel);
    private final JList<String> itemView = new JList<>(itemModel);
    private final JLabel itemsTitle = new JLabel("Items");
    private final JTextField itemField = new JTextField(15);

    /**
     * Builds the window around an existing BasketBuddy.
     *
     * @param app
     *            the model this window displays and edits
     */
    public BasketBuddyGUI(BasketBuddy app)
    {
        super("BasketBuddy");
        this.app = app;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2, 10, 0));
        add(buildListPanel());
        add(buildItemPanel());
        setPreferredSize(new Dimension(650, 400));
        pack();
        setLocationRelativeTo(null);
        refreshLists();
    }


    private JPanel buildListPanel()
    {
        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listView.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
            {
                refreshItems();
            }
        });

        JButton newButton = new JButton("New list");
        newButton.addActionListener(e -> createList());
        JButton removeButton = new JButton("Remove list");
        removeButton.addActionListener(e -> removeList());

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(newButton);
        buttons.add(removeButton);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        panel.add(new JLabel("Shopping lists"), BorderLayout.NORTH);
        panel.add(new JScrollPane(listView), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        return panel;
    }


    private JPanel buildItemPanel()
    {
        itemView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addItem());
        itemField.addActionListener(e -> addItem()); // Enter key
        JButton removeButton = new JButton("Remove selected");
        removeButton.addActionListener(e -> removeItem());

        JPanel addRow = new JPanel(new FlowLayout());
        addRow.add(itemField);
        addRow.add(addButton);

        JButton sortButton = new JButton("Sort A-Z");
        sortButton.addActionListener(e -> sortItems());

        JPanel actionRow = new JPanel(new FlowLayout());
        actionRow.add(removeButton);
        actionRow.add(sortButton);

        JPanel south = new JPanel(new GridLayout(2, 1));
        south.add(addRow);
        south.add(actionRow);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
        panel.add(itemsTitle, BorderLayout.NORTH);
        panel.add(new JScrollPane(itemView), BorderLayout.CENTER);
        panel.add(south, BorderLayout.SOUTH);
        return panel;
    }

    // ---- actions ---------------------------------------------------------


    private void createList()
    {
        String name = JOptionPane.showInputDialog(this, "List name:");
        if (name == null)
        {
            return; // cancelled
        }
        try
        {
            app.createList(name);
            refreshLists();
            listView.setSelectedIndex(app.getListCount() - 1);
        }
        catch (IllegalArgumentException ex)
        {
            showError("Please enter a valid list name (it cannot be blank).");
        }
    }


    private void removeList()
    {
        int index = listView.getSelectedIndex();
        if (index < 0)
        {
            showError("Select a list to remove first.");
            return;
        }
        app.removeList(index);
        refreshLists();
    }


    private void addItem()
    {
        ShoppingList list = selectedList();
        if (list == null)
        {
            showError("Select a list first.");
            return;
        }
        try
        {
            list.addItem(new GroceryItem(itemField.getText()));
            itemField.setText("");
            refreshLists(); // updates the "(n items)" count
        }
        catch (IllegalArgumentException ex)
        {
            showError("Please enter a valid item name (it cannot be blank).");
        }
    }


    private void removeItem()
    {
        ShoppingList list = selectedList();
        int itemIndex = itemView.getSelectedIndex();
        if (list == null || itemIndex < 0)
        {
            showError("Select an item to remove first.");
            return;
        }
        list.removeItem(list.getItem(itemIndex));
        refreshLists();
    }


    private void sortItems()
    {
        ShoppingList list = selectedList();
        if (list == null)
        {
            showError("Select a list first.");
            return;
        }
        list.sortItems();
        refreshItems();
    }

    // ---- display helpers -------------------------------------------------


    private ShoppingList selectedList()
    {
        int index = listView.getSelectedIndex();
        return index < 0 ? null : app.getList(index);
    }


    /** Rebuilds the left list, keeping the current selection if possible. */
    private void refreshLists()
    {
        int selected = listView.getSelectedIndex();
        listModel.clear();
        for (int i = 0; i < app.getListCount(); i++)
        {
            ShoppingList list = app.getList(i);
            listModel.addElement(
                list.getName() + " (" + list.getItemCount() + " items)");
        }
        if (selected >= 0 && selected < listModel.size())
        {
            listView.setSelectedIndex(selected);
        }
        refreshItems();
    }


    /** Rebuilds the right list for whichever shopping list is selected. */
    private void refreshItems()
    {
        itemModel.clear();
        ShoppingList list = selectedList();
        if (list == null)
        {
            itemsTitle.setText("Items");
            return;
        }
        itemsTitle.setText("Items in " + list.getName());
        for (int i = 0; i < list.getItemCount(); i++)
        {
            itemModel.addElement(list.getItem(i).toString());
        }
    }


    private void showError(String message)
    {
        JOptionPane.showMessageDialog(
            this,
            message,
            "BasketBuddy",
            JOptionPane.WARNING_MESSAGE);
    }


    /**
     * Launches the GUI.
     *
     * @param args
     *            not used
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(
            () -> new BasketBuddyGUI(new BasketBuddy()).setVisible(true));
    }
}
