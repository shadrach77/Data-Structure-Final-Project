import java.time.LocalDate;

public class SongGenerator {

    public static Song generateSong(int id) {

        return new Song(
            "Song" + id,
            "Artist" + id,
            180,
            LocalDate.of(2026, 1, 1)
        );
    }
}