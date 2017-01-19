package druid;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class DruidTest {
	public static void main(String[] args) throws Exception {
		int times = 50;
		int numOfThreads = Runtime.getRuntime().availableProcessors() * 2;
		ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
		final DatabaseOperator test = new DatabaseOperator();
		test.setDataSource(DruidUtils.getDataSource());

		boolean createResult = true;
	/*	try {
			test.createTable();
			createResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		if (createResult) {
			List<Future<Long>> results = new ArrayList<Future<Long>>();
			for (int i = 0; i < times; i++) {
				results.add(executor.submit(new Callable<Long>() {
					@Override
					public Long call() throws Exception {
						long begin = System.currentTimeMillis();
						try {
							test.insert();
							// insertResult = true;
						} catch (Exception e) {
							e.printStackTrace();
						}
						long end = System.currentTimeMillis();
						return end - begin;
					}
				}));
			}
			executor.shutdown();
			while (!executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS));

			long sum = 0;
			for (Future<Long> result : results) {
				sum += result.get();
			}

			System.out.println("number of threads :" + numOfThreads + " times:"
					+ times);
			System.out.println("running time: " + sum + "ms");
			System.out.println();
			try {
				// test.tearDown();
				// dropResult = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("初始化数据库失败");
		}
	}
}
