/**
 * StationInvalidValidException is a class which inherits Exception an is used to fire an exception if a proposed value
 * in the class Station is invalid, which would include a negative count of particles or an exceeding of the int value
 *
 * @author      Kendrick Bollens
 * @date        2021-11-29
 * @version     1.0
 */
public class StationInvalidValueException extends Exception {
    public StationInvalidValueException(String message) {
        super(message);
    }
}
