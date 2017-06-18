package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;

/**
 * �ļ�������
 *
 * @author xiaoleilu
 */
public class FileUtil {

    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * �����ļ����������ļ����ڣ�ֱ�ӷ�������ļ�
     *
     * @param fullFilePath �ļ���ȫ·����ʹ��POSIX���
     * @return �ļ�����·��Ϊnull������null
     * @throws IOException
     */
    public static File touch(String fullFilePath) throws IOException {
        if (fullFilePath == null) {
            return null;
        }
        File file = new File(fullFilePath);

        file.getParentFile().mkdirs();
        if (!file.exists()) file.createNewFile();
        return file;
    }

    /**
     * �����ļ��У��������ֱ�ӷ��ش��ļ���
     *
     * @param dirPath �ļ���·����ʹ��POSIX��ʽ�������ĸ�ƽ̨
     * @return ������Ŀ¼
     */
    public static File mkdir(String dirPath) {
        if (dirPath == null) {
            return null;
        }
        File dir = new File(dirPath);
        dir.mkdirs();
        return dir;
    }

    /**
     * ������ʱ�ļ�<br>
     * ��������ļ���Ϊ prefix[Randon].suffix
     * From com.jodd.io.FileUtil
     *
     * @param prefix    ǰ׺������3���ַ�
     * @param suffix    ��׺�����null��ʹ��Ĭ��.tmp
     * @param dir       ��ʱ�ļ�����������Ŀ¼
     * @param isReCreat �Ƿ����´����ļ���ɾ��ԭ���ģ������µģ�
     * @return
     * @throws IOException
     */
    public static File createTempFile(String prefix, String suffix, File dir, boolean isReCreat) throws IOException {
        int exceptionsCount = 0;
        while (true) {
            try {
                File file = File.createTempFile(prefix, suffix, dir).getCanonicalFile();
                if (isReCreat) {
                    file.delete();
                    file.createNewFile();
                }
                return file;
            } catch (IOException ioex) {    // fixes java.io.WinNTFileSystem.createFileExclusively access denied
                if (++exceptionsCount >= 50) {
                    throw ioex;
                }
            }
        }
    }

    /**
     * �����ļ�<br>
     * ���Ŀ���ļ�ΪĿ¼����Դ�ļ�����ͬ�ļ���������Ŀ��Ŀ¼
     *
     * @param src        Դ�ļ�
     * @param dest       Ŀ���ļ���Ŀ¼
     * @param isOverride �Ƿ񸲸�Ŀ���ļ�
     * @throws IOException
     */
    public static void copy(File src, File dest, boolean isOverride) throws IOException {
        //check
        if (!src.exists()) {
            throw new FileNotFoundException("File not exist: " + src);
        }
        if (!src.isFile()) {
            throw new IOException("Not a file:" + src);
        }
        if (equals(src, dest)) {
            throw new IOException("Files '" + src + "' and '" + dest + "' are equal");
        }

        if (dest.exists()) {
            if (dest.isDirectory()) {
                dest = new File(dest, src.getName());
            }
            if (dest.exists() && !isOverride) {
                throw new IOException("File already exist: " + dest);
            }
        }

        // do copy file
        FileInputStream input = new FileInputStream(src);
        FileOutputStream output = new FileOutputStream(dest);
        try {
            IOUtil.copy(input, output);
        } finally {
            close(output);
            close(input);
        }

        if (src.length() != dest.length()) {
            throw new IOException("Copy file failed of '" + src + "' to '" + dest + "' due to different sizes");
        }
    }

    /**
     * �ƶ��ļ�����Ŀ¼
     *
     * @param src        Դ�ļ�����Ŀ¼
     * @param dest       Ŀ���ļ�����Ŀ¼
     * @param isOverride �Ƿ񸲸�Ŀ��
     * @throws IOException
     */
    public static void move(File src, File dest, boolean isOverride) throws IOException {
        //check
        if (!src.exists()) {
            throw new FileNotFoundException("File already exist: " + src);
        }
        if (dest.exists()) {
            if (!isOverride) {
                throw new IOException("File already exist: " + dest);
            }
            dest.delete();
        }

        //��ԴΪ�ļ��У�Ŀ��Ϊ�ļ�
        if (src.isDirectory() && dest.isFile()) {
            throw new IOException(StrUtil.format("Can not move directory [{}] to file [{}]", src, dest));
        }

        //��ԴΪ�ļ���Ŀ��Ϊ�ļ���
        if (src.isFile() && dest.isDirectory()) {
            dest = new File(dest, src.getName());
        }

        if (src.renameTo(dest) == false) {
            //���ļ�ϵͳ��ͬ������£�renameTo��ʧ�ܣ���ʱʹ��copy��Ȼ��ɾ��ԭ�ļ�
            try {
                copy(src, dest, isOverride);
                src.delete();
            } catch (Exception e) {
                throw new IOException(StrUtil.format("Move [{}] to [{}] failed!", src, dest), e);
            }

        }
    }

