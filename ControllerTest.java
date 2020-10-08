
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.util.Duration;

public class ControllerTest implements ActionListener, ChangeListener
{
    Player player = new Player();//Player object
    Saver saver = new Saver();//Saver object
    
    /**
     * The Event handlers when buttons are clicked in the GUI class
     */
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == GUI.btnPlay)//if btnPlay is clicked
        {
            try
            {
                player.play();//Plays the track
                
                //gets current time of track and updates label
                player.convertTime(player.getTime());
                GUI.lblTime.setText(player.getConvertTime());
                
                //gets total track time and updates label
                player.convertTime(player.getTotalTime());
                GUI.lblTotalTime.setText(player.getConvertTime());
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! MP3 Player Won't Play!");//error message
            }
        }
        else if(ae.getSource() == GUI.btnStop)//if btnStop is clicked
        {
            try
            {
                player.stop();//Stops Track
                GUI.lblTime.setText("0");//Resets the label
                GUI.lblTotalTime.setText("0");//Resets the label
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! MP3 Player Won't Stop!");//error message
            }
        }
        else if(ae.getSource() == GUI.btnPause)//if btnPause is clicked
        {
            try
            { 
                player.pause();//Pauses Track
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! MP3 Player Won't Pause!");//error message
            }
        }
        else if(ae.getSource() == GUI.btnOpenFile)//if btnOpenFile is clicked
        {
            try
            {    
                player.openFile();//Opens directory file
            }
            catch(Exception e)
            {
                 JOptionPane.showMessageDialog(GUI.jpl,"ERROR! File won't open!");//error message
            }
        }
        else if(ae.getSource() == GUI.btnMute)//if btnMute is clicked
        {
            try
            {
                if (player.getMute() == false) 
                {
                    //Mutes the track being played
                    player.setMute(true);
                    saver.setMute(player.getMute());
                    GUI.lblMute.setText("Muted");//informs user that player is muted
                }
                else 
                {
                    //Unmutes the track being played
                     player.setMute(false);
                    saver.setMute(player.getMute());
                    GUI.lblMute.setText("");//informs user that player is not muted anymore
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! MP3 Player Won't Mute!");//error message
            }
        }
        else if(ae.getSource() == GUI.btnSaveSettings)//if btnSaveSettings is clicked
        {
            try
            {
                player.setSavedSettingsFilename();
                saver.setFilename(player.getSavedSettingsFilename());
                saver.setMute(player.getMute());
                saver.setVolume(player.getVolume());
                saver.saveSettings();//saves the volume and mute settings
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Settings Not Saved!");//error message
            }
        } 
        else if(ae.getSource() == GUI.btnLoadSettings)//if btnLoadSettings is clicked
        {
            try
            {
                saver.loadSettings();//loads the volume and mute saved settings
                player.setVolume(saver.getVolume());
                player.setMute(saver.getMute());
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Settings Not Loaded!");//error message
            }
        }
        else if(ae.getSource() == GUI.btnRestart)//if btnRestart is clicked
        {
            try
            {
                player.restart();//invokes the player restart method
                
                //gets total track time and updates label
                player.convertTime(player.getTime());
                GUI.lblTime.setText(player.getConvertTime());
                
                //gets total track time and updates label
                player.convertTime(player.getTotalTime());
                GUI.lblTotalTime.setText(player.getConvertTime());  
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! No Track Playing");//error message
            }
        }
        else if(ae.getSource() == GUI.btnOpenPlaylist)//if btnOpenPlayList is clicked
        {
            try
            {
                player.openPlayList();//invokes openplaylist method from player class
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Playlist not Opened!");//error message
            }
        }
    }
    
    /**
      * The Event handlers when sliders are used in the GUI class
     */
    public void stateChanged(ChangeEvent ce) 
    {
         if(GUI.sldVolume.getValueIsAdjusting())//if sldVolume is adjusted
        {
            try
            {
                float volume = GUI.sldVolume.getValue() / 100.0f;//makes the volume compatible for the methods in the player and saver classes
                player.setVolume(volume);//sets the volume in the Player class
                saver.setVolume(player.getVolume());//sets the volume in the Player class
                
                //Adds the volume to the stringbuilder so lblVolumeLevel can show the volume level as a string type
                StringBuilder sb = new StringBuilder();
                sb.append(player.getVolume());
                GUI.lblVolumeLevel.setText(sb.toString());
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Volume can only be adjusted when track is playing!");//error message
            }
        }
        else if(GUI.sldTime.getValueIsAdjusting())//if sldTime is adjusted
        {
            try
            {
                // long millis = (long)player.getTotalTime().toMillis();////retrieves total track time in milliseconds
                // int seconds = (int) (millis/ 1000) % 60; ////converts milliseconds to seconds
                // GUI.sldTime.setMaximum(seconds); ////sets the maximum value of sldTimer as total track time converted into seconds
                // Duration time = GUI.sldTime.getValue(); ////sets the duration time type as the same time as the value selected on the sldTimer (need to convert seconds to duration))
                // player.setTime(time); ////Sets the time of the track as the smae value as sldTimer
            }
            catch(Exception e)
            {
                 JOptionPane.showMessageDialog(GUI.jpl,"ERROR! Time can only be adjusted when track is playing!");//error message
            }
        } 
    }
}

