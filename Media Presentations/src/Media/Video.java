package Media;

import Component.Media;

public class Video extends LeafMediaComponent {
    private String VideoName;

    public Video(String VideoName) {
        this.VideoName = VideoName;
    }

    @Override
    public void play(int indent) {
        super.play(indent);
        System.out.println("Playing Video " + VideoName);
    }
}