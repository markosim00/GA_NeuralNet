package main;

import java.util.Arrays;

public class Hromozom implements Comparable{
	
	private float argumenti[] = new float[27];
	private int gen[][] = new int[27][32];
	private float trosak;
	
	public Hromozom() {
		
	}

	public float[] getArgumenti() {
		return argumenti;
	}

	public void setArgumenti(float[] argumenti) {
		this.argumenti = argumenti;
	}

	public int[][] getGen() {
		return gen;
	}

	public void setGen(int[][] gen) {
		this.gen = gen;
	}
	
	public float getTrosak() {
		return trosak;
	}

	public void setTrosak() {
		Trosak tr = new Trosak();
		this.trosak = tr.fja_troska(28, this.argumenti);
	}

	public void koduj_u_binarni(float argumenti[]) {
		
		for(int i = 0; i < 27; i++) {
			for(int j = 0; j < 32; j++) {
				double sum = 0.00;
				for(int k = 0; k <j; k++) 
					sum += this.gen[i][k]*(1/Math.pow(2, k+1));
				this.gen[i][j] =  (int) Math.round(argumenti[i] - 1/Math.pow(2, j+1) - sum);
			}
		}
		
		this.gen = gen;
	}

	@Override
	public String toString() {
		return "Hromozom [argumenti=" + Arrays.toString(argumenti) + "]";
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Hromozom) {
			Hromozom h = (Hromozom) o;
			if(this.getTrosak() < h.getTrosak()) {
				return -1;
			}
			else
				return 1;
		}
			
		return 0;
	}

	
	
	

}
