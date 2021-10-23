package radar;
import avioni.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import informacije.PodaciIzVazdusnogProstora;
import simulator.Simulacija;
import vazdusni_prostor.MapaVazdusnogProstora;

public class Radar extends Thread
{
	private MapaVazdusnogProstora mapa;
	private Simulacija simulacija;
	private PodaciIzVazdusnogProstora podaci;
	public static PrintWriter pisac;
	private int vrijeme=2;
	private static FileHandler filehandler;
	
	
	static
	{
		try
		{
			filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"Radar.log",true);
			filehandler.setFormatter(new SimpleFormatter());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	static
	{
		try
		{
			pisac=new PrintWriter(new BufferedWriter(new FileWriter(new File("mapa.txt"))));
		}
		catch(Exception ex)
		{
			log(ex);
		}
	}
	
	public Radar(MapaVazdusnogProstora mapa,PodaciIzVazdusnogProstora podaci,Simulacija simulacija)
	{
		this.mapa=mapa;
		this.podaci=podaci;
		this.simulacija=simulacija;
		try
		{
			FileInputStream citac=new FileInputStream(new File(System.getProperty("user.dir")+File.separatorChar+"src"+File.separatorChar+"properties_fajlovi"+File.separatorChar+"radar.properties"));
			Properties svojstva=new Properties();
			svojstva.load(citac);
			vrijeme=Integer.parseInt(svojstva.getProperty("vrijeme"));//vrijeme azuriranja
			citac.close();
		}
		catch(Exception ex)
		{
			log(ex);
		}
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
				{
				podaci.setPodaciIzVazdusnogProstora(mapa);
				
				
				if(mapa.getNeprijateljUProstoru() && !simulacija.isUbaciLovackiAvion()) //ako je neprijateljski avion usao u vazdusni prostor i ako je dat znak za presretanje neprijatelja
				{
					Date date=new Date();
					SimpleDateFormat format=new SimpleDateFormat("hh-mm-ss");
					File putanja=new File(System.getProperty("user.dir")+File.separatorChar+"events"+File.separatorChar+format.format(date)+".txt");
					try
					{
						PrintWriter pisac=new PrintWriter(new BufferedWriter(new FileWriter(putanja)));
						String opis="Neprijateljska letjelica je usla u vazdusni prosotor u "+format.format(date); //tekst koji se upisuje u fajlove u events-u kada radar primjeti neprijateljski avion
						pisac.println(opis);
						pisac.close();
					}
					catch(Exception ex)
					{
						log(ex);
					}
					VojniAvion nepriajtelj=mapa.getNeprijateljskiAvion();
					simulacija.setUbaciLovackiAvion(true); //posalji lovacku zastitu;
				}
				else if(!mapa.getNeprijateljUProstoru() && simulacija.isUbaciLovackiAvion()) //ukoliko su prijateljski lovci unistili neprijatelja u vazdusnom prostoru
				{
					mapa.setNeprijateljUProstoru(false);  // dajemo znak da nepriajtelj vise ne postoji u nasem vazdsunom prostoru
					simulacija.setUbaciLovackiAvion(false);
					System.out.println("Neprijateljski avion vise ne narusava nas vazdusni prostor!");
					simulacija.setUbaciNeprijateljskuLetjelicu(false);
					simulacija.getLovac1().setPromjenaPravca(false);  //lovcima dopustamo da izadju iz prostora
					simulacija.getLovac2().setPromjenaPravca(false);
					mapa.setZabranaLetenja(false); //vazdusni prostor je opet otvoren za saobracaj
					
					synchronized(simulacija)
					{
						simulacija.notify();  // posto je u toku bila zabrana letenja simulacija je bila na wait pa je obavjestavamo da moze da nastavi
					}
					
				}
				Thread.sleep(1000);
				
				}
				catch (Exception ex)
				{
					log(ex);
				}
			
				
				}
		
	}
	
	public static void log(Exception ex)
	{
		Logger logger=Logger.getLogger(Radar.class.getName());
		logger.addHandler(filehandler);
		logger.log(Level.WARNING,Radar.class.getName(),ex);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
