package Main;

import Composite.*;
import Media.*;

public class Main {
    public static void main(String[] args) {

        Text introText = new Text("A vacation to remember...");


        Sequence mediaSequence = new Sequence();
        mediaSequence.addComponent(new Image("pic1"));
        mediaSequence.addComponent(new Video("vid1"));
        mediaSequence.addComponent(new Image("pic2"));
        mediaSequence.addComponent(new Video("vid2"));
        mediaSequence.addComponent(new Image("pic3"));
        mediaSequence.addComponent(new Video("vid3"));

        Music backgroundMusic = new Music("Some lovely music");

        Parallel parallelMedia = new Parallel();

        parallelMedia.addComponent(mediaSequence);
        parallelMedia.addComponent(backgroundMusic);


        Text outroText = new Text("We'll go there again!");


        Sequence fullPresentation = new Sequence();
        fullPresentation.addComponent(introText);
        fullPresentation.addComponent(parallelMedia);
        fullPresentation.addComponent(outroText);


        fullPresentation.play("");
    }
}
