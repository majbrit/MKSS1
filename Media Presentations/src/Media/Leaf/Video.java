package Media.Leaf;


public class Video extends LeafMediaComponent {
    private String VideoName;

    public Video(String VideoName) {
        this.VideoName = VideoName;
    }

    @Override
    public void play(String level) {
        System.out.println(level + "Playing Video " + VideoName);
    }
}