import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapMusicLibrary implements MusicLibrary {
    private HashMap<String, Song> songs = new HashMap<>();

    public HashMapMusicLibrary() {}

    @Override
    public boolean addSong(Song song) {
        String key = song.getTitle().toLowerCase();
        if (songs.containsKey(key)) 
            return false;
        songs.put(key, song);
        return true;
    }

    @Override
    public boolean removeSong(String title) {
        return songs.remove(title.toLowerCase()) != null;
    }

    @Override
    public Song searchSong(String title) {
        return songs.get(title.toLowerCase());
    }

    @Override
    public List<Song> getAllSongs() { 
        return new ArrayList<>(songs.values());
    }

    @Override
    public int getSize() {
        return songs.size();
    }
}