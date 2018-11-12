package com.demo.sort;

/*
 * 冒泡排序
 */
public class BubbleSort {

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
		
		//倒序排列
		Integer[] sort = sort(array);
		for (int i = 0; i < sort.length; i++) {
			System.out.println(sort[i]);
		}
		
	}
	
	/*
	 * 倒序排列
	 */
	public static Integer[] sort(Integer[] array){
		if(array == null || array.length == 0){
			return null;
		}
		Integer temp = 0;
		for (int i = 0,length = array.length-1; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if(array[j] <= array[j+1]){
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		return array;
	}
 	
}
