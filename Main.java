import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

private static Song createSong(Scanner sc) {

    while (true) {

        try {

            System.out.print("Title: ");
            String title = sc.nextLine().trim();

            System.out.print("Artist: ");
            String artist = sc.nextLine().trim();

            System.out.print("Duration (seconds): ");
            int duration = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Publish Date (YYYY-MM-DD): ");
            LocalDate publishDate =
                    LocalDate.parse(sc.nextLine().trim());

            return new Song(
                    title,
                    artist,
                    duration,
                    publishDate
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: Duration must be a valid number."
            );

        } catch (DateTimeParseException e) {

            System.out.println(
                    "Error: Date must be in YYYY-MM-DD format."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}

private static void displaySongs(List<Song> songs) {

    if (songs.isEmpty()) {

        System.out.println("No songs available.");
        return;
    }

    for (Song song : songs) {
        System.out.println(song);
    }
}

private static void runMusicLibrary(
        MusicLibrary library,
        Scanner sc
) {

    while (true) {

        System.out.println("\n===== MUSIC LIBRARY =====");
        System.out.println("1. Add Song");
        System.out.println("2. Search Song");
        System.out.println("3. Remove Song");
        System.out.println("4. View All Songs");
        System.out.println("5. View Library Size");
        System.out.println("6. Back");

        System.out.print("Choice: ");

        String choice = sc.nextLine().trim();

        switch (choice) {

            case "1":

                Song song = createSong(sc);

                if (library.addSong(song)) {

                    System.out.println(
                            "Song added successfully."
                    );

                } else {

                    System.out.println(
                            "Duplicate title detected."
                    );
                }

                break;

            case "2":

                System.out.print(
                        "Enter title: "
                );

                String searchTitle =
                        sc.nextLine().trim();

                Song found =
                        library.searchSong(searchTitle);

                if (found != null) {

                    System.out.println("Found:");
                    System.out.println(found);

                } else {

                    System.out.println(
                            "Song not found."
                    );
                }

                break;

            case "3":

                System.out.print(
                        "Enter title: "
                );

                String removeTitle =
                        sc.nextLine().trim();

                if (
                        library.removeSong(removeTitle)
                ) {

                    System.out.println(
                            "Song removed."
                    );

                } else {

                    System.out.println(
                            "Song not found."
                    );
                }

                break;

            case "4":

                displaySongs(
                        library.getAllSongs()
                );

                break;

            case "5":

                System.out.println(
                        "Library Size: "
                                + library.getSize()
                );

                break;

            case "6":

                return;

            default:

                System.out.println(
                        "Invalid choice."
                );
        }
    }
}

private static void runPlaylist(
        Playlist playlist,
        Scanner sc
) {

    while (true) {

        System.out.println("\n===== PLAYLIST =====");
        System.out.println("1. Add Song");
        System.out.println("2. Search Song");
        System.out.println("3. Remove Song");
        System.out.println("4. Reorder Song");
        System.out.println("5. View All Songs");
        System.out.println("6. View Playlist Size");
        System.out.println("7. Back");

        System.out.print("Choice: ");

        String choice =
                sc.nextLine().trim();

        switch (choice) {

            case "1":

                Song song = createSong(sc);

                playlist.addSong(song);

                System.out.println(
                        "Song added successfully."
                );

                break;

            case "2":

                System.out.print(
                        "Enter title: "
                );

                String searchTitle =
                        sc.nextLine().trim();

                Song found =
                        playlist.searchSong(searchTitle);

                if (found != null) {

                    System.out.println("Found:");
                    System.out.println(found);

                } else {

                    System.out.println(
                            "Song not found."
                    );
                }

                break;

            case "3":

                System.out.print(
                        "Enter title: "
                );

                String removeTitle =
                        sc.nextLine().trim();

                if (
                        playlist.removeSong(removeTitle)
                ) {

                    System.out.println(
                            "Song removed."
                    );

                } else {

                    System.out.println(
                            "Song not found."
                    );
                }

                break;

            case "4":

                try {

                    System.out.print(
                            "Song title: "
                    );

                    String title =
                            sc.nextLine().trim();

                    System.out.print(
                            "New index: "
                    );

                    int newIndex =
                            Integer.parseInt(
                                    sc.nextLine().trim()
                            );

                    if (
                            playlist.reorderSong(
                                    title,
                                    newIndex
                            )
                    ) {

                        System.out.println(
                                "Song reordered."
                        );

                    } else {

                        System.out.println(
                                "Reorder failed."
                        );
                    }

                } catch (
                        NumberFormatException e
                ) {

                    System.out.println(
                            "Index must be a number."
                    );
                }

                break;

            case "5":

                displaySongs(
                        playlist.getAllSongs()
                );

                break;

            case "6":

                System.out.println(
                        "Playlist Size: "
                                + playlist.getSize()
                );

                break;

            case "7":

                return;

            default:

                System.out.println(
                        "Invalid choice."
                );
        }
    }
}

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    MusicLibrary arrayListLibrary =
            new ArrayListMusicLibrary();

    MusicLibrary hashMapLibrary =
            new HashMapMusicLibrary();

    Playlist arrayListPlaylist =
            new ArrayListPlaylist();

    Playlist linkedListPlaylist =
            new LinkedListPlaylist();

    while (true) {

        System.out.println(
                "\n===== MUSIC MANAGEMENT SYSTEM ====="
        );

        System.out.println(
                "1. ArrayList Music Library"
        );

        System.out.println(
                "2. HashMap Music Library"
        );

        System.out.println(
                "3. ArrayList Playlist"
        );

        System.out.println(
                "4. LinkedList Playlist"
        );

        System.out.println(
                "5. Exit"
        );

        System.out.print("Choice: ");

        String choice =
                sc.nextLine().trim();

        switch (choice) {

            case "1":

                runMusicLibrary(
                        arrayListLibrary,
                        sc
                );

                break;

            case "2":

                runMusicLibrary(
                        hashMapLibrary,
                        sc
                );

                break;

            case "3":

                runPlaylist(
                        arrayListPlaylist,
                        sc
                );

                break;

            case "4":

                runPlaylist(
                        linkedListPlaylist,
                        sc
                );

                break;

            case "5":

                System.out.println(
                        "Thank you!"
                );

                sc.close();

                return;

            default:

                System.out.println(
                        "Invalid choice."
                );
        }
    }
}

}