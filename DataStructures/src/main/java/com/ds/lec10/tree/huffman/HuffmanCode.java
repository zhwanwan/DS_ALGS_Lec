package com.ds.lec10.tree.huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 哈夫曼编码
 * <p>
 * 1.哈夫曼编码(Huffman Coding)，又称霍夫曼编码，是一种编码方式, 属于一种程序算法。
 * 2.哈夫曼树在电讯通信中的经典的应用之一。
 * 3.哈夫曼编码广泛地用于数据文件压缩。其压缩率通常在20%～90%之间。
 * 4.哈夫曼码是可变字长编码(VLC)的一种。Huffman于1952年提出一种编码方法，称之为最佳编码。
 * <p>
 * 哈夫曼编码主要目的是根据使用频率来最大化节省字符（编码）的存储空间。
 * 哈夫曼编码是一种无前缀编码。解码时不会混淆。其主要应用在数据压缩，加密解密等场合。
 *
 * @author zhwanwan
 * @create 2019-09-16 2:59 PM
 */
public class HuffmanCode {
    public static void main(String[] args) {

        /*String content = "i like like like java do you like a java";
        System.out.printf("原字符是\n%s\n长度为 %d\n", content, content.length());
        byte[] contentBytes = content.getBytes();
        System.out.println(Arrays.toString(contentBytes));
        List<Node> nodes = getNodes(contentBytes);
        //nodes.forEach(System.out::println);
        System.out.println("遍历哈夫曼树");
        Node root = createHuffmanTree(nodes);
        HuffmanTree.listHuffmanTreeNodes(root);
        Map<Byte, String> huffmanCodes = getHuffmanCodes(root);
        System.out.println("哈夫曼编码：" + huffmanCodes);
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodeBytes) + " 长度= " + huffmanCodeBytes.length);
        //System.out.println(byteToString(true, (byte) 1));
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println(new String(sourceBytes));*/

        //测试文件压缩
        /*System.out.println("~~~~~~测试文件压缩~~~~~~");
        String srcFile = "C:\\Users\\zhwanwan\\Pictures\\2020-vision1.jpg";
        String dstFile = "D:\\2020-vision1.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩完毕！");*/

        System.out.println("测试文件解压");
        String zipFile = "D:\\2020-vision1.zip";
        String destFile = "D:\\2020-vision1.jpg";
        unzipFile(zipFile, destFile);
        System.out.println("解压完毕！");

    }


    /**
     * 解压文件
     *
     * @param zipFile
     * @param destFile
     */
    public static void unzipFile(String zipFile, String destFile) {
        try (FileInputStream fis = new FileInputStream(zipFile);
             ObjectInputStream ois = new ObjectInputStream(fis);
             FileOutputStream fos = new FileOutputStream(destFile)
        ) {
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //写入文件
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件压缩
     *
     * @param srcFile  被压缩的文件的全路径
     * @param destFile 压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String destFile) {
        try (FileInputStream fis = new FileInputStream(srcFile);
             FileOutputStream fos = new FileOutputStream(destFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {

            //创建一个和源文件大小一样的byte[]
            byte[] bytes = new byte[fis.available()];
            //读取文件
            fis.read(bytes);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(bytes);
            //将哈夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //将哈夫曼编码写入压缩文件，以便解压使用
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 压缩数据的解码
     *
     * @param huffmanCodes
     * @param huffmanBytes
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToString(!flag, b));
        }
        //把字符串安装指定的哈夫曼编码进行解码
        //把哈夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<>();
        huffmanCodes.forEach((key, val) -> map.put(val, key));

        List<Byte> list = new ArrayList<>();
        //i可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; //内部计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count); //i不动让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) //未匹配
                    count++;
                else //匹配到
                    flag = false;
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;

    }

    /**
     * 将一个byte转成一个二进制的字符串
     *
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @param b
     * @return 对应的二进制字符串(补码 ）
     */
    private static String byteToString(boolean flag, byte b) {
        int temp = b;
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp); //返回的是补码
        if (flag)
            return str.substring(str.length() - 8);
        else
            return str;

    }

    /**
     * 根据原始字符对应的字节数组返回哈夫曼压缩后的字节数组
     *
     * @param bytes
     * @return
     */
    public static byte[] huffmanZip(byte[] bytes) {
        //根据原始字节数组生成结点链表nodes
        List<Node> nodes = getNodes(bytes);
        //通过nodes创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //生成哈夫曼编码
        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTreeRoot);
        //生成压缩字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;

    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     *
     * @param bytes        原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 哈夫曼编码处理后的 byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes)
            stringBuilder.append(huffmanCodes.get(b));
        int len = (stringBuilder.length() + 7) / 8;
        //创建压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) { //因为是每8位对应一个byte,所以步长+8
            String byteStr;
            if (i + 8 > stringBuilder.length())
                byteStr = stringBuilder.substring(i);
            else
                byteStr = stringBuilder.substring(i, i + 8);
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(byteStr, 2);
        }
        return huffmanCodeBytes;
    }

    //哈夫曼编码
    //思路:
    //将哈夫曼编码表存放在 Map<Byte,String> 形式
    //生成的哈夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    private static Map<Byte, String> huffmanCodes = new HashMap<>();

    private static StringBuilder builder = new StringBuilder();

    private static Map<Byte, String> getHuffmanCodes(Node root) {
        if (root == null)
            return null;
        getCodes(root.getLeft(), "0", builder);
        getCodes(root.getRight(), "1", builder);
        return huffmanCodes;
    }

    /**
     * 将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node
     * @param code    路径：左子结点是0, 右子结点是1
     * @param builder
     */
    private static void getCodes(Node node, String code, StringBuilder builder) {
        if (node == null)
            return;
        StringBuilder stringBuilder = new StringBuilder(builder);
        stringBuilder.append(code);
        if (node.getData() == null) { //非叶子结点
            //递归处理
            getCodes(node.getLeft(), "0", stringBuilder);
            getCodes(node.getRight(), "1", stringBuilder);
        } else //叶子结点
            huffmanCodes.put(node.getData(), stringBuilder.toString());

    }

    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            /*if (count == null)
                map.put(b, 1);
            else
                map.put(b, count + 1);*/
            map.merge(b, 1, Integer::sum);
        }
        map.forEach((key, val) -> nodes.add(new Node(val, key)));
        return nodes;
    }

    //创建哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.getWeight() + right.getWeight(), null);
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
