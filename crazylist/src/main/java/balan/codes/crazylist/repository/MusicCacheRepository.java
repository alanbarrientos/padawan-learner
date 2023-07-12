package balan.codes.crazylist.repository;

import balan.codes.crazylist.model.MusicCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicCacheRepository extends JpaRepository<MusicCache, Integer> {

    MusicCache findMusicCacheBySpotifyId(String id);



}
