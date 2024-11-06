package Composite;

import Component.Media;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeMediaComponent implements Media {

    protected List<Media> components = new ArrayList<>();

    public void addComponent(Media component) {
        components.add(component);
    }

    protected abstract String getPlayMessage();

    @Override
    public void play(String level) {
        System.out.println(level + getPlayMessage());
        for (Media component : components) {
            component.play(level+"  ");
        }
    }
}
