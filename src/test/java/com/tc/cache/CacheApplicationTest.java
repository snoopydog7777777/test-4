package com.tc.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tc.cache.model.CacheData;
import com.tc.cache.service.CacheService;

@SpringBootTest
class CacheApplicationTest {

	@Test
	public void testCacheService() throws Throwable{
		CacheService cacheService = new CacheService();
		
		cacheService.setSize(3);
		
		CacheData cacheData1 = new CacheData();
		cacheData1.setId(0);
		cacheData1.setData("A");
		
		cacheService.cachePut(cacheData1);
		
		CacheData cacheData2 = new CacheData();
		cacheData2.setId(1);
		cacheData2.setData("B");
		
		cacheService.cachePut(cacheData2);
		
		CacheData cacheData3 = new CacheData();
		cacheData3.setId(2);
		cacheData3.setData("C");
		
		cacheService.cachePut(cacheData3);
		
		CacheData cacheData4 = new CacheData();
		cacheData4.setId(3);
		cacheData4.setData("D");
		
		cacheService.cachePut(cacheData4);
		
		ArrayList<CacheData> cacheDataList = cacheService.getCacheDataList();
		assertEquals(cacheDataList.get(2), cacheData4);
		assertEquals(cacheDataList.get(1), cacheData3);
		assertEquals(cacheDataList.get(0), cacheData2);
		
		
		CacheData cacheData5 = new CacheData();
		cacheData5.setId(4);
		cacheData5.setData("X");
		
		cacheService.cachePut(cacheData5);
		
		assertEquals(cacheDataList.get(2), cacheData5);
		assertEquals(cacheDataList.get(1), cacheData4);
		assertEquals(cacheDataList.get(0), cacheData3);
		
		cacheService.cacheIdGet(2);
		assertEquals(cacheDataList.get(2), cacheData3);
		assertEquals(cacheDataList.get(1), cacheData5);
		assertEquals(cacheDataList.get(0), cacheData4);
		
		CacheData cacheData6 = new CacheData();
		cacheData6.setId(5);
		cacheData6.setData("J");
		
		cacheService.cachePut(cacheData6);
		assertEquals(cacheDataList.get(2), cacheData6);
		assertEquals(cacheDataList.get(1), cacheData3);
		assertEquals(cacheDataList.get(0), cacheData5);
		
		cacheService.cacheIdGet(7);
		assertEquals(cacheDataList.get(2), cacheData6);
		assertEquals(cacheDataList.get(1), cacheData3);
		assertEquals(cacheDataList.get(0), cacheData5);
		
		
	}

}
