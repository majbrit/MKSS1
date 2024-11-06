package Composite;

public class Sequence extends CompositeMediaComponent {
    @Override
    protected String getPlayMessage() {
        return "Playing in sequence:";
    }
}