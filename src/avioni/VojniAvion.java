package avioni;
import interfejsi.*;
import vazdusni_prostor.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import osobe.*;

public class VojniAvion extends Avion implements VojniObjekat
{
	protected boolean neprijateljski;
	
public VojniAvion()
{
}

public VojniAvion(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY,boolean neprijateljski) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
	this.neprijateljski=neprijateljski;
}

public VojniAvion(MapaVazdusnogProstora mapa ,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,boolean neprijateljski) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	this.neprijateljski=neprijateljski;
}
public VojniAvion(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina,boolean neprijateljski) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	this.neprijateljski=neprijateljski;
	this.brzina=brzina;
}
public boolean getNeprijateljski()
{
	return this.neprijateljski;
}
public void setNeprijateljski(boolean neprijateljski)
{
	this.neprijateljski=neprijateljski;
}
@Override
public String toString() {
	return new StringBuilder().append("Vojni-Avion").append("#").append(getNeprijateljski()).append("#").append(super.toString()).toString().trim();
}
@Override
public void iscrtaj(Graphics graphic) {
	
		graphic.setColor(Color.BLUE);
		graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
		graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
			
}
}
