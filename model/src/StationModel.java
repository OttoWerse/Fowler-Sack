import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class StationModel {

    //Attributes
    private LinkedList<Station> currentStationList;     //the current stationlist to update the property change listener
    private PropertyChangeSupport savechanges = new PropertyChangeSupport(this);    //Property change listener to udpate gui
    private String defaultPath = "./xmlstorage/default.xml";    //Default path for the XML-File


    //Constructors
    public StationModel(LinkedList<Station> currentStationList) {
        this.currentStationList = currentStationList;
    }

    public StationModel(LinkedList<Station> currentStationList, String defaultPath) {
        this.currentStationList = currentStationList;
        this.defaultPath = defaultPath;
    }

    //Test MAIN
    public void main(String[] args) {
        //Test path
        String path = "./xmlstorage/Test.xml";

        //create Test Stations
        Station st1 = new Station("Wert1", new Date(91, Calendar.JANUARY, 1), 12, 14);
        Station st2 = new Station("Wert2", new Date(107, Calendar.DECEMBER, 13), 11, 10);
        Station st3 = new Station("Wert3", new Date(120, Calendar.MARCH, 11), 230, 20);
        Station st4 = new Station("Wert4", new Date(104, Calendar.AUGUST, 27), 77, 90);

        //create Test list and add stations
        LinkedList<Station> list = new LinkedList<Station>();
        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);

        //save to xml
        safeStationlist(path,list);

        //load from xml
        LinkedList<Station> list2 = loadStationlist(path);

        //see if everything is alright with Sys Out
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i).getStationID());
        }

    }

    //Method to Store Station in XML FILE
    public void safeStationlist(String path,LinkedList<Station> newstationlist){

        //Create Encoder
        XMLEncoder e = null;

        //Catch Errors if Something is Wrong with the File
        try {
            //Initializing Decoder
            e = new XMLEncoder(
                    //Assigning a buffered
                    new BufferedOutputStream(
                            //Creating an output with the Path to the Correct File
                            new FileOutputStream(path)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        //write the object in the file
        e.writeObject(newstationlist);
        
        //close the encoder
        e.close();
    
        //fire the propertyChange
        savechanges.firePropertyChange("StationList", currentStationList, newstationlist);
        this.setCurrentStationList(newstationlist);

    }

    public void safeStationlist(LinkedList<Station> stationlist){
        safeStationlist(defaultPath, stationlist);
    }



    //Method to Load StationList from a XML-File
    public LinkedList<Station> loadStationlist(String path){

        //Create Decoder
        XMLDecoder d = null;

        //Catch Errors if Something is Wrong with the File
        try {
            //Initializing Decoder
            d = new XMLDecoder(
                    //Assigning a buffered
                    new BufferedInputStream(
                            //Creating an input with the Path to the Correct File
                            new FileInputStream(path)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        //Temporary safe the Loaded Station
        LinkedList<Station> loadedStationlist = (LinkedList<Station>) d.readObject();

        //close the Decoder
        d.close();

        //give back the Station
        return loadedStationlist;
    }

    //Same Method as above but uses a default path
    public LinkedList<Station> loadStationlist(){
        return loadStationlist(defaultPath);
    }


    //Getters and Setters
    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public LinkedList<Station> getCurrentStationList() {
        return currentStationList;
    }

    public void setCurrentStationList(LinkedList<Station> currentStationList) {
        this.currentStationList = currentStationList;
    }

    //Adding and removing a Property change listener
    public void addPropertyChangeListener(PropertyChangeListener l) {
        savechanges.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        savechanges.removePropertyChangeListener(l);
    }

}
