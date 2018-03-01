//Copyright (c) 2018 Yohanes Nugroho
//Licensed under the Apache License, Version 2.0
package com.tinyhack.vncharset;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class VNCharsetProvider extends CharsetProvider {

    Map<String, Charset> charsets = new Hashtable<>(1);

    public VNCharsetProvider() {
        super();
        TCVN3 tcvn3_1 = new TCVN3("TCVN-3-1", null);
        TCVN3 tcvn3_2 = new TCVN3("TCVN-3-2", null);
        charsets.put(tcvn3_1 .name(), tcvn3_1);
        //aliases
        charsets.put("TCVN3-1", tcvn3_1);
        charsets.put("TCVN3-2", tcvn3_2);
    }

    @Override
    public Iterator<Charset> charsets() {
            return charsets.values().iterator();
    }

    @Override
    public Charset charsetForName(String charsetName) {
        if (charsets.containsKey(charsetName)) {
            return  charsets.get(charsetName);
        }
        return null;
    }
}
