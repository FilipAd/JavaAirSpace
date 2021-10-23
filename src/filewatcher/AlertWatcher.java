package filewatcher;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;

import radar.Upozorenje;



public class AlertWatcher extends Watcher
{
private static FileHandler filehandler;
static
{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"AlertWatcher.log",true);
		filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}

public AlertWatcher()
{
	super(System.getProperty("user.dir")+File.separatorChar+"alert");
}
@Override
public void azuriraj(Path nazivfajla)
{
	try
	{
		FileInputStream citac=new FileInputStream(direktorijum.toString()+File.separatorChar+nazivfajla.toString());
		ObjectInputStream in=new ObjectInputStream(citac);
		Upozorenje upozorenje=new Upozorenje();
		upozorenje=(Upozorenje)in.readObject();
		//in.close();
		citac.close();
		JFrame frame=new JFrame();
		frame.setSize(new Dimension(800,300));
		frame.setLayout(null);
		JLabel label=new JLabel();
		label.setBounds(200,30,550,250);
		label.setText(upozorenje.toString());
		label.setBackground(Color.CYAN);
		frame.add(label);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.RED);
		frame.setVisible(true);
	}
	catch(Exception ex)
	{
		log(ex);
	}
}
public static void log(Exception ex)
{
	Logger logger=Logger.getLogger(AlertWatcher.class.getName());
	logger.addHandler(filehandler);
	logger.log(Level.WARNING,ex.fillInStackTrace().toString());
}
}
