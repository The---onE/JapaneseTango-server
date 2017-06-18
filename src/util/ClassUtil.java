package util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * �๤����
 * 1��ɨ��ָ�����µ�������
 *
 * @author seaside_hi, xiaoleilu
 * @see http://www.oschina.net/code/snippet_234657_22722
 */
public class ClassUtil {

    /**
     * Class�ļ���չ��
     */
    private static final String CLASS_EXT = ".class";
    /**
     * Jar�ļ���չ��
     */
    private static final String JAR_FILE_EXT = ".jar";
    /**
     * ��Jar�е�·��jar����չ����ʽ
     */
    private static final String JAR_PATH_EXT = ".jar!";
    /**
     * ��PathΪ�ļ���ʽʱ, path�����һ����ʾ�ļ���ǰ׺
     */
    private static final String PATH_FILE_PRE = "file:";
    /**
     * ɨ��Class�ļ�ʱ�յĹ���������ʾ������
     */
    private static final ClassFilter NULL_CLASS_FILTER = null;


    /**
     * �����������͵�ö��
     *
     * @author xiaoleilu
     */
    private static enum BASIC_TYPE {
        BYTE, SHORT, INT, INTEGER, LONG, DOUBLE, FLOAT, BOOLEAN, CHAR, CHARACTER, STRING;
    }

    private ClassUtil() {
    }

    /**
     * ɨ��İ�·��������class�ļ�
     *
     * @param packageName ��·�� com | com. | com.abs | com.abs.
     * @return
     */
    public static Set<Class<?>> scanPackage(String packageName) {
        return scanPackage(packageName, NULL_CLASS_FILTER);
    }

    /**
     * ɨ���·��������class����������������class�ļ���</br>
     * �����·��Ϊ com.abs + A.class �������� abs�����classNotFoundException</br>
     * ��ΪclassName Ӧ��Ϊ com.abs.A ����ȴ��Ϊabs.A,�˹�����Ը��쳣���к��Դ���,�п�����һ�������Ƶĵط����Ժ���Ҫ�����޸�</br>
     *
     * @param packageName ��·�� com | com. | com.abs | com.abs.
     * @param classFilter class�����������˵�����Ҫ��class
     * @return
     */
    public static Set<Class<?>> scanPackage(String packageName, ClassFilter classFilter) {
        if (StrUtil.isBlank(packageName))
            throw new NullPointerException("packageName can't be blank!");
        packageName = getWellFormedPackageName(packageName);

        final Set<Class<?>> classes = new HashSet<Class<?>>();
        for (String classPath : getClassPaths(packageName)) {
            // ��� classes
            fillClasses(classPath, packageName, classFilter, classes);
        }

        //�������Ŀ��ClassPath��δ�ҵ���ȥϵͳ�����ClassPath����
        if (classes.isEmpty()) {
            for (String classPath : getJavaClassPaths()) {
                // ��� classes
                fillClasses(new File(classPath), packageName, classFilter, classes);
            }
        }
        return classes;
    }

    /**
     * ���ָ�����е�Public������<br>
     * ȥ�����صķ���
     *
     * @param clazz ��
     */
    public final static Set<String> getMethods(Class<?> clazz) {
        HashSet<String> methodSet = new HashSet<String>();
        Method[] methodArray = clazz.getMethods();
        for (Method method : methodArray) {
            String methodName = method.getName();
            methodSet.add(methodName);
        }
        return methodSet;
    }

    /**
     * ���ClassPath
     *
     * @return
     */
    public static Set<String> getClassPathResources() {
        return getClassPaths(StrUtil.EMPTY);
    }

    /**
     * ���ClassPath
     *
     * @param packageName ������
     * @return
     */
    public static Set<String> getClassPaths(String packageName) {
        String packagePath = packageName.replace(StrUtil.DOT, StrUtil.SLASH);
        Enumeration<URL> resources;
        try {
            resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
        } catch (IOException e) {
            return null;
        }
        Set<String> paths = new HashSet<String>();
        while (resources.hasMoreElements()) {
            paths.add(resources.nextElement().getPath());
        }
        return paths;
    }

    /**
     * ���Java ClassPath·���������� jre<br>
     *
     * @return
     */
    public static String[] getJavaClassPaths() {
        String[] classPaths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
        return classPaths;
    }

