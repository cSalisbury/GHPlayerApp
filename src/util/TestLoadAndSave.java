package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import model.Attack;
import model.CardAction;
import model.CharacterCard;
import model.Movement;

public class TestLoadAndSave {

	public static void main(final String[] args) {
		CharacterCard card = new CharacterCard();
		Attack top = new Attack();
		top.setRange(3);
		top.setPierce(1);
		top.setStrength(3);
		CardAction caTop = new CardAction();
		caTop.setAttack(top);
		card.setTopAction(caTop);

		Movement bottom = new Movement();
		bottom.setFly(true);
		bottom.setDestroy(true);
		bottom.setRange(5);
		bottom.setExperience(1);
		CardAction caBottom = new CardAction();
		caBottom.setMovement(bottom);
		card.setBottomAction(caBottom);

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
