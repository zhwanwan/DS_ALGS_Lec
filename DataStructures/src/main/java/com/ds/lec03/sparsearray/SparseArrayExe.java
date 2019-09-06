package com.ds.lec03.sparsearray;

import java.io.*;

/**
 * 1.将稀疏数组保存到磁盘map.data
 * 2.读取磁盘文件map.data恢复原始数组
 *
 * @author zhwanwan
 * @create 2019-08-15 8:13
 */
public class SparseArrayExe {


    public static void saveToFile(int[][] sparseArray, String path) throws IOException {

        File file = new File(path);
        if (!file.exists())
            file.createNewFile();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(sparseArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static int[][] recoverFromFile(String path) {
        File file = new File(path);
        int[][] arrs = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            arrs = (int[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return arrs;

    }

    public static void main(String[] args) throws Exception {
        //创建原始二维数组
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;
        int[][] sparseArray = SparseArray.convertToSparseArray(chessArray);
        //将稀疏数组存盘
        saveToFile(sparseArray, "map.data");
        //从文件恢复
        int[][] sparseArrs = recoverFromFile("map.data");
        int[][] arrs = SparseArray.convertToArrays(sparseArrs);
        SparseArray.printArrays(arrs);
    }

}
