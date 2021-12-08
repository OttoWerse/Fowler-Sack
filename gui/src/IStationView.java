import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

/**
 * Interface for a MVVM conform view for a station and the station list
 *
 * @author Otto Werse
 * @version 0.1
 * @date 2021-12-01
 */
public interface IStationView {
    /**
     * Updates the unique identifier of the currently selected station to display
     *
     * @param currentStationID the unique identifier of the Station to display.
     */
    void setStationID(String currentStationID);

    /**
     * Updates the last update date of the currently selected station to display
     *
     * @param currentDate the last update date of the Station to display.
     */
    void setDate(String currentDate);

    /**
     * Updates the target value of the currently selected station to display
     *
     * @param currentTarget the target value of the Station to display.
     */
    void setTarget(String currentTarget);

    /**
     * Updates the actual value of the currently selected station to display
     *
     * @param currentActual the actual value of the Station to display.
     */
    void setActual(String currentActual);

    /**
     * Updates the variance between target and actual value of the currently selected station to display
     *
     * @param currentVariance the variance between target and actual value of the Station to display.
     */
    void setVariance(String currentVariance);

    /**
     * Updates the list of available stations to display
     *
     * @param currentList the list of available stations to display.
     */
    void setCurrentList(LinkedList currentList);

    /**
     * Updates the color of the variance field
     *
     * @param highlight the color to display.
     */
    void setHighlight(Color highlight);

    /**
     * Shows an error message
     *
     * @param s the string display inside the error message.
     */
    void showError(String s);

    /**
     * Adds a property change listener to be notified of changes to the properties (actual)
     *
     * @param l an object that can subscribe to property changes.
     * @see PropertyChangeListener
     */
    void addPropertyChangeListener(PropertyChangeListener l);

    /**
     * Removes a property change listener to be notified of changes to the properties (actual)
     *
     * @param l an object that can subscribe to property changes.
     * @see PropertyChangeListener
     */
    void removePropertyChangeListener(PropertyChangeListener l);
}
