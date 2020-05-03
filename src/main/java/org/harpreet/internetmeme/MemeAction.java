/*
 * Copyright (c) 2009 Cliffano Subagio
 * Copyright (c) 2013 Harpreet Singh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.harpreet.internetmeme;

import hudson.model.Action;
import hudson.model.Run;
import jenkins.model.RunAction2;
import jenkins.tasks.SimpleBuildStep.LastBuildAction;

import java.util.Collection;
import java.util.Collections;

/**
 * {@link MemeAction} keeps the style and fact associated with the action.
 * @author cliffano
 * @author Harpreet Singh
 */
public final class MemeAction implements RunAction2, LastBuildAction {

    /**
     * The run
     */
    private transient Run<?, ?> mRun;

    /**
     * The style.
     */
    private final Style mStyle;

    /**
     * Constructs a MemeAction with specified style and fact.
     * @param style
     *            the style
     */
    public MemeAction(final Style style) {
        super();
        this.mStyle = style;
    }

    /**
     * Gets the action display name.
     * @return the display name
     */
    public String getDisplayName() {
        return "Internet Meme";
    }

    /**
     * This action doesn't provide any icon file.
     * @return null
     */
    public String getIconFileName() {
        return null;
    }

    /**
     * Gets the URL name for this action.
     * @return the URL name
     */
    public String getUrlName() {
        return "internetmeme";
    }

    /**
     * @return the style
     */
    public Style getStyle() {
        Style theStyle;
        if (mRun != null){
            theStyle = Style.get(mRun.getResult());
        } else {
            theStyle = mStyle;
        }
        return theStyle;
    }

    /*
     * @return the file name. The format is [good|bad]_build_number.png
     */
    public String getDisplayFileName() {
        String style = getStyle().toString().toLowerCase();
        int imageId = MemeDB.getRandomDBImageNumber();
        return style + "_" + imageId;
    }

    @Override
    public void onAttached(Run run) {
        this.mRun = run;
    }

    @Override
    public void onLoad(Run run) {
        this.mRun = run;
    }

    @Override
    public Collection<? extends Action> getProjectActions() {
        if (mRun != null) {
            return mRun.getParent().getLastCompletedBuild().getActions(MemeAction.class);
        } else {
            return Collections.singletonList(this);
        }
    }
}
