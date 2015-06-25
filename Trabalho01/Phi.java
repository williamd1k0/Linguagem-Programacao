import javax.swing.*;
/*
*   Trabalho de Linguagem de programação
*   William Claude Tumeo
*   22-06-2015
*/
public class Phi {

    public static void main(String[] args) {

        boolean repetir = false;
        int yesNo;
        int calcType;

        JOptionPane.showMessageDialog(null,"Calculo para identificar proporcao aurea.");
        do{
            do{
                calcType = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo de calculo:\n1 - Verificar proporcao de um retangulo.\n2 - Mostrar possiveis medidas a partir de uma so.","Digite somente 1 ou 2"));
                if (calcType == 1) {
                    calcularPhi();
                }else if (calcType == 2) {
                    medidasPhi();
                }
            }while ((calcType != 1) && (calcType != 2));

            yesNo = JOptionPane.showConfirmDialog(null, "Deseja calcular outro retangulo?","Calcular Phi",JOptionPane.YES_NO_OPTION);
            if(yesNo == JOptionPane.YES_OPTION){
                repetir = true;
            }else{
                repetir = false;
            }
        } while (repetir);
    }

    static boolean proporcao(double a){
        /*
        *   Método que calcula a proporção áurea (divisão da altura pelo comprimento precisa ser aprocimadamente 1,618).
        *   O método retorna true se estiver dentro da margem de erro e false caso não esteja.
        */
        if ((a > 1.5) && (a < 1.7)) {
            return true;
        }else{
            return false;
        }
    }

    static void medidasPhi(){
        /*
        *   Método que mostra possíveis comprimentos de um retângulo a partir de uma única medida.
        */
        double size0 = Double.parseDouble(JOptionPane.showInputDialog("Calculo de possiveis medidas dentro da proporcao aurea.\nDigite o comprimento desejado."));
        double size1 = size0 * 1.618;
        double size2 = size0 / 1.618;
        JOptionPane.showMessageDialog(null, "Medidas que podem ser usadas com " + size0 + ":\n" + size1 + "\n" + size2);
    }

    static void calcularPhi(){

        boolean isSquare = false;  //inicializando a variável "é um quadrado" com false
        double height, width, resultado;
        boolean isPhi;

        do {
            /*
            *   Se for digitado mesmo valor pra 'w' e 'h' (um quadrado), não há necessidade
            *   nem de fazer o cálculo. Nesse caso, fica no loop até inserir um retângulo.
            */
            if (isSquare) { // mensagem a ser exibida caso os valores resultem em um quadrado
                JOptionPane.showMessageDialog(null,"As medidas nao podem ser iguais!");
            }

            height = Double.parseDouble(JOptionPane.showInputDialog("Digite a altura."));
            width = Double.parseDouble(JOptionPane.showInputDialog("Digite a largura."));

            if (height == width) { // se for um quadrado, o isSquare (é um quadrado) recebe true
                isSquare = true;
            }else{ // senão for quadrado, aí recebe false
                isSquare = false;
            }

        } while (isSquare); // repete (loop) ENQUANTO for um quadrado (isSquare == true)

        //checagem pra fazer um único cálculo (medida maior / medida menor)
        if(height > width){
            resultado = height / width;
        }else{
            resultado = width / height;
        }
        isPhi = proporcao(resultado);

        if (isPhi) {
            JOptionPane.showMessageDialog(null, "Resultado:"+ resultado +"\nA medida esta na porporcao.");
        }else{
            JOptionPane.showMessageDialog(null, "Resultado:"+ resultado +"\nA medida nao esta na porporcao.");
        }
    }

}
