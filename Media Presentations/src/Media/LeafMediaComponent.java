package Media;

import Component.Media;
import Helpclasses.SpacePrinter;

public abstract class LeafMediaComponent implements Media {
    public void play(int indent) {
        SpacePrinter.printSpaces(indent);
    }
}
