import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BishopStrategyTest {

	private BishopStrategy bishop;
	
	@Before
	public void setUp(){
		bishop = new BishopStrategy( null );
	}
	
	@Test
	public void testBishopA3toB4() {
		assertTrue( bishop.isPatternLegal('a', 3, 'b', 4, null) );
	}
	
	@Test
	public void testBishopA3toB2() {
		assertTrue( bishop.isPatternLegal('a', 3, 'b', 2, null) );
	}
	
	@Test
	public void testBishopD3toE2() {
		assertTrue( bishop.isPatternLegal('d', 3, 'e', 2, null) );
	}
	
	@Test
	public void testBishopD3toC2() {
		assertTrue( bishop.isPatternLegal('d', 3, 'c', 2, null) );
	}
	
	@Test
	public void testBishopD3toD4() {
		assertFalse( bishop.isPatternLegal('d', 3, 'd', 4, null) );
	}
	
	@Test
	public void testBishopD3toD2() {
		assertFalse( bishop.isPatternLegal('d', 3, 'd', 2, null) );
	}
	
	@Test
	public void testBishopD3toE3() {
		assertFalse( bishop.isPatternLegal('d', 3, 'e', 3, null) );
	}
	
	@Test
	public void testBishopD3toC3() {
		assertFalse( bishop.isPatternLegal('d', 3, 'c', 3, null) );
	}

}
