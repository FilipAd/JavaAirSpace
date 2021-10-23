package vazdusni_prostor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import avioni.VojniAvion;
import leteciObjekti.*;
import radar.Upozorenje;

public class MapaVazdusnogProstora {

public Polje mapa[][];
private int brojRedovaMape;
private int brojKolonaMape;
private boolean zabranaLetenja;
private boolean neprijateljUProstoru;
private VojniAvion neprijateljskiAvion;
private static int brojSudara=1;
private static FileHandler filehandler;


static
{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"Mapa.log",true);
		filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}

public VojniAvion getNeprijateljskiAvion() {
	return neprijateljskiAvion;
}

//Konstruktor
public MapaVazdusnogProstora(){
	try
	{
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\properties_fajlovi\\config.properties");
		Properties prop=new Properties();
			prop.load(fs);
		brojRedovaMape=Integer.parseInt(prop.getProperty("brojVrstaVazdusnogProstora"));
		brojKolonaMape=Integer.parseInt(prop.getProperty("brojKolonaVazdusnogProstora"));
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	mapa=new Polje[brojRedovaMape][brojKolonaMape];
	for(int i=0;i<brojRedovaMape;i++)
	{
		for(int j=0;j<brojKolonaMape;j++)
		{
			mapa[i][j]=new Polje();
		}
	}

}

public void unistiNeprijatelja(){
	
	neprijateljskiAvion.setKrajObjekta(true);
	mapa[neprijateljskiAvion.getPozicijaX()][neprijateljskiAvion.getPozicijaY()].obrisiObjekatSaPolja(neprijateljskiAvion);
	
}


public synchronized void obrisiObjekatSaKoordinata(LeteciObjekat objekat,int pozicijaX,int pozicijaY){
	
	mapa[pozicijaX][pozicijaY].obrisiObjekatSaPolja(objekat);
}


public boolean getZabranaLetenja(){
	
	return zabranaLetenja;
}

public void setZabranaLetenja(boolean zabranaLetenja){
	
	this.zabranaLetenja=zabranaLetenja;
}

public boolean getNeprijateljUProstoru(){
	
	return neprijateljUProstoru;
}

public void setNeprijateljUProstoru(boolean neprijateljUProstoru){
	
	this.neprijateljUProstoru=neprijateljUProstoru;
}

public void setNeprijateljskiAvion(VojniAvion neprijateljskiAvion){
	
	this.neprijateljskiAvion=neprijateljskiAvion;
}



public synchronized void dodajObjekatNaPoziciju(LeteciObjekat objekat,int pozicijaX,int pozicijaY){
	
	if(objekat instanceof VojniAvion && neprijateljskiAvion!=null && !((VojniAvion) objekat).getNeprijateljski()){ //dodavanje vojnog prijateljskog aviona
		
		if(pozicijaX+1==neprijateljskiAvion.getPozicijaX() && pozicijaY==neprijateljskiAvion.getPozicijaY()){
			
				unistiNeprijatelja();
			}
		else if(pozicijaX-1==neprijateljskiAvion.getPozicijaX() && pozicijaY==neprijateljskiAvion.getPozicijaY()){
			
			unistiNeprijatelja();
		}
		else if(pozicijaY-1==neprijateljskiAvion.getPozicijaY() && pozicijaX==neprijateljskiAvion.getPozicijaX()){
			
			unistiNeprijatelja();
		}
		else if(pozicijaY+1==neprijateljskiAvion.getPozicijaY() && pozicijaX==neprijateljskiAvion.getPozicijaX()){
			
			unistiNeprijatelja();
		}
	}
	mapa[pozicijaX][pozicijaY].dodajObjekatNaPolje(objekat);
}

public int getBrojRedovaMape() {
	return brojRedovaMape;
}

public void setBrojRedovaMape(int brojRedovaMape) {
	this.brojRedovaMape = brojRedovaMape;
}

public int getBrojKolonaMape() {
	return brojKolonaMape;
}

public void setBrojKolonaMape(int brojKolonaMape) {
	this.brojKolonaMape = brojKolonaMape;
}
@Override
public String toString()
{
	StringBuilder stringbuilder=new StringBuilder();
	
	for(int i=0;i<brojRedovaMape;i++)
	{
		for(int j=0;j<brojKolonaMape;j++)
		{
			if(mapa[i][j].isSudar())
			{
				try
				{
					SimpleDateFormat format=new SimpleDateFormat("hh_mm_ss");
					Date datum=new Date();
					FileOutputStream pisac=new FileOutputStream(System.getProperty("user.dir")+File.separatorChar+"alert"+File.separatorChar+format.format(datum)+".ser");
					ObjectOutputStream out=new ObjectOutputStream(pisac);
					out.writeObject(new Upozorenje(i,j));
					out.close();
					//pisac.close();
					mapa[i][j].setSudar(false);
				}
				catch(Exception ex)
				{
					log(ex);
				}
			}
			if(!(mapa[i][j]).toString().isEmpty())
			{
				stringbuilder.append(mapa[i][j]).append(System.lineSeparator());
				
				if(mapa[i][j].isNeprijateljPrisutan() && !neprijateljUProstoru)
				{
					System.out.println("Nepriajtelj otkriven");
					neprijateljskiAvion=mapa[i][j].getNeprijatelj();
					this.neprijateljUProstoru=true;
				}
				if(neprijateljskiAvion!=null && neprijateljskiAvion.getKrajObjekta())
				{
					this.neprijateljUProstoru=false;
				}
			}
		}
	}
	return stringbuilder.toString().trim();
}

public static void log(Exception ex)
{
	Logger logger=Logger.getLogger(MapaVazdusnogProstora.class.getName());
	logger.addHandler(filehandler);
	logger.log(Level.WARNING,MapaVazdusnogProstora.class.getName(),ex);
}

}
