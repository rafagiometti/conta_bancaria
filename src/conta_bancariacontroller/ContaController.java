package conta_bancariacontroller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Optional;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;

public class ContaController implements ContaRepository {
    public ArrayList<Conta> listaContas = new ArrayList<Conta>();
    int numero = 0;

    @Override
    public void procurarPorNumero(int numero) {
        Optional<Conta> conta = buscarNaCollection(numero);
        if (conta.isPresent())
        	conta.get().visualizar();
        else
        	System.out.printf("\nA conta número %d não foi encontrada", numero);
    }

    @Override
    public void listarTodas() {
        for (var conta : listaContas) {
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
        Optional<Conta> buscaConta = buscarNaCollection(conta.getNumero());
        if (buscaConta.isPresent()) {
        	listaContas.set(listaContas.indexOf(buscaConta.get()), conta);
    	System.out.printf("\nA conta número %d foi atualizada com sucesso", conta.getNumero());
    }else
        	System.out.printf("\nA conta número %d não foi encontrada", numero);
        
    }

    @Override
    public void deletar(int numero) {
        Optional<Conta> conta = buscarNaCollection(numero);
        if (conta.isPresent()) {
            listaContas.remove(conta.get());
            System.out.printf("\nA conta número %d foi excluída com sucesso!", numero);
        } else {
            System.out.printf("\nA Conta número %d não foi encontrada!", numero);
        }
    }


    @Override
    public void sacar(int numero, float valor) {
    	
    	NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
    	
        Optional<Conta> conta = buscarNaCollection(numero);
        if (conta.isPresent()) {
            if(conta.get().sacar(valor) == true)
            	System.out.printf("\n O saque no valor de %s, foi efetuado com sucesso", nfMoeda.format(valor));
            } else 
                System.out.printf("\n A conta número %d não foi encontrada", numero);
            
    }

    @Override
    public void depositar(int numero, float valor) {
    	
    	NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
        Optional<Conta> conta = buscarNaCollection(numero);
        if (conta.isPresent()) {
        	conta.get().depositar(valor);
        	System.out.printf("\n O depósito no valor de %s", nfMoeda.format(valor));
        } else 
                System.out.printf("\n A conta número %d não foi encontrada", numero);
    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
       NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
       Optional<Conta> contaOrigem = buscarNaCollection(numeroOrigem);
       Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);
       
       if(contaOrigem.isPresent() && contaDestino.isPresent()){
    	   if (contaOrigem.get().sacar(valor)) {
    		  contaDestino.get().depositar(valor); 
    		  System.out.printf("\n A transferência no valor de %s, da conta %d para a conta %d foi efetuado com sucesso", nfMoeda.format(valor), numeroOrigem, numeroDestino);
    		  
    	  }
       }else
    	   System.out.printf("\n A conta numero %d não foi encontrada!", numero);
       
    }

    public int gerarNumero() {
        return ++numero;
    }

    public Optional<Conta> buscarNaCollection(int numero) {
        for (var conta : listaContas) {
            if (conta.getNumero() == numero) {
                return Optional.of(conta);
            }
        }
        return Optional.empty();
    }
}
