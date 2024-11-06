package Media;

public class Video implements Media {
    private String VideoName;

    public Video(String VideoName) {
        this.VideoName = VideoName;
    }

    @Override
    public void play(String level) {
        System.out.println(level + "Playing Video " + VideoName);
    }
}