package com.stackroute.MuzixApplicationTask.services;

import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.exception.TrackNotFoundException;

import java.util.List;

public interface TrackServices {
    //All the method declaration without having defination

    public void saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks();
    public void deleteTrack(int trackId) throws TrackNotFoundException;



    public boolean updateTrack(Track track,int trackId);
    public List<Track> findByTrackName(String trackName) throws TrackNotFoundException;



}
