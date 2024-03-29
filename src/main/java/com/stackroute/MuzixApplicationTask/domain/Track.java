package com.stackroute.MuzixApplicationTask.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Track {
    //Make id as primary key
    @Id
    //declare three variables for track information
    private int trackId;
    private String trackName;
    private String trackComment;
    //Empty constructor
    public Track()
    {

    }
    //parameterized constructor
    public Track(int trackId, String trackName, String trackComment) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackComment = trackComment;
    }
    //Getter and setter methods
    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackComment() {
        return trackComment;
    }

    public void setTrackComment(String trackComment) {
        this.trackComment = trackComment;
    }
    //Override the toString() method
    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", trackComment='" + trackComment + '\'' +
                '}';
    }
}