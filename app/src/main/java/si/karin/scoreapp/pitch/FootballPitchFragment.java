package si.karin.scoreapp.pitch;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import si.karin.scoreapp.MainActivity;
import si.karin.scoreapp.R;

/**
 * Fragment for football match pitch view
 * <p>
 * Created by Karin on 18.4.2016.
 */
public class FootballPitchFragment extends PitchFragment implements MatchPitchCallback {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // set callback
        ((MainActivity) getActivity()).setFootballMatchPitchCallback(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sp_home = "Football_home";
        sp_away = "Football_away";

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        imgPitch.setImageResource(R.mipmap.football_pitch);

        return rootView;
    }
}
