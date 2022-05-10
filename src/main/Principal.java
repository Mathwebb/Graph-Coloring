package main;

import grafos.Grafo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.System;

import coloracao.Baseline;
import coloracao.Heuristica;

public class Principal {
	public static Grafo<String> grafoAleatorio() {
		Random gerador = new Random();

		Grafo<String> grafo = new Grafo<String>();
		ArrayList<String> nomes = new ArrayList<String>();
		int numVert = gerador.nextInt(3, 9);

		for (Integer i = 0; i < numVert; i++) {
			nomes.add(String.valueOf(i));
		}

		int maxAresta = 0;
		for (int i = 1; i < nomes.size(); i++) {
			maxAresta += i;
		}
		int numArestas;
		if (maxAresta == 0) {
			numArestas = 0;
		} else {
			numArestas = gerador.nextInt(maxAresta);
		}
		if (numArestas < nomes.size())
			numArestas = nomes.size();
		grafo.constroiGrafoAleatorio(nomes, numArestas);
		return grafo;
	}

	public static Grafo<String> grafoAleatorio(Integer numVert) {
		Random gerador = new Random();

		Grafo<String> grafo = new Grafo<String>();
		ArrayList<String> nomes = new ArrayList<String>();

		for (Integer i = 0; i < numVert; i++) {
			nomes.add(String.valueOf(i));
		}

		int maxAresta = 0;
		for (int i = 1; i < nomes.size(); i++) {
			maxAresta += i;
		}
		int numArestas;
		if (maxAresta == 0) {
			numArestas = 0;
		} else {
			numArestas = gerador.nextInt(maxAresta);
		}
		if (numArestas < nomes.size())
			numArestas = nomes.size();
		grafo.constroiGrafoAleatorio(nomes, numArestas);
		return grafo;
	}

	public static void executaAlgoritmos(Grafo<String> grafo, Grafo<String> grafoHeur) {
		Grafo<String> resultadoBaseline;
		Grafo<String> resultadoHeuristica;
		Baseline<String> baseline = new Baseline<String>();
		Heuristica<String> heuristica = new Heuristica<String>();

		long tempoHeur = System.nanoTime();
		resultadoHeuristica = heuristica.welshPowel(grafoHeur);
		tempoHeur = System.nanoTime() - tempoHeur;

		System.out.print("Heurística: " + baseline.validaSolucaoOtima(resultadoHeuristica) + " cores - "
				+ (double) tempoHeur / 1000000 + " ms");

		System.out.println();

		long tempoBase = System.nanoTime();
		resultadoBaseline = baseline.algoritmoExato(grafo);
		tempoBase = System.nanoTime() - tempoBase;

		System.out.print("Baseline: " + baseline.validaSolucaoOtima(resultadoBaseline) + " cores - "
				+ (double) tempoBase / 1000000 + " ms");

		System.out.println("\n");
		System.out.println("Grafo utilizado(em formato de lista de adjacências):");

		for (Integer k = 0; k < grafo.size(); k++) {
			System.out.println(grafo.get(k).get(0).getDado());
			for (Integer t = 1; t < grafo.get(k).size(); t++) {
				System.out.print(grafo.get(k).get(0).getDado() + "-" + grafo.get(k).get(t).getDado() + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		Grafo<String> grafo = null;
		Scanner scanner = new Scanner(System.in);

		System.out.println("O programa faz execuções de grafos comparando as execuções de dois algoritmos diferentes,"
				+ " um baseline que utiliza força bruta e um heurístico que garante uma solução em menor tempo.");
		System.out.println("O nosso problema consiste em definir frequências para estações de rádio de forma que"
				+ " estações de rádio muito próximas não interfiram entre si.");
		Integer opt = 1;

		while (opt != 0) {
			System.out.println("1 - Utilizar um mapa de estações de rádio completamente aleatório");
			System.out.println("2 - Utilizar um grafo com uma quantidade fixa de estações e inteferências aleatórias");
			System.out.println("3 - Adicionar estações e interferências em um novo mapa");
			System.out.println("0 - Sair do programa");
			System.out.print("Digite uma opção (0 - 3): ");
			opt = Integer.parseInt(scanner.nextLine());
			System.out.println();

			if (opt == 1) {
				grafo = grafoAleatorio();
				Grafo<String> grafoHeur = new Grafo<String>(grafo);
				executaAlgoritmos(grafo, grafoHeur);
			}
			if (opt == 2) {
				System.out.println("Digite a quantidade de vértices que o grafo deve ter: ");
				Integer numVert = Integer.parseInt(scanner.nextLine());

				grafo = grafoAleatorio(numVert);
				Grafo<String> grafoHeur = new Grafo<String>(grafo);
				executaAlgoritmos(grafo, grafoHeur);
			}
			if (opt == 3) {
				grafo = new Grafo<String>();
				while (opt != 0) {
					System.out.println("1 - Adicionar nova estação");
					System.out.println("2 - Adicionar nova interferência");
					System.out.println("3 - Executar algoritmos");
					System.out.println("4 - Criar novo mapa");
					System.out.println("0 - Voltar");
					System.out.print("Digite uma opção (0 - 4): ");
					opt = Integer.parseInt(scanner.nextLine());
					System.out.println();

					if (opt == 1) {
						System.out.print("Digite o nome da estação a ser adicionada: ");
						String dadoVert = scanner.nextLine();
						grafo.addVertices(dadoVert);
						System.out.println();
					}
					if (opt == 2) {
						System.out.print("Digite o nome da estação 1 envolvida na interferência: ");
						String dadoVert1 = scanner.nextLine();
						System.out.print("Digite o nome da estação 2 envolvida na interferência: ");
						String dadoVert2 = scanner.nextLine();
						grafo.addAresta(dadoVert1, dadoVert2);
						System.out.println();
					}
					if (opt == 3) {
						Grafo<String> grafoHeur = new Grafo<String>(grafo);
						executaAlgoritmos(grafo, grafoHeur);
					}
					if (opt == 4) {
						grafo = new Grafo<String>();
					}
				}
				opt = 3;
			}

			System.out.println();
		}
		scanner.close();
	}
}
