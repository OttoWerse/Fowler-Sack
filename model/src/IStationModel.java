import java.beans.PropertyChangeListener;
import java.util.LinkedList;

/**
 * IStationModel is the Inteface for StationModel
 *
 * @author      Kendrick Bollens
 * @date        2021-12-01
 * @version     1.0
 */
public interface IStationModel {

    /**
     * This method is used to write a StationList into the stored xml-file for persistent storage
     *
     * @param StationList  the Stationlist that should be stored
     */
    void safeStationlist(LinkedList<Station> StationList);

    /**
     * This method is used to load a StationList from the stored xml-file for persistent storage
     *
     * @return StationList  the Stationlist that should be loaded from the xml-file
     *
     */
    LinkedList<Station> loadStationlist();

    /**
     * This method is used to attached a PropertyChangeListener to the Station Mehtod
     *
     * @param changeListener the PropertyChangeListener that should be attached
     * @see PropertyChangeListener
     */
    void addPropertyChangeListener(PropertyChangeListener changeListener);

    /**
     * This method is used to remove a PropertyChangeListener to the Station Mehtod
     *
     * @param changeListener the PropertyChangeListener that should be removed
     * @see PropertyChangeListener
     */
    void removePropertyChangeListener(PropertyChangeListener changeListener);
}
