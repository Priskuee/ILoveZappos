package priyanshu.zappos.com.ilovezappos;
import java.util.ArrayList;

/**
 * Created by PRIYANSHU on 2/3/2017.
 * This is the model which is used for storing the data and the model behind the data binding class.
 */

public class MyModel {

    String search;
    String brandName;
    String thumbnailImageUrl;
    String productId;
    String originalPrice;
    String styleId;
    String colorId;
    String price;
    String percentOff;
    String productUrl;
    String productName;
    ArrayList<MyModel> list=new ArrayList<>();
    public MyModel(){

    }
    public void setBrandName(String brandName){
        this.brandName=brandName;

    }
    public void setThumbnailImageUrl(String thumbnailImageUrl){
        this.thumbnailImageUrl=thumbnailImageUrl;

    }
    public void setProductId(String productId){
        this.productId=productId;
    }
    public void setOriginalPrice(String originalPrice){
        this.originalPrice=originalPrice;

    }
    public void setStyleId(String styleId){
        this.styleId=styleId;
    }
    public void setColorId(String colorId){
        this.colorId=colorId;
    }
    public void setPrice(String price){
        this.price=price;
    }
    public void setPercentOff(String percentOff){
        this.percentOff=percentOff;
    }
    public void setProductUrl(String productUrl){
        this.productUrl=productUrl;
    }
    public void setProductName(String productName){
        this.productName=productName;
    }

    public String getBrandName(){
        return this.brandName;
    }

    public String getThumbnailImageUrl(){
        return this.thumbnailImageUrl;
    }

    public String getProductId(){
        return this.productId;
    }

    public String getOriginalPrice(){
        return this.originalPrice;
    }

    public String getStyleId(){
        return this.styleId;
    }

    public String setColorId(){
        return this.colorId;
    }

    public String getPrice(){
        return this.price;
    }

    public String getPercentOff(){
        return this.percentOff;
    }

    public String getProductUrl(){
        return this.productUrl;
    }

    public String getProductName(){
        return this.productName;
    }
}
