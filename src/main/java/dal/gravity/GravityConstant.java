package dal.gravity;

/*
 * Concrete Strategy
 */
public class GravityConstant implements GravityModel {
	private double g;
	
	public GravityConstant (double inG) {
		g = inG;
	}
	
	@Override
	public double getGravitationalField () {
		return g;
	}
}
