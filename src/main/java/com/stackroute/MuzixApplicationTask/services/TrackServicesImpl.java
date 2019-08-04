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
    public Track saveTrack(Track track) throws TrackAlreadyExistsException
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
        return trackuser;

    }


    //method to get all tracks
    @Override
    public List<Track> getAllTracks(){
        return trackrepository.findAll();
    }
    //method to delete track
    @Override
    public  Track deleteTrack(int trackId) throws TrackNotFoundException {
        if(!trackrepository.existsById(trackId)
           {
               throw new TrackNotFoundException();
           }
        Track trackdeleted=trackrepository.deleteById(trackId);
         return trackdeleted;

    }
    //method to update track by id
    @Override
    public Track updateTrack(Track track, int trackId) throws trackNotFoundException
    {
        Optional<Track> optionalMusic=trackrepository.findById(trackId);
        if(!optionalMusic.isPresent())
        {
            throw new TrackNotFoundException();
        }
        track.setTrackId(trackId);
        Track trackupdate=trackrepository.save(track);
        return trackupdate;
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



