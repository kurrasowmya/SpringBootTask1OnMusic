package com.stackroute.MuzixApplicationTask.services;

import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.exception.TrackNotFoundException;
import com.stackroute.MuzixApplicationTask.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServicesImpl implements TrackServices {

    TrackRepository trackrepository;
    @Autowired
    public TrackServicesImpl(TrackRepository trackRepository)
    {
        this.trackrepository=trackRepository;
    }
    //method to save track
    @Override
    public void saveTrack(Track track) throws TrackAlreadyExistsException
    {
        if(trackrepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistsException();
        }
        Track trackuser=trackrepository.save(track);
        if(trackuser==null)
        {
            throw new TrackAlreadyExistsException();
        }

    }


    //method to get all tracks
    @Override
    public List<Track> getAllTracks(){
        return trackrepository.findAll();
    }
    //method to delete track
    @Override
    public  void deleteTrack(int trackId)  {
        trackrepository.deleteById(trackId);

    }
    //method to update track by id
    @Override
    public boolean updateTrack(Track track, int trackId)
    {
        Optional<Track> optionalMusic=trackrepository.findById(trackId);
        if(!optionalMusic.isPresent())
            return false;

        track.setTrackId(trackId);
        trackrepository.save(track);
        return true;
    }
    @Override
    public List<Track> findByTrackName(String trackname) throws TrackNotFoundException
    {
        List<Track> track= trackrepository.findByTrackName(trackname);
        if(track.isEmpty())
        {
            throw new TrackNotFoundException();
        }
        else {
           return track;
        }


    }




}

