import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.File;
import javax.swing.*;

public class Saver implements danSaver
{
    private double volume;//volume variable
    private boolean mute;//mute variable
    private String filename;//filename variable
    private static Player player = new Player();//player object
    private ArrayList<String> playlist = new ArrayList();//playlist ArrayList
    
    //settings functionality - band B requirements
    /**
     * Saves settings of the of the mute and volume once called by player
     */
    public void saveSettings()
    {
        try 
        {
            FileOutputStream outputStream = new FileOutputStream(filename);
            XMLEncoder encoder = new XMLEncoder(outputStream);
            encoder.writeObject(this);
            encoder.close();
            outputStream.close();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Saver Cannot Save File!");//error message
        }   
    }
    /**
     * accesses filename
     */
    public String getFilename()
    {
        return filename;//returns filename
    }
    /**
     * iterates filename
     */
    public void setFilename(String filename)
    {
        this.filename = filename;//sets filename
    }
    /**
     * Loads the settings of the of the mute and volume already saved once called by player
     */
    public void loadSettings()
    {
        try
        {
            FileInputStream input = new FileInputStream(filename);
            XMLDecoder decode = new XMLDecoder(input);
            Object object = decode.readObject();
            Saver temp = (Saver)object;
            setAll(temp.getMute(), temp.getVolume());
            decode.close();
            input.close();
        }
        catch (Exception e) 
        {
           JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Saver Cannot Load File!");//error message
        }  
    }
    /**
     * Set All method that sets updated mute and volume once loaded
     */
    public void setAll(boolean mute, double volume)
    {
        setMute(mute);//sets mute
        setVolume(volume);//sets volume
    }
    /**
     * Accesses mute
     */
    public boolean getMute()
    {
        try
        {  
            return mute;//returns mute 
        }
        catch (Exception e) 
        {
            return false;//if an error is found, mute will not be triggered
        }  
    }
    /**
     * Iterates Mute
     */
    public void setMute(boolean _mute)
    {
        this.mute = _mute;//updates mute
    }
    /**
     * Accesses Volume
     */
    public double getVolume()
    {
        return volume;//gets volume
    }
    /**
     * Set All method that sets updated mute and volume once loaded
     */
    public void setVolume(double _volume)
    {
        try
        {
            this.volume = _volume;//updates volume
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Saver cannot save Volume!");//error message
        }
    }
    
    
    
    
    
    
    
    //playlist functionality - band C requirements
    public void savePlayList(ArrayList<String> _filepaths, String _filepath)
    {
        System.out.println("Saving play List");//stub
    }
    public ArrayList<String> loadPlayList(String _filepath)
    {
        System.out.println("Loading play List");//stub
        return null;
    }
    /**
     * Returns playlist balready saved into system
     */
    public ArrayList<String> getHardCodedPlayList()
    {
        try 
        {
            File playlistDirectory = new File("C:\\Users\\jackb\\OneDrive\\Desktop\\University\\Assignments\\prjAssignment1\\PlayList"); //filepath to playlist
            for(int i=0; i<playlistDirectory.length(); i++)//loops through file and adds it to arraylist
            {
                StringBuilder sb = new StringBuilder();//StringBuilder object
                sb.append(playlistDirectory);//adds list of files to from directory to string builder
                playlist.add(sb.toString());//converts stringbuilder files to Strings and adds them to playList
                return playlist;//returns playslist
            }
        }
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Playlist can't be opened!");//error message
            return null;//returns null if exception is thrown
        }
        return null;//returns null if no files are added
    }
}
