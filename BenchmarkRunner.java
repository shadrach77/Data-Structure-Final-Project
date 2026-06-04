public class BenchmarkRunner {

    private static final int[] SIZES = {
        100,
        1000,
        10000,
        50000,
        100000
    };

    public static void main(String[] args) {

        System.out.println("=== MUSIC LIBRARY TESTS ===");

        for (int size : SIZES) {

            benchmarkLibrary(
                    new ArrayListMusicLibrary(),
                    "ArrayListMusicLibrary",
                    size
            );

            benchmarkLibrary(
                    new HashMapMusicLibrary(),
                    "HashMapMusicLibrary",
                    size
            );

            System.out.println();
        }

        System.out.println("\n=== PLAYLIST TESTS ===");

        for (int size : SIZES) {

            benchmarkPlaylist(
                    new ArrayListPlaylist(),
                    "ArrayListPlaylist",
                    size
            );

            benchmarkPlaylist(
                    new LinkedListPlaylist(),
                    "LinkedListPlaylist",
                    size
            );

            System.out.println();
        }
    }

    private static void benchmarkLibrary(
            MusicLibrary library,
            String name,
            int size
    ) {

        long start;
        long end;

        // ADD

        start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            library.addSong(
                    SongGenerator.generateSong(i)
            );
        }

        end = System.nanoTime();

        long addTime = end - start;

        // SEARCH

        start = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            library.searchSong(
                    "Song" + (size - 1)
            );
        }

        end = System.nanoTime();

        long searchTime = end - start;

        // REMOVE

        start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            library.removeSong(
                    "Song" + i
            );
        }

        end = System.nanoTime();

        long removeTime = end - start;

        System.out.println(
                name +
                        " | Size=" + size +
                        " | Add=" + addTime +
                        " ns | Search=" + searchTime +
                        " ns | Remove=" + removeTime + " ns"
        );
    }

    private static void benchmarkPlaylist(
            Playlist playlist,
            String name,
            int size
    ) {

        long start;
        long end;

        // ADD

        start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            playlist.addSong(
                    SongGenerator.generateSong(i)
            );
        }

        end = System.nanoTime();

        long addTime = end - start;

        // SEARCH

        start = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            playlist.searchSong(
                    "Song" + (size - 1)
            );
        }

        end = System.nanoTime();

        long searchTime = end - start;

        // REORDER

        start = System.nanoTime();

        for (int i = 0; i < 1000; i++) {

            playlist.reorderSong(
                    "Song" + (size / 2),
                    0
            );
        }

        end = System.nanoTime();

        long reorderTime = end - start;

        // REMOVE

        start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            playlist.removeSong(
                    "Song" + i
            );
        }

        end = System.nanoTime();

        long removeTime = end - start;

        System.out.println(
                name +
                        " | Size=" + size +
                        " | Add=" + addTime +
                        " ns | Search=" + searchTime +
                        " ns | Reorder=" + reorderTime +
                        " ns | Remove=" + removeTime + " ns"
        );
    }
}