    /**
     * ǿ��ת������
     *
     * @param clazz ��ת���ɵ�����
     * @param value ��Ҫת���Ķ���
     * @return ת����Ķ���
     */
    public static Object parse(Class<?> clazz, Object value) {
        try {
            return clazz.cast(value);
        } catch (ClassCastException e) {
            String valueStr = String.valueOf(value);

            Object result = parseBasic(clazz, valueStr);
            if (result != null) {
                return result;
            }

            if (Date.class.isAssignableFrom(clazz)) {
                //�жϱ�׼����
                return DateUtil.parse(valueStr);
            } else if (clazz == BigDecimal.class) {
                //��ѧ��������
                return new BigDecimal(valueStr);
            } else if (clazz == byte[].class) {
                //�����������ַ��������⣬�ڴ�ʹ��ϵͳĬ��
                return valueStr.getBytes();
            }

            //δ�ҵ���ת�������ͣ�����ԭֵ
            return value;
        }
    }

    /**
     * ת����������
     *
     * @param clazz    ת��������
     * @param valueStr ��ת�����ַ���
     * @return ת����Ķ�������ǻ������ͣ�����null
     */
    public static Object parseBasic(Class<?> clazz, String valueStr) {
        switch (BASIC_TYPE.valueOf(clazz.getSimpleName().toUpperCase())) {
            case STRING:
                return valueStr;
            case BYTE:
                if (clazz == byte.class) {
                    return Byte.parseByte(valueStr);
                }
                return Byte.valueOf(valueStr);
            case SHORT:
                if (clazz == short.class) {
                    return Short.parseShort(valueStr);
                }
                return Short.valueOf(valueStr);
            case INT:
                return Integer.parseInt(valueStr);
            case INTEGER:
                return Integer.valueOf(valueStr);
            case LONG:
                if (clazz == long.class) {
                    return Long.parseLong(valueStr);
                }
                return Long.valueOf(valueStr);
            case DOUBLE:
                if (clazz == double.class) {
                    return Double.parseDouble(valueStr);
                }
            case FLOAT:
                if (clazz == float.class) {
                    return Float.parseFloat(valueStr);
                }
                return Float.valueOf(valueStr);
            case BOOLEAN:
                if (clazz == boolean.class) {
                    return Boolean.parseBoolean(valueStr);
                }
                return Boolean.valueOf(valueStr);
            case CHAR:
                return valueStr.charAt(0);
            case CHARACTER:
                return Character.valueOf(valueStr.charAt(0));
            default:
                return null;
        }
    }

    /**
     * ת����������
     *
     * @param clazz
     * @return
     */
    public static Class<?> castToPrimitive(Class<?> clazz) {
        BASIC_TYPE basicType;
        try {
            basicType = BASIC_TYPE.valueOf(clazz.getSimpleName().toUpperCase());
        } catch (Exception e) {
            return clazz;
        }
        //��������
        switch (basicType) {
            case BYTE:
                return byte.class;
            case SHORT:
                return short.class;
            case INT:
                return int.class;
            case LONG:
                return long.class;
            case DOUBLE:
                return double.class;
            case FLOAT:
                return float.class;
            case BOOLEAN:
                return boolean.class;
            case CHAR:
                return char.class;
            default:
                return clazz;
        }
    }

