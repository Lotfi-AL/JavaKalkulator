package core;


//denne klassen utf�rer en operasjon p� to tall og returenrer resultatet.
//det er to tilfeller, det ene tilfellet hvor man f�r inn to strenger. dette skjer n�r vi utf�rer den f�rste operasjonen
// p� ett lengre regnestykke
// andre tilfellet er en float og en streng.

public class DelOperasjon {
    private float first;
    private float second;
    private String tegn;
    private float tall;

    public DelOperasjon(String first,String tegn, String second) {
        this.first = Float.parseFloat(first);
        this.second = Float.parseFloat(second);
        this.tegn = tegn;
    }
    public DelOperasjon(Float first,String tegn, String second) {
        this.first = first;
        this.second = Float.parseFloat(second);
        this.tegn = tegn;
    }

    //sjekker hvilken operasjon som skal utf�res.
    //er mulig � legge til andre operasjoner. bare � ha en ny test og contains("") og en ny fnc""
    private void regnUt() {
        if(tegn.contains("+")){
            fncPluss();
        }
        if(tegn.contains("-")) {
            fncMinus();
        }
        if(tegn.contains("*")) {
            fncGange();
        }
        if(tegn.contains("/")) {
            fncDele();
        }
    }
    // forskjellige operasjoner
    //er mulig � legge til andre operasjoner.
    private void fncPluss() {
        tall = first+second;
    }
    private void fncMinus() {
        tall = first-second;
    }
    private void fncGange() {
        tall = first*second;
    }
    private void fncDele() {
        tall = first/second;
    }
    public Float getTall() {
        regnUt();
        return tall;
    }
}
