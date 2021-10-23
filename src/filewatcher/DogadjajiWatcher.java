package filewatcher;

import java.io.File;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import glavna_aplikacija.Ekran;

public class DogadjajiWatcher extends Watcher 
{
	Ekran ekran;
	private static FileHandler filehandler;
	
	
	static
	{
		try
		{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"DogadjajiWatcher.log",true);
		filehandler.setFormatter(new SimpleFormatter());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public DogadjajiWatcher(Ekran ekran)
	{
		super(System.getProperty("user.dir")+File.separatorChar+"events");
		this.ekran=ekran;
	}
	
	@Override
	public void azuriraj(Path nazivfajla)
	{
		try
		{
			ekran.dodajLabelu();
		}
		catch(Exception ex)
		{
			log(ex);
		}
	}
	
	public static void log(Exception ex)
	{
		Logger logger=Logger.getLogger(DogadjajiWatcher.class.getName());
		logger.addHandler(filehandler);
		logger.log(Level.WARNING,DogadjajiWatcher.class.getName(),ex);
	}
	
}
