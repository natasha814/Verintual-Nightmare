package openworld.Location;

import openworld.Player;
import openworld.Item;

public class Forest extends Location {

    private Item item;

    public Forest(String name, String description, Item item) {
        super(name, description);
        this.item = item;
    }

    @Override
    public void enter(Player player) {
        System.out.println("You enter the " + getName() + ": " + getDescription());
        if (item != null) {
            String a_or_an = "a ";
            if (checkFirstLetterVowel(item)) {
                a_or_an = "an ";
            }
            System.out.println("You see " + a_or_an + item + " here.");
        }
        // will add code to pick up item etc
    }

    private boolean checkFirstLetterVowel(Item item) {
        String itemString = item.name();
        char lower_char = Character.toLowerCase(itemString.charAt(0));
        if (lower_char == 'a' || lower_char == 'e' || lower_char == 'i' || lower_char == 'o' || lower_char == 'u') {
            return true;
        }
        return false;
    }

}
