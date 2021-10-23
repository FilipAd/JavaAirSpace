package filewatcher;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.security.KeyStore.Entry;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class Watcher extends Thread
{
private WatchService watcher;
protected Path direktorijum;
private WatchKey key;
private static FileHandler filehandler;

static
{
	try 
	{
	filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"Watcher.log",true);
	filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}

public Watcher(String putanja)
{
	try
	{
		watcher=FileSystems.getDefault().newWatchService();
		direktorijum=Paths.get(putanja);
		direktorijum.register(watcher,ENTRY_CREATE,ENTRY_MODIFY);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
@Override
public void run()
{
	while(true)
	{
		try
		{
		key=watcher.take();
		}
		catch(Exception ex)
		{
			log(ex);
			return;
		}
		for(WatchEvent<?> dogadjaj:key.pollEvents())
		{
			WatchEvent.Kind<?> kind=dogadjaj.kind();
			WatchEvent<Path> dog=(WatchEvent<Path>)dogadjaj;
			Path nazivfajla=dog.context();
			if((nazivfajla.toString().trim().endsWith("txt") || nazivfajla.toString().trim().endsWith("properties")||nazivfajla.toString().trim().endsWith("ser")) && (kind.equals(ENTRY_MODIFY)||kind.equals(ENTRY_CREATE)))
				{
				azuriraj(nazivfajla);
	
				}
			boolean ispravan=key.reset();
			if(!ispravan)
			{
				break;
			}
			
		}
	}
}
public abstract void azuriraj(Path nazivfajla);
public static void log(Exception ex)
{
	Logger logger=Logger.getLogger(Watcher.class.getName());
	logger.addHandler(filehandler);
	logger.log(Level.WARNING,Watcher.class.getName(),ex);
}
}
