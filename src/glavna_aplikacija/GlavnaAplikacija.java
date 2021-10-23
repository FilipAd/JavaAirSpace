package glavna_aplikacija;
import leteciObjekti.*;
import simulator.Simulacija;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import avioni.Bombarder;
import avioni.Lovac;
import avioni.ProtivPozarniAvion;
import avioni.PutnickiAvion;
import avioni.TransportniAvion;
import filewatcher.DogadjajiWatcher;
import helikopteri.ProtivPozarniHelikopter;
import helikopteri.PutnickiHelikopter;
import helikopteri.TransportniHelikopter;
import informacije.PodaciIzVazdusnogProstora;
import interfejsi.VazduhVazduhBorba;
import vazdusni_prostor.MapaVazdusnogProstora;

public class GlavnaAplikacija extends Thread
{
	private Ekran ekran;
	private int PVisina;
	private int PSirina;
	private static BufferStrategy buffer;
	private static Graphics graphic;
	private MapaVazdusnogProstora mapa;
	private List<LeteciObjekat> letjelice=new ArrayList<>();
	public PodaciIzVazdusnogProstora podaci;
	
	private DogadjajiWatcher watcher;
	
	private static FileHandler filehandler;
	
	static
	{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"GlavnaAplikacija.log",true);
		SimpleFormatter format=new SimpleFormatter();
		filehandler.setFormatter(format);
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	}
	
	
	public GlavnaAplikacija(String naslov,int sirina,int visina,MapaVazdusnogProstora mapa,PodaciIzVazdusnogProstora podaci,Simulacija simulacija)
	{
		ekran=new Ekran(naslov,sirina,visina,simulacija,mapa);
		this.mapa=mapa;
		this.podaci=podaci;
		this.PSirina=mapa.getBrojKolonaMape()*10;
		this.PVisina=mapa.getBrojRedovaMape()*10;
		watcher=new DogadjajiWatcher(this.ekran);
		watcher.start();
	}
	
	public void iscrtaj()   //za oscrtavanje letjelica
	{
		buffer=ekran.getCanvas().getBufferStrategy();
		
		if(buffer==null)
		{
			ekran.getCanvas().createBufferStrategy(3);
			return;
		}
		
		graphic=buffer.getDrawGraphics();
		graphic.clearRect(0,0,PSirina+15,PVisina+15);
		graphic.setColor(Color.WHITE);//boja vazdusnog prostora
		graphic.fillRect(5,5,this.PSirina,this.PVisina);
		
		graphic.setColor(new Color(50,50,50));//boja na legendi za bespilotnuletjelicu
		graphic.fillOval(1200, 500,20,20);
		
		graphic.setColor(new Color(102,250,97));//boja na legendi putnicki avion
		graphic.fillOval(1200, 450,20,20);
		
		graphic.setColor(new Color(50,200,250));//boja na legendi za protiv pozarni avion 
		graphic.fillOval(1200, 400,20,20);
		
		graphic.setColor(new Color(0,102,0));//boja na legendi za protivpozarni helikopter
		graphic.fillOval(1200,350,20,20);
		
		graphic.setColor(Color.MAGENTA);//boja na legendi za putnicki helikopter
		graphic.fillOval(1200,300,20,20);
		
		graphic.setColor(Color.blue);//boja na legendi za nepriajteljski avion
		graphic.fillOval(1200,250,20,20);
		
		graphic.setColor(new Color(100,10,40));//boja na legendi za bombarder
		graphic.fillOval(1200,200,20,20);
		
		graphic.setColor(Color.red);//boja na legendi za prijateljskog lovca
		graphic.fillOval(1200,150,20,20);
		
		graphic.setColor(new Color(187,132,98));//boja an legendi za trapsortni avion
		graphic.fillOval(1200,100,20,20);
		
		graphic.setColor(new Color(150,204,90));//boja na legenti za trasportni helikoter
		graphic.fillOval(1200,50,20,20);
		
		if(letjelice.size()>0) {
			
			
			for(LeteciObjekat letjelica:letjelice) {
				letjelica.iscrtaj(graphic);
				
			}
		}
		
		letjelice.clear();
		buffer.show();
		graphic.dispose();
		
	}
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				List<String> redovi=podaci.getPodaciIzVazdusnogProstora();
				for(String jedanRed:redovi)
				{
					String [] elementi=jedanRed.split("#");
					if(elementi.length>1)
					{
						String letjelica=elementi[0];
						
						switch(letjelica)
						{
						case "Putnicki-Avion":
							PutnickiAvion putnickiavion=new PutnickiAvion(elementi[2],Integer.parseInt(elementi[3]),Integer.parseInt(elementi[4]),Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]));
							letjelice.add(putnickiavion);
							break;
						case "Protiv-Pozarni-Avion":
							ProtivPozarniAvion ppavion=new ProtivPozarniAvion(elementi[2],Integer.parseInt(elementi[3]),Integer.parseInt(elementi[4]),Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]));
							letjelice.add(ppavion);
							break;
						case "Transportni-Avion":
							TransportniAvion trasnportniavion=new TransportniAvion(elementi[2],Integer.parseInt(elementi[3]),Integer.parseInt(elementi[4]),Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]));
							letjelice.add(trasnportniavion);
							break;
						case "Lovac":
							Lovac lovac=new Lovac(elementi[4],Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]),Integer.parseInt(elementi[8]),Integer.parseInt(elementi[9]),Boolean.parseBoolean(elementi[2]));
							letjelice.add(lovac);
							break;
						case "Bombarder":
							Bombarder bombarder=new Bombarder(elementi[4],Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]),Integer.parseInt(elementi[8]),Integer.parseInt(elementi[9]),Boolean.parseBoolean(elementi[2]));
							letjelice.add(bombarder);
							break;
						case "Putnicki-Helikopter":
							PutnickiHelikopter putnickihelikopter=new PutnickiHelikopter(elementi[2],Integer.parseInt(elementi[3]),Integer.parseInt(elementi[4]),Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]));
							letjelice.add(putnickihelikopter);
							break;
						case "Protiv-Pozarni-Helikopter":
							ProtivPozarniHelikopter pphelikopter=new ProtivPozarniHelikopter(elementi[2],Integer.parseInt(elementi[3]),Integer.parseInt(elementi[4]),Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]));
							letjelice.add(pphelikopter);
							break;
						case "Transportni-Helikopter":
							TransportniHelikopter transportnihelikopter=new TransportniHelikopter(elementi[2],Integer.parseInt(elementi[3]),Integer.parseInt(elementi[4]),Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]));
							letjelice.add(transportnihelikopter);
							break;
						case "Bespilotna-Letjelica":
							BespilotnaLetjelica bespilotna=new BespilotnaLetjelica(elementi[2],Integer.parseInt(elementi[3]),Integer.parseInt(elementi[4]),Integer.parseInt(elementi[5]),Integer.parseInt(elementi[6]),Integer.parseInt(elementi[7]));
							letjelice.add(bespilotna);
							break;
							
							default:
						}
						
					}
				}
				
				this.iscrtaj();
			}
			catch(Exception ex)
			{
				log(ex);
			}
		}
	
	
		
	}
	
	public static void log(Exception ex)
	{
		Logger logger=Logger.getLogger(GlavnaAplikacija.class.getName());
		logger.addHandler(filehandler);
		logger.log(Level.WARNING,GlavnaAplikacija.class.getName(),ex);
	}
	
	public static void main(String args[])
	{
		
	}

}
