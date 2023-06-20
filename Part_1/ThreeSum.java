import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSum {
  private int[] initArray;
  private int[] result;
  // private int[] sortIndex;

  public ThreeSum(int[] array) {
    initArray = array;
    int index = 0;
    int index2 = 0;
    int index3 = 0;
    while (index < array.length) {
      for (int i = index; i < array.length; i++) {
        int res = binarySearch(array, getSubtractionValue(initArray[index], initArray[i + 1]));
        if (res > 0) {
          index2 = i + 1;
          index3 = res;
          break;
        }
      }
      if (index2 > 0 && index3 > 0) {
        result = new int[] { array[index], array[index2], array[index3] };
        return;
      }
      index++;
    }
  }

  public int[] getResult() {
    return this.result;
  }

  public static int getSubtractionValue(int a, int b) {
    return -(a + b);
  }

  public static int binarySearch(int[] a, int key) {
    int lo = 0, hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (key < a[mid])
        hi = mid - 1;
      else if (key > a[mid])
        lo = mid + 1;
      else
        return mid;
    }
    return -1;
  }

  public static void main(String[] a) {
    int[] args = { -1, -2, 0, 3 };
    ThreeSum threeSum = new ThreeSum(args);
    System.out.println(Arrays.toString(threeSum.getResult()));
  }
}
