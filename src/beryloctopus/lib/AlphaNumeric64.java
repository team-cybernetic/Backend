/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beryloctopus.lib;

import java.nio.ByteBuffer;

/**
 * @author Tootoot222
 */
public class AlphaNumeric64 {

    private static final byte[] alphabet32 = "0123456789abcdfghjkmnpqrstuvwxyz".getBytes();
    private static final byte[] alphabet64 = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz_+=!?".getBytes();
    //private static final byte[] alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_.".getBytes();

    public static String toAlphaNumeric64(byte[] in) {
        Debug.debug("wut");
        if (in == null || in.length == 0) {
            return (null);
        }
        StringBuilder res = new StringBuilder();
        byte[] arr;
        int mod = in.length % 3;
        Debug.debug("in length: %d, mod %d", in.length, mod);
        if (mod != 0) {
            arr = ByteBuffer.allocate(in.length + (3 - mod)).array();
            System.arraycopy(in, 0, arr, 0, in.length);
        } else {
            arr = in;
        }
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            mod = i % 3;
            Debug.debug("i: %d, mod %d, arr[i] = %d %02x", i, mod, arr[i], arr[i]);
            switch (mod) {
                case 0:
                    num = (arr[i] & 0xFC) >>> 2;
                    Debug.debug("alphabet64[%d] = '%c'", num, alphabet64[num]);
                    res.append((char) alphabet64[num]);
                    num = (arr[i] & 0x03) << 4;
                    Debug.debug("num = 0x%02x & 0x03 = 0x%02x, << 6 = 0x%02x", arr[i], arr[i] & 0x03, (arr[i] & 0x03) << 4);
                    break;
                case 1:
                    Debug.debug("num = %d [0x%02x] | ((0x%02x &0xF0) [0x%02x] >>> 4) [0x%02x] = 0x%02x", num, num, arr[i], arr[i] & 0xF0, (arr[i] & 0xF0) >>> 4, num | ((arr[i] & 0xF0) >>> 4));
                    num = num | ((arr[i] & 0xF0) >>> 4);
                    Debug.debug("alphabet64[%d] = '%c'", num, alphabet64[num]);
                    res.append((char) alphabet64[num]);
                    num = (arr[i] & 0x0F) << 2;
                    break;
                case 2:
                    num = num | ((arr[i] & 0xC0) >>> 6);
                    Debug.debug("alphabet64[%d] = '%c'", num, alphabet64[num]);
                    res.append((char) alphabet64[num]);
                    num = arr[i] & 0x3F;
                    Debug.debug("alphabet64[%d] = '%c'", num, alphabet64[num]);
                    res.append((char) alphabet64[num]);
                    break;
            }
            Debug.debug("res = \"%s\"", res.toString());
        }
        return (res.toString());
    }

    public static void main(String[] args) {
        byte[] arr1 = {(byte) 0xFF};
        /*
        00 0000 | 01 0000 | 00 1000 | 00 0011
        0, 16, 8, 3
        0G83
        */
        Debug.debug("arr1 toAlphaNum64 = \"%s\"", toAlphaNumeric64(arr1));
    }
}
