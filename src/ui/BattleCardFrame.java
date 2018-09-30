package ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.BattleCard;
import util.DrawCards;

public class BattleCardFrame extends JFrame {

	final JScrollPane cardScrollPane = new JScrollPane();
	final JPanel cardPanel = new JPanel();

	List<BattleCard> cards;

	public BattleCardFrame(final List<BattleCard> cs) {
		cards = cs;

		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (cards.size() == 1) {
			this.setTitle("Drawn Cards");
		} else {
			this.setTitle("Drawn Card");
		}

		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(cardScrollPane);
		Dimension d = new Dimension(500, 500);
		cardScrollPane.setPreferredSize(d);
		cardScrollPane.setViewportView(cardPanel);

		for (BattleCard bc : cards) {
			cardPanel.add(DrawCards.createBattleCardPanel(bc));
		}
	}
}
