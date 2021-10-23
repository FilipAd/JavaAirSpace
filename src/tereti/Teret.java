package tereti;

public class Teret 
{
private String vrsta;
private double tezina;
public Teret()
{
}
public Teret(String vrsta,double tezina)
{
	this.vrsta=vrsta;
	this.tezina=tezina;
}
public String getVrsta() {
	return vrsta;
}
public void setVrsta(String vrsta) {
	this.vrsta = vrsta;
}
public double getTezina() {
	return tezina;
}
public void setTezina(double tezina) {
	this.tezina = tezina;
}


}
