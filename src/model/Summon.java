package model;

public class Summon extends BaseAction {
	private int health;
	private int maxHealth;
	private int speed;
	private int power;
	private int range;
	private String text;

	public Summon() {

	}

	public int getHealth() {
		return health;
	}

	public void setHealth(final int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(final int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(final int speed) {
		this.speed = speed;
	}

	public int getPower() {
		return power;
	}

	public void setPower(final int power) {
		this.power = power;
	}

	public int getRange() {
		return range;
	}

	public void setRange(final int range) {
		this.range = range;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Summon [health: " + this.health + ",  text: " + this.text + ", maxHealth: " + this.maxHealth
				+ ", speed: " + this.speed + ", power: " + this.power + ", range: " + this.range + "]";
	}
}
