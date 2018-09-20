package service;

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

	public Player loadPlayer(final String fileName) {
		String playerString = null;
		try {
			playerString = new String(Files.readAllBytes(Paths.get("docs/players/" + fileName)));
		} catch (Exception e) {
			System.out.println("Exception while parsing JSON: " + e);
		}
		System.out.println("playerString.length: " + playerString.length());

		ObjectMapper om = new ObjectMapper();
		Player player = null;
		try {
			player = om.readValue(playerString, Player.class);
		} catch (Exception e) {
			System.out.println("Error while mapping: " + e);
		}

		return player;
	}

	public Character loadCharacter(final String fileName) {
		String characterString = null;
		try {
			characterString = new String(Files.readAllBytes(Paths.get("docs/characters/" + fileName)));
		} catch (Exception e) {
			System.out.println("Exception while parsing JSON: " + e);
		}
		System.out.println("characterString.length: " + characterString.length());

		ObjectMapper om = new ObjectMapper();
		Character character = null;
		try {
			character = om.readValue(characterString, Character.class);
		} catch (Exception e) {
			System.out.println("Error while mapping: " + e);
		}

		return character;
	}

	public List<CharacterCard> loadDeck(final String fileName) {
		String deckString = null;
		try {
			deckString = new String(Files.readAllBytes(Paths.get("docs/decks/" + fileName)));
		} catch (Exception e) {
			System.out.println("Exception while parsing JSON: " + e);
		}
		System.out.println("characterString.length: " + deckString.length());

		ObjectMapper om = new ObjectMapper();
		List<CharacterCard> deck = null;
		try {
			deck = om.readValue(deckString, new TypeReference<List<CharacterCard>>() {
			});
		} catch (Exception e) {
			System.out.println("Error while mapping: " + e);
		}

		return deck;
	}
}
