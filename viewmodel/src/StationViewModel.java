import java.awt.*;

public class StationViewModel {
    Window window;
    Station station;

    public StationViewModel(Window window) {
        this.window = window;
    }

    public void setStation (Station station) {
        this.station = station;
    }

    public void updateVariance () {
        if (this.station.getTarget() * -0.10 >= this.station.getVariance()) {
            new Color(1.0f, 0.0f, 0.0f);
        }
        else if (this.station.getTarget() * 0.05 >= this.station.getVariance()) {
            new Color(0.0f, 1.0f, 0.0f);
        }
    }

}
