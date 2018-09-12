package service;

import java.util.ArrayList;
import java.util.List;

import model.CharacterCard;
import model.Party;
import model.Player;

public class Game {
	public Game() {

	}

	public Party loadParty(final String fileName) {
		// TODO: implement loading
		return new Party();
	}

	// TODO: Not a lot of error checking, should be in layer above
	public void shuffle(final Player p, final int removeId) {
		int removeIndex = 0;
		if (removeId == -1) {
			removeIndex = (int) Math.floor(Math.random() * p.getDiscard().size());
		} else {
			for (int i = 0; i < p.getHand().size(); i++) {
				if (p.getHand().get(i).getId() == removeId) {
					removeIndex = i;
					break;
				}
			}
		}
		CharacterCard removed = p.getDiscard().remove(removeIndex);
		p.getHand().addAll(p.getDiscard());
		p.getDiscard().clear();
		p.getRemoved().add(removed);
	}

	public void blockHitHand(final Player p, final int removeId) {
		int removeIndex = 0;
		for (int i = 0; i < p.getHand().size(); i++) {
			if (p.getHand().get(i).getId() == removeId) {
				removeIndex = i;
				break;
			}
		}
		CharacterCard removed = p.getHand().remove(removeIndex);
		p.getRemoved().add(removed);
	}

	public void blockHitDiscard(final Player p, final List<Integer> removeIds) {
		List<CharacterCard> removeCards = new ArrayList<CharacterCard>();
		for (int i = 0; i < p.getHand().size(); i++) {
			for (int j = 0; j < removeIds.size(); j++) {
				if (p.getHand().get(i).getId() == removeIds.get(j)) {
					removeCards.add(p.getHand().get(i));
					break;
				}
			}
		}
		for (CharacterCard removeCard : removeCards) {
			p.getHand().remove(removeCard);
			p.getRemoved().add(removeCard);
		}
	}
}
