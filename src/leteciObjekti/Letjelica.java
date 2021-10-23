package leteciObjekti;
import osobe.*;
import vazdusni_prostor.*;
import simulator.Simulacija;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class Letjelica extends LeteciObjekat
{
 
 private String model;
 private Integer oznaka;
 private static int id=0;
 private Osoba osobe[];
 private HashMap<String,String> karakterstike=new HashMap<>();

 
 public Letjelica()
 {
	 super();
 }
 public Letjelica(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina)
 {
	 super(mapa,brzina,pozicijaX,pozicijaY);
	 this.model=model;
	 this.karakterstike=karakterstike;
	 this.osobe=osobe; 
 }
 
 public Letjelica(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY)
 {
	 super(visina,brzina,pozicijaX,pozicijaY);
	 this.model=model;
	 this.oznaka=oznaka;
 }
 
 public Letjelica(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY)
 {
	 super(mapa,pozicijaX,pozicijaY);
	 this.model=model;
	 this.karakterstike=karakterstike;
	 this.osobe=osobe;
	 this.oznaka=Letjelica.id+1;
	 
 }
 
 public Letjelica(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,int pozicijaX,int pozicijaY,int brzina,Osoba osobe[])
 {
	 super(mapa,brzina,pozicijaX,pozicijaY);
	 this.oznaka=Letjelica.id+1;
	 this.model=model;
	 this.karakterstike=karakterstike;
	 this.osobe=osobe;
 }

 
 public Letjelica(String model,Integer oznaka,boolean neprijateljski)
 {
	 this.model=model;
	 this.oznaka=oznaka;
 }
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public Integer getOznaka() {
	return oznaka;
}
public void setOznaka(Integer oznaka) {
	this.oznaka = oznaka;
}
public int getVisina() {
	return visina;
}
public void setVisina(int visina) {
	this.visina = visina;
}
public int getBrzina() {
	return brzina;
}
public void setBrzina(int brzina) {
	this.brzina = brzina;
}
public Osoba[] getOsobe() {
	return osobe;
}
public void setOsobe(Osoba[] osobe) {
	this.osobe = osobe;
}
public HashMap<String, String> getKarakterstike() {
	return karakterstike;
}
public void setKarakterstike(HashMap<String, String> karakterstike) {
	this.karakterstike = karakterstike;
}
public void iscrtaj(Graphics graphic)
{
	graphic.setColor(Color.GREEN);
	graphic.fillOval(5+pozicijaX*10,5+pozicijaY*10,10,10);
}
@Override
public String toString()
{
	return new StringBuilder().append(model).append("#").append(this.oznaka).append("#").append(super.toString()).toString().trim();	
}



 
}
