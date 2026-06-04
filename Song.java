import java.time.LocalDate;

public class Song {
    private String title;
    private String artist;
    private int durationSeconds;
    private LocalDate publishDate;

    public Song(String title, String artist, int durationSeconds, LocalDate publishDate) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                "Title cannot be empty."
            );
        }

        if (artist == null || artist.isBlank()) {
            throw new IllegalArgumentException(
                "Artist cannot be empty."
            );
        }

        if (durationSeconds <= 0) {
            throw new IllegalArgumentException(
                "Duration must be positive."
            );
        }

        if (publishDate == null) {
            throw new IllegalArgumentException(
                "Publish date cannot be null."
            );
        }

        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
        this.publishDate = publishDate;
    }

    public String getTitle()           { 
        return title; 
    }
    public String getArtist()          { 
        return artist; 
    }
    public int getDurationSeconds()    { 
        return durationSeconds; 
    }
    public LocalDate getPublishDate()  { 
        return publishDate; 
    }

    @Override
    public String toString() {
        int min = durationSeconds / 60;
        int sec = durationSeconds % 60;
        return String.format("\"%s\" by %s | %s | %d:%02d",
            title, artist, publishDate, min, sec);
    }
}