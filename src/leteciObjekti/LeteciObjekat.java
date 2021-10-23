package leteciObjekti;
import vazdusni_prostor.*;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public abstract class LeteciObjekat extends Thread
{
	public static final int VISINA=150; // za simulaciju sudara
	public static final int MANEVAR1=2;
	public static final int MANEVAR2=2;
	 public MapaVazdusnogProstora mapa;
	 protected int pozicijaX;
	 protected int pozicijaY;
	 protected int brzina;
	 protected Integer visina;
	 private int posljedniRed;
	 private int poslednjaKolona;
	 private int stranaUlaska; //1->letjelica ulazi u vazdusni prostor sa lijeve strane; 2->sa gornje;3->sa desne;4->sa donje
	 private boolean krajObjekta=false;
	 private static boolean naIstojVisini;
	 private boolean promjenaPravca=false;
	 static Properties prop=new Properties();
	 static FileInputStream fs;
	 private static FileHandler filehandler;
	 
static
	 {
		 try
		 	{
			 	fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\properties_fajlovi\\config.properties");
			 	prop.load(fs);
			 	naIstojVisini=Boolean.parseBoolean(prop.getProperty("naIstojVisini"));
			 	fs.close();
		 	}
		 catch(Exception e)
		 	{
			 e.printStackTrace();
		 	}
	 }
static
{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"LeteciObjekat.log");
		filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}
	 
public LeteciObjekat()
{	
}
	 
public LeteciObjekat(int visina,int brzina,int pozicijaX,int pozicijaY)
{
this.visina=visina;
this.brzina=brzina;
this.pozicijaX=pozicijaX;
this.pozicijaY=pozicijaY;
}

