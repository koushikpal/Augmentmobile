package com.example.augmentmobile;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
public class MainActivity extends Activity {

	//variable for selection intent
	private final int PICKER = 1;
	//variable to store the currently selected image
	private int currentPic = 0;
	//gallery object
	private Gallery picGallery;
	//image view for larger display
	private ImageView picView;
	
	//adapter for gallery view
	private PicAdapter imgAdapt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //get the large image view
        picView = (ImageView) findViewById(R.id.picture);
                 
        //get the gallery view
        picGallery = (Gallery) findViewById(R.id.gallery);
      //create a new adapter
        imgAdapt = new PicAdapter(this);
         
        //set the gallery adapter
        picGallery.setAdapter(imgAdapt);
    }
    public class PicAdapter extends BaseAdapter {
    	//use the default gallery background image
    	int defaultItemBackground;
    	         
    	//gallery context
    	private Context galleryContext;
    	 
    	//array to store bitmaps to display
    	private Bitmap[] imageBitmaps;
    	 
    	//placeholder bitmap for empty spaces in gallery
    	Bitmap placeholder;
    	
    	public PicAdapter(Context c) {
    		 
    	    //instantiate context
    	    galleryContext = c;
    	 
    	    //create bitmap array
    	    imageBitmaps  = new Bitmap[10];
    	             
    	    //decode the placeholder image
    	    placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    	     
    	  //set placeholder as all thumbnail images in the gallery initially
    	    for(int i=0; i<imageBitmaps.length; i++)
    	        imageBitmaps[i]=placeholder;
    	    TypedArray styleAttrs = galleryContext.obtainStyledAttributes(R.styleable.PicGallery);
    	    
    	  //get the background resource
    	  defaultItemBackground = styleAttrs.getResourceId(
    	      R.styleable.PicGallery_android_galleryItemBackground, 0);
    	   
    	  //recycle attributes
    	  styleAttrs.recycle();
    	}
    	//return number of data items i.e. bitmap images
    	public int getCount() {
    	    return imageBitmaps.length;
    	}
    	//return item at specified position
    	public Object getItem(int position) {
    	    return position;
    	}
    	//return item ID at specified position
    	public long getItemId(int position) {
    	    return position;
    	}
    	//get view specifies layout and display options for each thumbnail in the gallery
    	public View getView(int position, View convertView, ViewGroup parent) {
    	 
    	    //create the view
    	    ImageView imageView = new ImageView(galleryContext);
    	    //specify the bitmap at this position in the array
    	    imageView.setImageBitmap(imageBitmaps[position]);
    	    //set layout options
    	    imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
    	    //scale type within view area
    	    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    	    //set default gallery item background
    	    imageView.setBackgroundResource(defaultItemBackground);
    	    //return the view
    	    
    	    return imageView;
    	}

    }
}
