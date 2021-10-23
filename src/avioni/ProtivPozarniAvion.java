package avioni;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import interfejsi.*;
import osobe.Osoba;
import vazdusni_prostor.MapaVazdusnogProstora;

public class ProtivPozarniAvion extends Avion implements GasenjePozara,CivilniObjekat
{
	
private double kolicinaVode;

public ProtivPozarniAvion()
{
}
public ProtivPozarniAvion(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
}

public ProtivPozarniAvion(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	
}
public ProtivPozarniAvion(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	this.brzina=brzina;
	
}
public ProtivPozarniAvion(double kolicinaVode)
{
	this.kolicinaVode=kolicinaVode;
}

public double getKolicinaVode() {
	return kolicinaVode;
}

public void setKolicinaVode(double kolicinaVode) {
	this.kolicinaVode = kolicinaVode;
}

@Override
public void ugasiPozar()
{
	System.out.println("Pozar ugasen!!!");
}
@Override
public String toString() 
{
	return new StringBuilder().append("Protiv-Pozarni-Avion").append("#").append(super.toString()).toString();

}
@Override
public void iscrtaj(Graphics graphic) {
	graphic.setColor(new Color(50,200,250));
	graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
	graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
			
}
}