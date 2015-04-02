package com.yash.ads;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.unity3d.player.UnityPlayer;

public class InterstitialLoader implements Runnable{
	
	String ID;
	InterstitialAd iad;
	public InterstitialLoader(String id) {
		ID = id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		iad = new InterstitialAd(hook.activity);
		iad.setAdUnitId(ID);
		requestAd();
	
		iad.setAdListener(new AdListener() {

			@Override
			public void onAdClosed() {
				// TODO Auto-generated method stub
				super.onAdClosed();
				 requestAd();
				
			}

			@Override
			public void onAdLoaded() {
				 UnityPlayer.UnitySendMessage("AdManager", "InterstitialLoaded", "");
			}
			
			
		});
		hook.interstitialAd = iad;
		
	}
	
	private void requestAd(){
		AdRequest.Builder builder = new AdRequest.Builder();
		if(hook.isTestAd){
			builder.addTestDevice(hook.GetTestDeviceID());
		}
		AdRequest adRequest = builder.build();
		iad.loadAd(adRequest);
	}

}
