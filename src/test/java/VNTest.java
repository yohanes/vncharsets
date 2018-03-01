import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class VNTest {

    static         String test_str = "Ti\u1ebfng Vi\u1ec7t, c\u00f2n g\u1ecdi"+
            " ti\u1ebfng Vi\u1ec7t Nam hay Vi\u1ec7t ng\u1eef, l\u00e0"+
            " ng\u00f4n ng\u1eef c\u1ee7a ng\u01b0\u1eddi Vi\u1ec7t "+
            "(ng\u01b0\u1eddi Kinh) v\u00e0 l\u00e0 ng\u00f4n ng\u1eef"+
            " ch\u00ednh th\u1ee9c t\u1ea1i Vi\u1ec7t Nam.\n";

    static byte test_bytes[] = new byte [] {(byte)0x54, (byte)0x69, (byte)0xd5, (byte)0x6e, (byte)0x67,
            (byte)0x20, (byte)0x56, (byte)0x69, (byte)0xd6, (byte)0x74, (byte)0x2c, (byte)0x20,
            (byte)0x63, (byte)0xdf, (byte)0x6e, (byte)0x20, (byte)0x67, (byte)0xe4, (byte)0x69,
            (byte)0x20, (byte)0x74, (byte)0x69, (byte)0xd5, (byte)0x6e, (byte)0x67, (byte)0x20,
            (byte)0x56, (byte)0x69, (byte)0xd6, (byte)0x74, (byte)0x20, (byte)0x4e, (byte)0x61,
            (byte)0x6d, (byte)0x20, (byte)0x68, (byte)0x61, (byte)0x79, (byte)0x20, (byte)0x56,
            (byte)0x69, (byte)0xd6, (byte)0x74, (byte)0x20, (byte)0x6e, (byte)0x67, (byte)0xf7,
            (byte)0x2c, (byte)0x20, (byte)0x6c, (byte)0xb5, (byte)0x20, (byte)0x6e, (byte)0x67,
            (byte)0xab, (byte)0x6e, (byte)0x20, (byte)0x6e, (byte)0x67, (byte)0xf7, (byte)0x20,
            (byte)0x63, (byte)0xf1, (byte)0x61, (byte)0x20, (byte)0x6e, (byte)0x67, (byte)0xad,
            (byte)0xea, (byte)0x69, (byte)0x20, (byte)0x56, (byte)0x69, (byte)0xd6, (byte)0x74,
            (byte)0x20, (byte)0x28, (byte)0x6e, (byte)0x67, (byte)0xad, (byte)0xea, (byte)0x69,
            (byte)0x20, (byte)0x4b, (byte)0x69, (byte)0x6e, (byte)0x68, (byte)0x29, (byte)0x20,
            (byte)0x76, (byte)0xb5, (byte)0x20, (byte)0x6c, (byte)0xb5, (byte)0x20, (byte)0x6e,
            (byte)0x67, (byte)0xab, (byte)0x6e, (byte)0x20, (byte)0x6e, (byte)0x67, (byte)0xf7,
            (byte)0x20, (byte)0x63, (byte)0x68, (byte)0xdd, (byte)0x6e, (byte)0x68, (byte)0x20,
            (byte)0x74, (byte)0x68, (byte)0xf8, (byte)0x63, (byte)0x20, (byte)0x74, (byte)0xb9,
            (byte)0x69, (byte)0x20, (byte)0x56, (byte)0x69, (byte)0xd6, (byte)0x74, (byte)0x20,
            (byte)0x4e, (byte)0x61, (byte)0x6d, (byte)0x2e, (byte)0x0a};


    @Test
    public void testFromBytes() throws java.io.UnsupportedEncodingException {
        String test_2 = new String(test_bytes, "TCVN-3-1");
        assertEquals(test_2, test_str);
    }


    @Test
    public void testToBytes() throws java.io.UnsupportedEncodingException  {
        byte b [] = test_str.getBytes("TCVN-3-1");
        assertTrue(Arrays.equals(b, test_bytes));
    }

}
