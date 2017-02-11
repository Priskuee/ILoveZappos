package priyanshu.zappos.com.ilovezappos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by PRIYANSHU on 2/8/2017.
 * This Activity takes the user to the zappos.com on click on the product.
 */

public class zapposActivity extends Activity {
    private WebView w;
    private Global ob;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zappos);
        pd=new ProgressDialog(this);
        pd.setMessage("Taking you to Zappos.com");
        pd.show();
        ob = (Global) getApplication();
        w=(WebView) findViewById(R.id.mWebViewFull);
        w.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if(pd!=null && pd.isShowing())
                {
                    pd.dismiss();
                }
            }
        });
        w.loadUrl(ob.getUrl());
        WebSettings webSettings = w.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && w.canGoBack()) {
            w.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
