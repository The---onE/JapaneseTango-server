package util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author xiaoleilu
 */
public class StrUtil {

    public static final String DOT = ".";
    public static final String SLASH = "/";
    public static final String EMPTY = "";

    /**
     * 字符串是否为空白 空白的定义如下：
     * 1、为null
     * 2、为不可见字符（如空格）
     * 3�?""
     *
     * @param str 被检测的字符�?
     * @return 是否为空
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 字符串是否为空，空的定义如下
     * 1、为null
     * 2、为""
     *
     * @param str 被检测的字符�?
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 获得set或get方法对应的标准属性名
     * 例如：setName 返回 name
     *
     * @param getOrSetMethodName set或get方法�?
     * @return 如果是set或get方法名，返回field�? 否则null
     */
    public static String getGeneralField(String getOrSetMethodName) {
        if (getOrSetMethodName.startsWith("get") || getOrSetMethodName.startsWith("set")) {
            return cutPreAndLowerFirst(getOrSetMethodName, 3);
        }
        return null;
    }

    /**
     * 生成set方法�?
     * 例如：name 返回 setName
     *
     * @param fieldName 属�?�名
     * @return setXxx
     */
    public static String genSetter(String fieldName) {
        return upperFirstAndAddPre(fieldName, "set");
    }

    /**
     * 生成get方法�?
     *
     * @param fieldName 属�?�名
     * @return getXxx
     */
    public static String genGetter(String fieldName) {
        return upperFirstAndAddPre(fieldName, "get");
    }

    /**
     * 去掉首部指定长度的字符串并将剩余字符串首字母小写
     * 例如：str=setName, preLength=3 -> return name
     *
     * @param str       被处理的字符�?
     * @param preLength 去掉的长�?
     * @return 处理后的字符串，不符合规范返回null
     */
    public static String cutPreAndLowerFirst(String str, int preLength) {
        if (str == null) {
            return null;
        }
        if (str.length() > preLength) {
            char first = Character.toLowerCase(str.charAt(preLength));
            if (str.length() > preLength + 1) {
                return first + str.substring(preLength + 1);
            }
            return String.valueOf(first);
        }
        return null;
    }

    /**
     * 原字符串首字母大写并在其首部添加指定字符�?
     * 例如：str=name, preString=get -> return getName
     *
     * @param str       被处理的字符�?
     * @param preString 添加的首�?
     * @return 处理后的字符�?
     */
    public static String upperFirstAndAddPre(String str, String preString) {
        if (str == null || preString == null) {
            return null;
        }
        return preString + upperFirst(str);
    }

