package phantomjs;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class Snippet {
	  public static String getAjaxCotnent(String url) throws IOException {
	        Runtime rt = Runtime.getRuntime();
	        Process p = rt.exec("C:\\Users\\Administrator\\phantomjs.exe F:\\zhd\\test.js");//这里我的codes.js是保存在c盘下面的phantomjs目录
	        String error = IOUtils.toString(p.getErrorStream());
	        System.out.println("error:"+error);
	        return IOUtils.toString(p.getInputStream());
	    }

	    public static void main(String[] args) throws IOException {
	        String back = getAjaxCotnent("");
	        System.out.println(back);
	    }
}

