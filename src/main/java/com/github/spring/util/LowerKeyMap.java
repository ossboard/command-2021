package com.github.spring.util;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.sql.Clob;

public class LowerKeyMap extends ListOrderedMap {

    private static final long serialVersionUID = -7700790403928325865L;
    public Object put(Object key, Object value) {
        if (value instanceof java.sql.Clob) {
            value = clob2String((java.sql.Clob) value);
        }
        return super.put(StringUtils.lowerCase((String) key), value);
    }

    public static String clob2String(Clob clob) {
        if (clob == null) {
            return "";
        }
        StringBuffer strOut = new StringBuffer();
        try {
            String str = "";
            BufferedReader br = null;
            br = new BufferedReader(clob.getCharacterStream());
            while ((str = br.readLine()) != null) {
                strOut.append(str);
            }
        } catch (Exception ignore) {
            //
        }
        return strOut.toString();
    }

}
