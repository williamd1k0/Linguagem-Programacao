//package Atv01;
import java.util.Scanner;

public class Exe02 {

    public static void main (String []args){

        Scanner prompt = new Scanner(System.in);

        System.out.print("Digite um numero: ");
        float num = prompt.nextFloat();

        if (num > 0){

            System.out.println("O numero " + num + " e positivo.");

        }else if (num < 0){

            System.out.println("O numero " + num + " e negativo.");

        }else{

            System.out.println("O numero " + num + " e neutro.");

        }

    }

}
