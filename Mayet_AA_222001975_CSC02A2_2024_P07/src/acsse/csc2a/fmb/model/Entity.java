package acsse.csc2a.fmb.model;

import acsse.csc2a.fmb.pattern.AbstractVisitable;

/**
 * An Immutable class to Provide information about an entity
 */
public abstract class Entity implements AbstractVisitable {
	private final int xLocation;
	private final double angle;

	/**
	 * @param xLocation The xLocation of the entity
	 * @param angle The angle the entity is facing
	 */
	public Entity(int xLocation, double angle) {
		this.xLocation = xLocation;
		this.angle = angle;
	}

	/**
	 * @return The X-Location of the entity
	 */
	public int getXLocation() {
		return xLocation;
	}

	/**
	 * @return The rotation Angle of the entity
	 */
	public double getAngle() {
		return angle;
	}

	@Override
	public String toString() {
		return String.format("X-Location: %s\nAngle: %s\n", xLocation, angle);
	}
}