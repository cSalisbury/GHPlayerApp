package util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import model.Attack;
import model.CardAction;
import model.CharacterCard;
import model.Movement;

public class TestLoadAndSave {

	public static void main(final String[] args) {
		String cards = null;
		try {
			File file = new File("docs/test");
			file.createNewFile();
			cards = new String(Files.readAllBytes(Paths.get("docs/decks/Tinkerer Deck.json")));
		} catch (Exception e) {
			System.out.println("Exception while parsing JSON: " + e);
		}
		System.out.println("Cards.length: " + cards.length());

		ObjectMapper om = new ObjectMapper();
		List<CharacterCard> cardList = null;
		try {
			cardList = om.readValue(cards, new TypeReference<List<CharacterCard>>() {
			});
		} catch (Exception e) {
			System.out.println("Error while mapping: " + e);
		}

		System.out.println("cardList" + cardList);

	}

	public static void mainOld(final String[] args) {
		CharacterCard card = new CharacterCard();
		Attack top = new Attack();
		top.setRange(3);
		top.setPierce(1);
		top.setPower(3);
		CardAction caTop = new CardAction();
		caTop.setAttack(top);
		card.setTop(caTop);

		Movement bottom = new Movement();
		bottom.setFly(true);
		bottom.setRange(5);
		CardAction caBottom = new CardAction();
		caBottom.setMovement(bottom);
		caBottom.setRemove(true);
		caBottom.setExperience(1);
		card.setBottom(caBottom);

		System.out.println("Testing Writing");
		String json = "";
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			json = ow.writeValueAsString(card);
		} catch (Exception e) {
			System.out.println("Error while writing: " + e);
		}

		System.out.println("json: " + json);

		System.out.println("Testing Mapping");
		String inJson = "{\"name\" : \"Test Card\",\"level\" : 1,\"initiative\" : 45,\"topAction\" : {\"attack\" : {\"experience\" : 0,\"destroy\" : false,\"strength\" : 3,\"range\" : 3,\"targets\" : 0,\"pierce\" : 1,\"aoe\" : false,\"remain\" : false},\"movement\" : null,\"persist\" : null,\"initiative\" : null},\"bottomAction\" : {\"attack\" : null,\"movement\" : {\"experience\" : 1,\"destroy\" : true,\"range\" : 5,\"jump\" : false,\"fly\" : true},\"persist\" : null,\"initiative\" : null}}";

		ObjectMapper om = new ObjectMapper();
		CharacterCard card2 = null;
		try {
			card2 = om.readValue(inJson, CharacterCard.class);
		} catch (Exception e) {
			System.out.println("Error while mapping: " + e);
		}

		System.out.println("card2: " + card2);

	}
}
