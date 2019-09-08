package io;

import core.Utregning;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class KalkIO implements KalkIOInterface{

    @Override
    public void save(String filename, Utregning tall) throws IOException{


        PrintWriter writer = new PrintWriter(filename);
        Utregning t1 = new Utregning(tall.getRegneStykket());
        String s = t1.getRegneStykket()+"="+t1.getReturTekst();
        writer.print(s);
        writer.flush();
        writer.close();

    }





    @Override
    public Utregning load(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        String[] calc = scanner.nextLine().split("=");
        scanner.close();
        Utregning tall = new Utregning(calc[0]);
        return tall;

    }
}
