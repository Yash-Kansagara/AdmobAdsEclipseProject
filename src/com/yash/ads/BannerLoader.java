package com.yash.ads;

import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BannerLoader  implements Runnable{

	public String ID, Position;
	
	public BannerLoader(String Id, String position) {
		this.ID = Id;
		this.Position = position;
	}
	
	@Override
	public void run() {
		try {
			
			// parent layout for ad
			LinearLayout layout = new LinearLayout(hook.activity);
			layout.setOrientation(LinearLayout.VERTICAL);
			if(Position.equals("TOP")){
				layout.setGravity(Gravity.TOP);
			}else{
				layout.setGravity(Gravity.BOTTOM);
			}
			
			// add parent layout to activity
			hook.activity.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

	         // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
	         AdView mAdView = new AdView(hook.activity);
	         mAdView.setAdSize(AdSize.SMART_BANNER);
	         
	         mAdView.setAdUnitId(ID);

	         // Add banner view to linear layout
	         layout.addView(mAdView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	         
	         // Create an ad request.
	         AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

	         // Optionally populate the ad request builder.
	         if(hook.isTestAd){
	        	 String id = hook.GetTestDeviceID();
	        	 adRequestBuilder.addTestDevice(id);
	         }

	         // Add the AdView to the view hierarchy.
//	         layout.addView(mAdView);

	         // Start loading the ad.
	         mAdView.loadAd(adRequestBuilder.build());
	         
	         hook.bannerAd = mAdView;
	          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
