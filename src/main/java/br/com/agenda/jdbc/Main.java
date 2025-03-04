package br.com.agenda.jdbc;

import br.com.agenda.jdbc.dao.ContatoDao;
import br.com.agenda.jdbc.model.Contato;

import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ContatoDao contatoDao = new ContatoDao();


        System.out.println("=== Adicionando um contato ===");
        Contato contato1 = new Contato();
        contato1.setNome("Gabriel Pereira Silva");
        contato1.setEmail("gabriel@example.com");
        contato1.setTelefone("1234-5678");
        contato1.setDataCadastro(Calendar.getInstance());
        contatoDao.adiciona(contato1);
        System.out.println("Contato adicionado com sucesso!\n");


        System.out.println("=== Listando todos os contatos ===");
        List<Contato> contatos = contatoDao.getLista();
        for (Contato contato : contatos) {
            System.out.println(contato.toString());
        }
        System.out.println();


        System.out.println("=== Buscando contato por ID ===");
        Contato contatoBuscado = contatoDao.buscaPorId(1L); // Altere o ID conforme necessário
        if (contatoBuscado != null) {
            System.out.println("Contato encontrado: " + contatoBuscado);
        } else {
            System.out.println("Contato não encontrado.");
        }
        System.out.println();


        System.out.println("=== Alterando um contato ===");
        if (contatoBuscado != null) {
            contatoBuscado.setNome("João da Silva"); // Altera o nome
            contatoDao.altera(contatoBuscado);
            System.out.println("Contato alterado com sucesso: " + contatoBuscado);
        } else {
            System.out.println("Contato não encontrado para alteração.");
        }
        System.out.println();


        System.out.println("=== Removendo um contato ===");
        if (contatoBuscado != null) {
            contatoDao.remove(contatoBuscado);
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Contato não encontrado para remoção.");
        }
        System.out.println();

        System.out.println("=== Listando contatos após remoção ===");
        contatos = contatoDao.getLista();
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }
}