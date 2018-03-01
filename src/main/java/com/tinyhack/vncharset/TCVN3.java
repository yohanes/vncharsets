//Copyright (c) 2018 Yohanes Nugroho
//Licensed under the Apache License, Version 2.0
package com.tinyhack.vncharset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class TCVN3 extends Charset {


    private final String TVCN3_1 = "                                        "+
            "\u0103\u00e2\u00ea\u00f4\u01a1\u01b0\u0111      \u00e0\u1ea3\u00e3"+
            "\u00e1\u1ea1 \u1eb1\u1eb3\u1eb5\u1eaf       \u1eb7\u1ea7\u1ea9"+
            "\u1eab\u1ea5\u1ead\u00e8 \u1ebb\u1ebd\u00e9\u1eb9\u1ec1\u1ec3\u1ec5"+
            "\u1ebf\u1ec7\u00ec\u1ec9   \u0129\u00ed\u1ecb\u00f2 \u1ecf\u00f5"+
            "\u00f3\u1ecd\u1ed3\u1ed5\u1ed7\u1ed1\u1ed9\u1edd\u1edf\u1ee1\u1edb"+
            "\u1ee3\u00f9 \u1ee7\u0169\u00fa\u1ee5\u1eeb\u1eed\u1eef\u1ee9\u1ef1"+
            "\u1ef3\u1ef7\u1ef9\u00fd\u1ef5 ";

    private final String TVCN3_2 ="                                 "+
            "\u0102\u00c2    \u00d0  \u00ca\u00d4\u01a0\u01af       "+
            "\u00c0\u1ea2\u00c3\u00c1\u1ea0 \u1eb0\u1eb2\u1eb4\u1eae       "+
            "\u1eb6\u1ea6\u1ea8\u1eaa\u1ea4\u1eac\u00c8 \u1eba\u1ebc\u00c9"+
            "\u1eb8\u1ec0\u1ec2\u1ec4\u1ebe\u1ec6\u00cc\u1ec8   \u0128"+
            "\u00cd\u1eca\u00d2 \u1ece\u00d5\u00d3\u1ecc\u1ed2\u1ed4\u1ed6"+
            "\u1ed0\u1ed8\u1edc\u1ede\u1ee0\u1eda\u1ee2\u00d9 \u1ee6\u0168"+
            "\u00da\u1ee4\u1eea\u1eec\u1eee\u1ee8\u1ef0\u1ef2\u1ef6\u1ef8"+
            "\u00dd\u1ef4 ";

    static class TVNC3Encoder extends  CharsetEncoder {

        protected TVNC3Encoder(TCVN3 cs) {
            super(cs, 1, 1);
        }

            @Override
        protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
            String tab = ((TCVN3)charset()).table;
            while (in.hasRemaining()) {
                char c = in.get();
                if (((int)c)<128) {
                    out.put((byte)c);
                } else {
                    int idx = tab.indexOf(c);
                    if (idx>=0){
                        out.put((byte)(128+idx));
                    } else {
                        return CoderResult.unmappableForLength(1);
                    }
                }

            }
            return CoderResult.UNDERFLOW;
        }
    }

    private String table;

    //An engine that can transform a sequence of bytes in a specific charset into a sequence of sixteen-bit Unicode characters.
    static class TVCN3Decoder extends CharsetDecoder {

        /**
         * Initializes a new decoder.  The new decoder will have the given
         * chars-per-byte values and its replacement will be the
         * string <tt>"&#92;uFFFD"</tt>.
         *
         * @param cs                  The charset that created this decoder
         * @throws IllegalArgumentException If the preconditions on the parameters do not hold
         */
        protected TVCN3Decoder(TCVN3 cs) {
            super(cs, 1, 1);
        }

        @Override
        protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
            String tab = ((TCVN3)charset()).table;
            while (in.hasRemaining()) {
                int by = (in.get() & 0xff);
                if (by<128){
                    out.put((char)by);
                } else {
                    out.put(tab.charAt(by-128));
                }
            }
            return CoderResult.UNDERFLOW;
        }
    }



    /**
     * Initializes a new charset with the given canonical name and alias
     * set.
     *
     * @param canonicalName The canonical name of this charset
     * @param aliases       An array of this charset's aliases, or null if it has no aliases
     * @throws IllegalCharsetNameException If the canonical name or any of the aliases are illegal
     */
    protected TCVN3(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
        if (canonicalName.equals("TCVN-3-1")) {
            table = TVCN3_1;
        } else if (canonicalName.equals("TCVN-3-2")) {
            table = TVCN3_2;
        } else {
            throw new IllegalCharsetNameException("Illegal charset name " + canonicalName);
        }
        this.table = table;
    }

    @Override
    public boolean contains(Charset cs) {
        return cs.name().equals(this.name());
    }

    @Override
    public CharsetDecoder newDecoder() {
        return new TVCN3Decoder(this);
    }

    @Override
    public CharsetEncoder newEncoder() {
        return new TVNC3Encoder(this);
    }
}
