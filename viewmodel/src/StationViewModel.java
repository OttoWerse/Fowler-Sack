import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StationViewModel implements PropertyChangeListener {
    Window window;
    Station station;

    public StationViewModel(Window window) {
        this.window = window;
        this.window.addPropertyChangeListener(this);
        this.window.updateCurrentStationID();
        System.out.println(this.window.getCurrentStationID());
    }


    public void setStation(Station station) {
        this.station = station;
        this.window.setCurrentStationID(String.valueOf(this.station.getStationID()));
        this.window.setCurrentDate(String.valueOf(this.station.getDate()));
        this.window.setCurrentTarget(String.valueOf(this.station.getTarget()));
        this.window.setCurrentActual(String.valueOf(this.station.getActual()));
        this.window.setCurrentVariance(String.valueOf(this.station.getVariance()));
        this.updateVariance();
    }

    public void updateVariance() {
        if (this.station.getTarget() * -0.10 >= this.station.getVariance()) {
            this.window.setHighlight(new Color(1.0f, 0.0f, 0.0f));
        } else if (this.station.getTarget() * 0.05 >= this.station.getVariance()) {
            this.window.setHighlight(new Color(0.0f, 1.0f, 0.0f));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this.window.getCurrentStationID());
    }
}
