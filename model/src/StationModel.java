import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.LinkedList;

/**
 * StationModel is a singleton class is which is intialised with a path where an xml-file is or will be stored.
 * The class is used to store and load a LinkedList with Stations in an xml-file and load it from the same file.
 * If the class is once intialised the path to the file cant be changed.
 * Also can a PropertyChangeListener be attached which will fire when while storing a file a change is detected.
 *
 * @author      Kendrick Bollens
 * @date        2021-12-02
 * @version     1.3
 */
public class StationModel implements IStationModel{

    //Singleton Instance
    private static StationModel single_instance = null;

    //Attributes
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);    //Property change listener to udpate gui
    private String defaultPath = "./xmlstorage/default.xml";    //Default path for the XML-File


    //Constructors
    private StationModel() {}

    private StationModel(String defaultPath) {
        this.defaultPath = defaultPath;
    }


    //Get the Singleton Instance
    public static StationModel getInstance(String path){//with path

        //creates new Instance with the path if not exists
        if (single_instance == null)
            single_instance = new StationModel(path);

        return single_instance;
    }

    public static StationModel getInstance(){//without path

        //creates new Instance if not exists
        if (single_instance == null)
            single_instance = new StationModel();

        return single_instance;
    }


    //Method to Store Station in XML FILE
    public void safeStationlist(LinkedList<Station> newstationlist){
        LinkedList<Station> oldList = loadStationlist();
        //Create Encoder
        XMLEncoder encoder;

        //Catch Errors if Something is Wrong with the File
        try {
            //Initializing Decoder
            encoder = new XMLEncoder(
                    //Assigning a buffered
                    new BufferedOutputStream(
                            //Creating an output with the Path to the Correct File
                            new FileOutputStream(defaultPath)));
            //write the object in the file
            encoder.writeObject(newstationlist);

            //close the encoder
            encoder.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }



        //fire the propertyChange
        LinkedList<Station> newList = loadStationlist();
        changes.firePropertyChange("StationList", oldList, newList);
    }





    //Method to Load StationList from a XML-File
    public LinkedList<Station> loadStationlist(){
        //tempoary List object
        LinkedList<Station> loadedStationList = new LinkedList<>();


        //Create Decoder
        XMLDecoder decoder;

        //Catch Errors if Something is Wrong with the File
        try {
            //Initializing Decoder
            decoder = new XMLDecoder(
                    //Assigning a buffered
                    new BufferedInputStream(
                            //Creating an input with the Path to the Correct File
                            new FileInputStream(defaultPath)));
            //Temporary safe the Loaded Station
            loadedStationList = (LinkedList<Station>) decoder.readObject();

            //close the Decoder
            decoder.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }



        //give back the Station
        return loadedStationList;
    }

    //Adding and removing a Property change listener
    public void addPropertyChangeListener(PropertyChangeListener changeListener) {
        changes.addPropertyChangeListener(changeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener changeListener) {
        changes.removePropertyChangeListener(changeListener);
    }

}
