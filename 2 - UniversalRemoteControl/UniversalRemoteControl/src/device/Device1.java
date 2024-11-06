package device;

public class Device1 implements IDevice{

    private boolean on;
    private boolean play;
    private boolean next;

    public Device1() {
        on = false;
        play = false;
        next = false;
    }
    /**
     * Turn device on
     */

    public void on() {
        on = true;
        System.out.println("Device on");
    }

    /**
     * Turn device off
     */
    public void off() {
        on = false;
        System.out.println("Device off");
    }

    /**
     * play device
     */
    public void play() {
        play = true;
        System.out.println("Device play");
    }

    /**
     * pause device
     */
    public void pause() {
        play = false;
        System.out.println("Device pause");
    }

    /**
     * select next on device
     */
    public void next() {
        next = true;
        System.out.println("Device next");
    }

    /**
     * select previous on device
     */
    public void previous() {
        next = false;
        System.out.println("Device previous");
    }

    public boolean isOn() {
        return on;
    }

    public boolean isPlay() {
        return play;
    }

    public boolean isNext() {
        return next;
    }
}
