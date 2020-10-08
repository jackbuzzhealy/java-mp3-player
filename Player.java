import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.lang.Math;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.embed.swing.JFXPanel;
import javafx.util.Duration;

public class Player implements danPlayer 
{
   private JFXPanel fxPanel = new JFXPanel();//fxPanel needed for playing media files
   private String filePath;//filePath variable
   private Media song;//Media object
   private MediaPlayer mediaPlayer;//Media Player object
   private String savedSettingsFilename; //savedSettingsFilename variable
   private ArrayList<String> playlist = new ArrayList<String>();//Playlit ArrayList
   private String convertTime;//the player.getTime() value converted to String
   private static Saver saver = new Saver();
   
   //Band A Requirements 
   /**
    * Play method invokes media method from media player class to play mp3 file
    */
   public void play()
   {
      try 
      {
          mediaPlayer.play();
      } 
      catch (Exception e)
      {
          JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Track not selected!");//error message
      }
   }
   /**
    * Stop method invokes media method from media player class to stop mp3 file
    */
   public void stop()
   {
      try 
      {
          mediaPlayer.stop();
      } 
      catch (Exception e)
      {
          JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Track not selected!");//error message
      }
   }
   /**
    * Pause method invokes media method from media player class to pause mp3 file
    */
   public void pause()
   {
      try 
      {
          mediaPlayer.pause();
      } 
      catch (Exception e)
      {
          JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Track not selected!");//error message
      }
   }
   /**
    * OpenFile method enables user to pick a song
    */
   public void openFile()
   {
       try 
       {
           final JFileChooser fc = new JFileChooser();//JFileChooser object
           final FileFilter filter = new FileNameExtensionFilter("MP3 Files","mp3");//Filters the file so that only mp3 files will appear
           fc.setCurrentDirectory(new java.io.File("C:\\Users\\jackb\\OneDrive\\Desktop\\University\\Assignments\\prjAssignment1\\Tracks"));//filepath to mp3 files
           fc.setFileFilter(filter);
           fc.setDialogTitle("Tracks");//sets name of directory
           if(fc.showOpenDialog(GUI.btnOpenFile) == JFileChooser.APPROVE_OPTION)//if option is approved
           {
               filePath = fc.getSelectedFile().toString();
               song = new Media(new File(filePath).toURI().toString());//assigns song as the selected media file
               mediaPlayer = new MediaPlayer(song);//assigning mediaPlayer as the song
               GUI.lblTrackName.setText(getCurrentTrackName());//updating lblTrackName to the name of the song
           }
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(GUI.jpl, e.getMessage());//error message
       }
   }
   
   
   
   
   
   
   
   
   
   
   
   
   //Additional Methods
   /**
    * Accesses the name of the Saved Settings File
    */
   public String getSavedSettingsFilename()
   {
       return savedSettingsFilename;
   }
   /**
    * Sets the name of the Saved Settings File
    */
   public void setSavedSettingsFilename()
   {
       try 
       {
           double x = Math.random();//programme generates random number to ensure filename has a unique name to avoid duplication
           StringBuilder sb = new StringBuilder();//String Builder object
           String file = "SavedSettings";//All saved files start off with the name "SavedSettings"
           sb.append(file);//adding file to sb
           sb.append(x);//adding x to sb
           this.savedSettingsFilename = sb.toString();//converting sb to a string and assigning in to the savedSettingsFilename attribute
       } 
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(GUI.jpl, e.getMessage());//error message
       }
   }
   /**
    * Directs user to the SavedSettings folder to save mute and volume settings to programme files
    */
   public void selectSavedSetting()
   {
       try 
       {
           //FileChooser to bring user to the SavedSettingfiles
           final JFileChooser fc = new JFileChooser();
           fc.setCurrentDirectory(new java.io.File("C:\\Users\\jackb\\OneDrive\\Desktop\\University\\Assignments\\prjAssignment1\\SavedSettings"));
           fc.setDialogTitle("Saved Settings");
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(GUI.jpl, e.getMessage());//error message
       }
   }
   /**
    * Converts the time from duration to a string so a label can print it
    */
   public void convertTime(Duration trackTime)
   {   
       try 
       {
            long millis = (long)trackTime.toMillis();//retrieves total track time in milliseconds
            int minutes = (int) (millis/ 1000) / 60;//calcualtes minutes
            int seconds = (int) (millis/ 1000) % 60;//calculates secoonds
            StringBuilder sb = new StringBuilder();//StringBuilder object
            sb.append(minutes);//add minutes to StringBuilder
            sb.append(":");//add a colon to StringBuilder
            sb.append(seconds);//add seconds to StringBuilder
            convertTime = sb.toString();//converts stringbuilder to a string and returns it
       }
        catch (Exception e)
       {
           JOptionPane.showMessageDialog(GUI.jpl, "Track Time cannot be converted");//error message
       }
   }
   /**
    * Accesses convertTime
    */
   public String getConvertTime()
   {   
        //returns convertTime;
        return convertTime;
   }
   
   
   
   
   
   
   
   //Band B Requirements
   /**
    * Accesses the name of the track
    */
   public String getCurrentTrackName()
   {
       try 
       {
           //the following code excludes the full directory path and the .mp3 part of the string only returning the name of the track
           Path filePath = Paths.get(this.filePath);  
           StringBuilder sb = new StringBuilder();
           sb.append(filePath.getFileName());
           String path = sb.toString();
           String TrackName = path.replaceFirst("[.][^.]+$", "");
           return TrackName;
       }
       catch (Exception e) 
       {
           return "Track Not Selected";
       }
   }
   /**
    * sets the mute
    */
   public void setMute(boolean _mute)
   {
       try 
       {
           if (_mute == true)
           {
              mediaPlayer.setVolume(0.0);
           }
           else 
           {
              mediaPlayer.setVolume(1.0);
           }
       }
       catch (Exception e) 
       {
           JOptionPane.showMessageDialog(GUI.jpl, "Mute not Set");//error message
       }
   }
   /**
    * sets the volume
    */
   public void setVolume(double _volume)
   {
       try 
       {
           mediaPlayer.setVolume(_volume);
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(GUI.jpl, "Volume  not Set");//error message
       }
   }
   /**
    * Accesses the the time passed in a current playing track
    */
   public Duration getTime()
   {
       try 
       {
           Duration milliSeconds = mediaPlayer.getCurrentTime();
           return milliSeconds;
       }
       catch (Exception e)
       {
           return null;//returns a null value if exception is thrown
       }
   }
   /**
    * gets the mute value
    */
   public boolean getMute()
   { 
       try 
       {
           if (mediaPlayer.getVolume() == 0)
           {
               return true;
           }
           else 
           {
               return false;
           }
       }
       catch (Exception e) 
       {
           return false;//returns false (unmuted) if exception is thrown
       }
   }
   /**
    * gets the volume value
    */
   public double getVolume()
   {
       try 
       {
           return mediaPlayer.getVolume();
       }
       catch (Exception e)
       {
           return 0;//returns zero as the value of volume if exception is thrown
       }
   }
    
   
   
   
   
   
   
   
   
   
   
   
   
   //Band C requirements
   /**
    * sets the time at a particular time of the track
    */
   public void setTime(Duration _time)
   {
       mediaPlayer.seek(_time);//seeks the time of the song requested by the user
   }
   /**
    * Retrieves the total of the track
    */
   public Duration getTotalTime()
   {
       try 
       {
           Duration milliSeconds = mediaPlayer.getTotalDuration();
           return milliSeconds;
       }
       catch (Exception e)
       {
           return null;
       }
   }
   /**
    * opens the playlist stored in the files of the programme
    */
   public void openPlayList()
   {
       try 
       {
           //file chooser to select the track from the playlist
           final JFileChooser fc = new JFileChooser();
           final FileFilter filter = new FileNameExtensionFilter("MP3 Files","mp3");
           fc.setCurrentDirectory(new java.io.File("C:\\Users\\jackb\\OneDrive\\Desktop\\University\\Assignments\\prjAssignment1\\PlayList"));
           fc.setFileFilter(filter);
           fc.setDialogTitle("PlayList");
           if(fc.showOpenDialog(GUI.btnOpenFile) == JFileChooser.APPROVE_OPTION)
           {
               filePath = fc.getSelectedFile().toString();
               song = new Media(new File(filePath).toURI().toString());
               mediaPlayer = new MediaPlayer(song);//plays the selected track
               GUI.lblTrackName.setText(getCurrentTrackName());//sets the label to the track name
           }
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(GUI.jpl, "Playlist not opened");//error message
       }
   }
   /**
    * retrieves the playlist type
    */
   public ArrayList<String> getPlayList()
   {
       playlist = saver.getHardCodedPlayList();//retrieves hard coded playlist built into the programme via the saver class
       return playlist;//returns playlist value
   }
   /**
    * Selects the track in an ArrayList via the number
    */
   public void playTrack(int _trackNo)
   {
       try 
       {
           song = new Media(new File(playlist.get(_trackNo)).toURI().toString());//assigns the song media object as the selected track on the arraylist
           mediaPlayer = new MediaPlayer(song);
           GUI.lblTrackName.setText(getCurrentTrackName());//sets the track name on the label
           mediaPlayer.play();//plays the track
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(GUI.jpl, "Track Number Doesn't Exist");//error message
       }
   }
   /**
    * Restarts the track
    */
   public void restart()
   {
       try 
       {
           mediaPlayer.stop();//stops player
           mediaPlayer.play();//plays plays again
       }
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(GUI.jpl, "No Track Selected");//error message
       }
   }
}
