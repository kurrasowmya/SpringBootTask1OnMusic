package com.stackroute.MuzixApplicationTask.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.exception.TrackNotFoundException;
import com.stackroute.MuzixApplicationTask.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServicesImpl implements TrackServices {

    TrackRepository trackrepository;
    RestTemplate resttemplate = new RestTemplate();


    @Autowired
    public TrackServicesImpl(TrackRepository trackRepository) {
        this.trackrepository = trackRepository;
    }

    //method to save track
    @Override
    public boolean saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackrepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException();
        }
        Track trackuser = trackrepository.save(track);
        if (trackuser == null) {
            throw new TrackAlreadyExistsException();
        }
        else {
            return true;
        }

    }

    @Override
    public String getTopTracks() {

        final String Root_URL = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=50413fa749a11104ddf05f7d2e68050d&format=json";
        String toptracks = resttemplate.getForObject(Root_URL, String.class);



            ObjectMapper mapper = new ObjectMapper();



            try {
//            converting string as a json node
                JsonNode rootNode = mapper.readTree(toptracks);
                ArrayNode arrayNode =  (ArrayNode)rootNode.path("tracks").path("track");
                //iterate the JSON array
                for (int i = 0; i < arrayNode.size(); i++) {
                    //get a new Track object and fill it with data using setters
                    Track track = new Track();
                    track.setTrackId(i);
                    track.setTrackName(arrayNode.get(i).path("name").asText());
                    track.setTrackComment(arrayNode.get(i).path("artist").path("name").asText());
                    //save the track to database
                    trackrepository.save(track);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        return toptracks;

    }


    //method to get all tracks
    @Override
    public List<Track> getAllTracks() {
        return trackrepository.findAll();
    }

    //method to delete track
    @Override
    public boolean deleteTrack(int trackId) {

        trackrepository.deleteById(trackId);
        return true;


    }

    //method to update track by id
    @Override
    public boolean updateTrack(Track track, int trackId) {
        Optional<Track> optionalMusic = trackrepository.findById(trackId);
        if (!optionalMusic.isPresent())
            return false;

        track.setTrackId(trackId);
        trackrepository.save(track);
        return true;
    }

    @Override
    public List<Track> findByTrackName(String trackname) throws TrackNotFoundException {
        List<Track> track = trackrepository.findByTrackName(trackname);
        if (track.isEmpty()) {
            throw new TrackNotFoundException();
        } else {
            return track;
        }


    }
}

