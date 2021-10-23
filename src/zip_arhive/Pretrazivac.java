package zip_arhive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.attribute.BasicFileAttributes;

public class Pretrazivac implements FileVisitor<Path> 
{

	private PathMatcher matcher;
	static ZipOutputStream pisac;
	private static FileHandler filehandler;
	
	
	static
	{
		try
		{
			
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"Pretrazivac.log",true);
		filehandler.setFormatter(new SimpleFormatter());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	Pretrazivac()
	{
	}
	
	
	Pretrazivac(String tipFajla)
	{
		matcher=FileSystems.getDefault().getPathMatcher("glob:"+tipFajla);
		try
		{
			Date datum=new Date();
			SimpleDateFormat format=new SimpleDateFormat("_yyyy_MM_dd_hh_mm");
			pisac=new ZipOutputStream(new FileOutputStream(System.getProperty("user.dir")+File.separatorChar+"arhive"+File.separatorChar+"backup"+format.format(datum)+".zip"));
		}
		catch(Exception ex)
		{
			log(ex);
		}
	}
	
	void pronadji(Path fajl)
	{
		Path naziv=fajl.getFileName();
		if(naziv!=null && matcher.matches(naziv))
		{
			try
			{
				pisac.putNextEntry(new ZipEntry(naziv.toString()));
			}
			catch(Exception ex)
			{
				log(ex);
			}
		}
	}
	
	void zatvori()
	{
		try
		{
			pisac.close();
		}
		catch(Exception ex)
		{
			log(ex);
		}
	}
	@Override
	public FileVisitResult visitFile(Path fajl, BasicFileAttributes atributi) {
		pronadji(fajl);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path direktorijum, BasicFileAttributes attrs) {
		pronadji(direktorijum);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path fajl, IOException ex) {
		System.err.println(ex);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path direktorijum, IOException arg1) throws IOException {
		pronadji(direktorijum);
		return FileVisitResult.CONTINUE;
	}
	
	public static void log(Exception ex)
	{
		Logger logger=Logger.getLogger(Pretrazivac.class.getName());
		logger.addHandler(filehandler);
		logger.log(Level.WARNING,Pretrazivac.class.getName(),ex);
	}
	
	
	
	
}
