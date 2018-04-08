package org.logstash.plugins;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.logstash.LogstashAPI.AgentObserver;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class CowObserver extends Plugin {
    static final BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 1000);
    /**
     * Constructor to be used by plugin manager for plugin instantiation.
     * Your plugins have to provide constructor with this exact signature to
     * be successfully loaded by manager.
     *
     * @param wrapper
     */
    public CowObserver(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("CowObserver has entered the field...");
    }

    @Extension
    public static class CowAgentObserver implements AgentObserver {

        @Override
        public void onStart() {
            cowSay("The agent has started!");
            bloomFilter.put(123);
        }

        @Override
        public void onShutdown() {
            cowSay("The agent has stopped");
        }

        private void cowSay(String message) {
            System.out.println("The cow says: " + message);
        }
    }
}
