package Media;

public class Image implements Media {

    private String pictureName;

    public Image(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public void play(String level) {
        System.out.println(level + "Showing picture " + pictureName);
    }
}
