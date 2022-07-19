package main;

public class Trosak {
	
	private int L1N = 4;
	private int L2N = 3;
	
	private double layer1[][] = new double[L1N][1];
	private double layer2[][] = new double[L2N][L1N];
	private double layer3[][] = new double[1][L2N];
	
	private double bias1[] = new double[L1N];
	private double bias2[] = new double[L2N];
	private double bias3[] = new double[1];
	
	private double output0[] = new double[1];
	private double output1[] = new double[L1N];
	private double output2[] = new double[L2N];
	
	private double x_r[] = {
		    0.00, 0.10, 0.20, 0.30, 0.40, 0.50, 0.60, 0.70, 0.80, 0.90,
		    1.00, 1.10, 1.20, 1.30, 1.40, 1.50, 1.60, 1.70, 1.80, 1.90,
		    2.00, 2.10, 2.20, 2.30, 2.40, 2.50, 2.60, 2.70, 2.80, 2.90,
		    3.00, 3.10, 3.20, 3.30, 3.40, 3.50, 3.60, 3.70, 3.80, 3.90,
		    4.00, 4.10, 4.20, 4.30, 4.40, 4.50, 4.60, 4.70, 4.80, 4.90
	};

	private double y_b[] = {
		11.4248, 11.4148, 11.3853, 11.3374, 11.2731, 11.1949, 11.1060, 11.0098, 10.9102, 10.8112,
	    10.7167, 10.6305, 10.5561, 10.4963, 10.4537, 10.4298, 10.4256, 10.4414, 10.4764, 10.5293,
	    10.5980, 10.6796, 10.7711, 10.8687, 10.9685, 11.0666, 11.1590, 11.2421, 11.3126, 11.3675,
	    11.4049, 11.4230, 11.4214, 11.3999, 11.3595, 11.3017, 11.2290, 11.1441, 11.0504, 10.9518,
		10.8520, 10.7552, 10.6651, 10.5854, 10.5192, 10.4692, 10.4374, 10.4249, 10.4324, 10.4596
	};
	
	public Trosak() {
		
	}
	
	
	public double calc_neuron(int neurons, double input_weights[], double input_values[], double bias) {
		int i, j;
		double sum = bias;
		for (i = 0; i < neurons; i++) {
			sum += input_weights[i] * input_values[i];
		}
		return sum;
	}
	
	public float fja_troska(int num_of_args, float args[]) {
		double y_r[] = y_b;
		
		int total_expected_args = 1 + L1N + L1N * L2N + L2N + L1N + L2N + 1;
		
		if(num_of_args != total_expected_args) {
			System.out.println("Broj argumenata nije odgovarajuci, ocekivano " + (total_expected_args - 1) + "realnih vrednosti");
			return (float) 0.0;
		}
		
		int i, j, k, ai = 1;
	    // layer1
	    for (i = 0; i < L1N; i++, ai++) {
	        layer1[i][0] = args[ai];
	    }
	    // layer2
	    for (i = 0; i < L2N; i++) {
	        for (j = 0; j < L1N; j++, ai++) {
	            layer2[i][j] = args[ai];
	        }
	    }
	    // layer3
	    for (i = 0; i < L2N; i++) {
	        layer3[0][i] = args[ai];
	    }
	    // bias1
	    for (i = 0; i < L1N; i++, ai++) {
	        bias1[i] = args[ai];
	    }
	    // bias2
	    for (i = 0; i < L2N; i++, ai++) {
	        bias2[i] = args[ai];
	    }
	    // bias3
	    bias3[0] = args[ai];

	    // idemo dalje!
	    double mse = 0;

	    for (k = 0; k < 50; k++) {
	        output0[0] = x_r[k];
	        // layer1
	        for (i = 0; i < L1N; i++) {
	            output1[i] = calc_neuron(1, layer1[i], output0, bias1[i]);
	        }
	        // layer2
	        for (i = 0; i < L2N; i++) {
	            output2[i] = calc_neuron(L1N, layer2[i], output1, bias2[i]);
	        }
	        // layer3
	        double val = calc_neuron(L2N, layer3[0], output2, bias3[0]);

	        double err = Math.pow(y_r[k] - val, 2);
	        mse += err;
	    }

	    return (float) (mse/50);
	}

}
