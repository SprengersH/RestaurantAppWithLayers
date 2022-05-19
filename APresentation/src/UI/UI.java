package UI;

public class UI {

    private final static String SPACING = "**********************************************";


    public void showMainMenu() {
        System.out.println(SPACING);
        System.out.println("*    RESTAURANT NAME HERE                    *");
        System.out.println("*    1. Order                                *");
        System.out.println("*    2. Menu                                 *");
        System.out.println("*    3. Check current table availability     *");
        System.out.println("*    4. Checkout Table                       *");
        System.out.println("*    5. Sales Records                        *");
        System.out.println(SPACING);
        System.out.println("*    Select your option:                     *");


    }

    public void showMenuPage() {
        System.out.println(SPACING);
        System.out.println("*    MENU PAGE                               *");
        System.out.println("*    1. Display available menu's             *");
        System.out.println("*    2. Display current menu                 *");
        System.out.println("*    3. Change menu's                        *");
        System.out.println("*    4. Go back to the main page             *");
        System.out.println(SPACING);
        System.out.println("*    Select your option:                     *");
    }


    public void showCourses() {
        System.out.println(SPACING);
        System.out.println("*    Course selector:                        *");
        System.out.println("*    1. Drinks                               *");
        System.out.println("*    2. Main Courses                         *");
        System.out.println("*    3. Side Dishes                          *");
        System.out.println("*    4. Go back to the main page             *");
        System.out.println(SPACING);
        System.out.println("*    Select your option:                     *");
    }
    public void showDrinksPage() {
        System.out.println(SPACING);
        System.out.println("*    DRINKS PAGE                             *");
        System.out.println(SPACING);
        System.out.println("*    Select your option and hit enter or     *");
        System.out.println("*    0 to end ordering and go back to main   *");
        System.out.println(SPACING);
    }

    public void showMainCourses() {
        System.out.println(SPACING);
        System.out.println("*    Main Courses                             *");
        System.out.println(SPACING);
        System.out.println("*    Select your option or 0 to cancel order: *");
    }

    public void showSideDishes() {
        System.out.println(SPACING);
        System.out.println("*    Side Dishes                            *");
        System.out.println(SPACING);
        System.out.println("*    Select your option or 0 to cancel order:*");
    }
}

