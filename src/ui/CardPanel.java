package ui;

import javax.swing.JPanel;

public class CardPanel extends JPanel {

	int cardId;
	boolean img;

	public int getCardId() {
		return cardId;
	}

	public void setCardId(final int cardId) {
		this.cardId = cardId;
	}

	public boolean isImg() {
		return img;
	}

	public void setImg(final boolean img) {
		this.img = img;
	}

}
