package Trigram;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;

public class KeyStore {

    private List<String> first = Collections.emptyList();
    private Map<String, List<String>> trigram = Collections.emptyMap();

    public KeyStore() {
    }

    
    public void addKeys(String text) throws IOException {
        String[] words = text.replaceAll("\n", " ").split(" ");
        List<String> wordList = new ArrayList<String>();
        for (String string : words) {
            if (string.trim().length() > 0)
                wordList.add(string);
        }
        if (wordList.size() < 3) {
            return;
        }
        first = Arrays.asList(wordList.get(0), wordList.get(1));
        trigram = new HashMap<String, List<String>>();
        for (int i = 0; i < wordList.size() - 2; i++) {
            String key = wordList.get(i) + '\0' + wordList.get(i + 1);
            List<String> list = trigram.get(key);
            if (list == null) {
                trigram.put(key, (list = new ArrayList<String>()));
            }
            list.add(wordList.get(i + 2));
        }
    }

    
    public void clear() {
        first = Collections.emptyList();
        trigram = Collections.emptyMap();
    }

    
    public int getSize() {
        return trigram.size();
    }

    
    public List<String> getRoot() {
        return first;
    }

    
    public List<String> getList(List<String> key) {
        String keyValue = Joiner.on("\0").useForNull("").join(key);
        return trigram.get(keyValue);
    }

    public boolean doesContain(List<String> key) {
        String keyValue = Joiner.on("\0").useForNull("").join(key);
        return trigram.containsKey(keyValue);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<String>> set : trigram.entrySet()) {
            String[] key = set.getKey().split("\0");
            String keyPart = Joiner.on(",").useForNull("-").join(key);
            String valuePart = Joiner.on(",").useForNull("").join(set.getValue());
            builder.append(keyPart + "=" + valuePart + "\n");
        }
        return builder.toString();
    }

}