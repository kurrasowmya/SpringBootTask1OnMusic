package com.stackroute.MuzixApplicationTask.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection= "track")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    //Make id as primary key
    @Id
    //declare three variables for track information
    private int trackId;
    private String trackName;
    private String trackComment;
}
