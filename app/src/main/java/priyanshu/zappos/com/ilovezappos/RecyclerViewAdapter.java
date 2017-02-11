package priyanshu.zappos.com.ilovezappos;
/**
 * Created by PRIYANSHU on 2/9/2017.
 * This is the RecyclerViewAdapter which binds the product data with the UI thread, using databinding class.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import java.util.List;
import static com.android.volley.VolleyLog.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private List<MyModel> products;
    private Context mcontext;
    private Global ob;
    final SparseBooleanArray selectedItems=new SparseBooleanArray(20);

    public RecyclerViewAdapter(Global ob,Context context,List<MyModel> products) {
        this.products = products;
        this.mcontext=context;
        this.ob=ob;
    }

    private static MyClickListener myClickListener;

    public  class ViewHolder extends RecyclerView.ViewHolder implements Button.OnClickListener {
        public TextView mTextView,mbrand,mprice,moriginalPrice,mOff;
        public ImageView mImageView;

        final public Button mButton;
        public ViewHolder(View v) {
            super(v);
            int width=mcontext.getResources().getDisplayMetrics().widthPixels;
            int height=mcontext.getResources().getDisplayMetrics().heightPixels;
           mTextView=(TextView) v.findViewById(R.id.testViewProduct);
            mImageView=(ImageView) v.findViewById(R.id.imageViewProduct);
            mbrand=(TextView)v.findViewById(R.id.testViewProductBrand);
            mbrand.setTypeface(null,Typeface.BOLD);
            mprice=(TextView)v.findViewById(R.id.testViewPrice);
            mOff=(TextView)v.findViewById(R.id.testViewOff);
            moriginalPrice=(TextView)v.findViewById(R.id.testViewOriginalPrice);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(width/2,height/4);
            LinearLayout.LayoutParams layoutButton = new LinearLayout.LayoutParams(width/7,height/12);
            mImageView.setLayoutParams(layout);
            mButton=(Button) v.findViewById(R.id.buttonProduct);
            mButton.setLayoutParams(layoutButton);
            mButton.setOnClickListener(this);
        }

        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), mButton,selectedItems);
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
holder.mTextView.setText(products.get(position).getProductName());
        ImageLoader imageLoader = AppSingleton.getInstance(mcontext).getImageLoader();
        imageLoader.get(products.get(position).getThumbnailImageUrl(), new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    holder.mImageView.setImageBitmap(response.getBitmap());
                }
            }
        });
        holder.mbrand.setText(products.get(position).getBrandName());
        holder.mprice.setText(products.get(position).getPrice());
        holder.mprice.setTextColor(Color.BLUE);
        if(products.get(position).getPercentOff().equalsIgnoreCase("0%")){

        }
        else {
            holder.moriginalPrice.setText(products.get(position).getOriginalPrice());
            holder.mOff.setText(products.get(position).getPercentOff() + " Off");
            holder.moriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.moriginalPrice.setTextColor(Color.RED);
        }
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                ob.setUrl(products.get(position).getProductUrl());
                mcontext.startActivity(new Intent(mcontext, zapposActivity.class));
            }
        });
       holder.mButton.setSelected(selectedItems.get(position,false));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, Button v,SparseBooleanArray selectedITems);
    }
}
