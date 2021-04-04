package problemset1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeSort {

	public static void main(String[] args) {
		
		int[] arr = new int[] {1,7,3,6,0,8,2,4,9,5};
		
		mergeSort(arr, 0, arr.length - 1);
		System.out.println(arr.toString());
	}
	
	private static void mergeSort(int[] arr, int low, int high) {
		
		if(low == high) {
			return;
		} else if(high - low == 1) {
			merge(arr, low, low, high);
		} else {
			int mid = (high + low) / 2;
			mergeSort(arr, low, mid);
			mergeSort(arr, mid + 1, high);
			merge(arr, low, mid, high);
		}
	}
	
	private static void merge(int[] arr, int low, int mid, int high) {
		int arr1Size = (mid- low) + 1;
		int arr2Size = (high - (mid+1)) + 1;
		
		int[] arrLeft = new int[arr1Size];
		int[] arrRight = new int[arr2Size];
		
		IntStream.rangeClosed(low, mid).forEach(i -> arrLeft[i-low] = arr[i]);
		IntStream.rangeClosed(mid + 1, high).forEach(i -> arrRight[i-(mid + 1)] = arr[i]);
		
		int i = 0;
		int j = 0;
		int k = low;
		while(true) {
			if(i < arr1Size && j < arr2Size) {
				if(arrLeft[i] <= arrRight[j]) {
					arr[k] = arrLeft[i];
					i++;
				} else {
					arr[k] = arrRight[j];
					j++;
				}
			} else if(i < arr1Size) {
				arr[k] = arrLeft[i];
				i++;
			} else if(j < arr2Size) {
				arr[k] = arrRight[j];
				j++;
			} else {
				break;
			}
			k++;
		}
	}

}