    /**
     * 大写首字�?
     * 例如：str = name, return Name
     *
     * @param str 字符�?
     * @return
     */
    public static String upperFirst(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 小写首字�?
     * 例如：str = Name, return name
     *
     * @param str 字符�?
     * @return
     */
    public static String lowerFirst(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 去掉指定前缀
     *
     * @param str    字符�?
     * @param prefix 前缀
     * @return 切掉后的字符串，若前�?不是 prefix�? 返回原字符串
     */
    public static String removePrefix(String str, String prefix) {
        if (str != null && str.startsWith(prefix)) {
            return str.substring(prefix.length());
        }
        return str;
    }

    /**
     * 忽略大小写去掉指定前�?
     *
     * @param str    字符�?
     * @param prefix 前缀
     * @return 切掉后的字符串，若前�?不是 prefix�? 返回原字符串
     */
    public static String removePrefixIgnoreCase(String str, String prefix) {
        if (str != null && str.toLowerCase().startsWith(prefix.toLowerCase())) {
            return str.substring(prefix.length());
        }
        return str;
    }

    /**
     * 去掉指定后缀
     *
     * @param str    字符�?
     * @param suffix 后缀
     * @return 切掉后的字符串，若后�?不是 suffix�? 返回原字符串
     */
    public static String removeSuffix(String str, String suffix) {
        if (str != null && str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    /**
     * 忽略大小写去掉指定后�?
     *
     * @param str    字符�?
     * @param suffix 后缀
     * @return 切掉后的字符串，若后�?不是 suffix�? 返回原字符串
     */
    public static String removeSuffixIgnoreCase(String str, String suffix) {
        if (str != null && str.toLowerCase().endsWith(suffix.toLowerCase())) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    /**
     * 切分字符�?
     * a#b#c -> [a,b,c]
     * a##b#c -> [a,"",b,c]
     *
     * @param str       被切分的字符�?
     * @param separator 分隔符字�?
     * @return 切分后的集合
     */
    public static List<String> split(String str, char separator) {
        return split(str, separator, 0);
    }

    /**
     * 切分字符�?
     *
     * @param str       被切分的字符�?
     * @param separator 分隔符字�?
     * @param limit     限制分片�?
     * @return 切分后的集合
     */
    public static List<String> split(String str, char separator, int limit) {
    	if (str == null) {
            return null;
        }
        List<String> list = new ArrayList<String>(limit == 0 ? 16 : limit);
        if (limit == 1) {
            list.add(str);
            return list;
        }

        boolean isNotEnd = true;    //未结束切分的标志
        int strLen = str.length();
        StringBuilder sb = new StringBuilder(strLen);
        for (int i = 0; i < strLen; i++) {
            char c = str.charAt(i);
            if (isNotEnd && c == separator) {
                list.add(sb.toString());
                //清空StringBuilder
                sb.delete(0, sb.length());

                //当达到切分上�?-1的量时，将所剩字符全部作为最后一个串
                if (limit != 0 && list.size() == limit - 1) {
                    isNotEnd = false;
                }
            } else {
                sb.append(c);
            }
        }
        list.add(sb.toString());
        return list;
    }

    /**
     * 切分字符�?
     * from jodd
     *
     * @param str       被切分的字符�?
     * @param delimiter 分隔�?
     * @return
     */
    public static String[] split(String str, String delimiter) {
        if (str == null) {
            return null;
        }
        if (str.trim().length() == 0) {
            return new String[]{str};
        }

        int dellen = delimiter.length();    //del length
        int maxparts = (str.length() / dellen) + 2;        // one more for the last
        int[] positions = new int[maxparts];

        int i, j = 0;
        int count = 0;
        positions[0] = -dellen;
        while ((i = str.indexOf(delimiter, j)) != -1) {
            count++;
            positions[count] = i;
            j = i + dellen;
        }
        count++;
        positions[count] = str.length();

        String[] result = new String[count];

        for (i = 0; i < count; i++) {
            result[i] = str.substring(positions[i] + dellen, positions[i + 1]);
        }
        return result;
    }

    /**
     * 重复某个字符
     *
     * @param c     被重复的字符
     * @param count 重复的数�?
     * @return 重复字符字符�?
     */
    public static String repeat(char c, int count) {
        char[] result = new char[count];
        for (int i = 0; i < count; i++) {
            result[i] = c;
        }
        return new String(result);
    }

    /**
     * 给定字符串转换字符编�?
     * 如果参数为空，则返回原字符串，不报错�?
     *
     * @param str           被转码的字符�?
     * @param sourceCharset 原字符集
     * @param destCharset   目标字符�?
     * @return 转换后的字符�?
     */
    public static String convertCharset(String str, String sourceCharset, String destCharset) {
        if (isBlank(str) || isBlank(sourceCharset) || isBlank(destCharset)) {
            return str;
        }
        try {
            return new String(str.getBytes(sourceCharset), destCharset);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 比较两个字符串是否相同，如果为null或�?�空串则算不�?
     *
     * @param str1 字符�?1
     * @param str2 字符�?2
     * @return 是否非空相同
     */
    public static boolean equalsNotEmpty(String str1, String str2) {
        if (isEmpty(str1)) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 格式化文�?
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   参数�?
     * @return 格式化后的文�?
     */
    public static String format(String template, Object... values) {
        return String.format(template.replace("{}", "%s"), values);
    }

    /**
     * 连接字符�?
     *
     * @param items     待连接的字符串数�?
     * @param separator 分隔字符�?
     * @return 格式化后的文�?
     */
    public static String join(String[] items, String separator) {
        StringBuffer sb = new StringBuffer();
        sb.append(items[0]);
        for (int i = 1; i < items.length; ++i) {
            sb.append(separator);
            sb.append(items[i]);
        }
        return new String(sb);
    }


    /**
     * 连接字符�?
     *
     * @param items     待连接的字符串列�?
     * @param separator 分隔字符�?
     * @return 格式化后的文�?
     */
    public static String join(List<String> items, String separator) {
        StringBuffer sb = new StringBuffer();
        sb.append(items.get(0));
        for (int i = 1; i < items.size(); ++i) {
            sb.append(separator);
            sb.append(items.get(i));
        }
        return new String(sb);
    }
}

