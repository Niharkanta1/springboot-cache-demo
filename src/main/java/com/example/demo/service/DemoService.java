package com.example.demo.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.demo.model.CacheEntry;

@Service
public class DemoService {

    private final Map<Integer, CacheEntry> cache = new HashMap<>();

    public String getData(int id) {
        CacheEntry data = cache.get(id);
        if (data != null) {
            if (!data.isExpired())
                return "Cache:" + data.getValue();
            else
                removeExpiredEntries(id, data);
        } else {
            System.out.println("Fetching data for id:" + id);
            cache.put(id, new CacheEntry("Key-" + id, 5L, TimeUnit.SECONDS));
            return "Fetch Successful for id:" + id;
        }
        return "key expired!";
    }

    @Scheduled(fixedRate = 10000) // 10 seconds
    public void removeExpiredEntries() {
        System.out.println("remove expire entries scheduler......");
        if (cache.isEmpty())
            return;
        Iterator<Entry<Integer, CacheEntry>> itr = cache.entrySet().iterator();
        while (itr.hasNext()) {
            Entry<Integer, CacheEntry> entry = itr.next();
            if (entry.getValue().isExpired()) {
                deleteValue(entry.getValue());
                itr.remove();
            }
        }
        // cache.entrySet().stream().filter(e -> e.getValue().isExpired())
        // .forEach(expired -> deleteValue(expired.getValue()));;
    }

    public void removeExpiredEntries(Integer id, CacheEntry data) {
        if (cache.containsKey(id)) {
            cache.remove(id);
            deleteValue(data);
        }
    }

    private void deleteValue(CacheEntry cacheEntry) {
        System.out.println("Deleting cache for:" + cacheEntry.getValue());
    }
}
