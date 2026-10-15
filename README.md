# BasketBuddy
CS 2114 Project 2 - BasketBuddy - group 85
## Project Overview

BasketBuddy is a Java console application that helps users create and manage
grocery shopping lists. One generic `LinkedChain<T>` stores two element types:
`LinkedChain<ShoppingList>` holds every list (in `BasketBuddy`) and each list
keeps its own `LinkedChain<GroceryItem>` (in `ShoppingList`).

## Features

- Create, view, and remove multiple shopping lists
- Add and remove grocery items in a list
- View all items in a list, or retrieve one by position
- Validate input so blank names, missing items, empty lists, and invalid
  positions show a message instead of crashing

## Project layout

```
src/   LinkedChain.java  GroceryItem.java  ShoppingList.java  BasketBuddy.java
test/  GroceryItemTest.java  ShoppingListTest.java  BasketBuddyTest.java  LinkedChainUsageTest.java
lib/   junit-4.13.2.jar  hamcrest-core-1.3.jar
```

## How to compile and run

Requires Java 11 or newer. Commands are run from the project folder
(on Windows, replace `:` with `;` in the classpath).

**Compile the program**
```
javac -d bin src/*.java
```

**Run the program**
```
java -cp bin BasketBuddy
```

**Compile and run the tests (JUnit 4)**
```
javac -d bin -cp bin:lib/junit-4.13.2.jar test/*.java
java -cp bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore GroceryItemTest ShoppingListTest BasketBuddyTest LinkedChainUsageTest
```
Expected result: `OK (60 tests)`.

**In Eclipse:** put `src` and `test` on the build path and add the JUnit 4
library. Right-click `BasketBuddy.java` → Run As → Java Application to run
the program, and right-click the `test` folder → Run As → JUnit Test to run
the tests.

### Menu
```
1. Create a shopping list      5. Remove an item from a list
2. View shopping lists         6. View items in a list
3. Remove a shopping list      7. Get an item by position
4. Add an item to a list       0. Exit
```
List and item numbers are shown starting at 1 and converted to the 0-based
positions used by the classes.

---

## Reflection

### What changed from our specification?

**Classes.** We built the four classes in the spec (`GroceryItem`,
`ShoppingList`, `BasketBuddy`, and the provided `LinkedChain`) plus the three
test classes. We added one more test class, `LinkedChainUsageTest`, because our
test plan has a "LinkedChain Tests" section that needed somewhere to live; it
checks how our app types behave inside the chain, not the chain's internals.

**Method signatures.** `GroceryItem` and `ShoppingList` match the spec exactly.
We added `toString()` to `ShoppingList` (returns the list name) for display.
The biggest change is `BasketBuddy`. The spec only listed `main`, with all the
logic in a while loop. That would have made the class impossible to test with
JUnit, and the rubric requires tests for every public app method. So we moved
the logic into an object with public methods:

| Method | Purpose |
|---|---|
| `BasketBuddy()` | creates an empty `LinkedChain<ShoppingList>` |
| `createList(String)` | creates and stores a list |
| `getList(int)` | gets a list by 0-based position |
| `removeList(int)` | removes a list by position |
| `getListCount()` | number of lists |
| `run(Scanner, PrintStream)` | the while-loop menu |
| `main(String[])` | calls `run` with `System.in` / `System.out` |

Passing a `Scanner` and `PrintStream` into `run` lets the tests feed scripted
input and read the output, which turned our "BasketBuddy Manual Tests" into
automated JUnit tests.

**Where validation lives.** This matches the spec's table:

| Bad input | Caught in | What the user sees |
|---|---|---|
| Blank item name | `GroceryItem` constructor | "Please enter a valid item name" |
| Blank list name | `ShoppingList` constructor | "Please enter a valid list name" |
| Remove a missing item | `ShoppingList.removeItem()` returns false | "Item not found." |
| Remove from an empty list | `ShoppingList.removeItem()` returns false | "The list is empty." |
| Remove the same item twice | `ShoppingList.removeItem()` | first removes one, later ones return false |
| Invalid position | `LinkedChain.get()` throws `IndexOutOfBoundsException` | "Invalid position…" |

`BasketBuddy` only catches these exceptions and prints a message. Two cases
the spec did not list are handled there: non-numeric input for a number
("Please enter a number.") and the input stream ending (the program exits
cleanly instead of crashing).

**Removing a list.** `ShoppingList` does not override `equals`, so lists are
compared by identity. `removeList(position)` gets the list with `get`
and then removes that exact object with `remove`. This means two lists with
the same name can exist without removing the wrong one.

**Test cases.** All cases in the test plan are implemented. We added a few:
removing an item by a different-but-equal `GroceryItem` object, `getItem` with
a negative position, and running out of input.

### What changed in the LinkedChain?

We used the provided `LinkedChain` **unchanged**. Our app classes only call
its public methods `add`, `remove(T)`, `get`, `size` and `isEmpty`, and all
application logic (name rules, empty-list messages, menu handling) lives in
our own classes.

The provided class differs from what our spec's method signatures assumed,
so we changed our code instead of the chain:

- Our spec named the position and size methods `getEntry(int)` and
  `getSize()`. The provided class calls them `get(int)` and `size()`, so
  `ShoppingList` and `BasketBuddy` call those.
- Our spec said `remove`, `contains` and `count` throw
  `IllegalArgumentException` for null. The provided class returns `false`
  or `0` instead (only `add` throws). Because of this, `ShoppingList`
  rejects null items itself before calling the chain, so our app still
  behaves the way the spec describes. We updated `LinkedChainUsageTest` to
  expect the provided class's real null behavior.
- `add` returns `boolean` instead of `void`; we ignore the return value.

### What happened to our unknowns?

- **How to test a while-loop UI with JUnit** — figured out: pass a `Scanner`
  and `PrintStream` into `run()` and check the output text.
- **How to make removal find the right grocery item** — figured out:
  overriding `equals` and `hashCode` in `GroceryItem` lets the chain match by
  name, and `remove` only removes the first match.
- *[Add any other unknowns from our scope and say whether they're solved or
  still open.]*

### What doesn't work yet?

- All MVP features in the spec work, and all 60 tests pass.
- Viewing a list calls `getItem(i)` in a loop, which is O(n²) as noted in the
  spec. That is fine for grocery-sized lists but is not efficient.
- Item names are case-sensitive ("milk" and "Milk" are different items), as
  the spec says. Users may find this surprising.
- There is no save/load, so lists are lost when the program exits.
- No stretch goals (sorting, budget, store-specific lists) are started.

---

## Next Steps

### What is left to finish the MVP?
1. Run the manual tests from the spec once more by hand in the console.
2. Prevent duplicate list names, or show a warning when one is created.
3. Polish menu messages and the item display.

### Which stretch goals will we attempt?
- **Committing to:** sorting items alphabetically, and organizing by budget
  (adding an optional price to `GroceryItem` and a list total).
- **Dropping (for now):** store-specific list generation. It needs store data
  we don't have and is the largest of the three.

### What new tests will we need?
- Sorting: sorting an empty list, one item, already-sorted items, and
  duplicates.
- Budget: `GroceryItem` rejecting a negative or non-numeric price; list total
  on an empty list; total after removing an item; `BasketBuddy` handling
  "abc" as a price without crashing.
- Duplicate list names: creating "Weekly" twice.

### Who does what?
| Item | Owner |
|---|---|
| Duplicate list names + tests | Randy |
| Sorting + tests | Varooni |
| Budget (price, total) + tests | Wayne and Randy |
| Final manual test pass and README update | Varooni |

---

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
