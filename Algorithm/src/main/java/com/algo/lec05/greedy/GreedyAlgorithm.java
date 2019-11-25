package com.algo.lec05.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 贪心算法：
 * 贪婪算法(贪心算法)是指在对问题进行求解时，在每一步选择中都采取最好或者最优(即最有利)的选择，
 * 从而希望能够导致结果是最好或者最优的算法；
 * 贪婪算法所得到的结果不一定是最优的结果(有时候会是最优解)，但是都是相对近似(接近)最优解的结果。
 * <p>
 * 思路分析:
 * 使用贪婪算法，效率高:
 * 目前并没有算法可以快速计算得到准备的值，使用贪婪算法，则可以得到非常接近的解，并且效率高。
 * 选择策略上，因为需要覆盖全部地区的最小集合:
 * 遍历所有的广播电台, 找到一个覆盖了最多未覆盖的地区的电台(此电台可能包含一些已覆盖的地区，但没有关系）
 * 将这个电台加入到一个集合中(比如ArrayList), 想办法把该电台覆盖的地区在下次比较时去掉。
 * 重复第1步直到覆盖了全部的地区
 * --------------------------------------------------------------------------------------------
 * 贪心算法注意事项和细节
 * <p>
 * 贪婪算法所得到的结果不一定是最优的结果(有时候会是最优解)，但是都是相对近似(接近)最优解的结果
 * 比如上题的算法选出的是K1, K2, K3, K5，符合覆盖了全部的地区
 * 但是我们发现 K2, K3,K4,K5 也可以覆盖全部地区，如果K2的使用成本低于K1,
 * 那么我们上题的 K1, K2, K3, K5 虽然是满足条件，但是并不是最优的.
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        Map<String, Set<String>> broadcasts = new HashMap<>();
        Set<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        Set<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        Set<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        Set<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        Set<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        broadcasts.put("K1", set1);
        broadcasts.put("K2", set2);
        broadcasts.put("K3", set3);
        broadcasts.put("K4", set4);
        broadcasts.put("K5", set5);
        //allAreas 存放所有的地区
        Set<String> allAreas = new HashSet<>();
        broadcasts.forEach((k, set) -> {
            allAreas.addAll(set);
        });
        //allAreas.forEach(System.out::println);
        //存放选择的电台集合
        List<String> selects = new ArrayList<>();
        Set<String> tempSet = new HashSet<>();

        while (!allAreas.isEmpty()) {
            String maxKey = null;
            //遍历broadcasts,取出对应的key
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                //将当前电台覆盖区域加入tempSet
                tempSet.addAll(broadcasts.get(key));
                //求出tempSet和 allAreas的交集, 交集会赋给tempSet
                tempSet.retainAll(allAreas);
                if (!tempSet.isEmpty() && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size()))
                    maxKey = key;
            }
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("选择电台：" + selects);
    }

}
