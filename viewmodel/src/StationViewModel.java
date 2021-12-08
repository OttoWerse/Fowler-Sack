import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * View Model for a station and the station list
 *
 * @author Otto Werse
 * @version 0.1
 * @date 2021-12-01
 */
public class StationViewModel implements PropertyChangeListener {
    private final IStationView stationView;
    private final StationModel stationModel;
    private LinkedList<Station> stationList;
    private Station station;

    // Constructor with path for XML-File
    public StationViewModel(IStationView stationView, String path) {
        this.stationView = stationView;
        this.stationView.addPropertyChangeListener(this);
        this.stationModel = StationModel.getInstance(path);
        this.stationModel.addPropertyChangeListener(this);
        this.loadStationList();
    }

    // Constructor with default path for XML-File
    public StationViewModel(IStationView stationView) {
        this.stationView = stationView;
        this.stationView.addPropertyChangeListener(this);
        this.stationModel = StationModel.getInstance();
        this.stationModel.addPropertyChangeListener(this);
        this.loadStationList();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // System.out.println(evt);

        // Update the complete Station
        if (evt.getPropertyName() == "StationID") {

            // Gets the selected Station ID
            String selectedStationID = evt.getNewValue().toString();

            // Get every Element with the StationID
            List<Station> shortlist = this.stationList.stream().filter(s -> selectedStationID.equals(s.getStationID())).collect(Collectors.toList());

            // Get the index from the first Element pulled from the Stationlist
            int index = this.stationList.indexOf(shortlist.get(0));

            // Get the new Station
            Station newStation = this.stationList.get(index);

            // Update the current Station with the selected Station
            this.setStation(newStation);

            // Update when Actual changes
        } else if (evt.getPropertyName() == "Actual") {

            // Try to update the value and catch if there is an error
            try {
                int newActualValue = Integer.parseInt(evt.getNewValue().toString());
                this.station.setActual(newActualValue);
                this.updateVariance();

            } catch (NumberFormatException e) {
                this.stationView.showError("Ungültige Zahl!");

            } catch (StationInvalidValueException e) {
                this.stationView.showError("Ungültige Station!");
            }
            // Update the StationList XML-File
            this.stationModel.safeStationlist(this.stationList);

            // Reload the StationList from the XML-File
            this.loadStationList();


            // Load the StationList
        } else if (evt.getPropertyName() == "StationList") {
            this.loadStationList();
        }
    }

    /**
     * Updates the station list
     */
    private void loadStationList() {
        this.stationList = this.stationModel.loadStationlist();
        this.stationView.setCurrentList(this.stationList);
    }

    /**
     * Changes the currently selected station and updates fields accordingly
     *
     * @param station the station object to get values to display from
     */
    public void setStation(Station station) {
        this.station = station;
        this.stationView.setStationID(String.valueOf(this.station.getStationID()));
        this.stationView.setDate(String.valueOf(this.station.getDate()));
        this.stationView.setTarget(String.valueOf(this.station.getTarget()));
        this.stationView.setActual(String.valueOf(this.station.getActual()));
        this.updateVariance();
    }

    /**
     * Adds a randomly generated station
     */
    public void addStation() {
        Station newStation = new Station(ThreadLocalRandom.current().nextLong(0, Integer.MAX_VALUE) + "", new Date(ThreadLocalRandom.current().nextLong(0, 1700000000000L)), ThreadLocalRandom.current().nextInt(0, 69420), ThreadLocalRandom.current().nextInt(0, 69420));
        this.stationList.add(newStation);
        this.stationModel.safeStationlist(this.stationList);
        this.loadStationList();
    }

    /**
     * Computes the desired color for the variance field depending on its contents
     */
    public void updateVariance() {
        this.stationView.setVariance(String.valueOf(this.station.getVariance()));
        if (this.station.getTarget() * -0.10 >= this.station.getVariance()) {
            // System.out.println("RED");
            this.stationView.setHighlight(new Color(1.0f, 0.0f, 0.0f));
        } else if (this.station.getTarget() * 0.05 <= this.station.getVariance()) {
            // System.out.println("GREEN");
            this.stationView.setHighlight(new Color(0.0f, 1.0f, 0.0f));
        } else {
            this.stationView.setHighlight(new Color(0.0f, 0.0f, 0.0f));
        }
    }
}
