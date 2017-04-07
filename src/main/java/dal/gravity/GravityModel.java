package dal.gravity;

/*
 * Strategy interface for replacing g, the gravity field.
 */
public interface GravityModel {
	public double getGravitationalField ();
}
