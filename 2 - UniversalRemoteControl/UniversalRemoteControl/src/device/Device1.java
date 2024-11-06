package device;

public class Device1 implements IDevice{

    /**
     * Turn device on
     */

    public void on() {
        System.out.println("Device on");
    }

    /**
     * Turn device off
     */
    public void off() {
        System.out.println("Device off");
    }

    /**
     * play device
     */
    public void play() {
        System.out.println("Device play");
    }

    /**
     * pause device
     */
    public void pause() {
        System.out.println("Device pause");
    }

    /**
     * select next on device
     */
    public void next() {
        System.out.println("Device next");
    }

    /**
     * select previous on device
     */
    public void previous() {
        System.out.println("Device previous");
    }
}
