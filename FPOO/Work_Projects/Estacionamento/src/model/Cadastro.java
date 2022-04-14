package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cadastro {
	
	public void cadastrar(String info, String fileName, boolean append) {
		try {
			FileWriter fw = new FileWriter(fileName + ".csv", append);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(info);
			
			bw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("Erro geral."+ e);
		}
		
	
	}
	public ArrayList<String> listar(String fileName) {
		ArrayList<String> carro = new ArrayList<>();
		try {
			FileReader fr = new FileReader(fileName +".csv");
			BufferedReader br = new BufferedReader(fr);
			
			String linha = "\r\n";
			
			while((linha = br.readLine()) != null) {
				carro.add(linha);
				System.out.println(linha);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo n�o encontrado.");
		} catch (IOException e) {
			System.out.println("Arquivo est� aberto");
		} catch (Exception e) {
			System.out.println("Erro geral");
		}
		return carro;
	}
}