    /**
     * ��ȡ����·��<br/>
     * �˷��������ж�����·���Ƿ���Ч���ļ���Ŀ¼���ڣ�
     *
     * @param path      ���·��
     * @param baseClass ���·������Ե���
     * @return ����·��
     */
    public static String getAbsolutePath(String path, Class<?> baseClass) {
        if (path == null) {
            path = StrUtil.EMPTY;
        }
        if (baseClass == null) {
            return getAbsolutePath(path);
        }
        return baseClass.getResource(path).getPath();
    }

    /**
     * ��ȡ����·���������classes�ĸ�Ŀ¼
     *
     * @param pathBaseClassLoader ���·��
     * @return ����·��
     */
    public static String getAbsolutePath(String pathBaseClassLoader) {
        if (pathBaseClassLoader == null) {
            pathBaseClassLoader = StrUtil.EMPTY;
        }

        ClassLoader classLoader = ClassUtil.getClassLoader();
        URL url = classLoader.getResource(pathBaseClassLoader);
        if (url == null) {
            return classLoader.getResource(StrUtil.EMPTY).getPath() + pathBaseClassLoader;
        }
        return url.getPath();
    }

    /**
     * �ļ��Ƿ����
     *
     * @param path �ļ�·��
     * @return �Ƿ����
     */
    public static boolean isExist(String path) {
        return new File(path).exists();
    }

