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

The implementation follows the original project specification. No changes were made to the planned class design, method signatures, or input validation requirements.

The application uses the provided LinkedChain implementation through its public API and reuses the generic data structure to store both grocery items and shopping lists. Application logic remains within the appropriate application classes.

The testing approach follows the requirements of the specification, including testing normal cases and invalid-input cases.


## Attribution

| File | Who | How |
|---|---|---|
| `LinkedChain.java` | CS 2114 staff (provided) | Used unchanged |
| `GroceryItem.java` | TBD | Implemented, revised and reviewed |
| `ShoppingList.java` | TBD | Implemented, revised and reviewed |
| `BasketBuddy.java` | Varooni Sawant | Implemented, revised and reviewed |
| `GroceryItemTest.java` | TBD | Implemented, revised and reviewed |
| `ShoppingListTest.java` | TBD | Implemented, revised and reviewed |
| `README.md` | Varooni | revised and reviewed|
