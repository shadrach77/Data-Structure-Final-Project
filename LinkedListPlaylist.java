import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListPlaylist implements Playlist {

    private LinkedList<Song> songs = new LinkedList<>();

    public LinkedListPlaylist() {
    }

    @Override
    public boolean addSong(Song song) {
        songs.add(song);
        return true;
    }

       @Override
    public boolean removeSong(String title) {
        return songs.removeIf(s -> s.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public boolean reorderSong(String title, int newIndex) {

        Song foundSong = null;

        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(title)) {
                foundSong = s;
                break;
            }
        }

        if (foundSong == null) {
            return false;
        }

        if (newIndex < 0 || newIndex >= songs.size()) {
            return false;
        }

        songs.remove(foundSong);
        songs.add(newIndex, foundSong);

        return true;
    }

        @Override
    public Song searchSong(String title) {

        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(title)) {
                return s;
            }
        }

        return null;
    }

        @Override
    public List<Song> getAllSongs() { return new ArrayList<>(songs); }

    @Override
    public int getSize() {
        return songs.size();
    }
}