package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Character;
import model.CharacterCard;
import model.Player;

public class LoadService {

	public LoadService() {

	}

	public Player loadPlayer(final String filePath) throws IOException {
		String playerString = null;

		// playerString = new
		// String(Files.readAllBytes(Paths.get("docs/players/" + fileName)));
		System.out.println("About to use filePath: " + filePath);
		playerString = new String(Files.readAllBytes(Paths.get(filePath)));

		ObjectMapper om = new ObjectMapper();
		Player player = null;
		player = om.readValue(playerString, Player.class);

		return player;
	}

	public Character loadCharacter(final String filePath) throws IOException {
		String characterString = null;
		characterString = new String(Files.readAllBytes(Paths.get(filePath)));

		ObjectMapper om = new ObjectMapper();
		Character character = null;
		character = om.readValue(characterString, Character.class);

		return character;
	}

	public List<CharacterCard> loadDeck(final String filePath) throws IOException {
		String deckString = null;
		deckString = new String(Files.readAllBytes(Paths.get(filePath)));

		ObjectMapper om = new ObjectMapper();
		List<CharacterCard> deck = null;
		deck = om.readValue(deckString, new TypeReference<List<CharacterCard>>() {
		});

		return deck;
	}
}
