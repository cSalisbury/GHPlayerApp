package model;

public class BaseAction {
	private int experience;
	private boolean destroy;

	public BaseAction() {

	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(final int experience) {
		this.experience = experience;
	}

	public boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(final boolean destroy) {
		this.destroy = destroy;
	}

	@Override
	public String toString() {
		return "BaseAction [experience: " + this.experience + ", is destroy: " + this.destroy + "]";
	}
}
