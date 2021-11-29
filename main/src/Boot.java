import java.awt.*;
import java.util.LinkedList;

public class Boot {
    public static void main(String[] args) {
        Window view1 = new Window(new LinkedList(), "ABCDEF","Today","12","22","1245216", Color.BLUE);
        StationViewModel m = new StationViewModel(view1);
    }
}
