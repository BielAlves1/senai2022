package model;

import java.util.Scanner;

public class Ex6 {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		double dim, r, h, area, base, volume;
		
		System.out.print("Insira o diametro do cilindro: ");
		dim = entrada.nextDouble();
		System.out.print("Insira a altura do cilindro: ");
		h = entrada.nextDouble();
		
		r = dim / 2;
		base = 3.14 * (r * r);
		volume = base * h;
		area = 2 * 3.14 * r * (r + h);
		
		System.out.printf("a area � de %.2fcm� e o volume � de %.2fcm�", area, volume);
				
	}
}
