package simulator;
import leteciObjekti.*;
import java.io.File;
import osobe.Pilot;
import osobe.Putnik;
import rakete.Raketa;
import vazdusni_prostor.MapaVazdusnogProstora;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import osobe.Osoba;
import avioni.Bombarder;
import avioni.Lovac;
import avioni.ProtivPozarniAvion;
import avioni.PutnickiAvion;
import avioni.TransportniAvion;
import avioni.VojniAvion;
import helikopteri.ProtivPozarniHelikopter;
import helikopteri.PutnickiHelikopter;
import helikopteri.TransportniHelikopter;
import interfejsi.VazduhVazduhBorba;

public class Simulacija extends Thread
{
	
	int vrijeme;
	static Properties prop=new Properties();
	static FileInputStream fs;
	MapaVazdusnogProstora mapa;
	private boolean ubaciNeprijateljskuLetjelicu;
	private boolean moguceUbacitiNeprijateljskuLetjelicu;
	private boolean moguceUbacitiVojniAvion;
	private boolean moguceUbacitiRaketu;
	private boolean ubaciLovackiAvion;
	private Lovac lovac1;
	private Lovac lovac2;
	
	
private static FileHandler filehandler;

static
{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"Simulacija.log",true);
		filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}
	
public Simulacija(MapaVazdusnogProstora mapa)
{
	this.mapa=mapa;
	{
		try {
		
		fs=new FileInputStream(System.getProperty("user.dir")+File.separatorChar+"src"+File.separatorChar+"properties_fajlovi"+File.separatorChar+"config.properties");
		prop.load(fs);
		vrijeme=Integer.parseInt(prop.getProperty("vrijeme"));
		 ubaciNeprijateljskuLetjelicu=Boolean.parseBoolean(prop.getProperty("ubaciNeprijateljskuLetjelicu"));
		 moguceUbacitiNeprijateljskuLetjelicu=Boolean.parseBoolean(prop.getProperty("moguceUbacitiNeprijateljskuLetjelicu"));
		 moguceUbacitiVojniAvion=Boolean.parseBoolean(prop.getProperty(" moguceUbacitiVojniAvion"));
		 moguceUbacitiRaketu=Boolean.parseBoolean(prop.getProperty("moguceUbacitiRaketu"));
		 ubaciLovackiAvion=Boolean.parseBoolean(prop.getProperty("ubaciLovackiAvion"));
		}
		catch(Exception ex)
		{
			log(ex);
		}
		
	}
}

public int getVrijeme() {
	return vrijeme;
}

public void setVrijeme(int vrijeme) {
	this.vrijeme = vrijeme;
}

public static Properties getProp() {
	return prop;
}

public static void setProp(Properties prop) {
	Simulacija.prop = prop;
}

public static FileInputStream getFs() {
	return fs;
}

public static void setFs(FileInputStream fs) {
	Simulacija.fs = fs;
}

public MapaVazdusnogProstora getMapa() {
	return mapa;
}

public void setMapa(MapaVazdusnogProstora mapa) {
	this.mapa = mapa;
}