public LeteciObjekat(MapaVazdusnogProstora mapa,int brzina,int pozicijaX,int pozicijaY)
{
	this.mapa=mapa;
	this.brzina=brzina;
	this.pozicijaX=pozicijaX;
	this.pozicijaY=pozicijaY;
	
	if(!this.naIstojVisini)
	{
		this.visina=new Random().nextInt(10)+150;
	}
	else
	{
		this.visina=LeteciObjekat.VISINA;
	}
	this.posljedniRed=mapa.getBrojRedovaMape()-1;
	this.poslednjaKolona=mapa.getBrojKolonaMape()-1;
	
	if(this.pozicijaX==0)
	{
		this.stranaUlaska=2;
	}
	else if(this.pozicijaX==this.posljedniRed)
	{
		this.stranaUlaska=4;
	}
	else if(this.pozicijaY==0)
	{
		this.stranaUlaska=1;
	}
	else if(this.pozicijaY==this.poslednjaKolona)
	{
		this.stranaUlaska=3;
	}
	
}
public LeteciObjekat(MapaVazdusnogProstora mapa,int pozicijaX,int pozicijaY)
{
	this.mapa=mapa;
	this.pozicijaX=pozicijaX;
	this.pozicijaY=pozicijaY;
	this.brzina=new Random().nextInt(3)+1;
	
	this.posljedniRed=mapa.getBrojRedovaMape()-1;
	this.poslednjaKolona=mapa.getBrojKolonaMape()-1;
	
	if(!this.naIstojVisini)
	{
		this.visina=new Random().nextInt(5)+150;
	}
	
	
	else
	{
		this.visina=LeteciObjekat.VISINA; //kada je postavljeno na istoj visini objekti imaju istu visinu da bi se simulirao sudar
	}
	if(this.pozicijaX==0)
	{
		this.pozicijaY=(new Random()).nextInt(mapa.getBrojKolonaMape());
		this.stranaUlaska=2;
	}
	
	
	else if(this.pozicijaX==this.posljedniRed)
	{
		this.pozicijaY=(new Random()).nextInt(mapa.getBrojKolonaMape());
		this.stranaUlaska=4;
	}
	
	
	else if(this.pozicijaX>0 && this.pozicijaX<this.posljedniRed)
	{
	 int broj[]= {0,this.poslednjaKolona};
	 this.pozicijaY=broj[(new Random().nextInt(2))];
	 
	 if(this.pozicijaY==0)
	 {
		 this.stranaUlaska=1;
	 }
	 else if(this.pozicijaY==this.poslednjaKolona)
	 {
		 this.stranaUlaska=3;
	 }
	}
}
public void polukruznoVracanje() throws Exception
{
	int svePutanje[]= {this.poslednjaKolona-pozicijaY,this.posljedniRed-pozicijaX,pozicijaY,pozicijaX}; //sve putanje od objekta do kraja vazdusnog prostora
	int minimalanPutZaBjezanje=svePutanje[0];
	int redniBrojPutanje=0;
	
	for(int i=0;i<svePutanje.length;i++)  //pronalazenje minimalnog puta za bjezanje iz vazzdusnog rpostora
	{
		if(minimalanPutZaBjezanje>svePutanje[i])
		{
			minimalanPutZaBjezanje=svePutanje[i];
			redniBrojPutanje=i;
		}
	}
	
	switch(redniBrojPutanje)
	{
	case 0:
		if(this.stranaUlaska==3) //ukoliko leti sa desna u lijevo polukruzno vracanje nazad
		{
			for(int i=0;i<LeteciObjekat.MANEVAR1;i++)
			{
				Thread.sleep(brzina*1000);
				pozicijaY--;
				mapa.dodajObjekatNaPoziciju(this,pozicijaX,pozicijaY);
				mapa.obrisiObjekatSaKoordinata(this,pozicijaX,pozicijaY+1);
			}
			
			for(int i=0;i<LeteciObjekat.MANEVAR2;i++)
			{
				Thread.sleep(brzina*1000);
				pozicijaX++;
				mapa.dodajObjekatNaPoziciju(this, pozicijaX, pozicijaY);
				mapa.obrisiObjekatSaKoordinata(this,this.pozicijaX-1,this.pozicijaY);
			}
			
			
		}
		this.stranaUlaska=1;
		break;
	case 1:
		if(this.stranaUlaska==4) //ukoliko leti  odozdo prema gore polukruzno vracanje nazad
		{
			for(int i=0;i<LeteciObjekat.MANEVAR1;i++)
			{
				Thread.sleep(brzina*1000);
				this.pozicijaX--;
				mapa.dodajObjekatNaPoziciju(this,this.pozicijaX,pozicijaY);
				mapa.obrisiObjekatSaKoordinata(this,this.pozicijaX+1,pozicijaY);
			}
			for(int i=0;i<LeteciObjekat.MANEVAR2;i++)
			{
				Thread.sleep(brzina*1000);
				this.pozicijaY++;
				mapa.dodajObjekatNaPoziciju(this,this.pozicijaX,this.pozicijaY);
				mapa.obrisiObjekatSaKoordinata(this, this.pozicijaX,this.pozicijaY-1);
			}
			
		}
		this.stranaUlaska=2;
		break;
	case 2:
		if(this.stranaUlaska==1)//ukoliko leti s lijeva u desno vracanje nazad
		{
			for(int i=0;i<LeteciObjekat.MANEVAR1;i++)
			{
				Thread.sleep(brzina*1000);
				this.pozicijaY++;
				mapa.dodajObjekatNaPoziciju(this,this.pozicijaX,this.pozicijaY);
				mapa.obrisiObjekatSaKoordinata(this, this.pozicijaX,this.pozicijaY-1);
			}
			for(int i=0;i<LeteciObjekat.MANEVAR2;i++)
			{
				Thread.sleep(brzina*1000);
				this.pozicijaX++;
				mapa.dodajObjekatNaPoziciju(this,this.pozicijaX,this.pozicijaY);
				mapa.obrisiObjekatSaKoordinata(this,this.pozicijaX-1,this.pozicijaY);
			}
			
		}
		this.stranaUlaska=3;
		break;
	case 3:
		if(this.stranaUlaska==2) //ukoliko leti odozgo prema dole vracanje nazad
		{
			for(int i=0;i<LeteciObjekat.MANEVAR1;i++)
			{
				Thread.sleep(brzina*1000);
				this.pozicijaX++;
				mapa.dodajObjekatNaPoziciju(this, this.pozicijaX,this.pozicijaY);
				mapa.obrisiObjekatSaKoordinata(this, this.pozicijaX-1,this.pozicijaY);
			}
			for(int i=0;i<LeteciObjekat.MANEVAR2;i++)
			{
				Thread.sleep(brzina*1000);
				this.pozicijaY++;
				mapa.dodajObjekatNaPoziciju(this,this.pozicijaX,this.pozicijaY);
				mapa.obrisiObjekatSaKoordinata(this, this.pozicijaX,this.pozicijaY-1);
			}
			
		}
		this.stranaUlaska=4;
		break;
		
	}
	this.promjenaPravca=true;
}
@Override
public void run()
{
	while(!this.krajObjekta) //dok objekat ne dodje do kraja table
	{
		try
		{
			if(mapa.getZabranaLetenja() && !promjenaPravca)  //u koliko je na snazi zabrana letenja provjera
			{
				polukruznoVracanje();
			}
			
			Thread.sleep(brzina*1000);
			
			if(this.stranaUlaska==1) //ako je objekat usao sa lijeve strane i leti prema desno
			{
				if(this.pozicijaY<this.poslednjaKolona && !this.krajObjekta) //leti u desno sve dok ne dodjes do kraja vazdusnog prostora
				{
					this.pozicijaY++;	
					mapa.dodajObjekatNaPoziciju(this,this.pozicijaX,this.pozicijaY);
				}
				
				if(this.pozicijaY>0) //obrisi objekat sa prethodne pozicije
				{
					mapa.obrisiObjekatSaKoordinata(this, this.pozicijaX, this.pozicijaY-1);
				}
				
				if(this.pozicijaY==this.poslednjaKolona) //ako je objekat dosao do poslednje kolone vazdusnog prostora obrisi ga i postavi flag da je zavrsio
				{
					mapa.obrisiObjekatSaKoordinata(this,this.pozicijaX,this.pozicijaY);
					this.krajObjekta=true;
				}
				
				
			}
			
			else if(this.stranaUlaska==3) //ako je objekat usao sa desne strane i leti prema lijevo
			{
				if(this.pozicijaY>0 && !this.krajObjekta)
				{
					this.pozicijaY--;
					mapa.dodajObjekatNaPoziciju(this, this.pozicijaX, this.pozicijaY);
				}
				
				if(this.pozicijaY<this.poslednjaKolona)
				{
					mapa.obrisiObjekatSaKoordinata(this,pozicijaX,pozicijaY+1);
				}
				
				if(this.pozicijaY==0)
				{
					mapa.obrisiObjekatSaKoordinata(this,this.pozicijaX,this.pozicijaY);
					this.krajObjekta=true;
				}
				
			}
			
			else if(this.stranaUlaska==2) //ako je objekat usao sa gornej strane u vazdusni prostor i leti prema dole
			{
				if(this.pozicijaX<this.posljedniRed && !this.krajObjekta)
				{
					this.pozicijaX++;
					mapa.dodajObjekatNaPoziciju(this,this.pozicijaX,this.pozicijaY);
				}
				
				if(this.pozicijaX>0)
				{
					mapa.obrisiObjekatSaKoordinata(this,this.pozicijaX-1,this.pozicijaY);
				}
				
				if(this.pozicijaX==this.posljedniRed)
				{
					mapa.obrisiObjekatSaKoordinata(this,this.pozicijaX,this.pozicijaY);
					this.krajObjekta=true;
				}
			}
			else if(this.stranaUlaska==4)
			{
				if(this.pozicijaX>0 && !this.krajObjekta)
				{
				this.pozicijaX--;
				mapa.dodajObjekatNaPoziciju(this,this.pozicijaX,this.pozicijaY);	
				}
				if(this.pozicijaX>this.poslednjaKolona)
				{
					mapa.obrisiObjekatSaKoordinata(this, this.pozicijaX+1,this.pozicijaY);
				}
				if(this.pozicijaX==0)
				{
					mapa.obrisiObjekatSaKoordinata(this, this.pozicijaX,this.pozicijaY);
					this.krajObjekta=true;
				}
				
			}
		
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}



public boolean isPromjenaPravca() {
	return promjenaPravca;
}

public void setPromjenaPravca(boolean promjenaPravca) {
	this.promjenaPravca = promjenaPravca;
}


public boolean getKrajObjekta()
{
	return this.krajObjekta;
}

public void setKrajObjekta(boolean krajObjekta)
{
	this.krajObjekta=krajObjekta;
}


public int getStranaUlaska() 
	 {
			return stranaUlaska;
	 	}

public void setStranaUlaska(int stranaUlaska) 
	{
			this.stranaUlaska = stranaUlaska;
		}

public int getPozicijaX()
	{
		return pozicijaX;
	}

public void setPozicijaX(int pozicijaX)
	{
		this.pozicijaX = pozicijaX;
	}

public int getPozicijaY() 
	{
		return pozicijaY;
	}

public void setPozicijaY(int pozicijaY) 
	{
		this.pozicijaY = pozicijaY;
	}
public void setVisina(int visina)
{
	this.visina=visina;
}
public int getVisina()
{
	return this.visina;
}
public abstract void iscrtaj(Graphics graphic);

@Override
public String toString()
{
	return new StringBuilder().append(visina).append("#").append(brzina).append("#").append(pozicijaX).append("#").append(pozicijaY).toString().trim();
}
}
