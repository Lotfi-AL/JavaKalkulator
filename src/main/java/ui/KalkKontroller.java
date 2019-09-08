package ui;


import java.io.IOException;

import core.Utregning;
import io.KalkIO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;




public class KalkKontroller {
    @FXML private TextField txtUt,txtTall3;
    @FXML private Button btnTest;

    private Utregning tall;
    private KalkIO io;

    @FXML
    public void initialize() {
        io = new KalkIO();
    }




    @FXML
    private void test() {

        String string =txtTall3.getText();
        tall = new Utregning(string);
        String rosa  = tall.getText();
        txtUt.setText(rosa);
        //System.out.println("noob");


    }

    public void save() {
        try {
            //System.out.println(txtTall3.getText());
            if(txtTall3.getText().equals("")) {
                txtUt.setText("Du kan ikke lagre til fil uten � ha skrevet inn noe.");
                return;
            }
            Utregning ts = new Utregning(txtTall3.getText());
            io.save("app.txt",ts);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            txtUt.setText("Noe gikk galt under skriving til fil");
        }
    }

    public void load() {
        try {
            Utregning tall = io.load("app.txt");
            txtTall3.setText(tall.getRegneStykket());
            txtUt.setText(tall.getReturTekst());


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            txtUt.setText("Filnavnet finnes ikke, er du sikker på at du har skrevet riktig? Sjekk en gang til");
        }
    }


}
