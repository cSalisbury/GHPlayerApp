package model;

public class BaseAction {
	private int experience;

	public BaseAction() {

	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(final int experience) {
		this.experience = experience;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(final boolean remove) {
		this.remove = remove;
	}

	@Override
	public String toString() {
		return "BaseAction [experience: " + this.experience + "]";
	}
}
