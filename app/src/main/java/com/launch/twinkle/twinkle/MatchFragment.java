package com.launch.twinkle.twinkle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class MatchFragment extends Fragment {
  private static final String TAG = MatchFragment.class.getSimpleName();
  // match user id, Name, age, comment, commenter user id, number of messages.
  private List<String> templateData = new ArrayList<String>();
  private View view;

  public MatchFragment() {
    templateData.add("10153082238072156");
    templateData.add("Holman G");
    templateData.add("25 yrs old");
    templateData.add("Ian, she seems like a really nice girl.");
    templateData.add("10153082238072156");
    templateData.add("6 more messages");
  }

  public void setMatchingPage() {
    TextView matchName = (TextView) view.findViewById(R.id.match_name);
    matchName.setText(templateData.get(1));
    TextView matchAge = (TextView) view.findViewById(R.id.match_age);
    matchAge.setText(templateData.get(2));
    TextView matchMessage = (TextView) view.findViewById(R.id.message);
    matchMessage.setText(templateData.get(3));
    TextView matchMoreMessages = (TextView) view.findViewById(R.id.match_more_messages);
    matchMoreMessages.setText(templateData.get(5));
    setPage(templateData.get(0), (ImageView) view.findViewById(R.id.match_picture));
    setPage(templateData.get(4), (ImageView) view.findViewById(R.id.profile_picture));
  }

  public void setPage(String userId, final ImageView imageView) {

    // Proof of concept for binding to picture
    String pictureKey = "users/" + userId + "/profilePictureUrl";
    Firebase userFirebaseRef = new Firebase(Constants.FIREBASE_URL);
    userFirebaseRef.child(pictureKey).

    addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange (DataSnapshot snapshot){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = (String) snapshot.getValue();
        try {
          URL imageURL = new URL(url);

          Bitmap image = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
          imageView.setImageBitmap(image);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onCancelled (FirebaseError firebaseError){
      }
    });
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_match, container, false);

    getActivity().getActionBar().setTitle("Today's Match");
    getActivity().invalidateOptionsMenu();

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    setMatchingPage();
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  private class PictureGetter extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... v) {
      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
    }
  }
}
