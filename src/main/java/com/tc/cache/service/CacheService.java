package com.tc.cache.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tc.cache.api.CacheApiDelegate;
import com.tc.cache.model.CacheData;

@Component
public class CacheService implements CacheApiDelegate {

	@Value("${tc.cache.size}")
	private int size;
	
	private ArrayList<CacheData> cacheDataList;
	
	
	public void setSize(int size)
	{
		this.size = size;
	}
	public CacheService()
	{
		cacheDataList = new ArrayList<CacheData>();
	}
	
	public ArrayList<CacheData> getCacheDataList()
	{
		return this.cacheDataList;
	}
	
    @Override
    public ResponseEntity<Object> cacheIdGet(Integer id) {
        //TODO implement method
    	CacheData currentData = null;
    	int cacheSize = cacheDataList.size();
    	for(int i = 0; i < cacheSize; i++)
    	{
    		CacheData c = cacheDataList.get(i); 
    		if(c.getId() == id)
    		{
    			currentData = c;
    			cacheDataList.remove(i);
    			cacheDataList.add(currentData);
    			break;
    		}
    	}
    	
    	if(currentData == null)
    		return new ResponseEntity<>("Cache not exist", HttpStatus.OK);
    	return new ResponseEntity<>(currentData, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> cachePut(CacheData cacheData) {
        //TODO implement method
    	int cacheDataLength = cacheDataList.size();
    	if(cacheDataLength >= size)
    	{
    		cacheDataList.remove(0);
    	}
    	
    	cacheDataList.add(cacheData);

    	System.out.println("cacheDataList:" + cacheDataList);
    	
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
