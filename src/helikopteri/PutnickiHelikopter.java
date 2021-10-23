package helikopteri;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import interfejsi.*;
import osobe.Osoba;
import vazdusni_prostor.MapaVazdusnogProstora;

public class PutnickiHelikopter extends Helikopter implements Putnicki,CivilniObjekat
{
private int brojSjedista;

public PutnickiHelikopter()
{

}
public PutnickiHelikopter(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
}

public PutnickiHelikopter(MapaVazdusnogProstora mapa,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY) {
	
	super(mapa,"MI-8",karakteristike,osobe,pozicijaX,pozicijaY);
}

public PutnickiHelikopter(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	
}

public PutnickiHelikopter(int brojSjedista)
{
	this.brojSjedista=brojSjedista;
}
public int getBrojSjedista() {
	return brojSjedista;
}
public void setBrojSjedista(int brojSjedista) {
	this.brojSjedista = brojSjedista;
}
@Override
public String toString() {
	return new StringBuilder().append("Putnicki-Helikopter").append("#").append(super.toString()).toString();
}
@Override
public void iscrtaj(Graphics graphic) {
	graphic.setColor(Color.MAGENTA);
	graphic.fillRect(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
	graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
			
}

}
