package com.zts;

import java.io.FileInputStream;

import opennlp.tools.namefind.TokenNameFinder;
import opennlp.tools.namefind.TokenNameFinderModel;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testNLP() {
		try {
			FileInputStream modelIn = new FileInputStream("/Users/zts/IdeaProjects/common/launcher/src/main/resources/bin/en-token.bin");
			TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(modelIn);
		}catch (Exception e){

		}

	}

}