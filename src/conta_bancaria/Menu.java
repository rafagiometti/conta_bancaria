package conta_bancaria;

import java.io.IOException;
import java.util.Scanner;

import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;
import conta_bancariacontroller.ContaController;

public class Menu {

    public static void main(String[] args) {
    	Scanner leia = new Scanner(System.in);
    	
    	ContaController contas = new ContaController();

        int opcao, numero, agencia, tipo, aniversario;
        String titular;
        float saldo, limite;
        
        //Dados para teste
        ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000.00f, 100.00f);
		contas.cadastrar(cc1);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Maria da Silva", 1000.00f, 12);
		contas.cadastrar(cp1);
        
        while (true) {

            System.out.println(Cores.TEXT_GREEN_BRIGHT + Cores.ANSI_BLACK_BACKGROUND);
            System.out.println("                                                     ");
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
            System.out.println("=====================================================");
            System.out.println("                                                     ");
            System.out.print("               Digite a opção desejada:              \n");
            
            opcao = leia.nextInt();  // ✅ Atribuindo valor à variável

            if (opcao == 9) {
                System.out.println("\nBanco do Brazil com Z");
                sobre();
                leia.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println("\nCriar Conta");
                    
                    System.out.println("Digite o número da agência");
                    agencia = leia.nextInt();
                    
                    System.out.println("Digite o nome do titular");
                    leia.skip("\\R");
                    titular = leia.nextLine();
                    
                    System.out.println("Digite o tipo de conta (1- CC | 2 - CP):");
                    tipo = leia.nextInt();
                    
                    System.out.println("Digite o saldo inicial da conta:");
                    saldo = leia.nextFloat();
                   
                    switch(tipo) {
                    case 1 -> {
                    	System.out.println("Digite o limite da conta:");
                    	limite = leia.nextFloat();
                    	contas.cadastrar(
                    			new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite)
                    			);
                    }
                    case 2 -> {
                    	System.out.println("Digite o dia do aniversário da conta:");
                    	aniversario = leia.nextInt();
                    	contas.cadastrar(
                    			new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario)
                    			);
                    }
                    }
                    
                    keyPress();
                    break;
                case 2:
                    System.out.println("\nListar todas as Contas");
                    contas.listarTodas();
                    keyPress();
                    break;
                case 3:
                    System.out.println("\nBuscar Conta por número");
                    
                    System.out.println("Digite o número da conta");
                    numero = leia.nextInt();
                    
                    contas.procurarPorNumero(numero);
                    keyPress();
                    break;
                case 4:
                    System.out.println("\nAtualizar dados da Conta");
                    keyPress();
                    break;
                case 5:
                    System.out.println("\nApagar Conta");
                    keyPress();
                    break;
                case 6:
                    System.out.println("\nSacar");
                    keyPress();
                    break;
                case 7:
                    System.out.println("\nDepositar");
                    keyPress();
                    break;
                case 8:
                    System.out.println("\nTransferir");
                    keyPress();
                    break;
                default:
                    System.out.println("\nOpção Inválida");
                    keyPress();
                    break;
            }
        }
    }

    public static void keyPress() {

        try {
            System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
            System.in.read();
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao tentar ler o teclado");
        }
    }
    public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Rafaela ");
		System.out.println("github.com/rafagiometti");
		System.out.println("*********************************************************");
	}
}



