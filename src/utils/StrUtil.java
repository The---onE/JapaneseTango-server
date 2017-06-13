package utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 瀛楃涓插伐鍏风被
 *
 * @author xiaoleilu
 */
public class StrUtil {

    public static final String DOT = ".";
    public static final String SLASH = "/";
    public static final String EMPTY = "";

    /**
     * 瀛楃涓叉槸鍚︿负绌虹櫧 绌虹櫧鐨勫畾涔夊涓嬶細 <br>
     * 1銆佷负null <br>
     * 2銆佷负涓嶅彲瑙佸瓧绗︼紙濡傜┖鏍硷級<br>
     * 3銆�""<br>
     *
     * @param str 琚娴嬬殑瀛楃涓�
     * @return 鏄惁涓虹┖
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 瀛楃涓叉槸鍚︿负绌猴紝绌虹殑瀹氫箟濡備笅
     * 1銆佷负null <br>
     * 2銆佷负""<br>
     *
     * @param str 琚娴嬬殑瀛楃涓�
     * @return 鏄惁涓虹┖
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 鑾峰緱set鎴杇et鏂规硶瀵瑰簲鐨勬爣鍑嗗睘鎬у悕<br/>
     * 渚嬪锛歴etName 杩斿洖 name
     *
     * @param getOrSetMethodName
     * @return 濡傛灉鏄痵et鎴杇et鏂规硶鍚嶏紝杩斿洖field锛� 鍚﹀垯null
     */
    public static String getGeneralField(String getOrSetMethodName) {
        if (getOrSetMethodName.startsWith("get") || getOrSetMethodName.startsWith("set")) {
            return cutPreAndLowerFirst(getOrSetMethodName, 3);
        }
        return null;
    }

    /**
     * 鐢熸垚set鏂规硶鍚�<br/>
     * 渚嬪锛歯ame 杩斿洖 setName
     *
     * @param fieldName 灞炴�у悕
     * @return setXxx
     */
    public static String genSetter(String fieldName) {
        return upperFirstAndAddPre(fieldName, "set");
    }

    /**
     * 鐢熸垚get鏂规硶鍚�
     *
     * @param fieldName 灞炴�у悕
     * @return getXxx
     */
    public static String genGetter(String fieldName) {
        return upperFirstAndAddPre(fieldName, "get");
    }

    /**
     * 鍘绘帀棣栭儴鎸囧畾闀垮害鐨勫瓧绗︿覆骞跺皢鍓╀綑瀛楃涓查瀛楁瘝灏忓啓<br/>
     * 渚嬪锛歴tr=setName, preLength=3 -> return name
     *
     * @param str       琚鐞嗙殑瀛楃涓�
     * @param preLength 鍘绘帀鐨勯暱搴�
     * @return 澶勭悊鍚庣殑瀛楃涓诧紝涓嶇鍚堣鑼冭繑鍥瀗ull
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
     * 鍘熷瓧绗︿覆棣栧瓧姣嶅ぇ鍐欏苟鍦ㄥ叾棣栭儴娣诲姞鎸囧畾瀛楃涓�
     * 渚嬪锛歴tr=name, preString=get -> return getName
     *
     * @param str       琚鐞嗙殑瀛楃涓�
     * @param preString 娣诲姞鐨勯閮�
     * @return 澶勭悊鍚庣殑瀛楃涓�
     */
    public static String upperFirstAndAddPre(String str, String preString) {
        if (str == null || preString == null) {
            return null;
        }
        return preString + upperFirst(str);
    }

