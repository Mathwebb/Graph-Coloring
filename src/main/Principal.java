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
	public static Grafo<String> grafoAleatorio(){
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
		if (numArestas < nomes.size()) numArestas = nomes.size();
		grafo.constroiGrafoAleatorio(nomes, numArestas);
		return grafo;
	}
	
	public static void main(String[] args) throws IOException {
		
		Grafo<String> resultadoBaseline;
		Grafo<String> resultadoHeuristica;
		Baseline<String> baseline = new Baseline<String>();
		Heuristica<String> heuristica = new Heuristica<String>();
		
//		nomes.add("A");
//		nomes.add("B");
//		nomes.add("C");
//		nomes.add("D");
//		nomes.add("E");
//		nomes.add("F");
//		nomes.add("G");
//		nomes.add("H");
//		nomes.add("I");
		
		for (Integer i = 0; i < 100; i++) {	
			Grafo<String> grafo = grafoAleatorio();
			
			Grafo<String> grafoHeur = new Grafo<String>(grafo);
			
			long tempoHeur = System.nanoTime();
			resultadoHeuristica = heuristica.welshPowel(grafoHeur);
			tempoHeur = System.nanoTime() - tempoHeur;
			
			System.out.print(baseline.validaSolucaoOtima(resultadoHeuristica)/* + (double) tempoHeur/1000000*/);
			
			System.out.print(" ");
			
			long tempoBase = System.nanoTime();
			resultadoBaseline = baseline.algoritmoExato(grafo);
			tempoBase = System.nanoTime() - tempoBase;
			
			System.out.print(baseline.validaSolucaoOtima(resultadoBaseline)/* + (double) tempoBase/1000000*/);
			
			System.out.println();
		}
	
		
//		for (Integer i = 0; i < grafo.size(); i++) {
//			System.out.println(grafo.get(i).get(0).getDado());
//			for (Integer t = 1; t < grafo.get(i).size(); t++) {
//				System.out.print(grafo.get(i).get(0).getDado() + "-" + grafo.get(i).get(t).getDado() + "  ");
//			}
//			System.out.println();
//		}
		
		
//		for (Integer i = 0; i < resultadoHeuristica.size(); i++) {
//			System.out.println(resultadoHeuristica.get(i).get(0).getFrequencia());
//		}
		
//		for (Integer i = 0; i < resultadoBaseline.size(); i++) {
//			System.out.println(resultadoBaseline.get(i).get(0).getFrequencia());
//		}
	}
}
