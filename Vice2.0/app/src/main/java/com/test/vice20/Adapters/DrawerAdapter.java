package com.test.vice20.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.test.vice20.Models.ContentOfParentDrawer;
import com.test.vice20.Models.OpenDrawer;
import com.test.vice20.Models.ParentDrawer;
import com.test.vice20.Models.FavoritesDrawer;
import com.test.vice20.R;

import java.util.ArrayList;
import java.util.List;



//custom adapter that does different things for multiple items:
    //if checkboxchecked it tells checkedArray the status
    // carries special view depending on which drawer is clicked


    //tells each view what to do dpending on the type of item it contains,
    //does this by determining 'if' item is an instance of a class


public class DrawerAdapter extends RecyclerView.Adapter {
    private List<OpenDrawer> data;
    private LayoutInflater inflater;
    private ArrayList<Boolean> isCheckedArray = new ArrayList<>();
    public DrawerAdapter(Context context, List<OpenDrawer> data, ArrayList<Boolean> isCheckedArray) {
        this.data = data;
        this.isCheckedArray = isCheckedArray;
        this.inflater = LayoutInflater.from(context);
    }

 //special view holder depending on if its a toggle, parent drawer, or (child) content of parent drawer
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView;



                if(viewType==0) {
                    itemLayoutView = inflater.inflate(R.layout.contents_of_category_drawer, parent, false);
                    ContentsOfCategoryDrawer categoryDrawerViewHolder = new ContentsOfCategoryDrawer(itemLayoutView);
                    return categoryDrawerViewHolder;
                }
            if(viewType==1) {
                itemLayoutView = inflater.inflate(R.layout.fragment_favorites, parent, false);
                FavoritesDrawer favoritesViewHolder = new FavoritesDrawer(itemLayoutView);
                return favoritesViewHolder;
            }

                    if(viewType==2) {
                itemLayoutView = inflater.inflate(R.layout.drawer_checkbox, parent, false);
                CheckBox checkViewHolder = new CheckBox(itemLayoutView);
                return checkViewHolder;
        }

        return null;
    }

  //tells drawer views what to do based on the contents of the drawer
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder recycleViewHolder, final int position) {
        final OpenDrawer item = data.get(position);

        if (item instanceof ParentDrawer) { //parent categories drawer
            ParentDrawer parentDrawerViewHolder = (ParentDrawer) parentDrawerViewHolder;
            parentDrawerViewHolder.name.setText(((ParentDrawer) item).getName());
            parentDrawerViewHolder.getIconNumber().setIconNumber(((ParentDrawer) item).getIconNumber());
        }

        if (item instanceof FavoritesDrawer) { //expanded categories drawer
            FavoritesDrawer favoritesDrawerViewHolder = (FavoritesDrawer) favoritesDrawerViewHolder;
            favoritesDrawerViewHolder.name.setText(((ParentDrawer) item).getName());
            favoritesDrawerViewHolder.getIconNumber().setIconNumber(((ParentDrawer) item).getIconNumber());
        }

        if (item instanceof ContentOfParentDrawer) {
            ContentOfParentDrawer contentOfParentDrawerViewHolder = (ContentOfParentDrawer) contentOfParentDrawerViewHolder;
            contentOfParentDrawerViewHolder.name.setText(((ContentOfParentDrawer) item).getName());
        }

        if (item instanceof com.test.vice20.Models.CheckBox)  {
            final com.test.vice20.Models.CheckBox checkBoxViewHolder = (com.test.vice20.Models.CheckBox) checkBoxViewHolder;
            checkBoxViewHolder.name.setText(((com.test.vice20.Models.CheckBox) item).getName());

            checkBoxViewHolder.mswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckChange(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        checkBoxViewHolder.mswitch.setChecked(true);
                        checkBoxViewHolder.set(position, true);
                    } else {
                        checkBoxViewHolder.mswitch.setChecked(false);
                        isCheckedArray.set(position, false);
                    }
                }
            });

            if (!isCheckedArray.get(position)) {
                checkBoxViewHolder.mswitch.isChecked(false);
            } else {
                checkBoxViewHolder.mswitch.setChecked(true);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (data.get(position) instanceof ContentOfParentDrawer) {
            return 0;
        }
        if (data.get(position) instanceof FavoritesDrawer) {
            return 1;
        }
        if (data.get(position) instanceof CheckBox) {
            return 2;
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

  // Array of booleans based on toggle positions
    public ArrayList<Boolean> getIsCheckedArray() {
        return isCheckedArray;
    }


///sets view depending on which button is clicked in the Drawer Fragment
    class ParentDrawerViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final ImageView imageView;

        final Button categoryButton;
        final Button favoritesButton;
        final Button thirdButton;

        public ParentDrawerViewHolder(View itemView) {
            super(itemView);
            categoryButton = (Button) itemView.findViewById(R.id.category_drawer);
            favoritesButton = (Button) itemView.findViewById(R.id.favorite_drawer);
        thirdButton = (Button) itemView.findViewById(R.id.tbd_third_drawer);
        }
    }


///sets views depending on which button is clicked inside of the contentsofcategorydrawer which was expanded from the category drawer on the drawer fragment
    class ContentsOfCategoryDrawer extends RecyclerView.ViewHolder {
        final TextView name;

        public ContentsOfCategoryDrawer(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.categories_list);
        }
    }

    class CheckBoxViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final Switch mswitch;

        public CheckBoxViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.contentsname);
            mswitch = (Switch) itemView.findViewById(R.id.switchit);
        }
    }
}
