package com.stackroute.MuzixApplicationTask.servicetest;


import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.repository.TrackRepository;
import com.stackroute.MuzixApplicationTask.services.TrackServicesImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

    //Test cases for TrackService methods
    @RunWith(SpringRunner.class)
    public class ServiceTest {

        //Create a mock for UserRepository
        @Mock
        TrackRepository trackRepository;

        //Inject the mocks as dependencies into UserServiceImpl
        @InjectMocks
        TrackServicesImpl trackService;

        Track track;
        List<Track> list= null;

        @Before
        public void setUp(){
            //Initialising the mock object
            MockitoAnnotations.initMocks(this);
            track = new Track(101,"aaa","good");
            list = new ArrayList<>();
            list.add(track);
        }

        @After
        public void tearDown() {
            track = null;
            list = null;
        }

        // Test for getAllTracks service method
        @Test
        public void getAllTracks() {

            trackRepository.save(track);
            //stubbing the mock to return specific data
            when(trackRepository.findAll()).thenReturn(list);
            List<Track> userlist = trackService.getAllTracks();
            Assert.assertEquals(list,userlist);
        }

        // Test for saveTrack service method
        @Test
        public void saveTrack() throws TrackAlreadyExistsException {
            when(trackRepository.save(track)).thenReturn(track);
            Assert.assertEquals(true,trackService.saveTrack(track));

            //verify here verifies that trackRepository save method is only called once
            verify(trackRepository,times(1)).save(track);

        }


        // Test for deleteTrack service method
       @Test
        public void deleteTrack() throws TrackAlreadyExistsException {
            Track track1 = new Track(102,"dbb","hhd");
            trackService.saveTrack(track);
            trackService.saveTrack(track1);
            Assert.assertTrue(trackService.deleteTrack(102));

            //verify here verifies that trackRepository deleteById method is only called once
            verify(trackRepository,times(1)).deleteById(track1.getTrackId());
        }

        // Test for updateTrack service method
       @Test
        public void updateTrack() {
            when(trackRepository.save(track)).thenReturn(track);
            //when(trackRepository.existsById(track.getTrackId()).
            when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
            track.setTrackComment("worst");
            Assert.assertTrue(trackService.updateTrack(track,102));
            //verify here verifies that trackRepository save method is only called once
            verify(trackRepository,times(1)).save(track);
        }
    }


