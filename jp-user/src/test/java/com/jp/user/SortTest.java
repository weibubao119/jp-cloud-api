package com.jp.user;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author : xh
 * @date : Created in 2022/8/2 13:34
 */
@SpringBootTest
public class SortTest {

    /**
     * 冒泡排序
     *
     * @param nums
     * @return
     */
    public static int[] bubbling(int[] nums) {
        int n = nums.length;
        // 外层需要遍历n-1次
        for (int i = 0; i < n - 1; i++) {
            boolean isChange = false;
            // 内层需要遍历n-1-i次，因为前面遍历的i已经放到后面正确的位置 就不用再遍历了
            for (int j = 0; j < n - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tem = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tem;
                    isChange = true;
                }
            }
            if (!isChange) {
                return nums;
            }
        }
        return nums;
    }

    /**
     * 插入排序
     * 直接插入排序的基本操作是将一个记录插入到已经排好的有序表中，从而得到一个新的、记录数增1的有序表。
     * 对于给定的一组记录，初始时假定第一个记录自成一个有序序列，其余记录为无序序列。接着从第二个记录开始，
     * 按照记录的大小依次将当前处理的记录插入到其之前的有序序列中，直到最后一个记录插到有序序列中为止。
     *
     * @param nums
     * @return
     */
    public static int[] insertionSort(int[] nums) {
        // 插入排序中，当待排序数组是有序时，是最优的情况，只需当前数跟前一个数比较一下就可以了，这时一共需要比较 N-1 次，时间复杂度为 O(N)。最坏的情况是待排序数组是逆序的，此时需要比较次数最多，最坏的情况是 O(n^2)
        for (int i = 0; i < nums.length; i++) {
            // 寻找元素 nums[i] 合适的插入位置
            // 这块是一直往前循环 交换之后还要判断这个数前面的跟他的大小 所以j取i的值 并且j--
            for (int j = i; j > 0; j--) {
                // 如果后面的小于前面的就互换
                if (nums[j] < nums[j - 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }
        return nums;
    }

    private static void quickSort(int[] array, int low, int high) {

        if (low >= high) {
            return;
        }
        int i = low, j = high, index = array[i]; // 取最左边的数作为基准数
        while (i < j) {
            while (i < j && array[j] >= index) { // 向左寻找第一个小于index的数
                j--;
            }
            if (i < j) {
                array[i++] = array[j]; // 将array[j]填入array[i]，并将i向右移动
            }
            while (i < j && array[i] < index) {// 向右寻找第一个大于index的数
                i++;
            }
            if (i < j) {
                array[j--] = array[i]; // 将array[i]填入array[j]，并将j向左移动
            }
        }
        array[i] = index; // 将基准数填入最后的坑
        quickSort(array, low, i - 1); // 递归调用，分治
        quickSort(array, i + 1, high); // 递归调用，分治
    }

    /**
     * 快速排序
     * 从要排序的数据中取一个数为“基准数”。
     * 通过一趟排序将要排序的数据分割成独立的两部分，其中左边的数据都比“基准数”小，右边的数据都比“基准数”大。
     * 然后再按步骤2对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     *
     * @param array
     */
    public static void quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 简单快速排序
     * 每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕
     *
     * @param array
     */
    public static int[] selectionSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            // 假如k是最小的
            int k = i;
            for (int j = k + 1; j < array.length; j++) {
                if (array[j] < array[k]) {
                    k = j; // 说明j是最小的 把j给到k
                }
            }
            // 交换array[i] 和 array[k]
            if (i != k){
                int tmp = array[i];
                array[i] = array[k];
                array[k] = tmp;
            }

        }
        return array;
    }

    public static void main(String[] args) {
        int[] selectNums = {7, 6, 9, 3, 1, 5, 2, 4};
        System.out.println("选择排序:" + Arrays.toString(selectionSort(selectNums)));

        int[] nums = {7, 6, 9, 3, 1, 5, 2, 4};
        System.out.println(Arrays.toString(insertionSort(nums)));
    }
}
