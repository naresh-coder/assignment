package com.rest.service.cachingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *  this is rest service used to  cache data in the key and pair value which will support all
 *   operation  get , create , update , remove and find all data
 */
@RestController
@RequestMapping("/store/service")
public class CacheURIResource {

	@Autowired
	private CacheService cacheService;


	@PostMapping("/cache/save")
	public  String save(@RequestBody  Store store){
		store.setOperation("save");
		cacheService.put(store);
		return " success ";

	}
	@PutMapping("/cache/update")
	public  Object update(@RequestBody  Store store){
		store.setOperation("update");
		cacheService.put(store);
		return " updated ";

	}
	@GetMapping("/cache/{key}")
	@ResponseBody
	public  Object get(@PathVariable String key){
		Store store = new Store();
		store.setOperation("get");
		store.setKey(key);
		return  cacheService.get(store);

	}
	@DeleteMapping("cache/remove/{key}")
	public  void remove(@PathVariable String key){
		Store store = new Store();
		store.setOperation("remove");
		store.setKey(key);
		cacheService.remove(store);
	}
	@GetMapping("/cache/all")
	public  Map<String,Object> findAll(){
		Store store = new Store();
		store.setOperation("all");
		return  cacheService.findAll(store);
	}

	@GetMapping("/cache/shutdown")
	public void shutdown(){
		CachingServiceApplication.applicationContext.close();
	}
}
