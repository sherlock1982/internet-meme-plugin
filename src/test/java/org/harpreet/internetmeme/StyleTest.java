package org.harpreet.internetmeme;

import hudson.model.Result;
import org.harpreet.internetmeme.Style;
import junit.framework.TestCase;

public class StyleTest extends TestCase {

	public void testGetWithFailureResultGivesBadAssStyle() {
		assertEquals(Style.BAD_BUILD, Style.get(Result.FAILURE));
	}

	public void testGetWithSuccessResultGivesSuitupStyle() {
		assertEquals(Style.GOOD_BUILD, Style.get(Result.SUCCESS));
	}

	public void testGetWithAbortedResultGivesAlertStyle() {
		assertEquals(Style.ALERT, Style.get(Result.ABORTED));
	}

	public void testGetWithNotBuiltResultGivesAlertStyle() {
		assertEquals(Style.ALERT, Style.get(Result.NOT_BUILT));
	}

	public void testGetWithUnstableResultGivesAlertStyle() {
		assertEquals(Style.ALERT, Style.get(Result.UNSTABLE));
	}
}
