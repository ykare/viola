package tgh2020.viola;

import android.net.Uri;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Memory {
    public static final int GREEN = 0;
    public static final int PINK = 1;
    public static final int YELOW = 3;

    private LocalDateTime timestamp;

    private int color;
    private String title;
    private Uri imageUri;
    private String text;

    private static List<Memory> memories = new ArrayList<>();

    public Memory(int color, String title, Uri imageUri, String text) {
        this.timestamp = LocalDateTime.now();
        this.color = color;
        this.title = title;
        this.imageUri = imageUri;
        this.text = text;
    }

    public static void addMemory(Memory memory) {
        memories.add(memory);
    }

    public static List<Memory> getMemories() {
        return memories;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getText() {
        return text;
    }
}
