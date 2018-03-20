package com.good.study101;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
/**
 * Java7 fork join框架
 * @author yaokai
 *
 */
public class ForkJoin_Java7 extends RecursiveTask<Integer>{
	public static final int STEP = 50;
    private int start;
    private int end;
	public ForkJoin_Java7(int start,int end){
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Integer compute() {
		if((end - start) <= STEP){
			return sum(start,end);
		}
		int mid = (start+end)/2;
		ForkJoin_Java7 task1 = new ForkJoin_Java7(start,mid);
		ForkJoin_Java7 task2 = new ForkJoin_Java7(mid+1,end);
		task1.fork();
		task2.fork();
		int t1 = task1.join();
		int t2 = task2.join();
		return t1 + t2;
	}
	private int sum(int from,int to){
		System.out.println("cal from:"+from+",to:"+to+"#"+Thread.currentThread());
		int sum = 0;
		for(int i=from;i<=to;i++){
			sum = sum + i;
		}
		return sum;
	}
	public static void main(String[] args) throws Exception {
		ForkJoinPool pool = new ForkJoinPool();
		System.out.println(pool.submit(new ForkJoin_Java7(1,1000000)).get());
	}

}
