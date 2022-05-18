package DTO;

public class ItemDTO {

    private  int menuNumber;
    private  int menuItemID;
    private  String courseType;
    private  String description;
    private  String name;
    private  double price;


    public ItemDTO(int menuItemID, int menuNumber, String courseType, String name, String description, double price) {
        this.menuItemID = menuItemID;
        this.menuNumber = menuNumber;
        this.courseType = courseType;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "menuNumber=" + menuNumber +
                ", menuItemID=" + menuItemID +
                ", courseType='" + courseType + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getMenuNumber() {
        return menuNumber;
    }

    public int getMenuItemID() {
        return menuItemID;
    }

    public String getCourseType() {
        return courseType;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
