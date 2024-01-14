import java.sql.Time;

public class DC {

    // Question1:ApplyMergesorttosortanarrayofStrings.(Assumethatallthecharactersin
    // all the Strings are in lowercase). (EASY)
    // Sample Input 1: arr = { "sun", "earth", "mars", "mercury"}
    // Sample Output 1: arr = { "earth", "mars", "mercury", "sun"}

    public static String[] mergeSort(String[] arr, int lo, int hi) {
        if (lo == hi) {
            String[] A = { arr[lo] };
            return A;
        }
        int mid = lo + (hi - lo) / 2;
        String[] arr1 = mergeSort(arr, lo, mid);
        String[] arr2 = mergeSort(arr, mid + 1, hi);
        String[] arr3 = merge(arr1, arr2);
        return arr3;
    }

    static String[] merge(String[] arr1, String[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        String[] arr3 = new String[m + n];
        int idx = 0;
        int i = 0;
        int j = 0;

        while (i < m && j < n) {
            if (isAlphabeticallySmaller(arr1[i], arr2[j])) {
                arr3[idx] = arr1[i];
                i++;
                idx++;
            } else {
                arr3[idx] = arr2[j];
                j++;
                idx++;
            }
        }

        while (i < m) {
            arr3[idx] = arr1[i];
            i++;
            idx++;
        }
        while (j < n) {
            arr3[idx] = arr2[j];
            j++;
            idx++;
        }

        return arr3;
    }

    // Return true if str1 appears before str2 in alphabetical order
    static boolean isAlphabeticallySmaller(String str1, String str2) {
        if (str1.compareTo(str2) < 0) {
            return true;
        }
        return false;
    }

    // Question 2 : Given an array nums of size n, returnthe majority element.
    // (MEDIUM)
    // Themajorityelement istheelementthatappearsmorethan⌊n/2⌋times.Youmayassume
    // that the majority element always exists in the array.
    // Sample Input 1: nums = [3,2,3]
    // Sample Output 1: 3
    // Sample Input 2: nums = [2,2,1,1,1,2,2]
    // Sample Output 2: 2
    // Constraints(extra Conditions):
    // ● n == nums.length
    // ● 1 <= n <= 5 * 104
    // ●-109 <= nums[i] <= 109

    // brute force approach
    public static int majorityElement(int[] nums) {
        int majorityCount = nums.length / 2;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    count += 1;
                }
            }
            if (count > majorityCount) {
                return nums[i];
            }
        }
        return -1;
    }

    // Approach 2- Divide & Conquer
    // Idea : Ifwe knowthemajorityelement in the left and right halvesof
    // anarray,wecan
    // determine which is the global majority element in linear time.
    // Here,weapplyaclassical divide&conquer approachthat recursesontheleftandright
    // halvesofanarrayuntilananswercanbetriviallyachievedforalength-1array.Notethat
    // becauseactuallypassingcopiesofsubarrayscoststimeandspace,weinsteadpassloand
    // hi indices that describe the relevant sliceof theoverall array. In thiscase,
    // themajority
    // element foralength-1sliceistrivially itsonlyelement,sotherecursionstopsthere.
    // Ifthe
    // currentsliceis longer
    // thanlength-1,wemustcombinetheanswersfortheslice'sleftand
    // righthalves. Iftheyagreeonthemajorityelement,
    // thenthemajorityelementfortheoverall
    // sliceisobviouslythesame[1].Iftheydisagree,onlyoneofthemcanbe"right",soweneedto
    // counttheoccurrencesoftheleftandrightmajorityelementstodeterminewhichsubslice's
    // answer isgloballycorrect. Theoverall answer for thearray is thus
    // themajorityelement
    // between indices 0 and n.
    // Time complexity - O(n*logn)

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }

        return count;
    }

    private static int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }
        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);
        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }
        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    public static int MajorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    // Question 3 : Given an array of integers. Find theInversion Countin the array.
    // (HARD)
    // InversionCount:For an array,
    // inversioncountindicateshowfar(orclose)thearrayisfrom
    // beingsorted. If thearray isalreadysorted then the inversioncount is0.
    // Ifanarray is
    // sorted in the reverse order then the inversion count is the maximum.
    // Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i <
    // j.
    // Sample Input 1: N = 5, arr[ ] = {2, 4, 1, 3, 5}
    // Sample Output 1: 3, because it has 3 inversions -(2, 1), (4, 1), (4, 3).

    // Sample Input 2 : N = 5, arr[ ] = {2, 3, 4, 5, 6}
    // Sample Output 2 : 0, because the array is already sorted
    // Sample Input 3 : N = 3, arr[] = {5, 5, 5}
    // Sample Output 3 : 0, because all the elements of the array are the same &
    // already in a sorted
    // manner.
    // (Hint : A sorting algorithm will be used to solve this question.)
    // Note- This question is important. Even if you are not able to come up with
    // the approach,
    // please understand the solution.

    public static int getInvCount(int arr[]) {
        int n = arr.length;
        int invCount = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    invCount++;
                }
            }
        }
        return invCount;
    }

    // optimum

    public static int merge(int arr[], int left, int mid, int right) {
        int i = left, j = mid, k = 0;
        int invCount = 0;
        int temp[] = new int[(right - left + 1)];
        while ((i < mid) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                k++;
                i++;
            } else {
                temp[k] = arr[j];
                invCount += (mid - i);
                k++;
                j++;
            }
        }
        while (i < mid) {
            temp[k] = arr[i];
            k++;
            i++;
        }
        while (j <= right) {
            temp[k] = arr[j];
            k++;
            j++;
        }
        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];
        }
        return invCount;
    }

    private static int mergeSort(int arr[], int left, int right) {
        int invCount = 0;
        if (right > left) {
            int mid = (right + left) / 2;
            invCount = mergeSort(arr, left, mid);
            invCount += mergeSort(arr, mid + 1, right);
            invCount += merge(arr, left, mid + 1, right);
        }
        return invCount;
    }

    public static int getInversions(int arr[]) {
        int n = arr.length;
        return mergeSort(arr, 0, n - 1);
    }

    public static void main(String[] args) {
        String[] arr = { "sun", "earth", "mars", "mercury" };
        String[] a = mergeSort(arr, 0, arr.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

        int nums[] = { 2, 2, 1, 1, 1, 2, 2 };
        System.out.println(majorityElement(nums));

        // int nums[] = { 2, 2, 1, 1, 1, 2, 2 };
        // System.out.println(majorityElement(nums));

        // int arr[] = { 1, 20, 6, 4, 5 };
        // System.out.println("Inversion Count = " + getInvCount(arr));

        // int arr[] = { 1, 20, 6, 4, 5 };
        // System.out.println("Inversion Count = " + getInversions(arr));

    }
}
