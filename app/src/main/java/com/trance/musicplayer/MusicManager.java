package com.trance.musicplayer;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MusicManager {
    final File dir = Environment.getExternalStorageDirectory();
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    private String mp3Pattern = ".mp3";

    // Constructor
    public MusicManager() {

    }

    /**
     * Function to read all mp3 files and store the details in
     * ArrayList
     */
    public ArrayList<HashMap<String, String>> getPlayList() {
        Log.i("Princy", "getPlayList" + " " + dir + " isdir=" + dir.isDirectory());
        if (dir != null) {
            scanDirectory(dir);
        }
        return songsList;
    }


    private void scanDirectory(File directory) {
        Log.i("Princy", "scanDirectory " + directory);
        if (directory != null) {
            File[] listFiles = directory.listFiles();

            Log.i("Princy", "listFiles=" + listFiles);
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        Log.i("Princy", "addSongToList" + file);
                        addSongToList(file);
                    }

                }
            }
        }
    }

    private void addSongToList(File song) {
        if (song.getName().endsWith(mp3Pattern) || song.getName().endsWith(".wma")) {
            HashMap<String, String> songMap = new HashMap<String, String>();
            songMap.put("songTitle",
                    song.getName().substring(0, (song.getName().length() - 4)));
            songMap.put("songPath", song.getPath());

            // Adding each song to SongList

            Log.i("Princy", "adding" + songMap.get("songTitle"));
            songsList.add(songMap);
        }
    }


}
