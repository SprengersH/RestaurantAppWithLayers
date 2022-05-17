package Logic;

public class Item {

    private int menuItemID;
    private int menuNumber;
    private String courseType;
    private String name;
    private String description;
    private double price;

    public Item(int menuItemID, int menuNumber, String courseType, String name, String description, double price) {
        this.menuItemID = menuItemID;
        this.menuNumber = menuNumber;
        this.courseType = courseType;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item() {
        this.menuItemID = menuItemID;
        this.menuNumber = menuNumber;
        this.courseType = courseType;
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public int getMenuNumber() {
        return menuNumber;
    }


    public String getCourseType() {
        return courseType;
    }


    public int getMenuItemID() {
        return menuItemID;
    }


    public double getPrice() {
        return price;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    
    public String toString() {
        return "MenuItem{" +
                menuItemID +
                ", menuNumber=" + menuNumber +
                ", courseType='" + courseType + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}
