package model;

import java.util.List;

public class Player {
	private String name;
	private Character character;
	private int numRetired;
	private List<CharacterCard> deck;
	private List<CharacterCard> hand;
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
		return hand;
	}

	public void setHand(final List<CharacterCard> hand) {
		this.hand = hand;
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
				+ ",  deck: " + this.deck + ",  hand: " + this.hand + ",  discard: " + this.discard + ",  removed: "
				+ this.removed + "]";
	}
}
