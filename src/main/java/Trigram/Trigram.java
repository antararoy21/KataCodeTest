package Trigram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Trigram {

    public static String read(InputStream is) throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String record = inputStream.readLine();
        while (record != null) {
            builder.append(record + "\n");
            record = inputStream.readLine();
        }
        return builder.toString();
    }

    private static void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("WARNING! Could not close file...", e);
            }
        }
    }

    public void generate(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            generate(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not find the file for the keys...", e);
        } finally {
            closeStream(fis);
        }
    }

    public void generate(InputStream is) {
        try {
            KeyStore store = new KeyStore();
            String text = read(is);
            store.addKeys(text);
            
            System.out.println("Original\n========");
            System.out.println(text);
			
            System.out.println("Trigram Keys\n=======");
            System.out.println(store.toString());
            System.out.println("Generated Text\n==============");
            System.out.println(new Generator(store).generateText());

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Not find the file for the keys...", e);
        } catch (IOException e) {
            throw new RuntimeException("Can not extract the keys...", e);
        }
    }

  
    public static void main(String[] args) {
        new Trigram().generate(Trigram.class.getResourceAsStream("testfile"));
    }

}
