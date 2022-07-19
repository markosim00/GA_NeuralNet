package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Genetski {
	
	
	
	public Genetski() {
		int max_iter = 150, pop = 10, t = 0, velicina_turnira = 3;
		float mut_rate = (float) 0.2;
		File file = new File("config.txt");
		try {
			Scanner sc = new Scanner(file);
			max_iter = sc.nextInt();
			mut_rate = sc.nextFloat();
			pop = sc.nextInt();
			velicina_turnira = sc.nextInt();
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random rand = new Random();
		Turnir turnir = new Turnir();
		ArrayList<Hromozom> populacija = new ArrayList<Hromozom>();
		
		for(int j = 0; j < pop; j++) {
			Hromozom hromozom = new Hromozom();
			float argumenti[] = new float[27];
			for(int i = 0; i < 27; i++) {
				argumenti[i] = rand.nextFloat();
			}
			hromozom.setArgumenti(argumenti);
			hromozom.koduj_u_binarni(argumenti);
			hromozom.setTrosak();
			System.out.println(hromozom.getTrosak());
			turnir.dodajHromozom(hromozom);
			populacija.add(hromozom);
		}
		float najmanji_trosak = 100;
		while((turnir.getNajbolji_hromozom() == null  || turnir.getNajbolji_hromozom().getTrosak() != 0) && t < max_iter) {
			
			while(populacija.size() < pop) {
				turnir.setNajbolji_hromozom(velicina_turnira);
				Hromozom h1 = turnir.getNajbolji_hromozom();
				turnir.setNajbolji_hromozom(velicina_turnira);
				Hromozom h2 = turnir.getNajbolji_hromozom();
				Ukrstanje ukrstanje = new Ukrstanje(h1, h2);
				Mutacija mutacija1 = new Mutacija(mut_rate, ukrstanje.getPotomak1());
				Mutacija mutacija2 = new Mutacija(mut_rate, ukrstanje.getPotomak2());
				populacija.add(mutacija1.getHromozom());
				populacija.add(mutacija2.getHromozom());
			}
			
			Collections.sort(populacija);
			if(populacija.get(0).getTrosak() < najmanji_trosak)
				najmanji_trosak = populacija.get(0).getTrosak();
			System.out.println("Najmanji trosak: " + najmanji_trosak);
			float srednji_trosak, ukupni_trosak = 0;
			for(Hromozom h : populacija) {
				ukupni_trosak += h.getTrosak();
			}
			srednji_trosak = ukupni_trosak/populacija.size();
			System.out.println("Srednji trosak: " + srednji_trosak);
			t++;
		}
		
		
	}
	
	
	

}
