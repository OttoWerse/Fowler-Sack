import java.util.concurrent.TimeUnit;

public class Boot {
    public static void main(String[] args) throws InterruptedException {
        Window view1 = new Window();
        StationViewModel model1 = new StationViewModel(view1);
        Window view2 = new Window();
        StationViewModel model2 = new StationViewModel(view2);
        TimeUnit.SECONDS.sleep(15);
        model1.addStation();
    }
}
