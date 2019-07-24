package com.stackroute.MuzixApplicationTask.services;

import com.stackroute.MuzixApplicationTask.domain.Track;

import java.util.List;

public interface TrackServices {
    //All the method declaration without having defination

    public void saveTrack(Track track);

    public List<Track> getAllTracks();
    public void deleteTrack(int trackId);


    public boolean updateTrack(Track track,int trackId);


}
