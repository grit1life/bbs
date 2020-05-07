package com.cafe24.louw0.test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {

	public static void main(String[] args) {
		Path p = Paths.get("");
		System.out.println(p.toAbsolutePath());

	}

}
