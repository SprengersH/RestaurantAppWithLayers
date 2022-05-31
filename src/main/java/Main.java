import domain.Entities.Restaurant;
import ui.OptionMenu;
import ui.UI;

public class Main {

    public static void main(String[] args) {

        OptionMenu menu = new OptionMenu(new UI(), new Restaurant());
        menu.run();

    }
}

