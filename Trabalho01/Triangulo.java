import java.util.*; 

public class Triangulo { 
//dados que representam os lados do triangulo 
    int ladoA; 
    int ladoB; 
    int ladoC; 

//classe que recebe tres variaveis de tipo inteiro 
//e os toma como lado de um triangulo 
Triangulo( int a, int b, int c ) { 
//validando os valores corretos de um triangulo 
//sabendo que para a existencia de um triangulo, um lado tem que ser maior que
//a diferenca entre os dois e menor que a soma dos dois
   
    if ( a < (b + c) && b < (a + c) && c < (a + b) ) { 
   
    ladoA = a; 
    ladoB = b; 
    ladoC = c;	
    
    //Se os dados nao forem corretos, envia uma mensagem de erro 
    } else { 
    System.out.println( "ERRO: Dados inseridos" 
    + " nao sao proprios de um triangulo" ); 
    System.exit( 0 ); 
    } 
} 

//Esta funcao retorna 'true' se o triangulo eh equilatero 
//caso contrario retorna false 
public boolean Equilatero() { 

    return ladoA == ladoB && 
    ladoC == ladoA && 
    ladoB == ladoC; 
} 

//Esta funcao retorna 'true' se o triangulo 
//eh Isoceles, caso contrario retorna 'false' 
public boolean Isoceles() { 

    return ladoA == ladoB || 
    ladoC == ladoA || 
    ladoB == ladoC; 
} 

//metodo principal 
public static void main( String[] args ) { 
    
    Scanner entrada = new Scanner( System.in ); 
    Triangulo meuTriangulo; 
//------------------------------ Loop -----------------------
    System.out.println( "Insira os lados do triangulo" ); 
    meuTriangulo = new Triangulo( entrada.nextInt(), 
    entrada.nextInt(), 
    entrada.nextInt() ); 

    if( meuTriangulo.Equilatero() ) 
    System.out.println( "Eh um triangulo EQUILATERO" ); 

    else if( meuTriangulo.Isoceles() ) 
    System.out.println( "Eh um triangulo ISOCELES" ); 

    else 
    System.out.println( "Eh um triangulo ESCALENO" ); 
//------------ Pergunta se o usuario quer refazer o calculo --------------
//------------------------------ Loop -----------------------
    } 
}