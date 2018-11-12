package com.demo.sort;

import org.junit.Test;

/*
 * 快速排序
 * 二分
 */
public class FastSort {

	public static void main(String[] args) {
		Integer[] array = new Integer[10];
		array[0] = 10;
		array[1] = 5;
		array[2] = 20;
		array[3] = 100;
		array[4] = 20;
		array[5] = 66;
		array[6] = 88;
		array[7] = 19;
		array[8] = 56;
		array[9] = 1;
		Integer[] sort = sort(array);
		for (int i = 0; i < sort.length; i++) {
			System.out.println(sort[i]);
		}
	}
	
	public static Integer[] sort(Integer[] array){
		if(array == null || array.length == 0){
			return null;
		}
		quicksort(array, 0, 9);
		return array;
	}
	
	public static void quicksort(Integer[] array, Integer left, Integer right){
		
		if(left > right){
			return;
		}
		
		int i,j,t,temp;
		temp = array[left];
		i = left;
		j = right;
		while(i != j){
			while(array[j] >= temp && i < j){
				j--;
			}
			
			while(array[i] <= temp && i < j){
				i++;
			}
			
			if(i < j){
				t = array[i];
				array[i] = array[j];
				array[j] = t;
			}
		}
		
		array[left] = array[i];
		array[i] = temp;
		
		quicksort(array, left, i-1);
		
		quicksort(array, i+1, right);
		return;
	}
	
	@Test 
	public void test(){
		System.out.println(9/10);
		double random = Math.random()*10*(9/10);
		System.out.println(random);
	}
}
