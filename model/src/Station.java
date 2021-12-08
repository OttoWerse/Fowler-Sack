import java.util.Date;
import java.util.Objects;

/**
 * Station is a class used to transfer the data from the xml-file to the view.
 * Inside of Station are the stationID which should be a unique identifier of a Station, the date, the targeted value
 * of particles and the actual value of particles.
 * The Station has also a calculated attribute which is called variance it is calculated by subtracting the target from
 * the actual value of particles.
 *
 * @author      Kendrick Bollens
 * @date        2021-12-02
 * @version     1.1
 */
public class Station {

    /*   Attributes   */
    private String stationID;
    private Date date;
    private int target, actual;

    /*   Constructors   */


    /**
     * a consturctor only for testing purposes it only creates the object without the propper attributes
     */
    public Station() {
    }


    /**
     *  a constructor for testing and blank station purposes which only hass the station ID
     *
     * @param stationID the station ID used to identify it
     */
    public Station(String stationID) {
        this.stationID = stationID;
    }

    /**
     * the full consturcort for Station
     *
     * @param stationID the station ID used to identify it
     * @param date  the date of checking
     * @param target the targeteted amount of particles
     * @param actual the actual amount of particles
     */
    public Station(String stationID, Date date, int target, int actual) {
        this.stationID = stationID;
        this.date = date;
        this.target = target;
        this.actual = actual;
    }



    /*   Getter   */

    /**
     * returns the station ID
     *
     * @return date
     */
    public String getStationID() {
        return stationID;
    }

    /**
     * returns the date
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * returns the target
     *
     * @return target
     */
    public int getTarget() {
        return target;
    }

    /**
     * returns the actual
     *
     * @return actual
     */
    public int getActual() {
        return actual;
    }

    /**
     * This method is a pseudo getter for the variance. The variance is calculated by subtracting target from
     * actual. It shows w
     *
     * @return variance The variance shows the difference between target and actual
     */
    public int getVariance() {
        return  actual - target;
    }

    /*   Setter   */

    /**
     * sets the station id
     *
     * @param stationID the station ID used to identify it
     */
    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    /**
     * sets the date
     *
     * @param date the date of checking
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *  Checks for an invalid parameter and Throws an error if it out of the configured range (less than zero or out of int)
     *  if every thing is fine sets the target
     *
     * @param target  the targeteted amount of particles the targeteted amount of particles
     * @throws StationInvalidValueException If there is an invalid parameter
     */
    public void setTarget(int target) throws StationInvalidValueException {
        //Throw error if target is less than 0
        if (target < 0)
            throw new StationInvalidValueException("Target cant be less then 0");
        if (actual > 2147483646)
            throw new StationInvalidValueException("Target cant be more then 2147483646");
        else
            this.target = target;
    }


    /**
     *  Checks for an invalid parameter and Throws an error if it out of the configured range (less than zero or out of int)
     *  if every thing is fine sets the actual
     *
     * @param actual the actual amount of particles the actual amount of particles
     * @throws StationInvalidValueException If there is an invalid parameter
     */
    public void setActual(int actual) throws StationInvalidValueException {
        //Throw error if actual is less than 0
        if (actual < 0)
            throw new StationInvalidValueException("Actual cant be less then 0");
        if (actual > 2147483646)
            throw new StationInvalidValueException("Actual cant be more then 2147483646");
        this.actual = actual;
    }

    //Overrides of Object methods
    @Override
    public String toString() {
        return this.stationID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return target == station.target && actual == station.actual && stationID.equals(station.stationID) && Objects.equals(date, station.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationID, date, target, actual);
    }
}
