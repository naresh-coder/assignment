package com.rest.service.cachingservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@RunWith(SpringRunner.class)
public class ThreadTest {


	Cache  cache = new Cache();



	@Test
	public void testThread(){

		Thread put = new PutThread();
		Thread update = new UpdateThread();
		Thread read = new ReadThread();
		Thread get = new GetThread();

		Thread put2 = new PutThread();

		put.setName(" PUT Thread");
		update.setName("REMOVE Thread");
		read.setName("READ Thread");
		get.setName(" GET Thread");

		put.start();
		update.start();
		read.start();
		get.start();
		put2.start();
	try {
		Thread.sleep(30000);
	}catch (Exception e){

	}



	}


	class PutThread extends  Thread{
		@Override
		public void run() {
			for(int i =0 ;i <10; i++){
				cache.put(""+i, "test"+i);
				System.out.println(i+ "test"+i+" put ======="+Thread.currentThread().getName());
			}
		}
	}

	class UpdateThread extends  Thread{
		@Override
		public void run() {
			for(int i =5 ;i <10; i++){
				cache.remove(""+i);
				System.out.println(" remove ======="+Thread.currentThread().getName());
			}
		}
	}

	class ReadThread extends  Thread{
		@Override
		public void run() {
			for(int i =0 ;i <10; i++){
				Object ob = cache.get(""+i);

				System.out.println("KE" +i+" ==>"+ob+" ======="+Thread.currentThread().getName());
			}
		}
	}

	class GetThread extends  Thread{
		@Override
		public void run() {
			for(int i =0 ;i <10; i++){
				Map<String,Object> map = cache.findAll();

				System.out.println(map+" ======="+Thread.currentThread().getName());
			}
		}
	}




}
