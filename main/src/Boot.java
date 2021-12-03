import java.util.concurrent.TimeUnit;

public class Boot {
    public static void main(String[] args) throws InterruptedException {
        // Station Model is created here, because it needs to be static-like, but is not a singleton-type yet.
        StationModel stationModel = new StationModel();
        Window view1 = new Window();
        StationViewModel model1 = new StationViewModel(view1, stationModel);
        Window view2 = new Window();
        StationViewModel model2 = new StationViewModel(view2, stationModel);
        TimeUnit.SECONDS.sleep(15);
        model1.addStation();
    }
}
