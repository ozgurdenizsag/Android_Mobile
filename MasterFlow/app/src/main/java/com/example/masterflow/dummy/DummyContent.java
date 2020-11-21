package com.example.masterflow.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    public static final List<DummyItem> ITEMS = new ArrayList<>();

    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<>();

    static {
        addItem(new DummyItem("Lien 1 : ", "Google", "Détails lien 1" ,"https://www.google.com/"));
        addItem(new DummyItem("Lien 2 : ", "Facebook", "Détails lien 2" ,"https://www.facebook.com/"));
        addItem(new DummyItem("Lien 3 : ", "Instagram", "Détails lien 3" ,"https://www.instagram.com/"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;
        public final String url;

        public DummyItem(String id, String content, String details, String url) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.url = url;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
