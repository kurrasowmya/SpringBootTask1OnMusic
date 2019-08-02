package com.stackroute.MuzixApplicationTask;

import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.services.TrackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MuzixApplicationTaskApplication extends ServletInitializer  {

	//implemented methods
	TrackServices trackServices;
	@Autowired
	public void setMusicService(TrackServices trackServices) {
		this.trackServices= trackServices;
	}

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(MuzixApplicationTaskApplication.class);}

	public static void main(String[] args) {
		SpringApplication.run(MuzixApplicationTaskApplication.class, args);
	}

	//setting values to the music
	/*@Override
	public void run(String...args) throws Exception
		{
			try {
				trackServices.saveTrack(new Track(6, "chainsmokers", "Excellent"));
				trackServices.saveTrack(new Track(7, "one direction", "Excellent"));
				trackServices.saveTrack(new Track(8, "vidyavox", "Excellent"));
			}
			catch (TrackAlreadyExistsException ex) {
				ex.printStackTrace();
			}
		}*/

}
