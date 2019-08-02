package com.stackroute.MuzixApplicationTask.repository;

import com.stackroute.MuzixApplicationTask.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Trackrepository that extends mongorepository
@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {
}
