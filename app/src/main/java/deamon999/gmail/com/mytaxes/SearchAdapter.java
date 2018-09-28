package deamon999.gmail.com.mytaxes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<Item> search_list;
    private ArrayList<Item> favorit_list;
    private Context context;

    public SearchAdapter(ArrayList<Item> search_list, ArrayList<Item> favorit_list, Context context) {
        this.search_list = search_list;
        this.favorit_list = favorit_list;
        this.context = context;
    }

    public SearchAdapter(ArrayList<Item> search_list, Context context) {
        this.search_list = search_list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder itemViewHolder, final int position) {
        Item person = search_list.get(position);
        itemViewHolder.first_name.setText(person.getFirstname());
        itemViewHolder.last_name.setText(person.getLastname());
        itemViewHolder.work_place.setText(person.getPlaceOfWork());
        itemViewHolder.work_position.setText(person.getPosition());

        if (person.getComment() == null) {
            itemViewHolder.comment.setVisibility(View.GONE);
        } else {
            itemViewHolder.comment.setText(person.getComment());
        }

        //Here you can fill your row view
    }

    @Override
    public int getItemCount() {
        return search_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView first_name;
        private TextView last_name;
        private TextView work_place;
        private TextView work_position;
        private EditText comment;
        private ImageButton favotits;
        private ImageButton pdf;

        public ViewHolder(View itemView) {
            super(itemView);
            first_name = itemView.findViewById(R.id.first_name);
            last_name = itemView.findViewById(R.id.last_name);
            work_place = itemView.findViewById(R.id.work_place);
            work_position = itemView.findViewById(R.id.work_position);
            comment = itemView.findViewById(R.id.comment_text);
            comment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Item item = search_list.get(getAdapterPosition());
                    item.setComment(s.toString());
                }
            });

            favotits = itemView.findViewById(R.id.favotirs_button);
            pdf = itemView.findViewById(R.id.pdf_button);

            favotits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (favorit_list == null) {
                        search_list.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                    } else {
                        if (!favorit_list.contains(search_list.get(getAdapterPosition()))) {
                            final Item person = search_list.get(getAdapterPosition());

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle(R.string.add_comment);

                            final EditText input = new EditText(context);
                            input.setInputType(InputType.TYPE_CLASS_TEXT);
                            builder.setView(input);

                            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    person.setComment(input.getText().toString());
                                }
                            });
                            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    person.setComment("Коментар");
                                }
                            });
                            builder.show();
                            favorit_list.add(person);
                        }
                    }
                }
            });

            pdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri address = Uri.parse(search_list.get(getAdapterPosition()).getLinkPDF());
                    Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                    context.startActivity(openlinkIntent);
                }
            });
        }
    }
}
