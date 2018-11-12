package com.demo.stack_queue_linkedList;

//栈     后进后出的数据结构
public class StackDemo {

	public static void main(String[] args) {

		String[] array = new String[6];
		array[0] = "a";
		array[1] = "h";
		array[2] = "a";
		array[3] = "a";
		array[4] = "h";
		array[5] = "a";
		
		int len = array.length;
		
		int mid = len/2;
		
		String[] subArray = new String[mid];
		
		int top = 0;
		for (int i = 0; i < mid; i++) {
			subArray[top++] = array[i];
		}
		int next = 0;
		if(len%2 == 0){
			next = mid;
		}else{
			next = mid + 1;
		}
		top = top-1;
		for(int j = next; j <= len-1; j++){
			if(!array[j].equals(subArray[top])){
				break;
			}
			top--;
		}
		if(top == -1){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}
	

}
