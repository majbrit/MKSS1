package Media;

import Component.Media;

public class Text extends LeafMediaComponent {
    private String content;

    public Text(String content) {
        this.content = content;
    }
    @Override
    public void play(int indent) {
        super.play(indent);
        System.out.println(content);
    }
}