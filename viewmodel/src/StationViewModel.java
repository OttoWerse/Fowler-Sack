import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StationViewModel implements PropertyChangeListener {
    Window window;
    LinkedList<Station> stationList;
    Station station;
    StationModel stationModel;

    public StationViewModel(Window window, StationModel stationModel) {
        this.window = window;
        this.window.addPropertyChangeListener(this);
        this.stationModel = stationModel;
        this.stationModel.addPropertyChangeListener(this);
        this.loadStationList();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // System.out.println(evt);
        if (evt.getPropertyName() == "StationID") {
            String value = evt.getNewValue().toString();
            Station currentStation = new Station(value);
            List<Station> shortlist = this.stationList.stream().filter(s -> currentStation.getStationID().equals(s.getStationID())).collect(Collectors.toList());
            int index = this.stationList.indexOf(shortlist.get(0));
            Station newStation = this.stationList.get(index);
            this.setStation(newStation);
        } else if (evt.getPropertyName() == "Actual") {
            int value = Integer.valueOf(evt.getNewValue().toString());
            try {
                this.station.setActual(value);
                this.updateVariance();

            } catch (StationInvalidValueException e) {
                // TODO: Error Message!
                e.printStackTrace();
            }
            this.stationModel.safeStationlist(this.stationList);
            this.loadStationList();
        } else if (evt.getPropertyName() == "StationList") {
            this.loadStationList();
        }
    }

    private void loadStationList() {
        this.stationList = this.stationModel.loadStationlist();
        this.window.setCurrentList(this.stationList);
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
