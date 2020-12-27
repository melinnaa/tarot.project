package tarot.project;

import static org.junit.Assert.*;

import org.junit.*;

public class CardTest {

	Card card; 
	Card card2;
	
	@Before
	public void setUp() {
		card = new Card("Lola", "fool", "une description quelconque", null);
		card2 = new Card("Jason", "fool", null, null);
		
	}
		
	@Test
	public void testGetName() {
		assertEquals(card.getName(),"Lola");
		assertFalse(card2.getName()=="Jake");
	}
	@Test
	public void testSetName() {
		card.setName("Toto");
		assertEquals(card.getName(), "Toto");
	}
	
	@Test
	public void isNameUsedTest() {
		assertTrue(Card.isNameUsed("Jason"));
		assertFalse(Card.isNameUsed("Pablo"));
	}
	
	@After 
	public void close() {
		card.deleteCard();
		card2.deleteCard();
	}
	
}
