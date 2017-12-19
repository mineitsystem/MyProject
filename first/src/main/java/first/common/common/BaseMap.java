/**
 * 프로그램명: BaseMap.java
 * 내     용:
 * 이     력:
 *  ---------------------------------------------------------------
 *  Revision    Date            Author      Description
 *  ---------------------------------------------------------------
 *  ver1.0      2013. 07. 01.   아무개              최초 작성
 */
package first.common.common;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * controller -> Service -> Dao
 * DTO 처리를 위한 MAP객체
 * VO,DTO 대신 처리.
 * @author tilldawn
 *
 */
public class BaseMap extends HashMap<Object, Object> {

    private static final long serialVersionUID = 1L;

    protected String name = null;

    public BaseMap() {
        super();
        this.name = "_basemapbox";
    }

    public BaseMap(String name) {
        super();
        this.name = name;
    }

    public String get(String key) {
        return getString(key);
    }

    @Override
    public String get(Object key) {
        return get((String)key);
    }

    public String[] getArray(String key) {
        String[] arr = null;
        try {
            Object o = super.get(key);
            Class<?> c = o.getClass();
            if (o != null) {
                if (c.isArray()) {
                    int length = Array.getLength(o);
                    if (length != 0) {
                        arr = new String[length];
                        for (int i = 0; i < length; i++) {
                            Object item = Array.get(o, i);
                            if (item == null) {
                                arr[i] = "";
                            } else {
                                arr[i] = item.toString();
                            }
                        }
                    }
                } else {
                    arr = new String[1];
                    arr[0] = o.toString();
                }
            }
        } catch (Exception e) {

        }
        return arr;
    }


    public boolean getBoolean(String key) {
        String value = getString(key);
        boolean isTrue = false;
        try {
            isTrue = Boolean.valueOf(value).booleanValue();
        } catch (Exception e) {

        }
        return isTrue;
    }

    public List<?> getList(String key) {
        return (List<?>)getObject(key);
    }


    public double getDouble(String key) {
        String value = removeComma(getString(key));
        if (value.equals("")) {
            return 0;
        }
        double num = 0;
        try {
            num = Double.valueOf(value).doubleValue();
        } catch (Exception e) {
            num = 0;
        }
        return num;
    }

    public float getFloat(String key) {
        return (float) getLong(key);
    }

    public int getInt(String key) {
        double value = getDouble(key);
        return (int) value;
    }

    public long getLong(String key) {
        String value = removeComma(getString(key));
        if (value.equals("")) {
            return 0;
        }
        long num = 0;
        try {
            num = Long.valueOf(value).longValue();
        } catch (Exception e) {
            num = 0;
        }
        return num;
    }

    public String getString(String key) {
        String value = null;
        try {
            Object o = super.get(key);
            if (o == null) {
                value = "";
            } else {
                Class<?> c = o.getClass();
                if (c.isArray()) {
                    int length = Array.getLength(o);
                    if (length == 0) {
                        value = "";
                    } else {
                        Object item = Array.get(o, 0);
                        if (item == null) {
                            value = "";
                        } else {
                            value = item.toString();
                        }
                    }
                } else {
                    value = o.toString();
                }
            }
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    public Object getObject(String key) {
        return super.get(key);
    }

    public void put(String key, String value) {
        super.put(key, value);
    }

    private static String removeComma(String s) {
        if (s == null) {
            return null;
        }
        if (s.indexOf(',') != -1) {
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c != ',') {
                    buf.append(c);
                }
            }
            return buf.toString();
        }
        return s;
    }

    @SuppressWarnings("rawtypes")
    public synchronized String toString() {
        int max = size() - 1;
        StringBuffer buf = new StringBuffer();
        Iterator<?> it = this.entrySet().iterator();

        for (int i = 0; i <= max; i++) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String value = null;
            Object o = entry.getValue();
            if (o == null) {
                value = "";
            } else {
                Class c = o.getClass();
                if (c.isArray()) {
                    int length = Array.getLength(o);
                    if (length == 0) {
                        value = "";
                    } else if (length == 1) {
                        Object item = Array.get(o, 0);
                        if (item == null) {
                            value = "";
                        } else {
                            value = item.toString();
                        }
                    } else {
                        StringBuffer valueBuf = new StringBuffer();
                        for (int j = 0; j < length; j++) {
                            Object item = Array.get(o, j);
                            if (item != null) {
                                valueBuf.append(item.toString());
                            }
                            if (j < length - 1) {
                                valueBuf.append(",");
                            }
                        }
                        value = "[" + valueBuf.toString() + "]";
                    }
                } else {
                    value = o.toString();
                }
            }
            buf.append(key).append("=").append(value);
            if (i < max) {
                buf.append("\n, ");
            }
        }

        return "BaseMap[" + name + "]=" + "{" + buf.toString() + "}";
    }
}
