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
        if (evt.getPropertyName() == "StationID") {
            String value = evt.getNewValue().toString();
            Station currentStation = new Station(value);
            int index = this.stationList.indexOf(currentStation);
            Station newStation = (Station) this.stationList.get(index);
            this.setStation(newStation);
        } else if (evt.getPropertyName() == "Actual") {
            int value = Integer.valueOf(evt.getNewValue().toString());
            try {
                int index = this.stationList.indexOf(this.station);
                this.station.setActual(value);
                Station newStation = (Station) this.stationList.get(index);
                newStation = this.station;
                this.updateVariance();

            } catch (StationInvalidValueException e) {
                // TODO: Error Message!
                e.printStackTrace();
            }
            StationModel.safeStationlist(this.stationList);
        }
    }

    public void setStation(Station station) {
        this.station = station;
        this.window.setStationID(String.valueOf(this.station.getStationID()));
        this.window.setDate(String.valueOf(this.station.getDate()));
        this.window.setTarget(String.valueOf(this.station.getTarget()));
        this.window.setActual(String.valueOf(this.station.getActual()));
        this.updateVariance();
    }

    public void updateVariance() {
        this.window.setVariance(String.valueOf(this.station.getVariance()));
        if (this.station.getTarget() * -0.10 >= this.station.getVariance()) {
            // System.out.println("RED");
            this.window.setHighlight(new Color(1.0f, 0.0f, 0.0f));
        } else if (this.station.getTarget() * 0.05 <= this.station.getVariance()) {
            // System.out.println("GREEN");
            this.window.setHighlight(new Color(0.0f, 1.0f, 0.0f));
        } else {
            this.window.setHighlight(new Color(0.0f, 0.0f, 0.0f));
        }
    }
}
