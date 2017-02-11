package priyanshu.zappos.com.ilovezappos;
/**
 * Created by PRIYANSHU on 2/3/2017.
 * This is the Main Activity which performs search operation and displays search results.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {
    private static final String JSON_OBJECT_REQUEST_URL = "https://api.zappos.com/Search?term=reebok&key=b743e26728e16b81da139182bb2094357c31d331";
    ProgressDialog progressDialog;
    private static final String TAG = "MainActivity";
    private RecyclerView rv;
    public Global ob;
    private SearchView searchView;
    public FloatingActionButton fb;
    private LinearLayoutManager lm;
    RecyclerViewAdapter adapter;
    ArrayList<MyModel> persons=new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        progressDialog = new ProgressDialog(this);
        ob=(Global) getApplication();
        fb=(FloatingActionButton) findViewById(R.id.fab);
        rv=(RecyclerView) findViewById(R.id.cv);
        rv.setHasFixedSize(false);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new RecyclerViewAdapter(ob,this,persons);
        rv.setAdapter(adapter);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Products");
        searchView.onActionViewExpanded();
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolate interpolator = new BounceInterpolate(0.2, 20);
                myAnim.setInterpolator(interpolator);
                fb.startAnimation(myAnim);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                View view = getCurrentFocus();
                InputMethodManager imm = (InputMethodManager)MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                searchView.clearFocus();
                volleyJsonObjectRequest(JSON_OBJECT_REQUEST_URL.replace("reebok", query));
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                finish();
                moveTaskToBack(true);
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.setOnItemClickListener(new RecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, Button v, SparseBooleanArray selectedItem) {
                Log.d("position",String.valueOf(position));
                final Animation myAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolate interpolator = new BounceInterpolate(0.2, 20);
                myAnim.setInterpolator(interpolator);

                fb.startAnimation(myAnim);
                fb.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                v.startAnimation(myAnim);
                if (selectedItem.get(position, false)) {
                    selectedItem.delete(position);
                    v.setSelected(false);
                }
                else {
                    selectedItem.put(position, true);
                    v.setSelected(true);
                    /*v.setText("Added");*/
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void volleyJsonObjectRequest(String url) {

        String REQUEST_TAG = "Zappos.Search";
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            persons.clear();
                            JSONObject json = new JSONObject(response.toString());
                            JSONArray result = json.getJSONArray("results");
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject c = result.getJSONObject(i);
                                MyModel pd = new MyModel();
                                pd.setBrandName(c.getString("brandName"));
                                pd.setThumbnailImageUrl((c.getString("thumbnailImageUrl")).replace("\\", ""));
                                pd.setProductId(c.getString("productId"));
                                pd.setOriginalPrice(c.getString("originalPrice"));
                                pd.setStyleId(c.getString("styleId"));
                                pd.setColorId(c.getString("colorId"));
                                pd.setPrice(c.getString("price"));
                                pd.setPercentOff(c.getString("percentOff"));
                                pd.setProductUrl(c.getString("productUrl"));
                                pd.setProductName(c.getString("productName"));
                                persons.add(pd);
                            }
                            final Animation myAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce);

                            // Use bounce interpolator with amplitude 0.2 and frequency 20
                            BounceInterpolate interpolator = new BounceInterpolate(0.2, 20);
                            myAnim.setInterpolator(interpolator);
                            fb.startAnimation(myAnim);
                            adapter.selectedItems.clear();
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                                  progressDialog.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                searchView.clearFocus();
                progressDialog.hide();
            }
        });
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq, REQUEST_TAG);
    }

    public void volleyCacheRequest(String url) {
        Cache cache = AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache();
        Cache.Entry entry = cache.get(url);
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    public void volleyInvalidateCache(String url) {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().invalidate(url, true);
    }

    public void volleyDeleteCache(String url) {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().remove(url);
    }

    public void volleyClearCache() {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
    }

}
