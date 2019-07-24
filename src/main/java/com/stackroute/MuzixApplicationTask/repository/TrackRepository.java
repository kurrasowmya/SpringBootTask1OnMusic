package com.stackroute.MuzixApplicationTask.repository;

import com.stackroute.MuzixApplicationTask.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Trackrepository that extends jparepository
@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
}
