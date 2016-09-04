package com.jacaranda.myscrum.data.model;

/**
 * Created by Gaby on 03/09/2016.
 */
public class OutStoryXSprint {
    public static final String TAG = OutStoryXSprint.class.getSimpleName();
    public static final String TABLE = "outStoryXSprint";

    // Labels Table Columns names
    public static final String KEY_Story_idStory = "Story_idStory";
    public static final String KEY_SprintXRelease_idSprintXRelease = "SprintXRelease_idSprintXRelease";

    private int Story_idStory;
    private int SprintXRelease_idSprintXRelease;

    // Setters & Getters

    public int getStory_idStory() {
        return Story_idStory;
    }

    public void setStory_idStory(int story_idStory) {
        Story_idStory = story_idStory;
    }

    public int getSprintXRelease_idSprintXRelease() {
        return SprintXRelease_idSprintXRelease;
    }

    public void setSprintXRelease_idSprintXRelease(int sprintXRelease_idSprintXRelease) {
        SprintXRelease_idSprintXRelease = sprintXRelease_idSprintXRelease;
    }
}
