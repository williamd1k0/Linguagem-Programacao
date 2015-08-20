package pseudodb;

import java.io.*;
import org.apache.commons.codec.binary.Base64;

/** <h1>Classe Files</h1>
 * Tem a função de executar métodos para a manutenção de arquivos.
 * @author tumeo 
 */
public final class Files {
    
    /** <h1>Atributo increment</h1>
     * Valor destinado ao incremento na codificação/decodificação da senha.<br>
     * Valor inicial: 10
     */
    private int increment = 10;
    
    /** <h1>Método Construtor 1</h1>
     * Sem parâmetros, somente retorna a instância padrão. 
     */
    public Files(){}
    
    /** <h1>Método Construtor 2</h1>
     * Retorna a instância e altera o valor do incremento para codificação/decodificação da senha.
     * @param incremento (int) valor do incremento.
     */
    public Files(int incremento){
        this.increment = incremento;
    }
    
    /** <h1>Método writeFile</h1>
     * Tem a função de escrever em um arquivo de texto.
     * Atenção: Se já existir o arquivo especificado, ele será sobrescrevido.
     * @param path (String) caminho onde será criado o arquivo.
     * @param file (String) nome do arquivo com extensão (se existir).
     * @param texto (String) texto a ser inserido.
     */
    public final void writeFile(String path, String file, String texto) {
        
        try {
            FileWriter fw = new FileWriter(path + file);
            fw.write(texto);
            fw.close();

        } catch (Exception e) {
            System.out.println("Deu ruim!");
            System.out.println(e);
        }
        System.out.println("success");
    }

    /** <h1>Método readFile</h1>
     * Tem a função de ler um arquivo de texto específico.
     * @param path (String) caminho onde está localizado o arquivo.
     * @param file (String) nome do arquivo com extensão (se existir).
     * @return (String) todo o texto do arquivo.
     * @throws Exception erro caso não encontre o arquivo.
     */
    public final String readFile(String path, String file) throws Exception {

        FileReader fr = new FileReader(path + file);
        int i;
        String texto = "";
        while ((i = fr.read()) != -1) {
            texto += (char) i;
        }
        fr.close();
        return texto;
    }

    /** <h1>Método deleteFoler</h1>
     * Tem a função de reletar todo o conteúdo de uma pasta específica.
     * @param directory (File) pasta a ser deletada.
     * @return (boolean) a confirmação se a pasta foi totalmente deletada.
     */
    public final boolean deleteFoler(File directory) {
        if (directory.exists()){
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
        }
        return directory.delete();
    }
    
    /** <h1>Método setPassIncrement</h1>
     * Tem a função de alterar o valor de incremento usado em {@link loopPass}.
     * @param increment (int) valor do incremento.
     */
    public final void setPassIncrement(int increment){
        this.increment = increment;
    }
    
    /** <h1>Método getPassIncrement</h1>
     * Retorna o valor atual do incremento usado em {@link loopPass}.
     * @return (int) valor do incremento {@link increment}.
     */
    public final int getPassIncrement(){
        return this.increment;
    }
    
    /** <h1>Método encodePass</h1>
     * Retorna a senha já codificada.
     * @param pass (String) senha a ser codificada.
     * @return (String) senha codificada.
     */
    public final String encodePass(String pass){
        return this.loopPass('e',pass,this.increment);
    }
    
    /** <h1>Método decodePass</h1>
     * Retorna a senha já decodificada.
     * @param pass (String) senha a ser decodificada.
     * @return (String) senha decodificada.
     */
    public final String decodePass(String pass){
        return this.loopPass('d',pass,this.increment);
    }
    
    /** <h1>Método loopPass</h1>
     * Tem a função de codificar ou decodificar uma String várias vezes em um loop.<br>
     * Exemplo: {@code loopPass('e', "minha senha", 5)}<br>
     * O método irá codificar a String "minha senha" 5 vezes seguidas.
     * @param type (char) Tipo do método: 'd' para decode ou 'e' para encode.
     * @param pass (String) Senha para codificar/decodificar.
     * @param increment (int) Tamanho do incremento do loop.
     * @return (String) senha codificada/decodificada.
     */
    final String loopPass(char type, String pass, int increment){
        if(type == 'e'){
            byte[] encodedBytes;
            for (int i = 0; i < increment; i++) {
                encodedBytes = Base64.encodeBase64(pass.getBytes());
                pass = new String(encodedBytes);
            }
        }else if (type == 'd'){
            byte[] decodedBytes;
            for (int i = 0; i < increment; i++) {
                decodedBytes = Base64.decodeBase64(pass);
                pass = new String(decodedBytes);
            }
        }
        return pass;
    }
    
}
