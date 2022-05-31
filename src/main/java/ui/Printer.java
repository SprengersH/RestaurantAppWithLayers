package ui;

public class Printer {

    public static <T extends Iterable<E>, E> void print(T list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }
}
