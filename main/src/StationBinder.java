import java.util.concurrent.TimeUnit;
/**
 * Starter Class / Binder / Boot
 *
 * @author Otto Werse
 * @version 0.1
 * @date 2021-12-01
 */
public class StationBinder {
    public static void main(String[] args) throws InterruptedException {
        // Create first view (widescreen)
        StationView view1 = new StationView();
        // Create ViewModel for the first view
        StationViewModel vmodel1 = new StationViewModel(view1);
        // Create second view (widescreen)
        StationView2 view2 = new StationView2();
        // Create ViewModel for the second view
        StationViewModel vmodel2 = new StationViewModel(view2);
        // Generate a new random station every 15 seconds (min. 1, max. 10)
        for (int i = 0; i < 10; i++) {
            vmodel1.addStation();
            TimeUnit.SECONDS.sleep(15);
        }
    }
}
