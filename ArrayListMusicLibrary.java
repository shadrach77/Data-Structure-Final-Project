import java.util.ArrayList;
import java.util.List;

public class ArrayListMusicLibrary implements MusicLibrary {
    private ArrayList<Song> songs = new ArrayList<>();

    public ArrayListMusicLibrary() {}

    @Override
    public boolean addSong(Song song) {
        // Prevent duplicate titles
        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(song.getTitle())) return false;
        }
        songs.add(song);
        return true;
    }

    @Override
    public boolean removeSong(String title) {
        return songs.removeIf(s -> s.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public Song searchSong(String title) {
        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(title)) return s;
        }
        return null;
    }

    @Override
    public List<Song> getAllSongs() { return new ArrayList<>(songs); }

    @Override
    public int getSize() { return songs.size(); }
}