package org.harpreet.internetmeme;

import static org.mockito.Mockito.mock;
import hudson.model.AbstractProject;
import org.harpreet.internetmeme.MemeDescriptor;
import junit.framework.TestCase;

public class MemeDescriptorTest extends TestCase {

	private MemeDescriptor descriptor;

	public void setUp() {
		descriptor = new MemeDescriptor();
	}

	public void testGetDisplayName() {
		assertEquals("Activate Internet Meme", descriptor.getDisplayName());
	}

	public void testIsApplicableGivesTrue() {
		assertTrue(descriptor.isApplicable(mock(AbstractProject.class)
				.getClass()));
	}
}
