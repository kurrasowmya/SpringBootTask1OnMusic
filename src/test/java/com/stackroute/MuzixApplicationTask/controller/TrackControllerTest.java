package com.stackroute.MuzixApplicationTask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.services.TrackServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Track track=new Track();
    @MockBean
    private TrackServices trackServices;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();


        track.setTrackId(101);
        track.setTrackName("Jonny123");
        track.setTrackComment("bhgfyfytruyt");
        track.setTrackId(102);
        track.setTrackName("pinky");
        track.setTrackComment("bhvhgyt");
        list = new ArrayList();

        list.add(track);
    }

    @Test
    public void savetrack() throws Exception {
        when(trackServices.saveTrack(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(status().isCreated())
                .andDo(print());


    }
    @Test
    public void savetrackFailure() throws Exception {
        when(trackServices.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(status().isConflict())
                .andDo(print());
    }

   @Test
    public void getAlltrack() throws Exception {
        when(trackServices.getAllTracks()).thenReturn(list);
        mockMvc.perform(get("/api/v1/get")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    public void deleteTrackTest() throws Exception{



        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete/101").accept(MediaType.APPLICATION_JSON))
                .andDo(print());


    }
    @Test
    public void updateTrackTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/update/102")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"trackid\": \"102\", \"trackName\" : \"New ToDo Text\", \"trackComment\" : \"false\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void getTrackByNameTest() throws Exception{

            when(trackServices.findByTrackName(track.getTrackName())).thenReturn(list);
            mockMvc.perform(get("/api/v1/name/jonny123")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());

        }



    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }










}