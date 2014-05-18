package iha.snr11435.stogfinder_11435_11536.app;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ButtonsFragment extends Fragment {

    private OnFragmentInteractionListener aCallback;
    private Button updateButton;
    private EditText filterText;

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
        filterText = (EditText)rootView.findViewById(R.id.filterEditText);
        filterText.addTextChangedListener(filterTextWatcher);
        return rootView;
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            aCallback.onFilterUpdated(s);
        }

    };

    public interface OnFragmentInteractionListener{
        public void onUpdateButtonClick();
        public void onFilterUpdated(CharSequence sequence);
    }
}
