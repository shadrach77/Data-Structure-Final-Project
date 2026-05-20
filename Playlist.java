import java.util.List;

public interface Playlist {
    boolean addSong(Song song);
    boolean removeSong(String title);
    boolean reorderSong(String title, int newIndex);
    Song searchSong(String title);
    List<Song> getAllSongs();
    int getSize();
}