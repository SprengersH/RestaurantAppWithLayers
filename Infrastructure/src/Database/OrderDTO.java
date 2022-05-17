package Database;

public class OrderDTO {

    private int menuitemid;
    private int menunumber;
    private String coursetype;
    private String name;
    private String description;
    private double price;

    public OrderDTO(int menuitemid, int menunumber, String coursetype, String name, String description, double price) {

        this.menuitemid = menuitemid;
        this.menunumber = menunumber;
        this.coursetype = coursetype;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getMenuitemid() {
        return menuitemid;
    }

    public int getMenunumber() {
        return menunumber;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
