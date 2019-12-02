package com.lancaster.dsl.SLO_DSL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GenerateCombinationsFiles {

	public static void main (String args[]){
		ArrayList<String> providers = new ArrayList<>();
		providers.add("google");
		providers.add("aws");
		providers.add("digitalocean");
		providers.add("rackspace");
		providers.add("azure");
		for(int i=1; i<=10; i++){
			ArrayList<ArrayList<String>> candidateProviders = new ArrayList<>();
			for(int j=0; j<i; j++){
				candidateProviders.add((ArrayList<String>) providers.clone());
			}
//			ArrayList<ArrayList<String>> combinations = new ArrayList<>();
//			GenerateCombinationsFiles.generateCombinations(candidateProviders, combinations);
//			System.out.println(combinations.size());
//			GenerateCombinationsFiles.writeArrayListToFile(combinations,"resources/comb/"+i+".txt");
			ArrayList<ArrayList<String>> combinations2 = GenerateCombinationsFiles.readFileIntoArrayList("resources/comb/10.txt");
			System.out.println(combinations2.size());
		}
	}
	public static void generateCombinations(ArrayList<ArrayList<String>> list,
			ArrayList<ArrayList<String>> combinations) {

		int n = list.size();
		int[] indices = new int[n];
		for (int i = 0; i < n; i++) {
			indices[i] = 0;
		}
		while (true) {
			for (int i = 0; i < n; i = i + list.size()) {
				ArrayList<String> vertices = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					vertices.add(list.get(j).get(indices[j]));
				}
				combinations.add(vertices);
			}
			int next = n - 1;
			while (next >= 0 && (indices[next] + 1 >= list.get(next).size())) {
				next--;
			}
			if (next < 0) {
				return;
			}
			indices[next]++;

			for (int i = next + 1; i < n; i++) {
				indices[i] = 0;
			}
		}
	}
	
	public static void writeArrayListToFile(ArrayList<ArrayList<String>> al, String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName); 
			for(ArrayList<String> ar : al){
				for(String str : ar){
					writer.write(str + ",");
				}
				writer.write(System.lineSeparator());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<ArrayList<String>> readFileIntoArrayList(String fileName) {
		try {
			Scanner s = new Scanner(new File(fileName));
			ArrayList<ArrayList<String>> list = new ArrayList<>();
			while (s.hasNext()) {
				String line = s.next();
				String[] comb = line.split(",");
				ArrayList<String> combList = new ArrayList<>(Arrays.asList(comb));
				list.add(combList);
			}
			s.close();
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
