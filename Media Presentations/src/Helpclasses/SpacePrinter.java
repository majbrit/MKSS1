package Helpclasses;

public class SpacePrinter {
    public static void printSpaces(int spaceNumber) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spaceNumber; i++) {
            spaces.append(" ");
        }
        System.out.print(spaces.toString());
    }
}
