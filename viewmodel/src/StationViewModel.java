import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

public class StationViewModel implements PropertyChangeListener {
    Window window;
    LinkedList stationList;
    Station station;

    public StationViewModel(Window window) {
        this.window = window;
        this.window.addPropertyChangeListener(this);
        this.stationList = StationModel.loadStationlist();
        this.window.setCurrentList(this.stationList);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName() + ": " + evt.getNewValue());
        if (evt.getPropertyName() == "StationID") {
            String value = evt.getNewValue().toString();
            Station currentStation = new Station(value);
            int index = this.stationList.indexOf(currentStation);
            System.out.println(index);
            Station newStation = (Station) this.stationList.get(index);
            this.setStation(newStation);
        }
    }

    public void setStation(Station station) {
        this.station = station;
        this.window.setStationID(String.valueOf(this.station.getStationID()));
        this.window.setDate(String.valueOf(this.station.getDate()));
        this.window.setTarget(String.valueOf(this.station.getTarget()));
        this.window.setActual(String.valueOf(this.station.getActual()));
        this.window.setVariance(String.valueOf(this.station.getVariance()));
        this.updateVariance();
    }

    public void updateVariance() {
        if (this.station.getTarget() * -0.10 >= this.station.getVariance()) {
            this.window.setHighlight(new Color(1.0f, 0.0f, 0.0f));
        } else if (this.station.getTarget() * 0.05 >= this.station.getVariance()) {
            this.window.setHighlight(new Color(0.0f, 1.0f, 0.0f));
        }
    }
}
