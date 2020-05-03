package org.harpreet.internetmeme.pipeline;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableSet;
import hudson.model.Run;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

import java.util.Set;

public class MemeStep extends Step {

	@DataBoundConstructor
	public MemeStep() {
	}

	@Override
	public StepExecution start(StepContext stepContext) {
		return new MemeStepExecution(stepContext);
	}

	@SuppressWarnings("unused")
	@Extension
	public static class DescriptorImpl extends StepDescriptor {
		@Override
		public Set<? extends Class<?>> getRequiredContext() {
			return ImmutableSet.of(Run.class, TaskListener.class);
		}

		@Override
		public String getFunctionName() {
			return "internetMeme";
		}

		@Nonnull
		@Override
		public String getDisplayName() {
			return "Activate Internet Meme";
		}
	}
}