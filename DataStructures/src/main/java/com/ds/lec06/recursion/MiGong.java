package com.ds.lec06.recursion;

/**
 * @author zhwanwan
 * @create 2019-09-02 14:10
 */
public class MiGong {

    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部设置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部设置为1```
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("初始地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        go2(map, 1, 1);
        System.out.println("小球走过，并标识过的地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 走迷宫
     * 说明：
     * 1.map表示地图
     * 2.i,j表示从地图哪个位置出发
     * 3.如果小球能到map[6][5]位置，则说明通路找到
     * 4.约定：map[i][j]的值
     * 0：该点没有走过；
     * 1：墙
     * 2：通路可以走
     * 3：该点已走过，但不通
     * 5.走迷宫时，确定一个走法策略：下 -> 右 -> 上 -> 左，如果该点走不通再回溯
     *
     * @param map 地图
     * @param i
     * @param j
     * @return 如果找到通路就返回true, 否则返回false
     */
    public static boolean go(int[][] map, int i, int j) {
        if (map[6][5] == 2) //通路已找到
            return true;
        else {
            if (map[i][j] == 0) { //当前位置还没走过
                //按照策略走
                map[i][j] = 2; //假设该位置可以走通
                if (go(map, i + 1, j))      //向下走
                    return true;
                else if (go(map, i, j + 1)) //向右走
                    return true;
                else if (go(map, i - 1, j)) //向上走
                    return true;
                else if (go(map, i, j - 1)) //向左走
                    return true;
                else {
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果map[i][j] != 0,可能是 1,2,3
                return false;
            }
        }
    }

    /**
     * 修改走法：上->右->下->左
     *
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean go2(int[][] map, int i, int j) {
        if (map[6][5] == 2)
            return true;
        else {
            if (map[i][j] == 0) {
                map[i][j] = 2; //先假设该点可通，按上右下左的顺序尝试走
                if (go2(map, i - 1, j))       //向上走
                    return true;
                else if (go2(map, i, j + 1))  //向右走
                    return true;
                else if (go2(map, i + 1, j))  //向下走
                    return true;
                else if (go2(map, i, j - 1))  //向左走
                    return true;
                else {
                    //走不通
                    map[i][j] = 3;
                    return false;
                }
            } else
                return false;
        }
    }

}
