package model;

public class CardAction {
	private Attack attack;
	private Movement movement;
	private Loot loot;
	private Persist persist;
	private Summon summon;
	private Trap trap;
	private int experience;
	private String text;
	private String[] element;
	private boolean remain;
	private boolean remove;
	private boolean unrecoverable;

	public CardAction() {

	}

	public Attack getAttack() {
		return attack;
	}

	public void setAttack(final Attack attack) {
		this.attack = attack;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(final Movement movement) {
		this.movement = movement;
	}

	public Loot getLoot() {
		return loot;
	}

	public void setLoot(final Loot loot) {
		this.loot = loot;
	}

	public Persist getPersist() {
		return persist;
	}

	public void setPersist(final Persist persist) {
		this.persist = persist;
	}

	public Summon getSummon() {
		return summon;
	}

	public void setSummon(final Summon summon) {
		this.summon = summon;
	}

	public Trap getTrap() {
		return trap;
	}

	public void setTrap(final Trap trap) {
		this.trap = trap;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(final int experience) {
		this.experience = experience;
	}

	public String[] getElement() {
		return element;
	}

	public void setElement(final String[] element) {
		this.element = element;
	}

	public boolean isRemain() {
		boolean tempRemain = remain;
		if (!tempRemain) {
			if (this.persist != null && this.persist.getRemainTime() != 0) {
				tempRemain = true;
			}
		}

		return tempRemain;
	}

	public void setRemain(final boolean remain) {
		this.remain = remain;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(final boolean remove) {
		this.remove = remove;
	}

	public boolean isUnrecoverable() {
		return unrecoverable;
	}

	public void setUnrecoverable(final boolean unrecoverable) {
		this.unrecoverable = unrecoverable;
	}

	@Override
	public String toString() {
		return "CardAction [attack: " + this.attack + ",  movement: " + this.movement + ", loot: " + this.loot
				+ ", persist: " + this.persist + ", summon: " + this.summon + ", trap: " + this.trap + ", element: "
				+ this.element + ", text: " + this.text + ", remain: " + remain + ", remove: " + remove
				+ ", unrecoverable: " + unrecoverable + "]";
	}
}
