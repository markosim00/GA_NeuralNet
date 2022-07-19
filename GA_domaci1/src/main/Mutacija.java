package main;

import java.util.List;
import java.util.Random;

public class Mutacija {
	
	private Hromozom hromozom;

	
	public Mutacija(float mut_rate, Hromozom hromozom) {
		Random rand = new Random();
		for(int i = 0; i < 27; i++) {
			if(rand.nextFloat() <= mut_rate) {
				int min = 0;
				int max = 31;
					
				int number1 = rand.nextInt(max - min) + min;
				int number2 = rand.nextInt(max - min) + min;
					
				if(number1 < number2) {
					int cnt = 0;
					for(int j = number1; j <= (number1 + number2)/2; j++) {
						int swap = hromozom.getGen()[i][j];
						hromozom.getGen()[i][j] = hromozom.getGen()[i][number2 - cnt];
						hromozom.getGen()[i][number2 - cnt] = swap;
						cnt++;
					}
				}
				else {
					int cnt = 0;
					for(int j = number2; j <= (number2 + number1)/2; j++) {
						int swap = hromozom.getGen()[i][j];
						hromozom.getGen()[i][j] = hromozom.getGen()[i][number1 - cnt];
						hromozom.getGen()[i][number1 - cnt] = swap;
						cnt++;
					}
				}
				this.hromozom = hromozom;
			}
		}
		
	}


	public Hromozom getHromozom() {
		return hromozom;
	}


	public void setHromozom(Hromozom hromozom) {
		this.hromozom = hromozom;
	}
	
	
	
}
