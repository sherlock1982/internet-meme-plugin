package org.harpreet.internetmeme;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.Build;
import hudson.model.BuildListener;
import hudson.model.Result;

import java.util.ArrayList;
import java.util.List;

import org.harpreet.internetmeme.MemeAction;
import org.harpreet.internetmeme.MemeRecorder;
import org.harpreet.internetmeme.Style;

import junit.framework.TestCase;

public class MemeRecorderTest extends TestCase {

	private MemeRecorder recorder;

	@Override
	public void setUp() {
		recorder = new MemeRecorder();
	}

	public void testGetProjectActionWithNoLastBuildGivesNullAction() {
		AbstractProject mockProject = mock(AbstractProject.class);
		when(mockProject.getLastBuild()).thenReturn(null);
		assertNull(recorder.getProjectAction(mockProject));
	}

	public void testGetProjectActionHavingLastBuildGivesMemeAction() {
		AbstractProject mockProject = mock(AbstractProject.class);
		Build mockBuild = mock(Build.class);

		when(mockProject.getLastBuild()).thenReturn(mockBuild);
		when(mockBuild.getResult()).thenReturn(Result.SUCCESS);

		Action action = recorder.getProjectAction(mockProject);

		assertTrue(action instanceof MemeAction);
		assertEquals(Style.GOOD_BUILD, ((MemeAction) action).getStyle());
	}

	public void testPerformWithFailureResultAddsMemeActionWithBadAssStyleAndExpectedFact()
			throws Exception {
		List<Action> actions = new ArrayList<Action>();
		AbstractBuild mockBuild = mock(AbstractBuild.class);
		when(mockBuild.getResult()).thenReturn(Result.FAILURE);
		when(mockBuild.getActions()).thenReturn(actions);

		assertEquals(0, actions.size());

		recorder.perform(mockBuild, mock(Launcher.class),
				mock(BuildListener.class));

		assertEquals(1, actions.size());
		assertTrue(actions.get(0) instanceof MemeAction);
		assertEquals(Style.BAD_BUILD, ((MemeAction) actions.get(0))
				.getStyle());
	}
}
