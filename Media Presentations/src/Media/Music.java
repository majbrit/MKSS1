package Media;

public class Music implements Media {
    private String MusicName;

    public Music(String MusicName) {
        this.MusicName = MusicName;
    }

    @Override
    public void play(String level) {
        System.out.println(level + "Playing music . " + MusicName);
    }
}
