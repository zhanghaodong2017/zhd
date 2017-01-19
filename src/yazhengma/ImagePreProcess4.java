package yazhengma;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;

public class ImagePreProcess4 {

    private static Map<BufferedImage, String> trainMap = null;
    private static int index = 0;

    public static int isBlack(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 300) {
            return 1;
        }
        return 0;
    }

    public static int isWhite(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() > 300) {
            return 1;
        }
        return 0;
    }
    public static boolean colorIsEques(int colorInt) {
        Color color = new Color(colorInt);
        return (color.getRed() == color.getGreen() || color.getGreen() == color.getBlue());

    }
    public static int getColorBright(int colorInt) {
        Color color = new Color(colorInt);
        return color.getRed() + color.getGreen() + color.getBlue();

    }

    public static int isBlackOrWhite(int colorInt) {
        if (getColorBright(colorInt) < 30 || getColorBright(colorInt) > 730) {
            return 1;
        }
        return 0;
    }

    public static BufferedImage removeBackgroud(String picFile)
            throws Exception {
    	Map<ThreeColor, Integer> map = new HashMap<>();

        BufferedImage img = ImageIO.read(new File(picFile));
        int width = img.getWidth();
        int height = img.getHeight();
        for (int x = 0; x < width ; ++x) {
            for (int y = 0; y < height; ++y) {
            	Color color = new Color(img.getRGB(x, y));
                ThreeColor threeColor = new ThreeColor();
                threeColor.setRed(color.getRed());
                threeColor.setGreen(color.getGreen());
                threeColor.setBlue(color.getBlue());
                Integer num = map.get(threeColor);
                if(num == null){
               	 num = 0;
                }
                map.put(threeColor, ++num);
            	int colorSum =getColorBright(img.getRGB(x, y)) ;
                if (colorSum <80 || colorIsEques(img.getRGB(x, y))) {
                    /*if (isBlackOrWhite(img.getRGB(x - 1, y))
                            + isBlackOrWhite(img.getRGB(x + 1, y))
                            + isBlackOrWhite(img.getRGB(x, y - 1))
                            + isBlackOrWhite(img.getRGB(x, y + 1)) == 4) {
                        img.setRGB(x, y, Color.WHITE.getRGB());
                    }*/
                	//img.setRGB(x, y, Color.WHITE.getRGB());
                	if(y >3){
                		img.setRGB(x, y, img.getRGB(x, y-3));
                	}else{
                		img.setRGB(x, y, Color.WHITE.getRGB());
                	}

                }
            }
        }
        for (ThreeColor threeColor : map.keySet()) {
        	System.out.println(threeColor +"size:"+map.get(threeColor));
		}
        img = img.getSubimage(38, 12, 88, 27);// x,y,width,heigh
        return img;
    }

    public static BufferedImage removeBlank(BufferedImage img) throws Exception {
        int width = img.getWidth();
        int height = img.getHeight();
        int start = 0;
        int end = 0;
        Label1: for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    start = y;
                    break Label1;
                }
            }
        }
        Label2: for (int y = height - 1; y >= 0; --y) {
            for (int x = 0; x < width; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    end = y;
                    break Label2;
                }
            }
        }
        return img.getSubimage(0, start, width, end - start + 1);
    }

    public static List<BufferedImage> splitImage(BufferedImage img)
            throws Exception {
        List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
        int width = img.getWidth();
        int height = img.getHeight();
        int index = width/4;
        for (int i = 0; i < 4; i++) {
        	subImgs.add(img.getSubimage(i*index, 0, index, height));
		}
        return subImgs;
    }

    public static Map<BufferedImage, String> loadTrainData() throws Exception {
        if (trainMap == null) {
            Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
            File dir = new File("train4");
            File[] files = dir.listFiles();
            for (File file : files) {
                map.put(ImageIO.read(file), file.getName().charAt(0) + "");
            }
            trainMap = map;
        }
        return trainMap;
    }

    public static int getDistance(BufferedImage img, BufferedImage sample) {
        int width = img.getWidth();
        int height = img.getHeight();
        int count = 0;
        int widthmin = width < sample.getWidth() ? width : sample.getWidth();
        int heightmin = height < sample.getHeight() ? height : sample
                .getHeight();
        for (int x = 0; x < widthmin; ++x) {
            for (int y = 0; y < heightmin; ++y) {
                if (isWhite(img.getRGB(x, y)) != isWhite(sample.getRGB(x, y))) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isNotEight(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int minCount = width;
        for (int y = height / 2 - 2; y < height / 2 + 2; ++y) {
            int count = 0;
            for (int x = 0; x < width / 2 + 2; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    count++;
                }
            }
            minCount = Math.min(count, minCount);
        }
        return minCount < 2;
    }

    public static boolean isNotThree(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int minCount = width;
        for (int y = height / 2 - 3; y < height / 2 + 3; ++y) {
            int count = 0;
            for (int x = 0; x < width / 2 + 1; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    count++;
                }
            }
            minCount = Math.min(count, minCount);
        }
        return minCount > 0;
    }

    public static boolean isNotFive(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int minCount = width;
        for (int y = 0; y < height / 3; ++y) {
            int count = 0;
            for (int x = width * 2 / 3; x < width; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    count++;
                }
            }
            minCount = Math.min(count, minCount);
        }
        return minCount > 0;
    }

    public static String getSingleCharOcr(BufferedImage img,
            Map<BufferedImage, String> map) throws Exception {
        String result = "#";
        int width = img.getWidth();
        int height = img.getHeight();
        int min = width * height;
        boolean bNotEight = isNotEight(img);
        boolean bNotThree = isNotThree(img);
        boolean bNotFive = isNotFive(img);
        for (BufferedImage bi : map.keySet()) {
            if (bNotThree && map.get(bi).startsWith("3"))
                continue;
            if (bNotEight && map.get(bi).startsWith("8"))
                continue;
            if (bNotFive && map.get(bi).startsWith("5"))
                continue;
            double count1 = getBlackCount(img);
            double count2 = getBlackCount(bi);
            if (Math.abs(count1 - count2) / Math.max(count1, count2) > 0.25)
                continue;
            int count = 0;
            if (width < bi.getWidth() && height < bi.getHeight()) {
                for (int m = 0; m <= bi.getWidth() - width; m++) {
                    for (int n = 0; n <= bi.getHeight() - height; n++) {
                        Label1: for (int x = m; x < m + width; ++x) {
                            for (int y = n; y < n + height; ++y) {
                                if (isWhite(img.getRGB(x - m, y - n)) != isWhite(bi
                                        .getRGB(x, y))) {
                                    count++;
                                    if (count >= min)
                                        break Label1;
                                }
                            }
                        }
                    }
                }
            } else {
                int widthmin = width < bi.getWidth() ? width : bi.getWidth();
                int heightmin = height < bi.getHeight() ? height : bi
                        .getHeight();
                Label1: for (int x = 0; x < widthmin; ++x) {
                    for (int y = 0; y < heightmin; ++y) {
                        if (isWhite(img.getRGB(x, y)) != isWhite(bi
                                .getRGB(x, y))) {
                            count++;
                            if (count >= min)
                                break Label1;
                        }
                    }
                }
            }
            if (count < min) {
                min = count;
                result = map.get(bi);
            }
        }
        return result;
    }

    public static String getAllOcr(String file) throws Exception {
        BufferedImage img = removeBackgroud(file);
        List<BufferedImage> listImg = splitImage(img);
        Map<BufferedImage, String> map = loadTrainData();
        String result = "";
        for (BufferedImage bi : listImg) {
            result += getSingleCharOcr(bi, map);
        }
        System.out.println(result);
        ImageIO.write(img, "JPG", new File("result4//" + result + ".jpg"));
        return result;
    }

    public static int getBlackCount(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int count = 0;
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    count++;
                }
            }
        }
        return count;
    }

