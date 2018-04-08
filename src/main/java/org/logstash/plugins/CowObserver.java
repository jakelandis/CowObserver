package org.logstash.plugins;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.logstash.LogstashAPI.Input;
import org.logstash.LogstashAPI.Plugin;

@Plugin("Moo")
public class CowObserver implements Input {
    static final BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 1000);

    @Override
    public void start() {
        cowSay("The agent has started!");
        bloomFilter.put(123);
    }

    @Override
    public void stop() {
        cowSay("The agent has stopped");
    }

    private void cowSay(String message) {
        System.out.println("The cow says: " + message);
    }
}
