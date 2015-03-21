//package Atv01;
import javax.swing.JOptionPane;

public class Exe04 {

    public static void main (String []args){

        int valor;

        valor = Integer.parseInt(JOptionPane.showInputDialog("Digite um numero inteiro."));

        for (int i = 0; i < 11; i++){
            int produto = valor * i;
            System.out.println("\n" + valor + " x " + i + " = " + produto);
        }
        
    }

}
