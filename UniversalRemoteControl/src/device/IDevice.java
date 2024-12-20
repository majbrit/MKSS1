package device;

public interface IDevice {
    public void on();
    public void off();
    public void play();
    public void pause();
    public void next();
    public void previous();
    public boolean isOn();
    public boolean isPlay();
    public boolean isNext();
}
