import java.util.concurrent.TimeUnit;

public class StationBinder {
    public static void main(String[] args) throws InterruptedException {
        StationView view1 = new StationView();
        StationViewModel model1 = new StationViewModel(view1);
        StationView2 view2 = new StationView2();
        StationViewModel model2 = new StationViewModel(view2);
        for (int i = 0; i < 10; i++) {
            model1.addStation();
            TimeUnit.SECONDS.sleep(15);
        }
    }
}
