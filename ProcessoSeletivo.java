package edu.dan.ControleCandidatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {

    public static void main(String[] args) {
        List<String> candidatosSelecionados = selecaoCandidatos();
        imprimirSelecionados(candidatosSelecionados);

        for(String candidatosContactados : candidatosSelecionados){
            entrandoEmContato(candidatosContactados);
        }
    }

    static void imprimirSelecionados(List<String> candidatosSelecionados) {
        System.out.println("Candidatos selecionados: ");
        for (String candidato : candidatosSelecionados) {
            System.out.println(candidato);
        }
    }

    static List<String> selecaoCandidatos() {
        String[] candidatos = {"Daniel", "Dan", "Dani", "Daniela", "Danniel", "Dânio", "Denise", "Danuza", "Dário", "Dória"};

        int candidatoAtual = 0;
        List<String> candidatosSelecionados = new ArrayList<>();

        while (candidatosSelecionados.size() < Constantes.getNumeroCandidatosParaAVaga() && candidatoAtual < candidatos.length) { //verificação para o index ir somente até o 9.
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = valorPretendido();

            System.out.printf("O candidato %s solicitou este valor de salário %.2f%n", candidato, salarioPretendido);

            if (Constantes.getSalarioBase() >= salarioPretendido) {
                System.out.println("O candidato " + candidato + " foi selecionado para a vaga");
                candidatosSelecionados.add(candidato);
            }
            candidatoAtual++;
        }
        return candidatosSelecionados;
    }

    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }

    static void entrandoEmContato(String candidatosContactados){

        boolean contatoSucedido = false;
        int tentativas = 1;

        while (tentativas < 3 && !contatoSucedido){
            if(atendeu()){
                System.out.printf("Conseguimos contato com o %s após %d tentativa(s)%n", candidatosContactados, tentativas);
                contatoSucedido = true;
            }else{
                System.out.printf("Ligando... %n", candidatosContactados);
            }
            tentativas++;
        }
        if(!contatoSucedido){
            System.out.printf("Não conseguimos contato com o candidato %s após as %d tentativas.%n", candidatosContactados, tentativas);
        }
    }

    static boolean atendeu(){
        //return new Random().nextInt(3)==1; outra forma de randomizar.
        return ThreadLocalRandom.current().nextBoolean();
    }

    static void analisarCandidato(double salarioPretendido) {
        if (Constantes.getSalarioBase() > salarioPretendido) {
            System.out.println("Ligar para o candidato");
        } else if (Constantes.getSalarioBase() == salarioPretendido) {
            System.out.println("Ligar para oferecer contra proposta");
        } else {
            System.out.println("Aguardando o resultado dos demais candidatos");
        }
    }
}