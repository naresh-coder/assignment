package com.rest.service.cachingservice;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 *  class used to store data in memory
 */
@Component
public class Cache {
	private Map<String,Object>  cacheMap;

	private volatile Set<String> lockedKeys = new HashSet<>();
	private volatile Set<String> removedLocks = new HashSet<>();

	public Cache(){
		cacheMap = new HashMap<>();
	}


	public  void put(String key, Object value){
		// to apply lock on key while update and put
		lockedKeys.add(key);

		//Object ObjectValue = this.get(key);
		//if(removedLocks.contains(key)){
			synchronized (cacheMap){
				while(true){
					try {
						if (!removedLocks.contains(key)) {
							cacheMap.put(key, value);
							cacheMap.notifyAll();
							break;
						} else {
							cacheMap.wait();
						}
					}
					catch (Exception e){

					}
				}

				lockedKeys.remove(key);
			}
		//}
		//

	}
	public  Object get(String key) {

		Object ojb ;
		synchronized (cacheMap) {
			while(true) {
				try {
					if (!lockedKeys.contains(key) && !removedLocks.contains(key)) {
						ojb = 	cacheMap.get(key);
						cacheMap.notifyAll();
						break;
					} else {
						cacheMap.wait();
					}
				}
				catch (Exception e){

				}
			}
			return  ojb;
		}
		//return  ojb;
	}

	public  void remove(String key){

		synchronized (cacheMap) {
            while(true) {
				try {
					if (!lockedKeys.contains(key) && !removedLocks.contains(key)) {
						cacheMap.remove(key);
						cacheMap.notifyAll();
						break;
					} else {
						cacheMap.wait();
					}
				}
				catch (Exception e){

				}
			}
		}

	}



		/*
		if(!lockedKeys.contains(key)){
			removedLocks.add(key);
			 cacheMap.remove(key);
		}
		else{
			synchronized (cacheMap) {
				cacheMap.remove(key);
			}
		}
		removedLocks.remove(key);
	*/
	public  Map<String,Object> findAll(){
		Map<String,Object> copy = new HashMap<>();
		synchronized (cacheMap) {
			for (String key : cacheMap.keySet()) {
				Object object = this.get(key);
				copy.put(key,object);
			}
		}
		return  copy;
	}



}
