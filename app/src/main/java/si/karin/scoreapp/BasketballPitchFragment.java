package si.karin.scoreapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by Karin on 18.4.2016.
 */
public class BasketballPitchFragment extends PitchFragment implements MatchPitchCallback{
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)getActivity()).setMatchPitchCallback(this);
    }

    /**
     * Returns a new instance of this fragment
     */
    public static BasketballPitchFragment newInstance() {

        Bundle args = new Bundle();

        BasketballPitchFragment fragment = new BasketballPitchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  super.onCreateView(inflater, container, savedInstanceState);
        imgPitch.setImageResource(R.mipmap.basketball_pitch);
        tvHome.setText(sharedPrefs.getInt("Basketball_home", 0)+"");
        tvAway.setText(sharedPrefs.getInt("Basketball_away", 0)+"");

        return rootView;
    }

    @Override
    public void setResult(int home, int away) {
        Log.e("BASKETBALL", "pitch");
        this.home = home;
        this.away = away;
        ((TextView) rootView.findViewById(R.id.anim_home)).setText(home + "");
        ((TextView) rootView.findViewById(R.id.anim_away)).setText(away + "");
        Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.from_left_to_center);
        a.setAnimationListener(showListener);
        scoreLayout.startAnimation(a);
    }
}
