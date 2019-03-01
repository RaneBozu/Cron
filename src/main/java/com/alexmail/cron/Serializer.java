package com.alexmail.cron;

import java.io.*;

class Serializer {

    static byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream b = new ByteArrayOutputStream();
             ObjectOutputStream ois = new ObjectOutputStream(b)) {
            ois.writeObject(obj);
            return b.toByteArray();
        }
    }
}