public void posaljiLovca(VojniAvion neprijatelj,int pozicijaX, int pozicijaY)
{
	int pozicijaX1=0;
	int pozicijaY1=0;
	int pozicijaX2=0;
	int pozicijaY2=0;
	
	if(neprijatelj.getStranaUlaska()==2) //neprijateljski avion ulazi odozgo u vazdusni prostor i ne leti uz lijevi rub
	{
		if(pozicijaY>0)					
		pozicijaY1=pozicijaY-1;
		pozicijaY2=pozicijaY+1;
		pozicijaX1=0;
		pozicijaX2=0;
		
	}
	
	if(neprijatelj.getStranaUlaska()==4) //neprijateljski avion ulazi odozdo u vazdusni prostor i ne leti uz lijevi rub
	{
		if(pozicijaY>0)
		pozicijaY1=pozicijaY-1;
		pozicijaY2=pozicijaY+1;
		pozicijaX1=mapa.getBrojRedovaMape()-1;
		pozicijaX2=mapa.getBrojRedovaMape()-1;
		
		
	}
	
	if(neprijatelj.getStranaUlaska()==3) //neprijateljski avion ulazi sa desne strane u vazdusni prostor i ne leti uz gornji rub
	{
		if(pozicijaX>0)
			pozicijaX1=pozicijaX-1;
			pozicijaX2=pozicijaX+1;
			pozicijaY1=mapa.getBrojKolonaMape()-1;
			pozicijaY2=mapa.getBrojKolonaMape()-1;
	}
	if(neprijatelj.getStranaUlaska()==1) //neprijateljski avion ulazi sa lijeve strane i ne leti uz gornji rub
	{
		if(pozicijaX>0)
			pozicijaX1=pozicijaX-1;
			pozicijaX2=pozicijaX+1;
			pozicijaY1=0;
			pozicijaY2=0;
	}
	System.out.println("Kreiranje lovackog para");
	System.out.println("x1: "+pozicijaX1+" y1: "+pozicijaY1+" x2: "+pozicijaX2+" y2: "+pozicijaY2);
	int brzinaLovca1=new Random().nextInt(4)+1;
	int brzinaLovca2=new Random().nextInt(4)+1;
	HashMap<Integer,String> karakteristike1=new HashMap<>();
	karakteristike1.put(1,"Dezurni lovac 1");
	HashMap<Integer,String> karakteristike2=new HashMap<>();
	karakteristike2.put(1,"Dezurni lovac 2");
	lovac1=new Lovac(mapa,"MiG-31",karakteristike1,null,pozicijaX1,pozicijaY1,brzinaLovca1,false);
	lovac2=new Lovac(mapa,"Su-27",karakteristike2,null,pozicijaX2,pozicijaY2,brzinaLovca2,false);
	//ja
	if(neprijatelj.getStranaUlaska()==1)
	{
	lovac1.setStranaUlaska(1);
	lovac1.setPozicijaX(pozicijaX1);
	lovac1.setPozicijaY(pozicijaY1);
	lovac1.setVisina(neprijatelj.getVisina());
	lovac2.setStranaUlaska(1);
	lovac2.setPozicijaX(pozicijaX2);
	lovac2.setPozicijaY(pozicijaY2);
	lovac2.setVisina(neprijatelj.getVisina());
	}
	else if(neprijatelj.getStranaUlaska()==2)
	{
		lovac1.setStranaUlaska(2);
		lovac2.setStranaUlaska(2);

		lovac1.setPozicijaX(pozicijaX1);
		lovac1.setPozicijaY(pozicijaY1);
		lovac1.setVisina(neprijatelj.getVisina());
		
		lovac2.setPozicijaX(pozicijaX2);
		lovac2.setPozicijaY(pozicijaY2);
		lovac2.setVisina(neprijatelj.getVisina());
	}
	else if(neprijatelj.getStranaUlaska()==3)
	{
		lovac1.setStranaUlaska(3);
		lovac2.setStranaUlaska(3);
		lovac1.setPozicijaX(pozicijaX1);
		lovac1.setPozicijaY(pozicijaY1);
		lovac1.setVisina(neprijatelj.getVisina());
		
		lovac2.setPozicijaX(pozicijaX2);
		lovac2.setPozicijaY(pozicijaY2);
		lovac2.setVisina(neprijatelj.getVisina());
	}
	else if(neprijatelj.getStranaUlaska()==4)
	{
		lovac1.setStranaUlaska(4);
		lovac2.setStranaUlaska(4);
		lovac1.setVisina(neprijatelj.getVisina());
		lovac1.setPozicijaX(pozicijaX1);
		lovac1.setPozicijaY(pozicijaY1);
		lovac2.setVisina(neprijatelj.getVisina());
		lovac2.setPozicijaX(pozicijaX2);
		lovac2.setPozicijaY(pozicijaY2);
	}
	
	System.out.println("lovac1 "+lovac1.getPozicijaX()+":"+lovac1.getPozicijaY()+" lovac2 "+lovac2.getPozicijaX()+":"+lovac2.getPozicijaY());
	neprijatelj.setPromjenaPravca(true);
	lovac1.setPromjenaPravca(true);
	lovac2.setPromjenaPravca(true);
	
	if(lovac1.getPozicijaX()==0 && lovac1.getPozicijaY()==0) //postavljanje lovaca u zavisnosti od poziicje neprijatelja
	{
		if(neprijatelj.getStranaUlaska()==1)
		{
			lovac1.setStranaUlaska(1);
		
		}
	}
	else if(lovac1.getPozicijaX()==0 && lovac1.getPozicijaY()==(mapa.getBrojKolonaMape()-1))
	{
		if(neprijatelj.getStranaUlaska()==3)
		{
			lovac1.setStranaUlaska(3);
		
		}
	}
	else if(lovac1.getPozicijaX()==(mapa.getBrojRedovaMape()-1) && lovac1.getPozicijaY()==0)
	{
		if(neprijatelj.getStranaUlaska()==1)
		{
			lovac1.setStranaUlaska(1);
			
		}
	}
	else if(lovac1.getPozicijaX()==(mapa.getBrojRedovaMape()-1) && lovac1.getPozicijaY()==(mapa.getBrojKolonaMape()-1))
	{
		if(neprijatelj.getStranaUlaska()==3)
		{
			lovac1.setStranaUlaska(3);
		}
	}
	mapa.dodajObjekatNaPoziciju(lovac1,lovac1.getPozicijaX(),lovac1.getPozicijaY());
	mapa.dodajObjekatNaPoziciju(lovac2,lovac2.getPozicijaX(),lovac2.getPozicijaY());
	mapa.setZabranaLetenja(true);
	System.out.println("lovac1 "+lovac1.getPozicijaX()+":"+lovac1.getPozicijaY()+" lovac2 "+lovac2.getPozicijaX()+":"+lovac2.getPozicijaY());
	lovac1.start();
	lovac2.start();
	
}

