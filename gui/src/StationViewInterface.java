import java.awt.*;
import java.util.LinkedList;

public interface StationViewInterface {
    void setStationID(String currentStationID);
    void setDate(String currentDate);
    void setTarget(String currentTarget);
    void setActual(String currentActual);
    void setVariance(String currentVariance);
    void setCurrentList(LinkedList currentList);
    void setHighlight(Color highlight);
}
