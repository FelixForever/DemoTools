package com.felix.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownServiceException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import android.util.Xml.Encoding;

public class HttpRequest {
	private final static String CLASS_TAG=HttpRequest.class.getName()+".";
//	public static final boolean downloadMp3FromNet(Mp3File mp3File,File directory)
//	{
//		final String TAG=CLASS_TAG+"downloadMp3FromNet";
//		if(directory==null||!directory.exists())
//		{
//			Log.i(TAG,"directory is null? "+(directory==null)+" directory is not exists? "+!directory.exists());
//			return false;
//		}
//		String urlStr=mp3File.getUrl();
//        String fileName=mp3File.getFileName()+"."+mp3File.getExtName();  
//        OutputStream output=null;  
//        try {  
//            /* 
//             * 通过URL取得HttpURLConnection 
//             * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置 
//             * <uses-permission android:name="android.permission.INTERNET" /> 
//             */  
//            URL url=new URL(urlStr);  
//            HttpURLConnection conn=(HttpURLConnection)url.openConnection();  
//            //取得inputStream，并将流中的信息写入SDCard  
//              
//            /* 
//             * 写前准备 
//             * 1.在AndroidMainfest.xml中进行权限配置 
//             * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> 
//             * 取得写入SDCard的权限 
//             * 2.取得SDCard的路径： Environment.getExternalStorageDirectory() 
//             * 3.检查要保存的文件上是否已经存在 
//             * 4.不存在，新建文件夹，新建文件 
//             * 5.将input流中的信息写入SDCard 
//             * 6.关闭流 
//             */  
//            //String SDCard=Environment.getExternalStorageDirectory()+""; 
//            File saveFile=new File(directory, fileName);
//            //String pathName=saveFile.getAbsolutePath();//文件存储路径  
//              
//            //File file=new File(pathName);  
//            InputStream input=conn.getInputStream();
//            if(saveFile.exists()){
//            	String repeatFilePath="";
//            	for(int i=0;i<1000;i++)
//            	{
//            		
//            		repeatFilePath=mp3File.getFileName()+"_"+i+"."+mp3File.getExtName(); 
//            		File repeatFile=new File(directory, repeatFilePath);
//            		if(!repeatFile.exists())
//            		{
//            			saveFile=repeatFile;
//            			break;
//            		}
//            	}
//            	if(saveFile.exists())
//            	{
//            		System.out.println("exits");  
//                    return false;  
//            	}
//            }else{
//                saveFile.createNewFile();//新建文件  
//                output=new FileOutputStream(saveFile);  
//                //读取大文件  
//                byte[] buffer=new byte[4*1024];  
//                while(input.read(buffer)!=-1){  
//                    output.write(buffer);  
//                }  
//                output.flush();  
//            }  
//        } catch (MalformedURLException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }finally{  
//            try {  
//                    output.close();  
//                    System.out.println("success");  
//                } catch (IOException e) {  
//                    System.out.println("fail");  
//                    e.printStackTrace();  
//                }  
//            
//        }  
//        return true;
//	}
	public static String httpGet(String url) throws ClientProtocolException,
			IOException
	{
		final String TAG = CLASS_TAG + "httpGet";
		HttpClient httpCient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpCient.execute(httpGet);
		final int resultCode = httpResponse.getStatusLine().getStatusCode();
		if (resultCode == 200)
		{
			HttpEntity entity = httpResponse.getEntity();
			String response = EntityUtils.toString(entity, "utf-8");
			return response;
		} else
		{
			Log.e(TAG, "return code is " + resultCode);
			return null;
		}
	}

	public static String httpPost(String url, List<NameValuePair> pairList)
			throws NullPointerException,UnknownServiceException,Exception
	{
		final String TAG = CLASS_TAG + "httpPost";
		if (pairList == null)
		{
			Log.e(TAG, "pairList is null");
			return null;
		}
		HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList,
				Encoding.UTF_8.toString());
		// URL使用基本URL即可，其中不需要加参数
		HttpPost httpPost = new HttpPost(url);
		// 将请求体内容加入请求中
		httpPost.setEntity(requestHttpEntity);
		// 需要客户端对象来发送请求
		HttpClient httpClient = new DefaultHttpClient();
		// 发送请求
		Log.i(TAG, "http client set successful");
		HttpResponse response = httpClient.execute(httpPost);
		Log.i(TAG, "get response==NULL? " + (response == null));
		// 显示响应

		if (response != null && response.getStatusLine().getStatusCode() == 200)
		{
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity == null)
			{
				Log.e(TAG, "httpEntity is null");
				throw new NullPointerException();
			}
			String str = EntityUtils.toString(httpEntity,
					Encoding.UTF_8.toString());
			if (str == null)
			{
				Log.e(TAG, "result string is null");
				throw new NullPointerException();
			}
			return str;
		} else
		{
			Log.e(TAG, "result code is "
					+ response.getStatusLine().getStatusCode());
			throw new UnknownServiceException();
		}

	}
}
