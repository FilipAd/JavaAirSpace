package rakete;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import leteciObjekti.*;
import vazdusni_prostor.MapaVazdusnogProstora;

public class Raketa extends LeteciObjekat
{
private int domet;
private int visina;
private int brzina;

public Raketa()
{

}
public Raketa(int domet,int visina,int brzina)
{
	this.domet=domet;
	this.visina=visina;
	this.brzina=brzina;
}
public Raketa(int domet,int visina,int brzina,int pozicijaX,int pozicijaY) {
	super(visina,brzina,pozicijaX,pozicijaY);
	this.domet=domet;
	this.visina=visina;
}

public Raketa(MapaVazdusnogProstora mapa,int pozicijaX,int pozicijaY) {
	super(mapa,pozicijaX,pozicijaY);
	this.domet=new Random().nextInt(100)+10;
	this.visina=new Random().nextInt(5)+200;
}
public void iscrtaj(Graphics graphic) {
	graphic.setColor(Color.CYAN);
	graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
	
}
@Override
public String toString() {
	return new StringBuilder().append("Raketa").append("#").append(domet).append("#").append(visina).append("#").append(super.toString()).toString();
}
}