package model;

public class BattleCard {
	private int id;
	private Integer modifier;
	private int multiplier;
	private int push;
	private int pull;
	private int pierce;
	private int target;
	private int shield;
	private int heal;
	private String text;
	private String[] condition;
	private String[] element;
	private boolean shuffle;
	private boolean repeat;
	private boolean remove;

	public BattleCard() {

	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(final Integer modifier) {
		this.modifier = modifier;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(final int multiplier) {
		this.multiplier = multiplier;
	}

	public int getPush() {
		return push;
	}

	public void setPush(final int push) {
		this.push = push;
	}

	public int getPull() {
		return pull;
	}

	public void setPull(final int pull) {
		this.pull = pull;
	}

	public int getPierce() {
		return pierce;
	}

	public void setPierce(final int pierce) {
		this.pierce = pierce;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(final int target) {
		this.target = target;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(final int shield) {
		this.shield = shield;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public int getHeal() {
		return heal;
	}

	public void setHeal(final int heal) {
		this.heal = heal;
	}

	public String[] getCondition() {
		return condition;
	}

	public void setCondition(final String[] condition) {
		this.condition = condition;
	}

	public String[] getElement() {
		return element;
	}

	public void setElement(final String[] element) {
		this.element = element;
	}

	public boolean isShuffle() {
		return shuffle;
	}

	public void setShuffle(final boolean shuffle) {
		this.shuffle = shuffle;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(final boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(final boolean remove) {
		this.remove = remove;
	}

	@Override
	public String toString() {
		return "BattleCard [id: " + this.id + ", modifier: " + this.modifier + ", multiplier: " + this.multiplier
				+ ",  push: " + this.push + ", pull: " + this.pull + ", pierce: " + this.pierce + ", target: "
				+ this.target + ", shield: " + this.shield + ", heal: " + this.heal + ", condition: " + this.condition
				+ ", element: " + this.element + ", text: " + this.text + ", shuffle: " + shuffle + ", repeat: "
				+ repeat + "]";
	}
}
