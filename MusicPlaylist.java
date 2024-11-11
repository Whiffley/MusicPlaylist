import java.util.*;
import java.io.*;

public class MusicPlaylist {
    public static void main(String[] args) {
        Queue<String> playlist = new LinkedList<>();
        Stack<String> history = new Stack<>();
        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to the CSE 122 Music Playlist!");

        String choice = "";
        while (!choice.equalsIgnoreCase("Q")) {
            menu();
            System.out.print("Enter your choice: ");
            choice = console.nextLine();

            if (choice.equalsIgnoreCase("A")) {
                addSong(playlist, console);
            } else if (choice.equalsIgnoreCase("P")) {
                playSong(playlist, history);
            } else if (choice.equalsIgnoreCase("Pr")) {
                printHistory(history);
            } else if (choice.equalsIgnoreCase("C")) {
                clearHistory(history);
            } else if (choice.equalsIgnoreCase("D")) {
                deleteHistory(history, console);
            }

        }

    }

    //Behavior:
    //  - prints the menu of options 
    public static void menu() {
        System.out.println("(A) Add song");
        System.out.println("(P) Play song");
        System.out.println("(Pr) Print history");
        System.out.println("(C) Clear history");
        System.out.println("(D) Delete from history");
        System.out.println("(Q) Quit");
        System.out.println();
    }

    //Behavior:
    //  - adds the user inputted song to the playlist
    //Parameters:
    //  - playlist: a queue holding the songs the user inputted
    //  - console: a scanner that takes user input
    public static void addSong(Queue<String> playlist, Scanner console) {
        System.out.print("Enter song name: ");
        String song = console.nextLine();
        playlist.add(song);
        System.out.println("Successfully added " + song);
        System.out.println();
        System.out.println();
    }

    //Behavior:
    //  - plays the first song added to the playlist and adds that song to the history
    //Exceptions:
    //  - IllegalStateException: thrown if the playlist is empty (aka there is no song to play)
    //Parameters:
    //  - playlist: a queue holding the songs the user inputted
    //  - history: a stack holding the songs that have been played so far
    public static void playSong(Queue<String> playlist, Stack<String> history) {
        if (playlist.isEmpty()) {
            throw new IllegalStateException();
        }

        String song = playlist.remove();
        System.out.println("Playing song: " + song);
        history.push(song);
        System.out.println();
        System.out.println();
    }

    //Behavior: 
    //  - prints the songs that have been played already in reverse chronological order
    //Exceptions:
    //  - IllegalStateException: thrown if history is empty (aka there is no history to print)
    //Parameters:
    //  - history: a stack holding the songs that have been played so far
    public static void printHistory(Stack<String> history) {
        if (history.isEmpty()) {
            throw new IllegalStateException();
        }
        Stack<String> aux = new Stack<>();

        while(!history.isEmpty()) {
            String song = history.pop();
            System.out.println(song);
            aux.push(song);
        }
        System.out.println();
        sToS(aux, history);
    }

    //Behavior:
    //  - transfers the contents of one stack to another stack
    //Parameters:
    //  - s1: the stack that is full at the beginning 
    //  - s2: the stack that is empty at the beginning
    public static void sToS(Stack<String> s1, Stack<String> s2) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        System.out.println();
    }

    //Behavior: 
    //  - clears the history 
    //Parameters:
    //  - history: a stack holding the songs that have been played so far
    public static void clearHistory(Stack<String> history) {
        while(!history.isEmpty()) {
            history.pop();
        }
        System.out.println();
    }

    //Behavior:
    //  - deletes a user inputted number of songs from either the beginning or end of
    //    the history stack. Positive input deletes from the top and negative 
    //    input from the bottom.
    //Exceptions:
    //  - IllegalArgumentException: thrown if the user inputted number for song deletion
    //    is greater than the number of songs in the history stack
    //Parameters:
    //  - history: a stack holding the songs that have been played so far
    //  - console: a scanner that takes user input
    public static void deleteHistory(Stack<String> history, Scanner console) {
        System.out.println("A positive number will delete from recent history.");
        System.out.println("A negative number will delete from the beginning of history.");
        System.out.print("Enter number of songs to delete: ");
        String stringNumSongs = console.nextLine();
        int numSongs = Integer.parseInt(stringNumSongs);
        int absNumSongs = Math.abs(numSongs);

        if (history.size() < absNumSongs) {
            throw new IllegalArgumentException();
        }

        if (numSongs > 0) {
            for (int i = 0; i < numSongs; i++) {
                history.pop();
            }
        } else {
            Stack<String> aux = new Stack<>();
            sToS(history, aux);
            for (int i = 0; i < absNumSongs; i++) {
                aux.pop();
            }
            sToS(aux, history);
        }
        System.out.println();
    }


}
