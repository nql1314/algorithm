package 二分查找;

public class BinarySearch {
    /**
     * 正常实现 log(n)
     * @param nums
     * @param k
     * @return
     */
    public int binarySearch(int[] nums,int k) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l+(h-l)/2;
            if(nums[m]==k){
                return m;
            }else if(nums[m]<k){
                l = m+1;
            }else{
                h = m-1;
            }
        }
        return -1;
    }

}
