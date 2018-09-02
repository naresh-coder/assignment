package com.rest.service.cachingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  @author Naresh
 *   this is classe used as service class to pass data to reposity
 */

@Service
@Transactional
public class CacheService {

	private static Logger logger = LoggerFactory.getLogger(CacheService.class);

	@Autowired
	private Cache cache ;

	@Autowired
	LogRepository logRepository;

	public void put(Store store){
		logger.debug(" ");
		cache.put(store.getKey(),store.getValue());
		LogEntiry  logEntiry  = convertLog(store);
		logRepository.save(logEntiry);

	}

	public  Object get(Store store){
		return  cache.get(store.getKey());

	}
	public  void remove(Store store){
		cache.remove(store.getKey());
	}
	public  Map<String,Object> findAll(Store store){
		return  cache.findAll();
	}

	// covert store object to log object to store in db
	private LogEntiry convertLog(Store store) {
		LogEntiry logEntiry = new LogEntiry();
		logEntiry.setId(store.getId());
		logEntiry.setKey(store.getKey());
		logEntiry.setValue(store.getValue().toString());
		logEntiry.setOperation(store.getOperation());
		return  logEntiry;
	}
	// used serialized data to file

	@PreDestroy
	public  void destroy(){
		Map<String,Object>  cacheMap = findAll( new Store());
		String filename = "C:\\DBs\\file.ser";

		// Serialization
		try (
			//Saving of object in a file
			FileOutputStream file = new FileOutputStream(new File(filename));
			ObjectOutputStream out = new ObjectOutputStream(file);){

			List<Store> storeList = new ArrayList<>();
			Store store ;

			for(Map.Entry<String,Object> entry : cacheMap.entrySet()){
				store = new Store();
				store.setKey(entry.getKey());
				store.setValue(entry.getValue());
				storeList.add(store);
			}
			out.writeObject(storeList);
		/*	out.close();
			file.close();*/
			logger.info("Object has been serialized");
		}
		catch (Exception e){
			logger.error(" error while serializing ",e);
		}

	}

	// used deserialize data to file

	@PostConstruct
	public void restoreData(){
		String filename = "C:\\DBs\\file.ser";
		try(
			// Reading the object from a file
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);){

			// Method for deserialization of object
			List<Store>  storeList= (ArrayList<Store>)in.readObject();
			for(Store store : storeList){
				this.put(store);
			}

			/*in.close();
			file.close();*/
			logger.info("Object has been deserialized ");
		}

		catch(IOException | ClassNotFoundException ex)
		{
			logger.error("IOException is caught",ex);
		}

	}


}
