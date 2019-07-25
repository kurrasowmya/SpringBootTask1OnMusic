package com.stackroute.MuzixApplicationTask.controller;

import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.exception.TrackNotFoundException;
import com.stackroute.MuzixApplicationTask.services.TrackServices;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController //created restcontroller annotation
@RequestMapping(value = "api/v1") //set path as api/v1
@Api(value = "Music Application") //swagger api description
public class TrackController {
    //Autowire Trackservices
    @Autowired
    TrackServices trackServices;

    public TrackController(TrackServices trackServices) {
        this.trackServices = trackServices;
    }

    //api operation value
    @ApiOperation(value = "Add an track")
    //method to save the tracks
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveTrack(@ApiParam(value = "Track object store in database table", required = true) @Valid @RequestBody Track track) {

        ResponseEntity responseEntity;
        try {
            trackServices.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update a track")
    //method to update the track
    @PutMapping(value = "/update/{trackId}") //put mapping for updating tracks
    public ResponseEntity<?> updateTrack(@ApiParam(value = "track Id to update Music object", required = true) @PathVariable int trackId,
                                         @ApiParam(value = "Update music object", required = true) @Valid @RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackServices.updateTrack(track,trackId);
            responseEntity = new ResponseEntity<String>("Successfully updated", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "View a list of available tracks", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    //method to get alltracks
    @GetMapping(value = "/get")
    public ResponseEntity<?> getAllTracks(@ApiParam(value = "Track object store in database table", required = true) @Valid @RequestBody Track track) {
        return new ResponseEntity<List<Track>>(trackServices.getAllTracks(), HttpStatus.OK);
    }
    //method to delete trackbyid
    @DeleteMapping(value = "/delete/{trackId}")
    public ResponseEntity<?> deleteTrack(@ApiParam(value = "deleting row from table by trackId", required = true) @PathVariable int trackId) {
        ResponseEntity responseEntity;
        try {
            trackServices.deleteTrack(trackId);
            responseEntity = new ResponseEntity<String>("Succesfully deleted", HttpStatus.NO_CONTENT);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;

    }

    @ApiOperation(value = "View a list of available tracks by track name", response = ResponseEntity.class)
    //method to get the track name
    @GetMapping("/name/{trackName}")
    public ResponseEntity<?> getTrackByName(@ApiParam(value = "getting track by track name", required = true)@PathVariable String trackName) {
        ResponseEntity responseEntity;
        try {
            trackServices.findByTrackName(trackName);
            responseEntity=new ResponseEntity<List<Track>>(trackServices.getAllTracks(),HttpStatus.OK);
        }
        catch (TrackNotFoundException ex)
        {
            responseEntity= new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
