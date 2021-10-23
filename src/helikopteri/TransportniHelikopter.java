package helikopteri;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import interfejsi.*;
import osobe.Osoba;
import tereti.*;
import vazdusni_prostor.MapaVazdusnogProstora;

public class TransportniHelikopter extends Helikopter implements Transporteri,CivilniObjekat
{
private double maksimalnaTezina;
private Teret tereti[]=new Teret[0];

public TransportniHelikopter()
{
}
public TransportniHelikopter(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
}

public TransportniHelikopter(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	
}
public TransportniHelikopter(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY,brzina);
	
}
public TransportniHelikopter(double maksimalnaTezina,Teret tereti[])
{
	this.maksimalnaTezina=maksimalnaTezina;
	this.tereti=tereti;
}
public double getMaksimalnaTezina() {
	return maksimalnaTezina;
}
public void setMaksimalnaTezina(double maksimalnaTezina) {
	this.maksimalnaTezina = maksimalnaTezina;
}
public Teret[] getTereti() {
	return tereti;
}
public void setTereti(Teret[] tereti) {
	this.tereti = tereti;
}
@Override
public String toString() {
	return new StringBuilder().append("Transportni-Helikopter").append("#").append(super.toString()).toString();
}
@Override
public void iscrtaj(Graphics graphic) {
	graphic.setColor(new Color(150,204,90));
	graphic.fillRect(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
	graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
			
}
}
