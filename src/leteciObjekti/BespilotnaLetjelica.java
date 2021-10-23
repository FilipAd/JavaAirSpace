package leteciObjekti;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import interfejsi.*;
import osobe.Osoba;
import vazdusni_prostor.MapaVazdusnogProstora;

public class BespilotnaLetjelica extends Letjelica implements CivilniObjekat
{
public BespilotnaLetjelica()
{
	
}
public void snimanjeTerena()
{
	System.out.println("Teren snimljen!!!");
}
public BespilotnaLetjelica(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
}

public BespilotnaLetjelica(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	
}
public BespilotnaLetjelica(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	this.brzina=brzina;
	
}
@Override
public String toString() {
	return new StringBuilder().append("Bespilotna-Letjelica").append("#").append(super.toString()).toString().trim();
}
@Override
public void iscrtaj(Graphics graphic) {
	graphic.setColor(new Color(50,50,50));
	graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
	graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
			
}
}
