public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    int length = a.length;
    for (int i = 0; i < length - 1; i++) {
      Boolean isPositive = a[i] > 0;
      if (!isPositive) {
        continue;
      }

      for (int j = i + 1; j < Math.min(i + 1 + n, length); j++) {
        a[i] += a[j];
      }
    }
  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}