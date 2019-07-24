package com.stackroute.MuzixApplicationTask.repository;

import com.stackroute.MuzixApplicationTask.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Trackrepository that extends jparepository
@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
    @Query("SELECT p FROM Track p WHERE p.trackName like ?1")
    List<Track> findByTrackName(String trackname);
}

