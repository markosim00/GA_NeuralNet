package main;

import java.util.Random;

public class Ukrstanje {
	
	private Hromozom potomak1;
	private Hromozom potomak2;
	
	public Ukrstanje(Hromozom roditelj1, Hromozom roditelj2) {
		
		Random rand = new Random();
		
		for(int i = 0; i < 27; i++) {
			for(int j = 0; j < 32; j++) {
				if(rand.nextInt() % 2 == 0) {
					this.potomak1.getGen()[i][j] = roditelj1.getGen()[i][j];
					this.potomak2.getGen()[i][j] = roditelj2.getGen()[i][j];
				}
				else{
					this.potomak1.getGen()[i][j] = roditelj2.getGen()[i][j];
					this.potomak2.getGen()[i][j] = roditelj1.getGen()[i][j];
				}
			}
		}
		
		
	}

	public Hromozom getPotomak1() {
		return potomak1;
	}

	public void setPotomak1(Hromozom potomak1) {
		this.potomak1 = potomak1;
	}

	public Hromozom getPotomak2() {
		return potomak2;
	}

	public void setPotomak2(Hromozom potomak2) {
		this.potomak2 = potomak2;
	}
	
	
	
	
}
