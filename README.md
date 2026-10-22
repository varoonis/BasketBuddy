# BasketBuddy
CS 2114 Project 2 - BasketBuddy - group 85
## Project Overview

BasketBuddy is a Java application that helps users create and manage grocery shopping lists. Users can organize grocery items into shopping lists and manage the items they need to buy.

The application uses a generic linked-chain data structure to store grocery items and shopping lists.

## Features

- Create, view, and remove multiple shopping lists
- Add and remove grocery items in a list
- View all items in a list, or retrieve one by position
- Validate input so blank names, missing items, empty lists, and invalid
  positions show a message instead of crashing

## Project layout

- BasketBuddy.java — Contains the main method and user interface for interacting with the application.
- GroceryItem.java — Represents an individual grocery item and manages its name.
- ShoppingList.java — Represents a shopping list and manages its grocery items.
- LinkedChain.java — Provides the generic linked-chain data structure used to store grocery items and shopping lists.
- GroceryItemTest.java — Contains JUnit tests for the GroceryItem class.
- ShoppingListTest.java — Contains JUnit tests for the ShoppingList class.
- README.md — Documents the project design, implementation, testing, and team contributions.

## Data Structure 

BasketBuddy uses the provided generic LinkedChain<T> implementation to store application data.

The same generic data structure is reused for two different element types:

- LinkedChain<GroceryItem> stores the grocery items belonging to a shopping list.
- LinkedChain<ShoppingList> stores the shopping lists managed by the application.

The application classes interact with LinkedChain through its public API. Application logic is implemented in the appropriate application classes rather than by modifying the internal implementation of the linked chain.

## Reflection

BasketBuddy is organized into three main classes: GroceryItem, ShoppingList, and BasketBuddy. Each class has its own responsibility. GroceryItem represents a grocery item, ShoppingList manages the items in a list, and BasketBuddy manages the shopping lists.

We use the provided LinkedChain class to store both grocery items and shopping lists. This lets us reuse the same generic data structure instead of creating separate data structures for each type. The application classes use the public methods of LinkedChain to add, remove, and access items.

Our goal was to follow the project specification while keeping the code organized and easy to understand. The provided LinkedChain.java file was used without changes except the package prj2; was added. We did not run into any major challenges while working on the project. We focused on following the project specifications, keeping each class organized, and using the provided LinkedChain implementation for both grocery items and shopping lists. This helped us understand how a generic data structure can be reused for different types of objects.

We still need to make sure the application handles invalid input correctly and that all the required JUnit tests pass. We will also check for any unfinished features or issues before submitting the project.

## Next Steps
After completing BasketBuddy, we can look at ways to improve the application and make it more useful. Some possible improvements include:

- Add sorting: Allow users to sort grocery items alphabetically to make shopping lists easier to navigate.
- Add budget tracking: Let users set a budget for each shopping list and keep track of estimated grocery costs.
- Add store-specific lists: Allow users to organize grocery items by store so they can plan their shopping trips more efficiently.
- Improve the user interface: Make the menu easier to navigate and provide clearer messages when users add, remove, or view items.
- Expand testing: Add more tests to cover edge cases and make sure future changes do not break existing functionality.

These improvements could make BasketBuddy more convenient to use while building on the functionality completed for this project.

## Attribution

| File | Who | How |
|---|---|---|
| `LinkedChain.java` | CS 2114 staff (provided) | Used unchanged |
| `GroceryItem.java` | Wayne Clue | Implemented, revised and reviewed |
| `ShoppingList.java` | Randy Park | Implemented, revised and reviewed |
| `BasketBuddy.java` | Varooni Sawant | Implemented, revised and reviewed |
| `BasketBuddyGUI.java` | Varooni Sawant | Implemented, revised and reviewed |
| `GroceryItemTest.java` | Wayne Clue | Implemented, revised and reviewed |
| `ShoppingListTest.java` | Randy Park | Implemented, revised and reviewed |
| `README.md` | Varooni | revised and reviewed|
