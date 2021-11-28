import java.awt.*;
import java.util.LinkedList;

public class Boot {
    public static void main(String[] args) {
        Window view1 = new Window(new LinkedList(), "","","","","", Color.BLUE);
        StationViewModel m = new StationViewModel(view1);
    }
}
