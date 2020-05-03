package org.harpreet.internetmeme.pipeline;

import org.harpreet.internetmeme.MemeRecorder;
import org.jenkinsci.plugins.workflow.steps.*;

import hudson.model.Run;
import hudson.model.TaskListener;

import javax.annotation.Nonnull;

public class MemeStepExecution extends SynchronousStepExecution<Void> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	protected MemeStepExecution(@Nonnull StepContext context) {
		super(context);
	}

	@Override
	protected Void run() throws Exception {
		TaskListener listener = getContext().get(TaskListener.class);
		Run<?, ?> run = getContext().get(Run.class);
		if (run == null || listener == null){
			return null;
		}

		listener.getLogger().println("Activating Internet Meme");
		
		MemeRecorder recorder = new MemeRecorder();
		recorder.perform(run);
		
		return null;
	}

}
