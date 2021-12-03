import java.util.Date;
import java.util.Objects;

public class Station {

    /*   Attributes   */
    private String stationID;
    private Date date;
    private int target, actual;

    /*   Constructors   */
    public Station() {
    }

    public Station(String stationID) {
        this.stationID = stationID;
    }

    public Station(String stationID, Date date, int target, int actual) {
        this.stationID = stationID;
        this.date = date;
        this.target = target;
        this.actual = actual;
    }

    /*   Methods   */


    /*   Getter   */
    public String getStationID() {
        return stationID;
    }

    public Date getDate() {
        return date;
    }

    public int getTarget() {
        return target;
    }

    public int getActual() {
        return actual;
    }

    // Variance is calculated by subtracting actual from target
    public int getVariance() {
        return  actual - target;
    }

    /*   Setter   */
    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTarget(int target) throws StationInvalidValueException {
        //Throw error if target is less than 0
        if (target < 0)
            throw new StationInvalidValueException("Target cant be less then 0");
        if (actual > 2147483646)
            throw new StationInvalidValueException("Actual cant be more then 2147483646");
        else
            this.target = target;
    }

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
