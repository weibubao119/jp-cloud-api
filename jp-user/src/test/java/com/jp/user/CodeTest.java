package com.jp.user;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : xh
 * @date : Created in 2022/7/27 13:13
 */
@SpringBootTest
public class CodeTest {

    // 二分查找法
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (true) {
            int min = start + (end - start) / 2;

            if (target == nums[min]) {
                return min;
            }
            if (target < nums[min]) {
                end = min - 1;
            }
            if (target > nums[min]) {
                start = min + 1;
            }
            if (start > end) {
                return -1;
            }
        }
    }

//    public int firstBadVersion(int n) {
//        int start = 0;
//        int end = n - 1;
//
//        while (start <= end){
//            int min = start + (end - start) / 2;
//            if (isBadVersion(min)){
//                end = min - 1;
//            }else {
//                start = min + 1;
//            }
//        }
//        return start;
//    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int min = left + (right - left) / 2;
            if (target == nums[min]) {
                return min;
            }
            if (target > nums[min]) {
                left = min + 1;
            }
            if (target < nums[min]) {
                right = min - 1;
            }
        }
        return left;
    }

    /**
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     *
     * @param nums
     * @return
     */
    // 双指针
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        // 定义两个指针 i 和 j
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            // 比较两个指针上的数的平方 大的放到最终结果--重点 逆序放所以定义新数组坐标为 pos = n - 1
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                // 向后移动指针
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                // 向前移动指针
                --j;
            }
            --pos;
        }
        return ans;
    }

    /**
     * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     *
     * @param nums
     * @param k
     * @return
     */
    // 数组翻转
    // 思路如下：
    // 首先对整个数组实行翻转，这样子原数组中需要翻转的子数组，就会跑到数组最前面。
    // 这时候，从 kk 处分隔数组，左右两数组，各自进行翻转即可。
    public static int[] rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1); // 整个数组翻转
        reverse(nums, 0, k - 1); // k前面的数组翻转
        reverse(nums, k, nums.length - 1); // k后面的数组翻转
        return nums;
    }

    public static int[] reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            ++start;
            --end;
        }
        return nums;
    }


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
     * 示例 1:
     * 输入: nums = [0,1,0,3,12] // j=3 i=5   1 3 2 0 0
     * 输出: [1,3,12,0,0]
     *
     * @param nums
     */
    public static int[] moveZeroes(int[] nums) {
        if (nums == null) {
            return nums;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果i的位置不等于0 则交换i和j位置的值 把不等于0的放到左边，等于0的放到右边(i等于0的情况下j的位置不变)
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
        return nums;
    }

    /**
     * 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列 ，请你从数组中找出满足相加之和等于目标数target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     * <p>
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     * <p>
     * 要求时间复杂度 O(n)
     *
     * @param numbers
     * @param target
     * @return
     */
    // 双链表 缩减空间
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int num = numbers[i] + numbers[j];
            if (num > target) {
                // 如果加起来大于这个数，那就是最小的加最大的还大于target，数组是递增的，所以j要减1，i不用增加，因为越来越大结果还是最大
                j--;
            } else if (num < target) {
                // 如果加起来小于这个数，那只能让小的再往后挪，然i加1
                i++;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 示例 1：
     * 输入：s = ["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * @param s
     */
    public static char[] reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j){
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i ++;
            j --;
        }
        return s;
    }

    /**
     * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例 1：
     * 输入：s = "Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"   2323334
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        int length = s.length();
        int i = 0;
        while (i < length){
            int start = i; // 每个单词的起始位置
            // 通过空格找到这个单词
            while (i < length && s.charAt(i) != ' '){
                // i一直加到空格全面 就是这个单词的最后一个位置
                i ++;
            }
            // 找到最后一个位置就能倒序插入到新的数组
            for (int p = start; p < i; p++){   // 0 + 5 - 1 - 1
                builder.append(s.charAt(start + i - 1 - p));
            }
            // 拼接上空格
            while (i < length && s.charAt(i) == ' '){
                i ++;
                builder.append(' ');
            }
        }
        return builder.toString();
    }

    public static int recursion(int n){
        if (n == 1){
            return n;
        }
        return n * recursion(n - 1);
    }


    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     * 思路： 使用两个指针变量，刚开始都位于链表的第 1 个结点，一个永远一次只走 1 步，一个永远一次只走 2 步，一个在前，一个在后，同时走,
     * 这样当快指针走完的时候，慢指针就来到了链表的中间位置
     *
     * 快指针前进的条件：当前的快指针和当前快指针的下一个节点都必须是非空
     * @param
     * @return
     */
//    public ListNode middleNode(ListNode head) {
//        if (head == null) {
//            return null;
//        }
//        ListNode slow = head;
//        ListNode fast = head;
//
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return slow;
//    }

    public static void main(String[] args) {

        System.out.println(recursion(10));

//        String s = "Let's take LeetCode contest";
//        System.out.println(reverseWords(s));


//        char[] s = {'h','e','l','l','o'};
//        System.out.println(Arrays.toString(reverseString(s)));

//        int[] numbers = {2,7,11,15};
//        System.out.println(Arrays.toString(twoSum(numbers,9)));

//        int[] nums = {3, 6, 4, 2, 11, 15};
//        System.out.println(Arrays.toString(bubbling(nums)));

//        int[] num3 = {0, 1, 0, 3, 12};
//        System.out.println(Arrays.toString(moveZeroes(num3)));

//        int[] num2 = {1,2,3,4,5,6,7};
//        System.out.println(Arrays.toString(rotate(num2,3)));

//        int[] num1 = {-4,-1,0,3,10};
//        System.out.println(Arrays.toString(num1));
//        System.out.println(Arrays.toString(sortedSquares(num1)));

//
//        int[] nums = {1, 3, 5, 6, 7, 8, 9};
//        System.out.println("坐标:" + search(nums, 6));
    }
}
