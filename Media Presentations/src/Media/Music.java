package Media;

import Component.Media;

public class Music extends LeafMediaComponent {
    private String MusicName;

    public Music(String MusicName) {
        this.MusicName = MusicName;
    }

    @Override
    public void play(int indent) {
        super.play(indent);
        System.out.println("Playing music . " + MusicName);
    }
}