    /**
     * 澶у啓棣栧瓧姣�<br>
     * 渚嬪锛歴tr = name, return Name
     *
     * @param str 瀛楃涓�
     * @return
     */
    public static String upperFirst(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 灏忓啓棣栧瓧姣�<br>
     * 渚嬪锛歴tr = Name, return name
     *
     * @param str 瀛楃涓�
     * @return
     */
    public static String lowerFirst(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 鍘绘帀鎸囧畾鍓嶇紑
     *
     * @param str    瀛楃涓�
     * @param prefix 鍓嶇紑
     * @return 鍒囨帀鍚庣殑瀛楃涓诧紝鑻ュ墠缂�涓嶆槸 preffix锛� 杩斿洖鍘熷瓧绗︿覆
     */
    public static String removePrefix(String str, String prefix) {
        if (str != null && str.startsWith(prefix)) {
            return str.substring(prefix.length());
        }
        return str;
    }

    /**
     * 蹇界暐澶у皬鍐欏幓鎺夋寚瀹氬墠缂�
     *
     * @param str    瀛楃涓�
     * @param prefix 鍓嶇紑
     * @return 鍒囨帀鍚庣殑瀛楃涓诧紝鑻ュ墠缂�涓嶆槸 prefix锛� 杩斿洖鍘熷瓧绗︿覆
     */
    public static String removePrefixIgnoreCase(String str, String prefix) {
        if (str != null && str.toLowerCase().startsWith(prefix.toLowerCase())) {
            return str.substring(prefix.length());
        }
        return str;
    }

    /**
     * 鍘绘帀鎸囧畾鍚庣紑
     *
     * @param str    瀛楃涓�
     * @param suffix 鍚庣紑
     * @return 鍒囨帀鍚庣殑瀛楃涓诧紝鑻ュ悗缂�涓嶆槸 suffix锛� 杩斿洖鍘熷瓧绗︿覆
     */
    public static String removeSuffix(String str, String suffix) {
        if (str != null && str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    /**
     * 蹇界暐澶у皬鍐欏幓鎺夋寚瀹氬悗缂�
     *
     * @param str    瀛楃涓�
     * @param suffix 鍚庣紑
     * @return 鍒囨帀鍚庣殑瀛楃涓诧紝鑻ュ悗缂�涓嶆槸 suffix锛� 杩斿洖鍘熷瓧绗︿覆
     */
    public static String removeSuffixIgnoreCase(String str, String suffix) {
        if (str != null && str.toLowerCase().endsWith(suffix.toLowerCase())) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    /**
     * 鍒囧垎瀛楃涓�<br/>
     * a#b#c -> [a,b,c]
     * a##b#c -> [a,"",b,c]
     *
     * @param str       琚垏鍒嗙殑瀛楃涓�
     * @param separator 鍒嗛殧绗﹀瓧绗�
     * @return 鍒囧垎鍚庣殑闆嗗悎
     */
    public static List<String> split(String str, char separator) {
        return split(str, separator, 0);
    }

    /**
     * 鍒囧垎瀛楃涓�
     *
     * @param str       琚垏鍒嗙殑瀛楃涓�
     * @param separator 鍒嗛殧绗﹀瓧绗�
     * @param limit     闄愬埗鍒嗙墖鏁�
     * @return 鍒囧垎鍚庣殑闆嗗悎
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

        boolean isNotEnd = true;    //鏈粨鏉熷垏鍒嗙殑鏍囧織
        int strLen = str.length();
        StringBuilder sb = new StringBuilder(strLen);
        for (int i = 0; i < strLen; i++) {
            char c = str.charAt(i);
            if (isNotEnd && c == separator) {
                list.add(sb.toString());
                //娓呯┖StringBuilder
                sb.delete(0, sb.length());

                //褰撹揪鍒板垏鍒嗕笂闄�-1鐨勯噺鏃讹紝灏嗘墍鍓╁瓧绗﹀叏閮ㄤ綔涓烘渶鍚庝竴涓覆
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
     * 鍒囧垎瀛楃涓�<br>
     * from jodd
     *
     * @param str       琚垏鍒嗙殑瀛楃涓�
     * @param delimiter 鍒嗛殧绗�
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
     * 閲嶅鏌愪釜瀛楃
     *
     * @param c     琚噸澶嶇殑瀛楃
     * @param count 閲嶅鐨勬暟鐩�
     * @return 閲嶅瀛楃瀛楃涓�
     */
    public static String repeat(char c, int count) {
        char[] result = new char[count];
        for (int i = 0; i < count; i++) {
            result[i] = c;
        }
        return new String(result);
    }

    /**
     * 缁欏畾瀛楃涓茶浆鎹㈠瓧绗︾紪鐮�<br/>
     * 濡傛灉鍙傛暟涓虹┖锛屽垯杩斿洖鍘熷瓧绗︿覆锛屼笉鎶ラ敊銆�
     *
     * @param str           琚浆鐮佺殑瀛楃涓�
     * @param sourceCharset 鍘熷瓧绗﹂泦
     * @param destCharset   鐩爣瀛楃闆�
     * @return 杞崲鍚庣殑瀛楃涓�
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
     * 姣旇緝涓や釜瀛楃涓叉槸鍚︾浉鍚岋紝濡傛灉涓簄ull鎴栬�呯┖涓插垯绠椾笉鍚�
     *
     * @param str1 瀛楃涓�1
     * @param str2 瀛楃涓�2
     * @return 鏄惁闈炵┖鐩稿悓
     */
    public static boolean equalsNotEmpty(String str1, String str2) {
        if (isEmpty(str1)) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 鏍煎紡鍖栨枃鏈�
     *
     * @param template 鏂囨湰妯℃澘锛岃鏇挎崲鐨勯儴鍒嗙敤 {} 琛ㄧず
     * @param values   鍙傛暟鍊�
     * @return 鏍煎紡鍖栧悗鐨勬枃鏈�
     */
    public static String format(String template, Object... values) {
        return String.format(template.replace("{}", "%s"), values);
    }

    /**
     * 杩炴帴瀛楃涓�
     *
     * @param items     寰呰繛鎺ョ殑瀛楃涓叉暟缁�
     * @param separator 鍒嗛殧瀛楃涓�
     * @return 鏍煎紡鍖栧悗鐨勬枃鏈�
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
     * 杩炴帴瀛楃涓�
     *
     * @param items     寰呰繛鎺ョ殑瀛楃涓插垪琛�
     * @param separator 鍒嗛殧瀛楃涓�
     * @return 鏍煎紡鍖栧悗鐨勬枃鏈�
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

