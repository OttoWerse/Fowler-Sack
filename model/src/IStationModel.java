import java.beans.PropertyChangeListener;
import java.util.LinkedList;

public interface IStationModel {

    //Methods to safe and load

    void safeStationlist(LinkedList<Station> StationList);

    LinkedList<Station> loadStationlist();

    //Getter and Setter
    String getDefaultPath();

    void setDefaultPath(String path);

    //Property Listener
    void addPropertyChangeListener(PropertyChangeListener l);
    void removePropertyChangeListener(PropertyChangeListener l);
}
