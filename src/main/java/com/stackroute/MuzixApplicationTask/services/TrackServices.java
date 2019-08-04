package com.stackroute.MuzixApplicationTask.services;

import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.exception.TrackNotFoundException;
import java.util.List;

public interface TrackServices
{
    //All the method declaration without haviing function body
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks();
    public Track deleteTrack(int trackId) throws TrackNotFoundException;
    public Track updateTrack(Track track,int trackId) throws TrackNotFoundException;
    public List<Track> findByTrackName(String trackName) throws TrackNotFoundException;
}
