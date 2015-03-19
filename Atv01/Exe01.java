//package Atv01;
import java.util.Scanner;

public class Exe01 {

    public static void main (String []args){

        Scanner prompt = new Scanner(System.in);
        
        System.out.print("Digite um numero: ");
        float num = prompt.nextFloat();

        if ((num % 2) == 0){

            System.out.println("O numero " + num + " e par.");

        }else{

            System.out.println("O numero " + num + " e impar.");

        }

    }

}
