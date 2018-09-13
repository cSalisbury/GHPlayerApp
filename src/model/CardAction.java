package model;

public class CardAction {
	private Attack attack;
	private Movement movement;
	private Loot loot;
	private Persist persist;
	// private Summon summon;
	// private Trap trap
	private String text;
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

	public Loot getInitiative() {
		return loot;
	}

	public void setInitiative(final Loot loot) {
		this.loot = loot;
	}

	public Persist getPersist() {
		return persist;
	}

	public void setPersist(final Persist persist) {
		this.persist = persist;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public boolean isRemain() {
		return remain;
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
				+ ", persist: " + this.persist + ", text: " + this.text + ", remain: " + remain + ", remove: " + remove
				+ ", unrecoverable: " + unrecoverable + "]";
	}
}
