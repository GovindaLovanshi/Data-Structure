import java.lang.annotation.Target;
import java.util.*;

public class DivideCon {
    public static void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void mergeSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }

        // kaam
        int mid = si + (ei - si) / 2; // (si +ei)/2
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        merge(arr, si, mid, ei);

    }

    public static void merge(int arr[], int si, int mid, int ei) {
        int temp[] = new int[ei - si + 1];
        int i = si; // iterater for left part
        int j = mid; // iterater for right part
        int k = 0;
        while (i <= mid && j <= ei) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        // left part
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // right part
        while (j <= ei) {
            temp[k++] = arr[j++];
        }

        // copy temp to orignal arr
        for (k = 0, i = si; k < temp.length; k++, i++) {
            arr[i] = temp[k];
        }

    }

    public static void print(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void QuickSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }

        int pIdx = partitaion(arr, si, ei);// pivot index
        QuickSort(arr, si, pIdx - 1); // left
        QuickSort(arr, pIdx + 1, ei); // right

    }

    public static int partitaion(int arr[], int si, int ei) {
        int pivot = arr[ei];
        int i = si - 1; // to make plase for els small place

        for (int j = si; j < ei; j++) {
            if (arr[j] <= pivot) {
                i++;
                // swap
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;

            }

        }

        i++;
        int temp = pivot;
        arr[ei] = arr[i];
        arr[i] = temp;
        return i;
    }

    // Search in sort and rotated sorted array
    public static int serch(int arr[], int tar, int si, int ei) {
        // bqase case
        if (si > ei) {
            return -1;
        }

        // mid
        int mid = si + (ei - si) / 2;

        if (arr[mid] = tar) {
            return mid;
        }
        // mid on L1
        if (arr[si] <= arr[mid]) {
            // case : a left
            if (arr[si] <= tar && tar <= mid) {
                return serch(arr, tar, si, mid - 1);
            } else {// case b right
                return serch(arr, tar, mid + 1, ei);
            }
            // mid on L2
        } else {
            // case : c right
            if (arr[mid] <= tar && tar <= arr[ei]) {
                return serch(arr, tar, mid + 1, ei);

            } else {// case: d left
                return serch(arr, tar, si, mid - 1);
            }
        }

    }

    public static void main(String args[]) {
        // int arr[] = { 6, 3, 9, 5, 2, 8 };
        // int Temp = 0;
        // System.out.println(serch(arr, Target, 0,arr.length-1));
        // mergeSort(arr,0 , arr.length-1);
        // printarr(arr);
        // QuickSort(arr, 0, arr.length-1);
        // print(arr);
        for (int i = 0; i <= arr.length; i++) {
            for (int j = i + 1; i <= arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[i] = temp;
                }
            }
        }
        System.out.println();
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
