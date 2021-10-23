package avioni;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import interfejsi.*;
import vazdusni_prostor.MapaVazdusnogProstora;
import osobe.*;

public class PutnickiAvion extends Avion implements Putnicki,CivilniObjekat
{
private int brojSjedista=150;
private double maksimalnaTezinaPrtljaga=5000;


public PutnickiAvion()
{
}
public PutnickiAvion(int brojSjedista,double maksimalnaTezinaPrtljaga)
{
	this.brojSjedista=brojSjedista;
	this.maksimalnaTezinaPrtljaga=maksimalnaTezinaPrtljaga;
}
public PutnickiAvion(String model,Integer oznaka,Integer visina,Integer brzina,int pozicijaX,int pozicijaY) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
}

public PutnickiAvion(MapaVazdusnogProstora mapa,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY) {
	
	super(mapa,"Boing-747",karakteristike,osobe,pozicijaX,pozicijaY);
}

public PutnickiAvion(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY);
	
}

public int getBrojSjedista() {
	return brojSjedista;
}
public void setBrojSjedista(int brojSjedista) {
	this.brojSjedista = brojSjedista;
}
public double getMaksimalnaTezinaPrtljaga() {
	return maksimalnaTezinaPrtljaga;
}
public void setMaksimalnaTezinaPrtljaga(double maksimalnaTezinaPrtljaga) {
	this.maksimalnaTezinaPrtljaga = maksimalnaTezinaPrtljaga;
}
@Override
public String toString() 
{
	return new StringBuilder().append("Putnicki-Avion").append("#").append(super.toString()).toString();
}
@Override
public void iscrtaj(Graphics graphic) {
	graphic.setColor(new Color(102,250,97));
	graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
	graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
			
}
}
