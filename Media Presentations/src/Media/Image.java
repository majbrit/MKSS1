package Media;

import Component.Media;

public class Image extends LeafMediaComponent {

    private String pictureName;

    public Image(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public void play(int indent) {
        super.play(indent);
        System.out.println("Showing picture " + pictureName);
    }
}
