package se.lexicon.tor;

import org.junit.Test;

import static org.junit.Assert.*;

public class CashTest
{
    @Test
    public void checkValue(){
        assertEquals(1, Cash._1kr.value);
        assertEquals(5, Cash._5kr.value);
        assertEquals(10, Cash._10kr.value);
        assertEquals(20, Cash._20kr.value);
        assertEquals(50, Cash._50kr.value);
        assertEquals(100, Cash._100kr.value);
        assertEquals(500, Cash._500kr.value);
        assertEquals(1000, Cash._1000kr.value);
    }
}
