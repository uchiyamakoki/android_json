# android_json
Android studio版本<br>
这次遇到了eclipse可以从服务器收到json数据而Android Studio返回为null的bug。。。。<br>
在此十分感谢 https://blog.csdn.net/fl1623863129/article/details/72956002 <br>

把http的请求单独放在一个新线程中

`new Thread(new Runnable(){

    public void run(){
        //...这里在写HttpURLConnection请求
    }
}).start();  // 最后别忘记start() 启动`
或者加以下这个方法，请求前调用一下就可以了

public static void closeStrictMode() {

StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()

.detectAll().penaltyLog().build());
}
还有一个失败原因没有在Androidmanifest.xml中加入权限：<uses-permission android:name="android.permission.INTERNET"/> 