@Override
public void run()
{
	while(true)
	{
		try
		{
			if(mapa.getZabranaLetenja()) //ako je u toku zabrana letenja 
			{
				synchronized(this)
				{
				wait();
				}
			}
			Thread.sleep(vrijeme*1000);
			
			if(this.ubaciLovackiAvion)  //ako je dat znak da se posalje lovacki avion
			{
				posaljiLovca(mapa.getNeprijateljskiAvion(),mapa.getNeprijateljskiAvion().getPozicijaX(),mapa.getNeprijateljskiAvion().getPozicijaY());
			}
			int vrstaLetjelice=new Random().nextInt(11);
			
			switch(vrstaLetjelice)
			{
			case 0:
				if(!this.ubaciNeprijateljskuLetjelicu) //ako nije dat znak da se posalje neprijtaljski avion
				{
					System.out.println("Putnicki avion ulazi u vazdusni prostor");
					HashMap<Integer,String> karakteristike =new HashMap<>();
					karakteristike.put(1,"Kompanija AEROFLOT");
					karakteristike.put(2,"Zeleni");
					PutnickiAvion putnicki=new PutnickiAvion(mapa, karakteristike,new Osoba[] {new Pilot(),new Putnik()},new Random().nextInt(mapa.getBrojRedovaMape()),0);
					mapa.dodajObjekatNaPoziciju(putnicki,putnicki.getPozicijaX(),putnicki.getPozicijaY());
					putnicki.start();
				}
				break;
			case 1:
				if(!this.ubaciNeprijateljskuLetjelicu)
				{
					System.out.println("Transportni avion ulazi u vazdusni prostor");
					HashMap<Integer,String> karakteristike =new HashMap<>();
					karakteristike.put(1,"Prevoz humanitarne pomoci");
					karakteristike.put(2,"Sivi");
					TransportniAvion transporter=new TransportniAvion(mapa,karakteristike,new Osoba[] {new Pilot(),new Putnik(),new Putnik()},new Random().nextInt(mapa.getBrojRedovaMape()),100);
					mapa.dodajObjekatNaPoziciju(transporter,transporter.getPozicijaX(),transporter.getPozicijaY());
					transporter.start();
				}
			case 2:
				if(!this.ubaciNeprijateljskuLetjelicu)
				{
					System.out.println("Putnicki helikopter ulazi u vazdusni prostor");
					HashMap<Integer,String>karakteristike=new HashMap<>();
					karakteristike.put(1,"Maskirno zeleni");
					PutnickiHelikopter putnickiHelikopter=new PutnickiHelikopter(mapa,karakteristike,new Osoba[] {new Pilot()},new Random().nextInt(mapa.getBrojRedovaMape()),0);
					mapa.dodajObjekatNaPoziciju(putnickiHelikopter,putnickiHelikopter.getPozicijaX(),putnickiHelikopter.getPozicijaY());
					putnickiHelikopter.start();
				}
				break;
				
			case 3:
				if(!this.ubaciNeprijateljskuLetjelicu)
				{
					System.out.println("Protivpozarni avion ulazi u vazdusni prostor");
					HashMap<Integer,String> karakteristike=new HashMap<>();
					karakteristike.put(1,"Air Canada");
					ProtivPozarniAvion ppAvion=new ProtivPozarniAvion(mapa,"Kanader A1",karakteristike,new Osoba[] {new Pilot(),new Putnik()},new Random().nextInt(mapa.getBrojRedovaMape()),0);
					ppAvion.start();
				}
				break;
			case 4:
				if(!this.ubaciNeprijateljskuLetjelicu) 
				{
					System.out.println("Protivpozarni helikopter ulazi u vazdusni prostor");
					HashMap<Integer,String>karakteristike=new HashMap<>();
					karakteristike.put(1,"Zuti");
					ProtivPozarniHelikopter ppHelikopter=new ProtivPozarniHelikopter(mapa,karakteristike,new Osoba[] {new Pilot()},new Random().nextInt(mapa.getBrojRedovaMape()),0);
					mapa.dodajObjekatNaPoziciju(ppHelikopter,ppHelikopter.getPozicijaX(),ppHelikopter.getPozicijaY());
					ppHelikopter.start();
					
				}
			case 5:
				if(!this.ubaciNeprijateljskuLetjelicu)
				{
					System.out.println("Transportni helikopter ulazi u vazdusni prostor");
					HashMap<Integer,String>karakteristike=new HashMap<>();
					karakteristike.put(1,"Narandzasti");
					TransportniHelikopter transportniHelikopter=new TransportniHelikopter(mapa,"MI-17", karakteristike,new Osoba[] {new Pilot()},new Random().nextInt(mapa.getBrojRedovaMape()),0);
					mapa.dodajObjekatNaPoziciju(transportniHelikopter,transportniHelikopter.getPozicijaX(),transportniHelikopter.getPozicijaY());
					transportniHelikopter.start();
				}
			case 6:
				if(!this.ubaciNeprijateljskuLetjelicu && this.moguceUbacitiRaketu) //slanje rakete
				{
				Raketa raketa=new Raketa(mapa,new Random().nextInt(mapa.getBrojRedovaMape()),0);
				mapa.dodajObjekatNaPoziciju(raketa,raketa.getPozicijaX(),raketa.getPozicijaY());
				}
				break;
				
			case 7:
				if(!this.ubaciNeprijateljskuLetjelicu && this.moguceUbacitiVojniAvion)  //ulazak prijateljskog lovca u prostor
				{
					System.out.println("Prijateljski lovac ulazi u vazdusni prostor");
					HashMap<Integer,String>karakteristike=new HashMap<>();
					karakteristike.put(1,"Crveni");
					Lovac lovac=new Lovac(mapa,"Mig-29",karakteristike,new Osoba[] {new Pilot()},new Random().nextInt(mapa.getBrojRedovaMape()),0,false);
					mapa.dodajObjekatNaPoziciju(lovac,lovac.getPozicijaX(),lovac.getPozicijaY());
					lovac.start();
					
				}
				break;
			case 8:
				if(!this.ubaciNeprijateljskuLetjelicu && this.moguceUbacitiVojniAvion)  //ulazak prijateljskog lovca u prostor
				{
					System.out.println("Prijateljski bombarder ulazi u vazdusni prostor");
					HashMap<Integer,String>karakteristike=new HashMap<>();
					karakteristike.put(1,"Svijetlo zeleni");
					Bombarder bombarder=new Bombarder(mapa,"TU-95",karakteristike,new Osoba[] {new Pilot(),new Pilot()},new Random().nextInt(mapa.getBrojRedovaMape()),0,false);
					mapa.dodajObjekatNaPoziciju(bombarder,bombarder.getPozicijaX(),bombarder.getPozicijaY());
					bombarder.start();
				}
				break;
				
			case 9:
				if(!this.ubaciNeprijateljskuLetjelicu && this.moguceUbacitiNeprijateljskuLetjelicu) {
					System.out.println("Neprijateljska letjelica ulazi u vazdusni prostor");
					HashMap<Integer,String> karakteristike=new HashMap<>();
					karakteristike.put(1, "Plavi");
					Lovac neprijatelj=new Lovac(mapa,"F-16",karakteristike,new Osoba[] {new Pilot()},new Random().nextInt(mapa.getBrojRedovaMape()),0,true);
					mapa.dodajObjekatNaPoziciju(neprijatelj, neprijatelj.getPozicijaX(),neprijatelj.getPozicijaY());
					neprijatelj.start();
					this.ubaciNeprijateljskuLetjelicu=true;
					this.moguceUbacitiNeprijateljskuLetjelicu=false;
					
				}
				break;
			case 10:
				
				if(!this.ubaciNeprijateljskuLetjelicu && this.moguceUbacitiNeprijateljskuLetjelicu) {
					System.out.println("Neprijateljska letjelica ulazi u vazdusni prostor");
					HashMap<Integer,String> karakteristike=new HashMap<>();
					karakteristike.put(1, "Plavi");
					Bombarder neprijatelj=new Bombarder(mapa,"B-2",karakteristike,new Osoba[] {new Pilot()},new Random().nextInt(mapa.getBrojRedovaMape()),0,true);
					mapa.dodajObjekatNaPoziciju(neprijatelj, neprijatelj.getPozicijaX(),neprijatelj.getPozicijaY());
					neprijatelj.start();
					this.ubaciNeprijateljskuLetjelicu=true;
					this.moguceUbacitiNeprijateljskuLetjelicu=false;
				}
				break;
			
			case 11:
				
				if(!this.ubaciNeprijateljskuLetjelicu) 
				{
					System.out.println("Bespilotna letjelica ulazi u vazdusni prostor");
					HashMap<Integer,String>karakteristike=new HashMap<>();
					karakteristike.put(1,"Ljubicasta");
					BespilotnaLetjelica bespilotna=new BespilotnaLetjelica(mapa,"Global HAWK",karakteristike,null,new Random().nextInt(mapa.getBrojRedovaMape()),0);
					mapa.dodajObjekatNaPoziciju(bespilotna,bespilotna.getPozicijaX(),bespilotna.getPozicijaY());
					bespilotna.start();
					
				}
				break;
			
			
			}
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
	

public boolean isUbaciNeprijateljskuLetjelicu() {
	return ubaciNeprijateljskuLetjelicu;
}

public void setUbaciNeprijateljskuLetjelicu(boolean ubaciNeprijateljskuLetjelicu) {
	this.ubaciNeprijateljskuLetjelicu = ubaciNeprijateljskuLetjelicu;
}

public boolean isMoguceUbacitiNeprijateljskuLetjelicu() {
	return moguceUbacitiNeprijateljskuLetjelicu;
}

public void setMoguceUbacitiNeprijateljskuLetjelicu(boolean moguceUbacitiNeprijateljskuLetjelicu) {
	this.moguceUbacitiNeprijateljskuLetjelicu = moguceUbacitiNeprijateljskuLetjelicu;
}

public boolean isMoguceUbacitiVojniAvion() {
	return moguceUbacitiVojniAvion;
}

public void setMoguceUbacitiVojniAvion(boolean moguceUbacitiVojniAvion) {
	this.moguceUbacitiVojniAvion = moguceUbacitiVojniAvion;
}

public boolean isMoguceUbacitiRaketu() {
	return moguceUbacitiRaketu;
}

public void setMoguceUbacitiRaketu(boolean moguceUbacitiRaketu) {
	this.moguceUbacitiRaketu = moguceUbacitiRaketu;
}

public boolean isUbaciLovackiAvion() {
	return ubaciLovackiAvion;
}

public void setUbaciLovackiAvion(boolean ubaciLovackiAvion) {
	this.ubaciLovackiAvion = ubaciLovackiAvion;
}

public Lovac getLovac1() {
	return lovac1;
}

public void setLovac1(Lovac lovac1) {
	this.lovac1 = lovac1;
}

public Lovac getLovac2() {
	return lovac2;
}

public void setLovac2(Lovac lovac2) {
	this.lovac2 = lovac2;
}

public static void log(Exception ex)
{
	Logger logger=Logger.getLogger(Simulacija.class.getName());
	logger.addHandler(filehandler);
	logger.log(Level.WARNING,Simulacija.class.getName(),ex);
}



}

