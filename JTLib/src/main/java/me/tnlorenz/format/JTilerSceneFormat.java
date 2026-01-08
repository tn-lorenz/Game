package me.tnlorenz.format;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class JTilerSceneFormat {
    public record Entry(int id, int x, int y, int layer) {}

    public static final byte[] MAGIC = {
            (byte) 0x89,
            'J','T','I','L','E','R',
            '\r','\n', 0x1A, '\n'
    };

    public static final int VERSION = 1;

    private JTilerSceneFormat() {}

    public static void write(Path file, List<Entry> entries) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(file)))) {
            // Header
            out.write(MAGIC);
            out.writeInt(VERSION);

            // Data
            out.writeInt(entries.size());
            for (Entry e : entries) {
                out.writeInt(e.id());
                out.writeInt(e.x());
                out.writeInt(e.y());
                out.writeInt(e.layer());
            }
        }
    }

    public static List<Entry> read(Path file) throws IOException {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(file)))) {
            // Header
            byte[] magic = new byte[MAGIC.length];
            in.readFully(magic);
            if (!Arrays.equals(magic, MAGIC)) throw new IOException("Not a JTiler file");

            int version = in.readInt();
            if (version != VERSION) throw new IOException("Unsupported version: " + version);

            // Data
            int count = in.readInt();
            List<Entry> entries = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                int id = in.readInt();
                int x  = in.readInt();
                int y  = in.readInt();
                int layer = in.readInt();
                entries.add(new Entry(id, x, y, layer));
            }

            return entries;
        }
    }
}
