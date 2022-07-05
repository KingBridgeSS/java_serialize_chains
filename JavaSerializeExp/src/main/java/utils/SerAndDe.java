package utils;

import java.io.*;

public class SerAndDe {
    public static void deserialize(byte[] bytes) {
        try {
            ByteArrayInputStream ais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(ais);
            ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static byte[] serialize(Object o) {
        try {
            ByteArrayOutputStream aos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(aos);
            oos.writeObject(o);
            oos.flush();
            oos.close();
            return aos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
