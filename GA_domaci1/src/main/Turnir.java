package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Turnir {
	
	private List<Hromozom> hromozomi = new ArrayList<Hromozom>();
	private Hromozom najbolji_hromozom;
	
	public Turnir() {
		
	}

	public Hromozom getNajbolji_hromozom() {
		return najbolji_hromozom;
	}

	public void setNajbolji_hromozom(int velicina_turnira) {
		
		float najmanji_trosak = 100;
		Random rand = new Random();
		List<Hromozom> populacija_za_turnir = new ArrayList<Hromozom>(); 
		
		for(int i = 0; i < velicina_turnira; i++) {
			populacija_za_turnir.add(hromozomi.get(rand.nextInt(hromozomi.size())));
		}
		
		for(Hromozom hromozom: populacija_za_turnir) {
			if(hromozom.getTrosak() < najmanji_trosak) {
				najmanji_trosak = hromozom.getTrosak();
				this.najbolji_hromozom = hromozom;
			}		
		}
		
		this.hromozomi.clear();
		populacija_za_turnir.clear();
	}

	public List<Hromozom> getHromozomi() {
		return hromozomi;
	}

	public void setHromozomi(List<Hromozom> hromozomi) {
		this.hromozomi = hromozomi;
	}
	
	public void dodajHromozom(Hromozom hromozom) {
		this.hromozomi.add(hromozom);
	}
	
	

}
