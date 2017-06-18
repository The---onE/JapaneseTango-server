package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * CSV�ļ���ع��ߣ����ŷָ����ļ���
 *
 * @author Luxiaolei
 */
public class CsvUtil {

    /**
     * ��������洢ΪCSV�ļ�
     *
     * @param rs           �����
     * @param pathWithName Ҫ�洢���ļ���·��
     */
    public static void toCSV(ResultSet rs, String pathWithName, String charset) {
        try {
            PrintWriter writer = FileUtil.getPrintWriter(pathWithName, charset, false);
            while (rs.next()) {
                int count = rs.getMetaData().getColumnCount();
                // ����һ��
                StringBuffer sb = new StringBuffer();
                for (int i = 1; i <= count; i++) {
                    sb.append(rs.getObject(i));
                    if (i == count)
                        break;
                    sb.append(",");
                }
                String line = sb.toString();
                writer.println(line);
            }
            writer.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �������е�����д��CSV�ļ�
     *
     * @param collection   Ҫд���ļ��ļ��ϼ���
     * @param pathWithName CSV�ļ�·�������ļ���
     * @param charset      �ַ���
     */
    public static boolean toCSV(Collection<String> collection, String pathWithName, String charset) {
        try {
            PrintWriter writer = FileUtil.getPrintWriter(pathWithName, charset, false);
            for (String line : collection) {
                writer.println(line);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
