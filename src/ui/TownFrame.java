package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Player;

public class TownFrame extends JFrame {

	final JButton editPlayerNameBut = new JButton("(edit)");
	final JLabel playerNameTitleLbl = new JLabel("Player:");
	final JLabel playerNameLbl = new JLabel("[name]");
	final JButton editCharacterNameBut = new JButton("(edit)");
	final JLabel characterNameTitleLbl = new JLabel("Character:");
	final JLabel characterNameLbl = new JLabel("[name]");

	final JButton editTotalGoldBut = new JButton("(edit)");
	final JLabel totalGoldTitleLbl = new JLabel("Gold:");
	final JLabel totalGoldLbl = new JLabel("[amount]");
	final JButton editTotalXPBut = new JButton("(edit)");
	final JLabel totalXPTitleLbl = new JLabel("XP:");
	final JLabel totalXPLbl = new JLabel("[amount]");
	final JLabel xpToNextLevelTitleLbl = new JLabel("XP to next Level:");
	final JLabel xpToNextLevelLbl = new JLabel("[amount]");

	final JButton retireCharacterBut = new JButton("Retire Character");
	final JButton buySellBut = new JButton("Buy/Sell Items");

	Player player;

	public TownFrame(final Player p) {
		player = p;

		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(Box.createHorizontalGlue());
		namePanel.add(editPlayerNameBut);
		namePanel.add(playerNameTitleLbl);
		namePanel.add(playerNameLbl);
		namePanel.add(Box.createHorizontalGlue());
		namePanel.add(editCharacterNameBut);
		namePanel.add(characterNameTitleLbl);
		namePanel.add(characterNameLbl);
		namePanel.add(Box.createHorizontalGlue());

		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.X_AXIS));
		totalPanel.add(Box.createHorizontalGlue());
		totalPanel.add(editTotalGoldBut);
		totalPanel.add(totalGoldTitleLbl);
		totalPanel.add(totalGoldLbl);
		totalPanel.add(Box.createHorizontalGlue());
		totalPanel.add(editTotalXPBut);
		totalPanel.add(totalXPTitleLbl);
		totalPanel.add(totalXPLbl);
		totalPanel.add(Box.createHorizontalGlue());
		totalPanel.add(xpToNextLevelTitleLbl);
		totalPanel.add(xpToNextLevelLbl);
		totalPanel.add(Box.createHorizontalGlue());

		JPanel otherPanel = new JPanel();
		otherPanel.setLayout(new BoxLayout(otherPanel, BoxLayout.X_AXIS));
		otherPanel.add(Box.createHorizontalGlue());
		otherPanel.add(retireCharacterBut);
		otherPanel.add(Box.createHorizontalGlue());
		otherPanel.add(buySellBut);
		otherPanel.add(Box.createHorizontalGlue());

		this.add(Box.createVerticalGlue());
		this.add(namePanel);
		this.add(Box.createVerticalGlue());
		this.add(totalPanel);
		this.add(Box.createVerticalGlue());
		this.add(otherPanel);
		this.add(Box.createVerticalGlue());

		editPlayerNameBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				editPlayerNameButtonAction();
			}
		});
		editCharacterNameBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				editCharacterNameButtonAction();
			}
		});
		editTotalGoldBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				editTotalGoldButtonAction();
			}
		});
		editTotalXPBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				editTotalXPButtonAction();
			}
		});
		retireCharacterBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				retireCharacterButtonAction();
			}
		});
		buySellBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				buySellButtonAction();
			}
		});

		refresh();
	}

	private void editPlayerNameButtonAction() {
		String pName = JOptionPane.showInputDialog(this, "Enter the Player's Name", player.getName());
		player.setName(pName);
		refresh();

	}

	private void editCharacterNameButtonAction() {
		String cName = JOptionPane.showInputDialog(this, "Enter the Character's Name", player.getCharacter().getName());
		player.getCharacter().setName(cName);
		refresh();
	}

	private void editTotalGoldButtonAction() {
		try {
			String tGold = JOptionPane.showInputDialog(this,
					"Enter " + player.getCharacter().getName() + "'s Total Gold", player.getCharacter().getTotalGold());
			player.getCharacter().setTotalGold(Integer.parseInt(tGold.trim()));
			refresh();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid Integer for Total Experience");
		}
	}

	private void editTotalXPButtonAction() {
		try {
			String tXP = JOptionPane.showInputDialog(this,
					"Enter " + player.getCharacter().getName() + "'s Total Experience",
					player.getCharacter().getTotalExperience());
			player.getCharacter().setTotalExperience(Integer.parseInt(tXP.trim()));
			refresh();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid Integer for Total Experience");
		}
	}

	private void retireCharacterButtonAction() {
		JOptionPane.showMessageDialog(this, "Coming Soon");
	}

	private void buySellButtonAction() {
		JOptionPane.showMessageDialog(this, "Coming Soon");
	}

	private void refresh() {
		playerNameLbl.setText(player.getName());
		characterNameLbl.setText(player.getCharacter().getName());
		totalGoldLbl.setText(Integer.toString(player.getCharacter().getTotalGold()));
		totalXPLbl.setText(Integer.toString(player.getCharacter().getTotalExperience()));
		xpToNextLevelLbl.setText(player.getCharacter().experienceToNextLevel());
	}

}
