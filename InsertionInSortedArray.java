package com.ptaExercise;

import java.util.ArrayList;
import java.util.Scanner;

// in descending order
public class InsertionInSortedArray {
	
	static public class List{
		int maxSize;
		ArrayList<Integer> data = new ArrayList<>();
		int last;
		
		public List(int maxSize) {
			this.maxSize = maxSize;
			
		}
		
		int binarySearch(int x) {
			int start = 0;
			int end = data.size() - 1;
			int middle = (start + end) / 2;
			
			while(start <= end) {
				if(x > data.get(middle)) {
					end = middle - 1;
				}else if(x < data.get(middle)) {
					start = middle + 1;
				}else {
					return middle;
				}
				middle = (start + end) / 2;
			}
			
			return -1;
			
		}
		
		int insertIndex(int x) {
			int position;
			int start = 0;
			int end = data.size() - 1;
			int middle = (start + end) / 2;
			while(start <= end) {
				if(x > data.get(middle) && x < data.get(middle - 1)) {
					position = middle;
					return position;
				}else if(x < data.get(middle) && x > data.get(middle + 1)) {
					position = middle + 1;
					return position;
				}else if(x > data.get(middle)) {
					end = middle - 1;
				}else if(x < data.get(middle)) {
					start = middle + 1;
				}
				middle = (start + end) / 2;
			}
			return -1;
		}
	}
	

	static void insert(List list, int x) {
		
		// if the list is full
		if(list.last == list.maxSize - 1) {
			System.out.println("Insertion failed.");
			
		}
		else if(list.binarySearch(x) != -1) {
			System.out.println("Element already exists!");
			
		}
		else if(list.binarySearch(x) == -1) {
			int index = list.insertIndex(x);
			int currentLength = list.data.size();
			list.data.add(0);
			for(int i = currentLength - 1; i >= index; i--) {
				list.data.set(i+1, list.data.get(i));
			}
			list.data.set(index, x);
			list.last++;
		}
		
		
		
		
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int last = sc.nextInt()-1;
		
		List list = new List(6);
		list.last = last;
		
		for(int i = 0; i <= last; i++) {
			list.data.add(sc.nextInt());
		}
		
		int insertKey = sc.nextInt();
		
		insert(list,insertKey);
		
		for(int i : list.data) {
			System.out.print(i + "\s");
		}
		
		System.out.println("\nLast = " + list.last);

	}

}
