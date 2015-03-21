//package Atv01;
import javax.swing.JOptionPane;

public class Exe05 {

    public static void main (String []args){

        String defaultPass, pass;
        defaultPass = "elpsycongroo";

        pass = JOptionPane.showInputDialog("Digite a senha!");

// Usando Switch
        switch (pass){

            case "elpsycongroo":
                JOptionPane.showMessageDialog(null, "Access Granted!");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Access Denied!");
        }

// Usando If-Else
        if (pass.equals(defaultPass)){

            JOptionPane.showMessageDialog(null, "Access Granted!");
        }else{

            JOptionPane.showMessageDialog(null, "Access Denied!");
        }

    }

}
