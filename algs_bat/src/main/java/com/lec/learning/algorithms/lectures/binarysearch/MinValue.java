package com.lec.learning.algorithms.lectures.binarysearch;

/**
 * Created by brian on 16/11/15.
 *
 * 对于一个有序循环数组arr，返回arr中的最小值。
 * 有序循环数组是指，有序数组左边任意长度的部分放到右边去，右边的部分拿到左边来。
 * 比如数组[1,2,3,3,4]，是有序循环数组，[4,1,2,3,3]也是。
 * 给定数组arr及它的大小n，请返回最小值。
 *
 * 测试样例：
 * [4,1,2,3,3],5
 * 返回：1
 */
public class MinValue {
    public int getMin(int[] arr, int n) {
        if (arr == null || n == 0) {
            return -1;
        }

        int lo = 0;
        int hi = n - 1;
        int mid = 0;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            //System.out.println("lo:" + arr[lo] + " hi:" + arr[hi] + " mid:" + arr[mid]+"          lo:" + lo + " hi:" + hi + " mid:" + mid);
            if (arr[lo] < arr[hi]) {
                return arr[lo];
            }

            if (arr[lo] > arr[mid]) {
                hi = mid;
            } else if (arr[lo] < arr[mid]) {
                lo = mid;
            } else {
                int min = arr[lo];
                for (int i = lo; i <= hi; i++) {
                    if (arr[i] < min) {
                        min = arr[i];
                        break;
                    }
                }
                return min;
            }

        }

        return arr[lo];

    }

    public static void main(String[] args) {
        //int []a = {4,1,2,3,3};
        //int []a ={1,2,3,4,5};
        int[] a = {236, 239, 239, 240, 240, 241, 242, 243, 244, 246, 252, 255, 257, 275, 275, 276, 279, 282, 283, 287, 290,
                293, 300, 302, 303, 305, 309, 314, 314, 315, 320, 327, 330, 333, 333, 338, 340, 343, 344, 348, 352, 354, 363,
                365, 369, 372, 376, 376, 376, 378, 380, 381, 383, 386, 391, 398, 398, 404, 405, 416, 417, 425, 427, 432, 432,
                440, 443, 444, 445, 446, 446, 446, 446, 447, 447, 449, 454, 455, 455, 455, 455, 458, 459, 465, 467, 476, 485,
                485, 487, 488, 496, 498, 502, 503, 504, 509, 512, 515, 517, 520, 520, 529, 532, 537, 541, 544, 552, 553, 555,
                555, 559, 561, 566, 568, 577, 579, 582, 587, 588, 593, 601, 607, 608, 609, 613, 614, 618, 621, 621, 1, 2, 6, 13,
                23, 26, 30, 32, 32, 33, 35, 37, 37, 39, 45, 49, 50, 55, 55, 64, 64, 66, 71, 73, 81, 81, 82, 86, 92, 95, 112, 113, 115,
                116, 119, 124, 125, 125, 126, 127, 132, 132, 133, 134, 150, 153, 156, 158, 159, 163, 163, 169, 177, 177, 177,
                180, 181, 184, 185, 187, 187, 188, 191, 192, 194, 195, 196, 205, 209, 210, 210, 212, 217, 217, 221, 224, 224,
                225, 225, 226, 229, 233, 234};
        MinValue mv = new MinValue();
        int min = mv.getMin(a, a.length);
        System.out.println(min);
    }
}
