package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private Character character;
	private int numRetired;
	private List<CharacterCard> deck;
	private List<CharacterCard> hand;
	private List<CharacterCard> persist;
	private List<CharacterCard> discard;
	private List<CharacterCard> removed;
	// TODO: Should these be under Character? Maybe just conditions?
	// private List<Item> items;
	// private List<Condition> conditions;

	public Player() {

	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(final Character character) {
		this.character = character;
	}

	public int getNumRetired() {
		return numRetired;
	}

	public void setNumRetired(final int numRetired) {
		this.numRetired = numRetired;
	}

	public List<CharacterCard> getDeck() {
		return deck;
	}

	public void setDeck(final List<CharacterCard> deck) {
		this.deck = deck;
	}

	public List<CharacterCard> getHand() {
		if (hand == null) {
			hand = new ArrayList<CharacterCard>();
		}
		return hand;
	}

	public List<CharacterCard> getPersist() {
		if (persist == null) {
			persist = new ArrayList<CharacterCard>();
		}
		return persist;
	}

	public void setPersist(final List<CharacterCard> persist) {
		this.persist = persist;
	}

	public List<CharacterCard> getDiscard() {
		return discard;
	}

	public void setDiscard(final List<CharacterCard> discard) {
		this.discard = discard;
	}

	public List<CharacterCard> getRemoved() {
		return removed;
	}

	public void setRemoved(final List<CharacterCard> removed) {
		this.removed = removed;
	}

	@Override
	public String toString() {
		return "Player [name: " + this.name + ",  character: " + this.character + ",  numRetired: " + this.numRetired
				+ ",  deck: " + this.deck + ",  hand: " + this.hand + ",  persist: " + this.persist + ",  discard: "
				+ this.discard + ",  removed: " + this.removed + "]";
	}
}
