package Media;

public class Text implements Media {
    private String content;

    public Text(String content) {
        this.content = content;
    }
    @Override
    public void play(String level) {
        System.out.println(level  + content);
    }
}