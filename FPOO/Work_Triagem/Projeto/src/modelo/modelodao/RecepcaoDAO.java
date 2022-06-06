package modelo.modelodao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Recepcao;

public class RecepcaoDAO {

	private BufferedReader br;
	private BufferedWriter bw;
	private String path = ".//data//pacientes.csv";

	public ArrayList<Recepcao> ler() {
		ArrayList<Recepcao> linhas = new ArrayList<>();
		Recepcao p;
		try {
			br = new BufferedReader(new FileReader(path));
			String linha = br.readLine();
			while (linha != null) {
				p = new Recepcao(linha);
				linhas.add(p);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return linhas;
	}

	public void escrever(ArrayList<Recepcao> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (Recepcao p : linhas) {
				bw.write(p.toCSV());
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}