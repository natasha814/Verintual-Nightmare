package src.main.java.openworld.Player;

import java.util.ArrayList;
import java.util.Scanner;

import src.main.java.openworld.Item;

public class Inventory {

    private ArrayList<Item> slots;

    public Inventory(Item item1, Item item2) {
        slots = new ArrayList<Item>(2);
        slots.add(0, item1);
        slots.add(1, item2);
    }

    public void useItem(int index) {
        String itemName = slots.get(index).name().toLowerCase();
        System.out.println("Using item " + itemName);
    }

    public void removeItem() {
        int index = -1;

        Scanner userIn = new Scanner(System.in);

        Item item1 = slots.get(0);
        Item item2 = slots.get(1);

        if (item1 == null && item2 == null) {
            System.out.println("You have no items to remove");
        } else if (item1 != null && item2 == null) {
            String a_or_an = "a ";
            if (checkFirstLetterVowel(item1)) {
                a_or_an = "an ";
            }
            System.out.println("You have " + a_or_an + item1.name().toLowerCase()
                    + " in slot 1 but slot 2 is empty, are you sure you want to remove the "
                    + item1.name().toLowerCase() + "? [Y/N]");
            char info = userIn.nextLine().toLowerCase().charAt(0);
            if (info == 'y') {
                index = 0;
            }
        } else if (item1 == null && item2 != null) {
            String a_or_an = "a ";
            if (checkFirstLetterVowel(item2)) {
                a_or_an = "an ";
            }
            System.out.println("You have nothing in slot 1 but " + a_or_an + item2.name().toLowerCase()
                    + " in slot 2, are you sure you want to remove the " + item2.name().toLowerCase() + "? [Y/N]");
            char info = userIn.nextLine().toLowerCase().charAt(0);
            if (info == 'y') {
                index = 1;
            }
        } else {
            String a_or_an1 = "a ";
            if (checkFirstLetterVowel(item1)) {
                a_or_an1 = "an ";
            }
            String a_or_an2 = "a ";
            if (checkFirstLetterVowel(item2)) {
                a_or_an2 = "an ";
            }
            System.out.println("You have " + a_or_an1 + item1.name().toLowerCase() + " in slot 1 and " + a_or_an2
                    + item2.name().toLowerCase() + " in slot 2.");
            System.out.println("Would you like to remove the item 1 [1], item 2 [2] or neither [3]");
            char info = userIn.nextLine().toLowerCase().charAt(0);
            if (info == '1') {
                index = 0;
            } else if (index == '2') {
                index = 1;
            }
        }

        if (index == 0 || index == 1) {
            Item item = slots.get(index);
            slots.remove(index);
            System.out.println("You have removed the " + item.name().toLowerCase());
        }

    }

    public void addItem(Item item) {
        if (slots.get(0) == null) {
            slots.add(0, item);
        } else if (slots.get(1) == null) {
            slots.add(1, item);
        } else {
            Scanner userIn = new Scanner(System.in);
            System.out.println(
                    "You have no space in your inventory, would you like to remove an item from your inventory? [Y/N]");

            char info = userIn.nextLine().toLowerCase().charAt(0);
            if (info == 'y') {
                removeItem();
                addItem(item);
                int itemIndex = slots.indexOf(item)+1;
                System.out.println(
                        item.name().toLowerCase() + " has been added to your inventory in slot " + (int) itemIndex);
            }

        }
    }

    public boolean searchForItem(Item item) {
        if (slots.contains(item)) {
            return true;
        }
        return false;
    }

    public ArrayList<Item> getInventory() {
        return slots;
    }

    public Item getInventoryItem(int index) {
        return slots.get(index);
    }

    private boolean checkFirstLetterVowel(Item item) {
        String itemString = item.name();
        char lower_char = Character.toLowerCase(itemString.charAt(0));
        if (lower_char == 'a' || lower_char == 'e' || lower_char == 'i' || lower_char == 'o' || lower_char == 'u') {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String resultString = "";

        Item item1 = slots.get(0);
        Item item2 = slots.get(1);
        if (item1 != null) {
            String a_or_an = "a ";
            if (checkFirstLetterVowel(slots.get(0))) {
                a_or_an = "an ";
            }
            resultString += "You have "+a_or_an+item1+" in slot 1 and ";
        } else 
            resultString += "You have nothign in slot 1 and ";
        if (item2 != null) {
            String a_or_an = "a ";
            if (checkFirstLetterVowel(slots.get(0))) {
                a_or_an = "an ";
            }
            resultString += "you have "+a_or_an+item2+" in slot 2.";
        } else  
            resultString += "you have nothing in slot 2.";
        return resultString;
    }

}
