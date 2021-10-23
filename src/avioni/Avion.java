package avioni;
import osobe.*;
import leteciObjekti.Letjelica;
import vazdusni_prostor.MapaVazdusnogProstora;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class Avion extends Letjelica
{
public Avion()
{
	super();
}


public Avion(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
}

public Avion(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	
}
public Avion(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	this.brzina=brzina;
	
}
@Override
public void iscrtaj(Graphics graphic)
{
	graphic.setColor(Color.GREEN);
	graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10,10,10);
	graphic.drawString(this.visina.toString(),5+pozicijaX*10-4,5+pozicijaY*10-1);
}
@Override
public String toString()
{
	return new StringBuilder().append("Avion").append("#").append(super.toString()).toString().trim();
}
/*public Avion(MapaVazdusnogProstora mapa,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY)
{
	super(mapa,,karakteristike,osobe,pozicijaX,pozicijaY);
}*/
}