    /**
     * �ر�
     *
     * @param closeable ���رյĶ���
     */
    public static void close(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    /**
     * ��������ļ��Ƿ���ͬһ���ļ�
     *
     * @param file1 �ļ�1
     * @param file2 �ļ�2
     * @return �Ƿ���ͬ
     */
    public static boolean equals(File file1, File file2) {
        try {
            file1 = file1.getCanonicalFile();
            file2 = file2.getCanonicalFile();
        } catch (IOException ignore) {
            return false;
        }
        return file1.equals(file2);
    }

    /**
     * ���һ���������д�����
     *
     * @param path     ���·��������·��
     * @param charset  �ַ���
     * @param isAppend �Ƿ�׷��
     * @return BufferedReader����
     * @throws IOException
     */
    public static BufferedWriter getBufferedWriter(String path, String charset, boolean isAppend) throws IOException {
        return new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(touch(path), isAppend), charset
                )
        );
    }

    /**
     * ���һ����ӡд����󣬿�����print
     *
     * @param path     ���·��������·��
     * @param charset  �ַ���
     * @param isAppend �Ƿ�׷��
     * @return ��ӡ����
     * @throws IOException
     */
    public static PrintWriter getPrintWriter(String path, String charset, boolean isAppend) throws IOException {
        return new PrintWriter(getBufferedWriter(path, charset, isAppend));
    }

    /**
     * ���һ�����������
     *
     * @param path ��������ļ�·��������·��
     * @return ���������
     * @throws IOException
     */
    public static OutputStream getOutputStream(String path) throws IOException {
        return new FileOutputStream(touch(path));
    }

    /**
     * ���һ��Ŀ¼
     *
     * @param dirPath ��Ҫɾ�����ļ���·��
     */
    public static void cleanDir(String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) cleanDir(file.getAbsolutePath());
                file.delete();
            }
        }
    }

    /**
     * ���һ���ļ���ȡ��
     *
     * @param path    ����·��
     * @param charset �ַ���
     * @return BufferedReader����
     * @throws IOException
     */
    public static BufferedReader getReader(String path, String charset) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
    }

    /**
     * ���ļ��ж�ȡÿһ������
     *
     * @param path       �ļ�·��
     * @param charset    �ַ���
     * @param collection ����
     * @return �ļ��е�ÿ�����ݵļ���
     * @throws IOException
     */
    public static <T extends Collection<String>> T readLines(String path, String charset, T collection) throws IOException {
        BufferedReader reader = null;
        try {
            reader = getReader(path, charset);
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                collection.add(line);
            }
            return collection;
        } finally {
            close(reader);
        }
    }

    /**
     * ���ո�����readerHandler��ȡ�ļ��е�����
     *
     * @param readerHandler Reader������
     * @param path          �ļ��ľ���·��
     * @param charset       �ַ���
     * @return ���ļ���load��������
     * @throws IOException
     */
    public static <T> T load(ReaderHandler<T> readerHandler, String path, String charset) throws IOException {
        BufferedReader reader = null;
        T result = null;
        try {
            reader = getReader(path, charset);
            result = readerHandler.handle(reader);
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            close(reader);
        }
        return result;
    }

    /**
     * ����ļ�����չ��
     *
     * @param fileName �ļ���
     * @return ��չ��
     */
    public static String getExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(StrUtil.DOT);
        if (index == -1) {
            return StrUtil.EMPTY;
        } else {
            String ext = fileName.substring(index + 1);
            //��չ���в��ܰ���·����صķ���
            return (ext.contains(String.valueOf(UNIX_SEPARATOR)) || ext.contains(String.valueOf(WINDOWS_SEPARATOR))) ? StrUtil.EMPTY : ext;
        }
    }

    /**
     * ������һ���ļ�·���ָ�����λ��
     *
     * @param filePath �ļ�·��
     * @return
     */
    public static int indexOfLastSeparator(String filePath) {
        if (filePath == null) {
            return -1;
        }
        int lastUnixPos = filePath.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filePath.lastIndexOf(WINDOWS_SEPARATOR);
        return (lastUnixPos >= lastWindowsPos) ? lastUnixPos : lastWindowsPos;
    }

    /**
     * ��Stringд���ļ�������ģʽ
     *
     * @param content д�������
     * @param path    �ļ�·��
     * @param charset �ַ���
     * @throws IOException
     */
    public static void writeString(String content, String path, String charset) throws IOException {
        PrintWriter writer = null;
        try {
            writer = getPrintWriter(path, charset, false);
            writer.print(content);
        } finally {
            close(writer);
        }
    }

    /**
     * ��ȡ�ļ�����
     *
     * @param path    �ļ�·��
     * @param charset �ַ���
     * @return ����
     * @throws IOException
     */
    public static String readString(String path, String charset) throws IOException {
        return new String(readBytes(new File(path)), charset);
    }

    //-------------------------------------------------------------------------- Write and read bytes

    /**
     * д���ݵ��ļ���
     *
     * @param dest Ŀ���ļ�
     * @param data ����
     * @throws IOException
     */
    public static void writeBytes(byte[] data, String path) throws IOException {
        writeBytes(touch(path), data);
    }

    /**
     * д���ݵ��ļ���
     *
     * @param dest Ŀ���ļ�
     * @param data ����
     * @throws IOException
     */
    public static void writeBytes(File dest, byte[] data) throws IOException {
        writeBytes(dest, data, 0, data.length, false);
    }

    /**
     * д�����ݵ��ļ�
     *
     * @param dest   Ŀ���ļ�
     * @param data   ����
     * @param off
     * @param len
     * @param append
     * @throws IOException
     */
    public static void writeBytes(File dest, byte[] data, int off, int len, boolean append) throws IOException {
        if (dest.exists() == true) {
            if (dest.isFile() == false) {
                throw new IOException("Not a file: " + dest);
            }
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(dest, append);
            out.write(data, off, len);
        } finally {
            close(out);
        }
    }

    /**
     * ��ȡ�ļ���������<br>
     * �ļ��ĳ��Ȳ��ܳ���Integer.MAX_VALUE
     *
     * @param file �ļ�
     * @return �ֽ���
     * @throws IOException
     */
    public static byte[] readBytes(File file) throws IOException {
        //check
        if (!file.exists()) {
            throw new FileNotFoundException("File not exist: " + file);
        }
        if (!file.isFile()) {
            throw new IOException("Not a file:" + file);
        }

        long len = file.length();
        if (len >= Integer.MAX_VALUE) {
            throw new IOException("File is larger then max array size");
        }

        byte[] bytes = new byte[(int) len];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(bytes);
        } finally {
            close(in);
        }

        return bytes;
    }

    // ---------------------------------------------------------------- stream

    /**
     * ����������д���ļ�<br>
     *
     * @param dest Ŀ���ļ�
     * @param in   ������
     * @throws IOException
     */
    public static void writeStream(File dest, InputStream in) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(dest);
            IOUtil.copy(in, out);
        } finally {
            close(out);
        }
    }

    /**
     * ����������д���ļ�<br>
     *
     * @param fullFilePath �ļ�����·��
     * @param in           ������
     * @throws IOException
     */
    public static void writeStream(String fullFilePath, InputStream in) throws IOException {
        writeStream(touch(fullFilePath), in);
    }

    //-------------------------------------------------------------------------- Interface

    /**
     * Reader����ӿ�
     *
     * @param <T>
     * @author Luxiaolei
     */
    public interface ReaderHandler<T> {
        public T handle(BufferedReader reader) throws IOException;
    }

    //---------------------------------------------------------------------------------------- Private method start
    //---------------------------------------------------------------------------------------- Private method end

    public static void main(String[] args) throws IOException {
        createTempFile("test", null, mkdir("e:\\opt\\temp"), false);
    }
}
