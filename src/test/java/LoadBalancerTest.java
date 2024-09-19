import org.jetbrains.debugger.loadbalancer.LoadBalancer;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoadBalancerTest {

    @Test
    public void multiThreadedTest() throws InterruptedException {
        LoadBalancer loadBalancer = new LoadBalancer(new LoadBalancer.RoundRobinStrategy());
        int serverSize = 5;
        List<String> servers = new ArrayList<>();
        for (int i = 0; i < serverSize; i++) {
            servers.add("Server " + i);
        }
        servers.forEach(loadBalancer::add);

        int nThreads = 2;
        int perThreadRequests = 2;
        List<List<String>> results = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            results.add(new ArrayList<>(Collections.nCopies(perThreadRequests, null)));
        }

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            final int index = i;
            threads.add(new Thread(() -> {
                List<String> threadResults = new ArrayList<>();
                for (int j = 0; j < perThreadRequests; j++) {
                    threadResults.add(loadBalancer.get());
                }
                for (int j = 0; j < threadResults.size(); j++) {
                    results.get(index).set(j, threadResults.get(j));
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        List<String> flattenedResults = new ArrayList<>();
        for (List<String> resultList : results) {
            flattenedResults.addAll(resultList);
        }

        Map<String, List<String>> groupedResult = new HashMap<>();
        for (String result : flattenedResults) {
            groupedResult.computeIfAbsent(result, k -> new ArrayList<>()).add(result);
        }

        assertFalse(groupedResult.isEmpty());
    }

}
