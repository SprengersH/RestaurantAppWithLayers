package Logic;

public class Table {

    private int tableNumber;
    private boolean available;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.available = true;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        String s = "";
        if (available) {
            s = "*  Table " +
                    tableNumber +
                    "   = available for customers       *";
        } else {
            s = "*  Table " +
                    tableNumber +
                    "   = occupied                      *";
        }
        return s;
    }
}

