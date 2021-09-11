public class ArrayMaxEle {
    public static int max(int[] m) {
    	int max = Integer.MIN_VALUE;
    	for(int i = 0; i < m.length; i++) {
    		if (m[i] > max) {
    			max = m[i];
    		}
    	}
    	return max;
    }

	public static void main(String[] args) {
		int[] nums = new int[]{9, 2, 15, 2, 22, 10, 6};
		System.out.println(max(nums));
	}
}