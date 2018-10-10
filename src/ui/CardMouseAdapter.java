package ui;

import java.awt.event.MouseAdapter;

public class CardMouseAdapter extends MouseAdapter {

	int cardId;

	public CardMouseAdapter(final int cardId) {
		super();
		this.setCardId(cardId);
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(final int cardId) {
		this.cardId = cardId;
	}

}
