import java.util.List;

public interface MusicLibrary {
    boolean addSong(Song song);
    boolean removeSong(String title);
    Song searchSong(String title);
    List<Song> getAllSongs();
    int getSize();
}