package iha.snr11435.stogfinder_11435_11536.app;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ButtonsFragment extends Fragment {

    private OnFragmentInteractionListener aCallback;
    private Button updateButton;

    @Override
    public void onDetach() {
        super.onDetach();
        aCallback = null;
    }

    public ButtonsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            aCallback = (OnFragmentInteractionListener)activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buttons, container, false);
        updateButton = (Button)rootView.findViewById(R.id.updateBtn);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aCallback.onUpdateButtonClick();
            }
        });
        return rootView;
    }

    public interface OnFragmentInteractionListener{
        public void onUpdateButtonClick();
    }
}
