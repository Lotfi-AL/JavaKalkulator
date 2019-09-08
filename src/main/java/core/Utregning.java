package core;

import java.util.ArrayList;
import java.util.List;

public class Utregning {
    private String regneStykket ="";
    private String returTekst = "Regnestykket var ikke gyldig, prov paa nytt.";
    private List<String> originalParts = new ArrayList<>();
    private List<String> parts3 = new ArrayList<>();
    private String tempTall="";
    private DelOperasjon firstNumb;

    //String p1[] = regneStykket.split("[+-/*]");
    public Utregning(String regneStykket) {

        //Vi gj�r om regnestykk streng til en char Array. s� vi kan iterere gjennom lett
        this.regneStykket = regneStykket;
        char[] chars = regneStykket.toCharArray();

        //Vi sjekker at regnestykket er gyldig i sjekk1()
        if(!sjekk1())
            return;


        //sjekk1 er gyldig. vi itererer gjennom char array og sjekker
        for (char tall: chars) {
            if(Character.isDigit(tall)) {
                tempTall+=tall;
            }
            else {
                originalParts.add(tempTall);
                tempTall ="";
                originalParts.add(Character.toString(tall));
            }

        }

        float finalResult = 0;
        originalParts.add(tempTall);
        parts3 = new ArrayList<>(originalParts);
        float tallet = 0;

        int originalLengde =originalParts.size();
        int ol2 = 0;


        for(int i =0;i<originalLengde;i++) {
            //gj�r f�rst gange og dele operasjoner alts� * og /
            //ingenting vil skje i denne l�kken, dersom det ikke er noen gange eller dele operasjoner i stykket.
            if(originalParts.get(i).equals("*")||originalParts.get(i).equals("/")) {
                DelOperasjon gangeEllerDele = new DelOperasjon(parts3.get(i-1-ol2),parts3.get(i-ol2),parts3.get(i+1-ol2));
                parts3.remove(i-ol2);
                parts3.remove(i-ol2);

                tallet = gangeEllerDele.getTall();

                parts3.set(i-1-ol2, Float.toString(tallet));

                ol2++;
                ol2++;
            }
        }
        //hvis det kun var �n operasjon og denne var gange eller dele vil det returners resultatet.

        if(parts3.size()==1) {
            returTekst = parts3.get(0);
            return;
        }


        firstNumb = new DelOperasjon(parts3.get(0),parts3.get(1),parts3.get(2));
        finalResult = finalResult+firstNumb.getTall();
        //firstNumb er det f�rste tallet vi skal minus eller plusse med alts� firstNumb+x
        //vi har lagret det f�rste tallet s� vi kan slette de tre delene, f�rste tall, opersasjon og tall nummer to.
        parts3.remove(0);
        parts3.remove(0);
        parts3.remove(0);

        // hvis det kun var �n operasjon og denne var plusse eller minusvil man hoppe over l�kken og returtekst
        //blir satt til dette tallet. som er lagret i firstNumb

        while(!(parts3.isEmpty())) {
            DelOperasjon utregning = new DelOperasjon(finalResult,parts3.get(0),parts3.get(1));
            parts3.remove(0);
            parts3.remove(0);
            finalResult = utregning.getTall();
            // for hvert steg s� setter vi finalResult til � v�re lik det nyeste resultatet
            // alts� f.eks 2+2+2  /finalResult =2
            //4+2 / finalResult = 4
            //6 /finalResult = 6

        }

        returTekst = Float.toString(finalResult);

    }


    private boolean sjekk1() {

        //sjeker at strenger bare inneholder tall og +-*/ og har to tall et foran og et bak rengeoperasjonen.
        if(!(regneStykket.matches("^[0-9+-/*]*$") && regneStykket.length() > 2)){
            return false;
        }
        //sjekker at f�rste char er et tall i regnestykket s� man ikke f�r _*2
        if(!Character.isDigit(regneStykket.charAt(0))) {
            return false;
        }
        char[] chars = regneStykket.toCharArray();

        //tester om det er 2 regneoperasjoner etter hverandre i regnestykket.
        for(int i = 0; i<chars.length-1;i++) {
            if(chars[i]=='*'||chars[i]=='/'||chars[i]=='+'||chars[i]=='-') {
                if(chars[i+1]=='*'||chars[i+1]=='/'||chars[i+1]=='+'||chars[i+1]=='-') {
                    return false;
                }
            }
        }

        return true;
    }


    public String getText() {
        if(!(sjekk1())) {
            return returTekst;
        }
        return returTekst;
    }
    public String getRegneStykket() {
        return regneStykket;
    }
    public String getReturTekst() {
        return returTekst;
    }
}