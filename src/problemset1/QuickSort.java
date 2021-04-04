package problemset1;

import java.util.Arrays;

public class QuickSort {

	
	public static void main(String[] args) {
		int[] arr = new int[] {1,4,2,7,5,9,0,3,6,8};
		
		quickSort(arr, 0, arr.length - 1);
		Arrays.stream(arr).forEach(System.out::println);
	}
	
	private static void quickSort(int[] arr, int low, int high) {
		if(low == high) {
			return;
		}
		if(high - low == 1) {
			if(arr[low] > arr[high]) {
				swap(arr, low, high);
			}
			return;
		}
		
		int pivotPos = partition(arr, low, high);
		quickSort(arr, low, pivotPos-1);
		quickSort(arr, pivotPos + 1, high);
		
	}
	
	private static int partition(int[] arr, int low, int high) {
		if(high == low) {
			return low;
		}
		int pivot = high;
		int i = low;
		int j = high-1;
		boolean foundLeft = false;
		boolean foundRight = false;
		while(j > i) {
			
			if(foundLeft && foundRight) {
				swap(arr, i, j);
				foundLeft = false;
				foundRight = false;
			} else if(!foundLeft) {
				if(arr[i] > arr[pivot]) {
					foundLeft = true;
					continue;
				}
				i++;
			} else {
				if(arr[j] < arr[pivot]) {
					foundRight = true;
					continue;
				}
				j--;
			}
		}
		if(arr[i] > arr[pivot]) {
			swap(arr, i, pivot);
		}
		return i;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
