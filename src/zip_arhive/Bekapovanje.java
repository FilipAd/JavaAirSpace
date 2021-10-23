package zip_arhive;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Bekapovanje extends Thread
{
Path korjenskiDirektorijum=Paths.get(System.getProperty("user.dir"));
String tipfajla="*.txt";
Pretrazivac pretrazivac;
private static FileHandler filehandler;
private final int INTERVAL_BACKUP=6000;


static
{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"Bekapovanje.log",true);
		filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}

}
public Bekapovanje()
{
	super();
}
@Override
public void run()
{
	try
	{
	while(true)
	{
		Thread.sleep(INTERVAL_BACKUP);
		try
		{
			pretrazivac=new Pretrazivac(tipfajla);
			Files.walkFileTree(korjenskiDirektorijum,pretrazivac);
		}
		catch(Exception ex)
		{
			log(ex);
		}
		pretrazivac.zatvori();
	}
	}
	catch (Exception ex)
	{
		log(ex);
	}
}

public static void log(Exception ex)
{
	Logger logger=Logger.getLogger(Bekapovanje.class.getName());
	logger.addHandler(filehandler);
	logger.log(Level.WARNING,Bekapovanje.class.getName(),ex);
}
}
