package conta_bancariacontroller;

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
        Optional<Conta> contaOpt = buscarNaCollection(numero);
        if (contaOpt.isPresent()) {
            Conta conta = contaOpt.get();
            if (conta.getSaldo() >= valor) {
                conta.setSaldo(conta.getSaldo() - valor);
                System.out.println("Saque realizado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente para saque.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    @Override
    public void depositar(int numero, float valor) {
        Optional<Conta> contaOpt = buscarNaCollection(numero);
        if (contaOpt.isPresent()) {
            Conta conta = contaOpt.get();
            conta.setSaldo(conta.getSaldo() + valor);
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        Optional<Conta> contaOrigemOpt = buscarNaCollection(numeroOrigem);
        Optional<Conta> contaDestinoOpt = buscarNaCollection(numeroDestino);

        if (contaOrigemOpt.isPresent() && contaDestinoOpt.isPresent()) {
            Conta contaOrigem = contaOrigemOpt.get();
            Conta contaDestino = contaDestinoOpt.get();

            if (contaOrigem.getSaldo() >= valor) {
                contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
                contaDestino.setSaldo(contaDestino.getSaldo() + valor);
                System.out.println("Transferência realizada com sucesso!");
            } else {
                System.out.println("Saldo insuficiente na conta de origem.");
            }
        } else {
            if (contaOrigemOpt.isEmpty()) {
                System.out.println("Conta de origem não encontrada!");
            }
            if (contaDestinoOpt.isEmpty()) {
                System.out.println("Conta de destino não encontrada!");
            }
        }
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
