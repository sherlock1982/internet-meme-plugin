package org.harpreet.internetmeme;

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
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class MemeRecorderTest extends TestCase {

	private MemeRecorder recorder;

	@Override
	public void setUp() {
		recorder = new MemeRecorder();
	}

	public void testGetProjectActionWithNoLastBuildGivesNullAction() {
		AbstractProject<?, ?> mockProject = mock(AbstractProject.class);
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
		AbstractBuild<?, ?> mockBuild = mock(AbstractBuild.class);
		when(mockBuild.getResult()).thenReturn(Result.FAILURE);

		ArgumentCaptor<MemeAction> actionCaptor = ArgumentCaptor.forClass(MemeAction.class);
		doNothing().when(mockBuild).addAction(actionCaptor.capture());

		recorder.perform(mockBuild, mock(Launcher.class),
				mock(BuildListener.class));

		MemeAction action = actionCaptor.getValue();
		verify(mockBuild, times(1)).addAction(same(action));

		assertNotNull(action.getDisplayFileName());
		assertEquals(Style.BAD_BUILD, action.getStyle());
	}
}
