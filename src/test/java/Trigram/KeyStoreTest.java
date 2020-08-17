package Trigram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class KeyStoreTest {
    
    @Test
    public void testAddKeys() throws IOException {
        KeyStore store = new KeyStore();
        store.addKeys(Trigram.read(KeyStoreTest.class.getResourceAsStream("simple")));
        assertEquals(store.getSize(), 8);
        assertTrue(store.getList(Arrays.asList("a","b")).equals(Arrays.asList("c")));
        assertTrue(store.getList(Arrays.asList("b","c")).equals(Arrays.asList("d")));
        assertTrue(store.getList(Arrays.asList("c","d")).equals(Arrays.asList("e")));
        assertTrue(store.getList(Arrays.asList("d","e")).equals(Arrays.asList("f")));
        assertTrue(store.getList(Arrays.asList("e","f")).equals(Arrays.asList("g")));
        assertTrue(store.getList(Arrays.asList("f","g")).equals(Arrays.asList("h")));
        assertTrue(store.getList(Arrays.asList("g","h")).equals(Arrays.asList("i")));
        assertTrue(store.getList(Arrays.asList("h","i")).equals(Arrays.asList("j")));
    }
    
    @Test
    public void testAddMultiKeys() throws IOException {
        KeyStore store = new KeyStore();
        store.addKeys(Trigram.read(KeyStoreTest.class.getResourceAsStream("complex")));
        assertEquals(store.getSize(), 3);
        assertTrue(store.getList(Arrays.asList("a","b")).equals(Arrays.asList("c", "d")));
        assertTrue(store.getList(Arrays.asList("b","c")).equals(Arrays.asList("a")));
        assertTrue(store.getList(Arrays.asList("c","a")).equals(Arrays.asList("b")));
    }
    
    @Test
    public void testGetRoot() throws IOException {
        KeyStore store = new KeyStore();
        store.addKeys(Trigram.read(KeyStoreTest.class.getResourceAsStream("simple")));
        assertTrue(store.getRoot().equals(Arrays.asList("a","b")));
    }

    @Test
    public void testClear() throws IOException {
        KeyStore store = new KeyStore();
        store.addKeys(Trigram.read(KeyStoreTest.class.getResourceAsStream("simple")));
        assertEquals(store.getSize(), 8);
        store.clear();
        assertEquals(store.getSize(), 0);
    }

    @Test
    public void testDoesContain() throws IOException {
        KeyStore store = new KeyStore();
        store.addKeys(Trigram.read(KeyStoreTest.class.getResourceAsStream("simple")));
        assertTrue(store.doesContain(Arrays.asList("a","b")));
    }
    
}