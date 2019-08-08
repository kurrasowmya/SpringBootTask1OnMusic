package com.stackroute.MuzixApplicationTask.controller;

import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.services.TrackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    @Autowired
    //Object for TrackServices
    private TrackServices trackServices;
    @Autowired
    private ResponseEntity responseEntity;
    //set the value for trackservices using constructor
    public TrackController(TrackServices trackServices) {
        this.trackServices = trackServices;
    }
    //Save the Track details
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        
        try {
            
            responseEntity = new ResponseEntity<Track>(trackServices.saveTrack(track), HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //retrieve the track details
    @GetMapping(value="/get")
    public ResponseEntity<?> getAllTracks(@RequestBody Track track){

        return new ResponseEntity<List<Track>>(trackServices.getAllTracks(),HttpStatus.OK);
    }
    //Update the track details
    @PutMapping(value="/update")
    public ResponseEntity<?> updateTrack(@RequestBody Track track){
     
        try
        {
            responseEntity=new ResponseEntity<Track>(trackServices.saveTrack(track), HttpStatus.CREATED);
        }
        catch (Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //Delete the Track details for given trackid
    @DeleteMapping(value = "/delete/{trackId}")
    public ResponseEntity<?> deleteTrack(@RequestBody int trackId)
    {
       
        try
        {
            
            responseEntity=new ResponseEntity<Track >(trackServices.deleteTrack(trackId),HttpStatus.NO_CONTENT);
        }
        catch (Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
