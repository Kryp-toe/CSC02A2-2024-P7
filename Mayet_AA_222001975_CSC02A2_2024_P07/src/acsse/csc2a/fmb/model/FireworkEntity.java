package acsse.csc2a.fmb.model;

import acsse.csc2a.fmb.pattern.AbstractVisitor;

public class FireworkEntity extends Entity{
	private final Firework firework;
	
	/**
	 * @param xLocation
	 * @param angle
	 * @param firework
	 */
	public FireworkEntity(int xLocation, double angle, Firework firework) {
		super(xLocation, angle);
		this.firework = firework;
	}
	
	/**
	 * @return
	 */
	public final Firework getFirework()
	{
		return firework;
	}
	
	@Override
	public String toString() {
		return String.format("%sFirework:\n%s\n", super.toString(), firework);

	}

	@Override
	public void accept(AbstractVisitor visitor) {
		visitor.visit(this);
	}
}