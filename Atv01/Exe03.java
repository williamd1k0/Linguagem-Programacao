//package Atv01;
import java.util.Scanner;

public class Exe03 {

    public static void main (String []args){

        float result;
        Scanner prompt = new Scanner(System.in);

        System.out.print("Digite um numero: ");
        float num1 = prompt.nextFloat();

        System.out.print("Digite outro numero: ");
        float num2 = prompt.nextFloat();

        System.out.print("Escolha um operador matematico [+][-][*][/]: ");
        String operador = prompt.next();

        switch (operador) {

            case "+":
                result = num1 + num2;
                System.out.println("\nO resultado e " + result + ".");
                break;
            case "-":
                result = num1 - num2;
                System.out.println("\nO resultado e " + result + ".");
                break;
            case "*":
                result = num1 * num2;
                System.out.println("\nO resultado e " + result + ".");
                break;
            case "/":
                result = num1 / num2;
                System.out.println("\nO resultado e " + result + ".");
                break;
            default:
            System.out.println("\nOperador invalido!");
        }

    }

}
