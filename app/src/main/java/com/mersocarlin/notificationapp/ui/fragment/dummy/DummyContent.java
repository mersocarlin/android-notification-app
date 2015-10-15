package com.mersocarlin.notificationapp.ui.fragment.dummy;

import java.util.ArrayList;
import java.util.List;

public class DummyContent {

    public static List<DummyItem> createItems(String itemPrefix, int qty) {
        List<DummyItem> result = new ArrayList<>();

        for(int i = 0; i < qty; i++) {
            result.add(new DummyItem(Integer.toString(i), itemPrefix + " Item " + i));
        }

        return result;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
