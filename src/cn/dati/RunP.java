package cn.dati;

import java.io.File;
import java.io.IOException;

public class RunP {
	public static void run(Callback callback) {
		new Thread() {
			public void run() {
				
				 try {
			            //ͼƬ�ļ�����ͼƬ����Ҫ��ʶ���ͼƬ 
					 	long startTime=System.currentTimeMillis();
			            File file = new File(AndroidImage.getAdbImage());
			            if(file.exists()) {
			            	System.out.println("�ļ�����" + "\t");
			            }else {
			            	System.out.println("�ļ�������" + "\t");
			            }
			            File newFile = ImgUtils.cropImage(file);
			            if(file.exists()) {
			            	System.out.println("���ļ�����" + "\t");
			            }else {
			            	System.out.println("���ļ�������" + "\t");
			            }
			            String recognizeText = new OCRHelper().recognizeText(newFile);
			            
			            System.out.println(recognizeText + "\t");
			            System.out.println("�ܺ�ʱ��"+(System.currentTimeMillis()-startTime)+"����");
			            if(callback!=null) {
			            	callback.succeed(recognizeText);
			            }else {
			            	openDefaultBrowser(recognizeText);
			            }
			        } catch (IOException e) {
			            e.printStackTrace();
			            System.out.println("IO �쳣" + "\t");
			        } catch (Exception e) {
			            e.printStackTrace();
			            System.out.println("�쳣" + "\t");
			        }
				
				
			};
			
			
		}.start();
		
	}
	
	  /** 
     * ��Ĭ�����������ҳ�� 
     */  
    public static void openDefaultBrowser(String str){  
    	if(str==null||str.isEmpty()) {
    		return;
    	}
    	try {
			Runtime.getRuntime().exec("cmd   /c   start   https://www.baidu.com/s?wd="+str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }  
}
