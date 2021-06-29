package testdatasource;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;


public class ScreenSave {
	public static void main(String[] args) {
        try {
        
    		Date currentdate = new Date();
    		
    		String screenshotfilename = currentdate.toString().replace(" ","-").replace(":","-");
        	
            Robot robot=new Robot();
            //根據指定的區域抓取屏幕的指定區域，1300是長度，800是寬度。
            BufferedImage bi=robot.createScreenCapture(new Rectangle(1920,1080));
            //把抓取到的內容寫到一個jpg文件中
//            ImageIO.write(bi, "jpg", new File("C:\\Users\\Tibame_T14\\Desktop\\screenshot\\" + screenshotfilename+".png"));
            
            ByteArrayOutputStream out =new ByteArrayOutputStream();
            ImageIO.write(bi,"png",out);//png 为要保存的图片格式
            byte[] barray = out.toByteArray();
          
            out.close();
            
 
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}