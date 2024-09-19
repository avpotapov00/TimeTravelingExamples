package org.jetbrains.debugger.loadbalancer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

public class LoadBalancer {
    private final Strategy strategy;
    private final Lock lock = new ReentrantLock();
    private final List<String> addresses = new ArrayList<>();

    public LoadBalancer(Strategy strategy) {
        this.strategy = strategy;
    }

    public void add(String address) {
        lock.lock();
        try {
            if (addresses.contains(address)) {
                throw new AddressAlreadyExistException();
            }
            addresses.add(address);
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        lock.lock();
        try {
            if (addresses.isEmpty()) {
                throw new NoAddressesAddedException();
            }
            int index = strategy.nextIndex(addresses);
            return addresses.get(index);
        } finally {
            lock.unlock();
        }
    }

    interface Strategy {
        int nextIndex(List<String> addresses);
    }

    public static class RoundRobinStrategy implements Strategy {
        private int index = 0;

        @Override
        public int nextIndex(List<String> addresses) {
            return (index++) % addresses.size();
        }
    }

    public static class RandomStrategy implements Strategy {
        private final Random random;

        public RandomStrategy(Random random) {
            this.random = random;
        }

        @Override
        public int nextIndex(List<String> addresses) {
            return random.nextInt(addresses.size());
        }
    }

    static class AddressAlreadyExistException extends RuntimeException {
    }

    static class NoAddressesAddedException extends RuntimeException {
    }
}
