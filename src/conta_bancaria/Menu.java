package conta_bancaria;

import java.util.Scanner;

import conta_bancaria.model.Conta;
import conta_bancaria.util.Cores;

public class Menu {

    public static void main(String[] args) {
        
        Scanner leia = new Scanner(System.in);
        
        int opcao;
        
        Conta c1 = new Conta(1, 123, 1, "Ayla", 500000);
        
        c1.visualizar(); 
        
        c1.sacar(100);
        c1.visualizar(); 
        
        c1.depositar(1000); 
        c1.visualizar(); 
        
        //Alterar propriedade titular
        c1.setTitular("Aylla Scaglia");
        c1.visualizar();
            System.out.println(Cores.TEXT_GREEN_BRIGHT + Cores.ANSI_BLACK_BACKGROUND + "                                                     ");
            System.out.println("                BANCO DO BRAZIL COM Z                ");
            System.out.println("                                                     ");
            System.out.println("=====================================================");
            System.out.println("                                                     ");
            System.out.println("               1 - Criar Conta                       ");
            System.out.println("               2 - Listar todas as Contas            ");
            System.out.println("               3 - Buscar conta por Número           ");
            System.out.println("               4 - Atualizar Dados da Conta          ");
            System.out.println("               5 - Apagar conta                      ");
            System.out.println("               6 - Sacar                             ");
            System.out.println("               7 - Depositar                         ");
            System.out.println("               8 - Transferir valores entre Contas   ");
            System.out.println("               9 - Sair                              ");
            System.out.println("                                                     ");
            System.out.println("✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷");
            System.out.println("                                                     ");
            System.out.println("               Digite a opção desejada:              ");
            leia.close();
        }
}