    /**
     * ��ǰ�̵߳�class loader
     *
     * @return
     */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * ���class loader<br>
     * ����ǰ�߳�class loader�����ڣ�ȡ��ǰ���class loader
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = getContextClassLoader();
        if (classLoader == null) {
            classLoader = ClassUtil.class.getClassLoader();
        }
        return classLoader;
    }

    //--------------------------------------------------------------------------------------------------- Private method start
    /**
     * �ļ������������˵�����Ҫ���ļ�<br>
     * ֻ����Class�ļ���Ŀ¼��Jar
     */
    private static FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return isClass(pathname.getName()) || pathname.isDirectory() || isJarFile(pathname);
        }
    };

    /**
     * �ı� com -> com. �����ڱȽϵ�ʱ��ѱ��� completeTestSuite.class��ɨ���ȥ�����û��"."
     * </br>��class����com��ͷ��class��Ҳ�ᱻɨ���ȥ,��ʵ���ƺ����ǰ����Ҫһ�� ".",����Ӱ�������
     *
     * @param packageName
     * @return
     */
    private static String getWellFormedPackageName(String packageName) {
        return packageName.lastIndexOf(StrUtil.DOT) != packageName.length() - 1 ? packageName + StrUtil.DOT : packageName;
    }

    /**
     * �������������class ��䵽 classes<br>
     * ͬʱ���жϸ�����·���Ƿ�ΪJar���ڵ�·��������ǣ���ɨ���Jar��
     *
     * @param path        Class�ļ�·����������Ŀ¼Jar��·��
     * @param packageName ��Ҫɨ��İ���
     * @param classFilter class������
     * @param classes     List ����
     */
    private static void fillClasses(String path, String packageName, ClassFilter classFilter, Set<Class<?>> classes) {
        //�ж�������·���Ƿ�Ϊ
        int index = path.lastIndexOf(JAR_PATH_EXT);
        if (index != -1) {
            path = path.substring(0, index + JAR_FILE_EXT.length());
            path = StrUtil.removePrefix(path, PATH_FILE_PRE);
            processJarFile(new File(path), packageName, classFilter, classes);
        } else {
            fillClasses(new File(path), packageName, classFilter, classes);
        }
    }

    /**
     * �������������class ��䵽 classes
     *
     * @param file        Class�ļ���������Ŀ¼Jar���ļ�
     * @param packageName ��Ҫɨ��İ���
     * @param classFilter class������
     * @param classes     List ����
     */
    private static void fillClasses(File file, String packageName, ClassFilter classFilter, Set<Class<?>> classes) {
        if (file.isDirectory()) {
            processDirectory(file, packageName, classFilter, classes);
        } else if (isClassFile(file)) {
            processClassFile(file, packageName, classFilter, classes);
        } else if (isJarFile(file)) {
            processJarFile(file, packageName, classFilter, classes);
        }
    }

    /**
     * �������ΪĿ¼�����,��Ҫ�ݹ���� fillClasses����
     *
     * @param directory
     * @param packageName
     * @param classFilter
     * @param classes
     */
    private static void processDirectory(File directory, String packageName, ClassFilter classFilter, Set<Class<?>> classes) {
        for (File file : directory.listFiles(fileFilter)) {
            fillClasses(file, packageName, classFilter, classes);
        }
    }

    /**
     * ����Ϊclass�ļ������,�������������class �� classes
     *
     * @param file
     * @param packageName
     * @param classFilter
     * @param classes
     */
    private static void processClassFile(File file, String packageName, ClassFilter classFilter, Set<Class<?>> classes) {
        final String filePathWithDot = file.getAbsolutePath().replace(File.separator, StrUtil.DOT);
        int subIndex = -1;
        if ((subIndex = filePathWithDot.indexOf(packageName)) != -1) {
            final String className = filePathWithDot.substring(subIndex).replace(CLASS_EXT, StrUtil.EMPTY);
            fillClass(className, packageName, classes, classFilter);
        }
    }

    /**
     * ����Ϊjar�ļ���������������������class �� classes
     *
     * @param file
     * @param packageName
     * @param classFilter
     * @param classes
     */
    private static void processJarFile(File file, String packageName, ClassFilter classFilter, Set<Class<?>> classes) {
        try {
            for (JarEntry entry : Collections.list(new JarFile(file).entries())) {
                if (isClass(entry.getName())) {
                    final String className = entry.getName().replace(StrUtil.SLASH, StrUtil.DOT).replace(CLASS_EXT, StrUtil.EMPTY);
                    fillClass(className, packageName, classes, classFilter);
                }
            }
        } catch (Throwable ex) {
        	ex.printStackTrace();
        }
    }

    /**
     * ���class �� classes
     *
     * @param className
     * @param packageName
     * @param classes
     * @param classFilter
     */
    private static void fillClass(String className, String packageName, Set<Class<?>> classes, ClassFilter classFilter) {
        if (className.startsWith(packageName)) {
            try {
                final Class<?> clazz = Class.forName(className, false, Thread.currentThread().getContextClassLoader());
                if (classFilter == NULL_CLASS_FILTER || classFilter.accept(clazz)) {
                    classes.add(clazz);
                }
            } catch (Throwable ex) {
            	ex.printStackTrace();
            }
        }
    }

    private static boolean isClassFile(File file) {
        return isClass(file.getName());
    }

    private static boolean isClass(String fileName) {
        return fileName.endsWith(CLASS_EXT);
    }

    private static boolean isJarFile(File file) {
        return file.getName().contains(JAR_FILE_EXT);
    }
    //--------------------------------------------------------------------------------------------------- Private method end

    /**
     * ������������ڹ��˲���Ҫ���ص���
     *
     * @author seaside_hi
     * @see http://www.oschina.net/code/snippet_234657_22722
     */
    public interface ClassFilter {
        boolean accept(Class<?> clazz);
    }
}
