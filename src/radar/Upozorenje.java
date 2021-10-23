package radar;

import java.io.Serializable;
import java.util.Date;

public class Upozorenje implements Serializable
{
private String opis;
private Date datum;
private int pozicijaX;
private int pozicijaY;

public Upozorenje()
{
}

public Upozorenje(int pozicijaX,int pozicijaY)
{
	this.pozicijaX=pozicijaX;
	this.pozicijaY=pozicijaY;
	datum=new Date();
	this.opis="Desio se sudar u vazdusnom prostoru";
	
}

public String getOpis() {
	return opis;
}

public void setOpis(String opis) {
	this.opis = opis;
}

public Date getDatum() {
	return datum;
}

public void setDatum(Date datum) {
	this.datum = datum;
}

public int getPozicijaX() {
	return pozicijaX;
}

public void setPozicijaX(int pozicijaX) {
	this.pozicijaX = pozicijaX;
}

public int getPozicijaY() {
	return pozicijaY;
}

public void setPozicijaY(int pozicijaY) {
	this.pozicijaY = pozicijaY;
}
public String toString()
{
	return this.opis+"na koordinatama "+this.pozicijaX+" : "+this.pozicijaY+" u "+this.datum;
}
}
