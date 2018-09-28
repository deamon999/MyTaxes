package deamon999.gmail.com.mytaxes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment extends Fragment {

    private EditText search_text;
    private Button search_button;
    private RecyclerView search_list;

    private ArrayList<Item> favorits;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favorits = ((MainActivity) getActivity()).getFavorits();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        search_text = view.findViewById(R.id.search_text);
        search_button = view.findViewById(R.id.button_search);
        search_list = view.findViewById(R.id.recyclerView_two);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(search_text.getText().toString())) {
                    Toast.makeText(getContext(), R.string.search_empty,
                            Toast.LENGTH_LONG).show();
                } else {
                    String search = search_text.getText().toString();
                    Taxes taxes = Taxes.retrofit.create(Taxes.class);
                    final Call<TaxesResualt> call =
                            taxes.getPersons(search);

                    call.enqueue(new Callback<TaxesResualt>() {
                        @Override
                        public void onResponse(Call<TaxesResualt> call, Response<TaxesResualt> response) {

                            TaxesResualt resualt = response.body();
                            SearchAdapter adapter = new SearchAdapter(resualt.getItems(), favorits, getContext());
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

                            search_list.setAdapter(adapter);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            search_list.setLayoutManager(linearLayoutManager);
                            search_list.setItemAnimator(itemAnimator);
                        }

                        @Override
                        public void onFailure(Call<TaxesResualt> call, Throwable throwable) {
                            Toast.makeText(getContext(), throwable.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        ((MainActivity) getActivity()).onFragmentInteraction(R.id.action_one);
        return view;
    }
}
