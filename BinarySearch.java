package com.ptaExercise;

import java.util.Arrays;

public class BinarySearch {
	
	public static void binarySearch(int arr[], int key) {
		
		//array should be in ascending order
		int start = 0;
		int end = arr.length - 1;
		int middle = (start + end) / 2;
		
		//compare key and middle
		while(start <= end) {
			if(key < arr[middle]) {
				end = middle - 1;
			}else if(key > arr[middle]) {
				start = middle + 1;
			}else if(key == arr[middle]) {
				System.out.println("index of the element is: " + middle);
				break;
			}
			middle = (start + end) / 2;
		}
		
		if(start > end) {
			System.out.println("Error: not found!");
		}	
	}

	public static void main(String[] args) {
		
		int arr[] = {1,2,3,4,5};
		binarySearch(arr,6);

	}

}
