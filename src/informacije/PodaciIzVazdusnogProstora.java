package informacije;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import vazdusni_prostor.MapaVazdusnogProstora;

public class PodaciIzVazdusnogProstora 
{
private File putanja;
private boolean citanje=false;
private static FileHandler filehandler;

static
{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"PodaciIzVazdusnogProstora.log",true);
		filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}
public PodaciIzVazdusnogProstora()
{
	putanja=new File("mapa.txt");  //postavljamo putanju na map.txt fajl
}


public synchronized void setPodaciIzVazdusnogProstora(MapaVazdusnogProstora mapa)
{
	if(citanje) //ako neka nit cita potrebno je sacekati sa upisom
	{
		try
		{
			wait();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	else 
	{
		PrintWriter pisac;
		try
		{
			pisac=new PrintWriter(new BufferedWriter(new FileWriter(putanja)));
			pisac.println(mapa);
			pisac.close();
			citanje=true;  //nakon upisa daje se signal da je omoguceno citanje
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
public synchronized List<String> getPodaciIzVazdusnogProstora()
{
	Path putanja=FileSystems.getDefault().getPath("mapa.txt");//stavljamo putanju do fajla iz kog se citaju podaci
	List<String> podaci=new ArrayList<>();
	try
	{
	podaci=Files.readAllLines(putanja);
	citanje=false;
	notify(); //obavjesti niti koje cekaju na upis
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return podaci;
	
}
public static void log(Exception ex)
{
	Logger logger=Logger.getLogger(PodaciIzVazdusnogProstora.class.getName());
	logger.addHandler(filehandler);
	logger.log(Level.WARNING,PodaciIzVazdusnogProstora.class.getName(),ex);
	
}

}
