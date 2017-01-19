package druid;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidUtils {

    public static String confile = "druid.properties";
    public static Properties p = null;

    static {
        p = new Properties();
        InputStream inputStream = null;
        try {
            //java应用

            confile = DruidUtils.class.getClassLoader().getResource("").getPath()
                    + confile;
            System.out.println(confile);
            File file = new File(confile);
            inputStream = new BufferedInputStream(new FileInputStream(file));
            p.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static final DataSource getDataSource() throws Exception {
        DataSource dataSource  = DruidDataSourceFactory.createDataSource(p);
        return dataSource;
    }
}
