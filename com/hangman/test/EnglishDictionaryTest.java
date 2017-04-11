package com.hangman.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.hangman.data.*;

public class EnglishDictionaryTest {


	@Test
	public void isNotNull_test() {
		EnglishDictionary ed = new EnglishDictionary();
		Assert.assertNotEquals(null, ed);
		
	}

}
