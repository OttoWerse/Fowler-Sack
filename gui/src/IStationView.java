import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

public interface IStationView {
    void setStationID(String currentStationID);
    void setDate(String currentDate);
    void setTarget(String currentTarget);
    void setActual(String currentActual);
    void setVariance(String currentVariance);
    void setCurrentList(LinkedList currentList);
    void setHighlight(Color highlight);

    public void addPropertyChangeListener(PropertyChangeListener l);
}
