package button;

import java.util.ArrayList;
import java.util.List;

public class UndoButton {
    private List<Integer> history;

    public UndoButton() {
        this.history = new ArrayList<>();
    }

    /**
     * Save pressed button in a list, so it can be undone when needed
     * @param buttonNumber The number of the button.
     */
    public void pushButtonNumber(int buttonNumber) {
        this.history.add(buttonNumber);
    }

    /**
     * Returns buttonNumber of last pressed button and deletes it from the list
     * @return buttonNumber The number of the button.
     */
    public Integer popButtonNumber() {
        if(this.history.size() == 0) {
            return null;
        }
        Integer buttonNumber = this.history.removeLast();
        return buttonNumber;
    }
}
