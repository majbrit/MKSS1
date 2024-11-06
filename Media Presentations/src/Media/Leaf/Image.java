package Media.Leaf;

import Media.Media;

public class Image extends LeafMediaComponent {

    private String pictureName;

    public Image(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public void play(String level) {
        System.out.println(level + "Showing picture " + pictureName);
    }
}
