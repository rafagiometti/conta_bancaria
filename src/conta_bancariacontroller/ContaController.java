package conta_bancariacontroller;

import java.util.ArrayList;
import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;

public class ContaController implements ContaRepository{
	//Criar collection ArrayList
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	
	//Variável para controlar os números das contas
	int numero = 0;
	
	
	@Override
	public Conta procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null)
			conta.visualizar();
		else
			System.out.printf("\nA Conta número %d não foi encontrada!", numero);
	
		return conta;
	}

	@Override
	public void listarTodas() {
		for(var conta : listaContas) {
			conta.visualizar();
		}
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A conta foi criada com sucesso!");
		
	}

	@Override
	public void atualizar(Conta conta) {
	}

	@Override
	public void deletar(int numero) {
		
	}

	@Override
	   public void sacar(int numero, float valor) {
        Conta conta = procurarPorNumero(numero);
        if (conta != null) {
            if (conta.getSaldo() >= valor) {
                conta.setSaldo(conta.getSaldo() - valor);
                System.out.println("Saque realizado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente para saque.");
            }
        }
    }


	@Override
	public void depositar(int numero, float valor) {
     Conta conta = procurarPorNumero(numero);
      if(conta != null) {
    	conta.setSaldo(conta.getSaldo() + valor);
    	System.out.println("Depósito realizado com sucesso!");
    }
    
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
	    Conta contaOrigem = procurarPorNumero(numeroOrigem);
	    Conta contaDestino = procurarPorNumero(numeroDestino);

	    
	    if (contaOrigem != null && contaDestino != null) {
	        if (contaOrigem.getSaldo() >= valor) {
	            contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
	            contaDestino.setSaldo(contaDestino.getSaldo() + valor);

	            System.out.println("Transferência realizada com sucesso!");
	        } else {
	            System.out.println("Saldo insuficiente na conta de origem.");
	        }
	    } else {
	        if (contaOrigem == null) {
	            System.out.println("Conta de origem não encontrada!");
	        }
	        if (contaDestino == null) {
	            System.out.println("Conta de destino não encontrada!");
	        }
	    }
	}


	//Métodos auxiliares
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	public Conta buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
}
