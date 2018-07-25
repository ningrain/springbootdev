package com.hh.springbootdev;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdevApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		Map<String, Long> map = new HashMap<>();
		map.put("Aaa", 3L);
		long aaa = map.getOrDefault("Aaa", 0L);
		// System.out.println(aaa);
		// System.out.println(MapUtils.getLong(map, "BBB"));
		System.out.println(MapUtils.getIntValue(map, "ccc"));
		// set.forEach((e) -> System.out.println("Aaaaaaaaa"));
	}
}
