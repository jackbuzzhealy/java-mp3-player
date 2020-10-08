import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javafx.scene.control.Slider;

public class GUI extends JFrame
{ 
    /**
     * The controls below are the buttons to be used on the MP3 player
     */
    public static JButton btnPlay = new JButton("Play");
    public static JButton btnStop = new JButton("Stop");
    public static JButton btnPause = new JButton("Pause");
    public static JButton btnOpenFile = new JButton("Open File");
    public static JButton btnMute = new JButton("Mute");
    public static JButton btnSaveSettings = new JButton("Save Settings");
    public static JButton btnLoadSettings = new JButton("Load Settings");
    public static JButton btnRestart = new JButton("Restart");
    public static JButton btnOpenPlaylist = new JButton("Open Playlist");
    
    /**
     * The controls below are the labels to be used on the MP3 player
     */
    public static JLabel lblTrackName = new JLabel("Track Name");
    public static JLabel lblTime = new JLabel("Track Time");
    public static JLabel lblTotalTime = new JLabel("Total Time");
    public static JLabel lblVolumeLevel = new JLabel("Volume Level");
    public static JLabel lblMute = new JLabel();
    
    /**
     * The controls below are the slider controls to be used to control 
     * the track time and volume on the MP3 player 
     */
    public static JSlider sldVolume = new JSlider(0,100,50);
    public static JSlider sldTime = new JSlider();
    
    ControllerTest control = new ControllerTest();//ControllerTest object
    Player player = new Player();//Player object
    
    public static JPanel jpl = new JPanel();//Jpanel object
    
    public GUI()
    {
        try
        {
            /**
             * Setting size and layout of the JFrame
             */
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(510,510);
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
            setTitle("Gong MP3 Player");
            
            /**
             * Adding Jpanel to the JFrame and setting the layout
             * of the Jpanel
             */
            add(jpl);
            jpl.setLayout(null);
            
            /**
             * Adding buttons onto the JPanel and setting their bounds
             */
            jpl.add(btnPlay); 
            btnPlay.setBounds(10,400,80,35);
            jpl.add(btnStop); 
            btnStop.setBounds(110,400,80,35);
            jpl.add(btnPause); 
            btnPause.setBounds(210,400,80,35);
            jpl.add(btnMute); 
            btnMute.setBounds(310,400,80,35);
            jpl.add(btnRestart);
            btnRestart.setBounds(410,400,80,35);
            jpl.add(btnOpenFile);
            btnOpenFile.setBounds(30,20,130,35);
            jpl.add(btnSaveSettings); 
            btnLoadSettings.setBounds(30,70,130,35);
            jpl.add(btnLoadSettings);
            btnSaveSettings.setBounds(30,120,130,35);
            jpl.add(btnOpenPlaylist);
            btnOpenPlaylist.setBounds(30,170,130,35);
            
            /**
             * Adding labels onto the JPanel and setting their bounds
             */
            jpl.add(lblTrackName);
            lblTrackName.setBounds(180,15,200,50);
            jpl.add(lblTime);
            lblTime.setBounds(250,280,100,50);
            jpl.add(lblTotalTime);
            lblTotalTime.setBounds(350,280,100,50);
            jpl.add(lblVolumeLevel); 
            lblVolumeLevel.setBounds(250,225,100,50);
            jpl.add(lblMute); 
            lblMute.setBounds(332,360,100,50);
            
            /**
             * Adding sliders onto the JPanel and setting their bounds
             */
            jpl.add(sldVolume);
            sldVolume.setBounds(30,230,200,40);
            jpl.add(sldTime);
            sldTime.setBounds(30,290,200,40);
         
            
            /**
             * Buttons and sliders getting
             * the event control handlers being added to them.
             */
            //buttons
            btnPlay.addActionListener(control);
            btnStop.addActionListener(control);
            btnPause.addActionListener(control);
            btnOpenFile.addActionListener(control);
            btnMute.addActionListener(control);
            btnSaveSettings.addActionListener(control);
            btnLoadSettings.addActionListener(control);
            btnRestart.addActionListener(control);
            btnOpenPlaylist.addActionListener(control);
            //sliders
            sldVolume.addChangeListener(control);
            sldTime.addChangeListener(control);
        }
        catch(Exception e)
        {
              JOptionPane.showMessageDialog(jpl,"ERROR! Application can't start!");//error message
        }
    }
}
