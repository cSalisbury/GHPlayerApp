package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.BattleCard;
import model.Character;
import model.CharacterCard;
import model.Player;

public class LoadSaveService {

	public LoadSaveService() {

	}

	public void savePlayer(final String filePath, final Player player)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();

		System.out.println("Saving Player: " + player + " at: " + filePath);
		om.writeValue(new File(filePath), player);
	}

	public Player loadPlayer(final String filePath) throws IOException {
		String playerString = null;
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

	public List<BattleCard> loadBattleDeck(final String filePath) throws IOException {
		String battleDeckString = null;
		battleDeckString = new String(Files.readAllBytes(Paths.get(filePath)));

		ObjectMapper om = new ObjectMapper();
		List<BattleCard> battleDeck = null;
		battleDeck = om.readValue(battleDeckString, new TypeReference<List<BattleCard>>() {
		});

		return battleDeck;
	}
}
