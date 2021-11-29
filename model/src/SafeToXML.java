import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.LinkedList;

public class SafeToXML {


    //Test MAIN
    public static void main(String[] args) {
        //Test path
        String path = "Test.xml";

        //create Test Stations
        Station st1 = new Station("Wert1");
        Station st2 = new Station("Wert2");
        Station st3 = new Station("Wert3");
        Station st4 = new Station("Wert4");

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
    public static void safeStationlist(String path,LinkedList<Station> stationlist){

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
        e.writeObject(stationlist);

        //close the encoder
        e.close();
    }

    public static LinkedList<Station> loadStationlist(String path){

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


}
