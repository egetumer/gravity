package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

    public static void main (String [] args) {
	NumberFormat nf = NumberFormat.getInstance ();
	nf.setMaximumFractionDigits (3);

	GravityConstant gEarth = new GravityConstant(9.80665);
	GravityConstant gJupiter = new GravityConstant(24.79);
	//double gEarth = 9.80665; // Earth's surface gravity
	//double gJupiter = 24.79; // Jupiter's surface gravity
	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, gEarth, delta);
	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, gEarth);
	RegularPendulum rpCoarse = new RegularPendulum (sLen, pMass, theta0, gEarth, .1);

	/*
	 * print out difference in displacement in 1 second intervals for 20 seconds
	 * then change the value of surface gravity from Earth's to Jupiter's
	 * and let pendulums continue swinging for another 20 seconds
	 */
	int iterations = (int) (1/delta);
	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
	for (int second = 1; second <= 40; second++) {
		if (second == 1) System.out.println ("\nwith gravity of Earth...");

		if (second == 21) {
			//sp = new SimplePendulum (sLen, pMass, theta0, gJupiter);
			//rp = new RegularPendulum (sLen, pMass, theta0, gJupiter, delta);
			sp.setGravityModel (gJupiter);
			rp.setGravityModel (gJupiter);
			rpCoarse.setGravityModel (gJupiter);
			System.out.println ("\nwith gravity of Jupiter...");
		}
		
	    for (int i = 0; i < iterations; i++) rp.step ();
	    for (int i = 0; i < 10; i++) rpCoarse.step ();
	    System.out.println ("t=" + second + "s: \t" +
				nf.format (Math.toDegrees (sp.getTheta (second)))
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
    }
}