/*    public static void downloadImage() {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(
                "http://reg.keepc.com/getcode/getCode.<a href="http://lib.csdn.net/base/php" class='replace_word' title="PHP知识库" target='_blank' style='color:#df3434; font-weight:bold;'>PHP</a>");
        for (int i = 0; i < 30; i++) {

            try {
                // 执行getMethod
                int statusCode = httpClient.executeMethod(getMethod);
                if (statusCode != HttpStatus.SC_OK) {
                    System.err.println("Method failed: "
                            + getMethod.getStatusLine());
                }
                // 读取内容
                String picName = "img4//" + i + ".jpg";
                InputStream inputStream = getMethod.getResponseBodyAsStream();
                OutputStream outStream = new FileOutputStream(picName);
                IOUtils.copy(inputStream, outStream);
                outStream.close();
                System.out.println(i + "OK!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放连接
                getMethod.releaseConnection();
            }
        }
    }*/

    public static void trainData() throws Exception {
        File dir = new File("temp4");
        File[] files = dir.listFiles();
        for (File file : files) {
            BufferedImage img = removeBackgroud("temp4//" + file.getName());
            List<BufferedImage> listImg = splitImage(img);
            if (listImg.size() == 4) {
                for (int j = 0; j < listImg.size(); ++j) {
                    ImageIO.write(listImg.get(j), "JPG", new File("train4//"
                            + file.getName().charAt(j) + "-" + (index++)
                            + ".jpg"));
                }
            }
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//    	String file = "F:\\image\\captchaView.jpg";
//    	BufferedImage img = removeBackgroud(file);
//    	saveImage(img, "1234");
    	List<BufferedImage> images = splitImage(ImageIO.read(new File("F:\\image\\456.jpg")));
    	for (int i = 0; i < images.size(); i++) {
    		saveImage(images.get(i), i+"");
		}
    	System.out.println("success!!!");
    }
    public static void saveImage(BufferedImage img,String imageName){
    	 try {
			ImageIO.write(img, "JPG", new File("F:\\image\\" + imageName + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
