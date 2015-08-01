import java.io.*;
import javax.swing.*;

class Files{

    String nome = "nome.cep";
    String idade = "idade.cep";
    String nick = "nick.cep";
    String email = "email.cep";

    public void writeFile(String path, String file, String texto){
        // Escreve no arquivo
        // Args:
        //      String path: caminho do arquivo
        //      String file: nome do arquivo
        //      String texto: texto do arquivo
        try{
            FileWriter fw = new FileWriter(path+file);
            fw.write(texto);
            fw.close();

        }catch(Exception e){
            System.out.println("Deu ruim!");
            System.out.println(e);
        }
        System.out.println("success");
    }

    public String readFile(String path, String file)throws Exception{
        // Lê o arquivo
        // Args:
        //      String path: caminho do arquivo
        //      String file: nome do arquivo
        // Retorno:
        //      String: todo o texto do arquivo

        FileReader fr = new FileReader(path+file);
        int i;
        String texto = "";
        while((i=fr.read())!=-1){
            texto += (char)i;
        }
        fr.close();
        return texto;
    }

    public boolean menu(User user) throws Exception{
        String opt;
        boolean exit;
        do {
            exit = false;
            opt = JOptionPane.showInputDialog("Menu:\n1. Criar usuario.\n2. Consultar.\n3. Alterar\n4. Deletar.\n5. Sair.");
        } while (!((opt.equals("1"))||(opt.equals("2"))||(opt.equals("3"))||(opt.equals("4"))||(opt.equals("5"))));

        switch(opt){
            case "1":
                this.createUser(user);
                break;
            case "2":
                this.showUser(this.queryUser());
                break;
            case "3":
                this.changeUser();
                break;
            case "4":
                this.deleteUser();
                break;
            case "5": exit = true; break;
        }
        return exit;
    }

    public void createUser(User newUser){
        boolean isNew = false;
        String check;
        newUser.email = JOptionPane.showInputDialog("Digite seu email:");

        try{
            check = this.readFile(newUser.email+File.separator, this.nome);
        }catch(Exception erro){
            isNew = true;
        }

        if(isNew){
            File pasta = new File(newUser.email);
            pasta.mkdir();
            this.newUser(newUser);
        }else{
            JOptionPane.showMessageDialog(null,"Este usuario ja existe!");
        }
    }

    public User queryUser() throws Exception{
        String getMail = "";
        boolean userExiste = true;
        try{
            getMail = this.readFile(JOptionPane.showInputDialog("Digite o email para consultar:")+File.separator,this.email);
        }catch(Exception erro){
            userExiste = false;
            JOptionPane.showMessageDialog(null,"Este usuario nao existe ou o email e invalido!");
        }
        if(userExiste){
            return catchUser(getMail);
        }else{
            User noUser = new User();
            noUser.email = "$*$";
            return noUser;
        }
    }

    public User catchUser(String email) throws Exception{
        String path = email+File.separator;
        User getUser = new User();

        getUser.email = email;
        getUser.nome = this.readFile(path, this.nome);
        getUser.idade = this.readFile(path, this.idade);
        getUser.nick = this.readFile(path, this.nick);
        return getUser;
    }

    public void showUser(User u){
        if(!u.email.equals("$*$")){
            JOptionPane.showMessageDialog(null,"Usuario: "+u.nome+"\nEmail: "+u.email+"\nIdade: "+u.idade+"\nApelido: "+u.nick);
        }
    }

    public void changeUser() throws Exception{
        User modUser = queryUser();
        String path = modUser.email+File.separator;
        if(!modUser.email.equals("$*$")){
            String opt;
            do {
                opt = "0";
                opt = JOptionPane.showInputDialog("Qual valor deseja alterar de "+modUser.email+"?\n1. Nome ["+modUser.nome+"]\n2. Idade ["+modUser.idade+"]\n3. Apelido ["+modUser.nick+"]\n4. Voltar.");

                switch(opt){
                    case "1":
                        modUser.nome = JOptionPane.showInputDialog("Digite o novo nome:");
                        this.writeFile(path, this.nome, modUser.nome);
                        break;
                    case "2":
                        modUser.idade = JOptionPane.showInputDialog("Digite a nova idade:");
                        this.writeFile(path, this.nome, modUser.idade);
                        break;
                    case "3":
                        modUser.nick = JOptionPane.showInputDialog("Digite o novo apelido:");
                        this.writeFile(path, this.nome, modUser.nick);
                        break;
                    case "4": break;
                    default: opt = "1";
                }
            } while (((opt.equals("1"))||(opt.equals("2"))||(opt.equals("3"))));

        }
    }

    public void deleteUser() throws Exception{
        User delUser = queryUser();
        if(!delUser.email.equals("$*$")){
            File pasta = new File(delUser.email);
            int opt = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir o usuario "+delUser.nome+"?");
            if (opt == JOptionPane.YES_OPTION) {
                deleteFoler(pasta);
                JOptionPane.showMessageDialog(null,"O usuario foi deletado!");
            }
        }
    }

    public void deleteFoler(File directory){
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                        files[i].delete();
                }
            }
        }
        directory.delete();
    }

    public void newUser(User newUser){
        String path = newUser.email+File.separator;
        newUser.nome = JOptionPane.showInputDialog("Digite seu nome:");
        newUser.idade = JOptionPane.showInputDialog("Digite sua idade:");
        newUser.nick = JOptionPane.showInputDialog("Digite seu apelido:");
        this.writeFile(path, this.email, newUser.email);
        this.writeFile(path, this.nome, newUser.nome);
        this.writeFile(path, this.idade, newUser.idade);
        this.writeFile(path, this.nick, newUser.nick);
    }

}
