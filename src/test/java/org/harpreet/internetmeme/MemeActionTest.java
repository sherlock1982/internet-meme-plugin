package org.harpreet.internetmeme;

import org.harpreet.internetmeme.MemeAction;
import org.harpreet.internetmeme.Style;
import junit.framework.TestCase;

public class MemeActionTest extends TestCase {

	private MemeAction action;

	public void setUp() {
		action = new MemeAction(Style.BAD_BUILD);
	}

	public void testAccessors() {
		assertEquals(Style.BAD_BUILD, action.getStyle());
		assertEquals("Internet Meme", action.getDisplayName());
	}
}
