package com.demo.sort;

/*
 * 桶排序
 * 
 * 占用空间大
 */
public class BucketSort {

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
		System.out.println("升序排列：====");
		Integer[] bucketSortLower = bucketSortLower(array, 100);
		Integer[] parseResAsc = parseResAsc(bucketSortLower, array.length);
		for (int i = 0; i < parseResAsc.length; i++) {
			System.out.print(parseResAsc[i]+"  ");
		}
		System.out.println();
		System.out.println("降序排列：====");
		Integer[] parseResDesc = parseResDesc(bucketSortLower, array.length);
		for (int i = 0; i < parseResDesc.length; i++) {
			System.out.print(parseResDesc[i]+"  ");
		}
		System.out.println();
	}
	
	/*
	 * 初级桶排序
	 */
	public static Integer[] bucketSortLower(Integer[] array, Integer maxValue){
		if(maxValue <= 0 || array == null || array.length == 0){
			return null;
		}
		Integer[] resArray = new Integer[maxValue+1];
		for (int i = 0; i < resArray.length; i++) {
			resArray[i] = 0;
		}
		
		for (int i = 0,length = array.length; i < length; i++) {
			resArray[array[i]] = resArray[array[i]]+1;
		}
		
		return resArray;
	}
	
	/*
	 * 解析结果
	 * 正序
	 */
	public static Integer[] parseResAsc(Integer[] array, Integer len){
		if(len <= 0 || array == null || array.length == 0){
			return null;
		}
		Integer[] resArray = new Integer[len];
		int index = 0;
		for (int i = 0,length = array.length; i < length; i++) {
			if(0 == array[i]){
				continue;
			}else{
				for (int j = 0,leng = array[i]; j < leng; j++) {
					resArray[index] = i;
					index ++;
				}
			}
		}
		return resArray;
	}
	
	/*
	 * 解析结果
	 * 降序
	 */
	public static Integer[] parseResDesc(Integer[] array, Integer len){
		if(len <= 0 || array == null || array.length == 0){
			return null;
		}
		Integer[] resArray = new Integer[len];
		int index = 0;
		for (int i = array.length-1,length = 0; i >= length; i--) {
			if(0 == array[i]){
				continue;
			}else{
				for (int j = 0,leng = array[i]; j < leng; j++) {
					resArray[index] = i;
					index++;
				}
			}
		}
		return resArray;
	}
	
}
