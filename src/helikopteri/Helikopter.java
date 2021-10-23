package helikopteri;
import osobe.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import leteciObjekti.Letjelica;
import vazdusni_prostor.MapaVazdusnogProstora;

public class Helikopter extends Letjelica 
{
public Helikopter()
{	
}
public Helikopter(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
}

public Helikopter(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	
}
public Helikopter(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY,brzina);
	
}
@Override
public String toString() 
{
	return new StringBuilder().append("Helikopter").append("#").append(super.toString()).toString().trim();
}
@Override
public void iscrtaj(Graphics graphic) {
	graphic.setColor(Color.ORANGE);
	graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
	graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
			
}
}
