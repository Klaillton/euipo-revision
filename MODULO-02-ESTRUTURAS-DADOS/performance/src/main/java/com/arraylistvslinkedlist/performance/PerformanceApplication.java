package com.arraylistvslinkedlist.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Compare tempo de add/contains/remove em:
// ArrayList vs LinkedList (10_000 elementos)
// HashSet vs TreeSet
// HashMap vs TreeMap
// Registre resultados e explique.

@SpringBootApplication
public class PerformanceApplication {

	private static final int NUM_ELEMENTS = 10_000;
	private static final Logger logger = LoggerFactory.getLogger(PerformanceApplication.class);

	private final List<Integer> arrayList = new ArrayList<>();
	private final List<Integer> linkedList = new LinkedList<>();
	private final Set<Integer> hashSet = new HashSet<>();
	private final Set<Integer> treeSet = new TreeSet<>();
	private final Map<Integer, String> hashMap = new HashMap<>();
	private final Map<Integer, String> treeMap = new TreeMap<>();

	public long start(){
		return System.currentTimeMillis();
	}

	public long end(long start){
		return System.currentTimeMillis() - start;
	}

	public void runPerformanceTests() {
		// Teste ArrayList vs LinkedList
		long start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			arrayList.add(i);
		}
		logger.info("ArrayList add: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			linkedList.add(i);
		}
		logger.info("LinkedList add: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			arrayList.contains(i);
		}
		logger.info("ArrayList contains: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			linkedList.contains(i);
		}
		logger.info("LinkedList contains: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			arrayList.remove(Integer.valueOf(i));
		}
		logger.info("ArrayList remove: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			linkedList.remove(Integer.valueOf(i));
		}
		logger.info("LinkedList remove: {} ms", end(start));

		// Teste HashSet vs TreeSet
		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			hashSet.add(i);
		}
		logger.info("HashSet add: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			treeSet.add(i);
		}
		logger.info("TreeSet add: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			hashSet.contains(i);
		}
		logger.info("HashSet contains: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			treeSet.contains(i);
		}
		logger.info("TreeSet contains: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			hashSet.remove(i);
		}
		logger.info("HashSet remove: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			treeSet.remove(i);
		}
		logger.info("TreeSet remove: {} ms", end(start));

		// Teste HashMap vs TreeMap
		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			hashMap.put(i, "Value " + i);
		}
		logger.info("HashMap put: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			treeMap.put(i, "Value " + i);
		}
		logger.info("TreeMap put: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			hashMap.get(i);
		}
		logger.info("HashMap get: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			treeMap.get(i);
		}
		logger.info("TreeMap get: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			hashMap.remove(i);
		}
		logger.info("HashMap remove: {} ms", end(start));

		start = start();
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			treeMap.remove(i);
		}
		logger.info("TreeMap remove: {} ms", end(start));

	}

	public static void main(String[] args) {
		SpringApplication.run(PerformanceApplication.class, args);

		PerformanceApplication app = new PerformanceApplication();
		app.runPerformanceTests();
	}

}
