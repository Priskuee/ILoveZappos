package priyanshu.zappos.com.ilovezappos;

import android.app.Application;

/**
 * Created by PRIYANSHU on 2/8/2017.
 * This is an instance of application for maintaing global variables.
 */

public class Global extends Application {
    String Url;
    public void setUrl(String Url){
        this.Url=Url;
    }
    public String getUrl(){
        return Url;
    }
}
