package problemset1;

public class BinarySearch {

	public static void main(String[] args) {
		
		int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10};
		
		System.out.println(binarySearch(9, 0, arr.length-1, arr));

		arr = new int[] {5,6,7,8,100,9,7,5,4,3,1,0,-1,-2};
		
		System.out.println(binarySearchMaximum(0, arr.length - 1, arr));
		
	}
	
	private static int binarySearchMaximum(int low, int high, int[] arr) {
		if(low == high){
			return arr[low];
		}
		if(high - low == 1) {
			return arr[high] >= arr[low] ? arr[high] : arr[low] ;
		}
		int mid = (low + high) /2;
		
		if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid + 1]) {
			return arr[mid];
		} else if(arr[mid] >= arr[mid-1] && arr[mid] <= arr[mid+1]) {
			return binarySearchMaximum(mid + 1, high, arr);
		} else  {
			return binarySearchMaximum(low, mid - 1, arr);
		} 
	}
	
	private static int binarySearch(int value, int low, int high, int[] arr) {
		
		if(low <= high) {
			int mid = (low + high) / 2;
			
			if(arr[mid] == value) { 
				return mid;
			} else if(value < arr[mid]) {
				return binarySearch(value, low, mid-1, arr);
			} else {
				return binarySearch(value, mid + 1, high, arr);
			}
		}
		return -1;
	}

}
