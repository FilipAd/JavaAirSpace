package filewatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


import simulator.Simulacija;

public class ConfigWatcher extends Watcher 
{
Simulacija simulacija;
private static FileHandler filehandler;

static
{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"ConfigWatcher.log");
		filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}

public ConfigWatcher(Simulacija simulacija)
{
	super(System.getProperty("user.dir")+File.separatorChar+"src"+File.separatorChar+"properties_fajlovi");
	this.simulacija=simulacija;
}
@Override
public void azuriraj(Path nazivfajla)
{
	try
	{
		FileInputStream citac=new FileInputStream(direktorijum.toString()+File.separatorChar+nazivfajla.toString());
		Properties properties=new Properties();
		properties.load(citac);
		synchronized (simulacija) 
		{
		simulacija.setVrijeme(Integer.parseInt(properties.getProperty("vrijeme")));
		simulacija.setMoguceUbacitiNeprijateljskuLetjelicu(Boolean.parseBoolean(properties.getProperty("moguceUbacitiNeprijateljskuLetjelicu")));
		simulacija.setMoguceUbacitiVojniAvion(Boolean.parseBoolean(properties.getProperty("moguceUbacitiVojniAvion")));
		}
		citac.close();
		
	}
	catch(Exception ex)
	{
		log(ex);
	}
}
public static void log(Exception ex)
{
	Logger logger=Logger.getLogger(ConfigWatcher.class.getName());
	logger.addHandler(filehandler);
	logger.log(Level.WARNING,ConfigWatcher.class.getName(),ex);
}
}
