package Media;

import Component.Media;

public class Text extends LeafMediaComponent {
    private String content;

    public Text(String content) {
        this.content = content;
    }
    @Override
    public void play(String level) {
        System.out.println(level + content);
    }
}