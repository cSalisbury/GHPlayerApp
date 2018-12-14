package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.BattleTactic;
import model.Player;
import util.DrawCards;

public class BattleTacticsFrame extends JFrame {

	final JPanel tacticsPanel = new JPanel();
	final JPanel controlPanel = new JPanel();

	CardPanel tPanel1;
	CardPanel tPanel2;

	final JLabel tacticTitleLbl = new JLabel("Chosen Tactic:");
	final JTextField tacticTextField = new JTextField("1");
	final JButton tacticPickerBut = new JButton("Choose");

	Player player;
	BattleTactic battleTactic1;
	BattleTactic battleTactic2;

	// Viewing Tactic
	public BattleTacticsFrame(final BattleTactic bt) {
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.add(Box.createVerticalGlue());
		CardPanel btPanel = DrawCards.createBattleTacticPanel(bt);
		this.add(btPanel);
		this.add(Box.createVerticalGlue());
	}

	// Choosing Tactic
	public BattleTacticsFrame(final Player p, final BattleTactic bt1, final BattleTactic bt2) {
		player = p;
		battleTactic1 = bt1;
		battleTactic2 = bt2;

		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.add(Box.createVerticalGlue());
		this.add(tacticsPanel);
		this.add(Box.createVerticalGlue());
		this.add(controlPanel);
		this.add(Box.createVerticalGlue());

		// draw two tactic panels, then add to tacticsPanel
		tacticsPanel.setLayout(new BoxLayout(tacticsPanel, BoxLayout.X_AXIS));
		tacticsPanel.add(Box.createHorizontalGlue());
		tPanel1 = DrawCards.createBattleTacticPanel(bt1);
		tacticsPanel.add(tPanel1);
		tacticsPanel.add(Box.createHorizontalGlue());
		tPanel2 = DrawCards.createBattleTacticPanel(bt2);
		tacticsPanel.add(tPanel2);
		tacticsPanel.add(Box.createHorizontalGlue());

		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.setSize(1000, 50);
		controlPanel.add(Box.createHorizontalGlue());
		controlPanel.add(tacticTitleLbl);
		controlPanel.add(tacticTextField);
		controlPanel.add(Box.createHorizontalGlue());
		controlPanel.add(tacticPickerBut);
		controlPanel.add(Box.createHorizontalGlue());

		tacticPickerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				tacticPickerButtonAction();
			}
		});
	}

	private void tacticPickerButtonAction() {
		String chosenTactic = tacticTextField.getText();

		Integer chosenTacticId = null;
		try {
			chosenTacticId = Integer.parseInt(chosenTactic.trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The chosen tactic id: " + chosenTactic + " is not a number");
			return;
		}
		if (chosenTacticId == battleTactic1.getId()) {
			player.getCharacter().setTactic(battleTactic1);
		} else if (chosenTacticId == battleTactic2.getId()) {
			player.getCharacter().setTactic(battleTactic2);
		} else {
			JOptionPane.showMessageDialog(this,
					"The chosen tactic id: " + chosenTactic + " is not one of the displayed options");
			return;
		}

		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
