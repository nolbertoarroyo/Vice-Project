//package com.test.vice20.Adapters;
//
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.Switch;
//import android.widget.TextView;
//
//
//import com.test.vice20.Models.ContentOfParentDrawer;
//import com.test.vice20.Models.DrawerSeperator;
//import com.test.vice20.Models.OpenDrawer;
//import com.test.vice20.Models.ParentDrawer;
//import com.test.vice20.Models.Toggle;
//import com.test.vice20.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
////custom adapter that does different things for multiple items:
//    //if toggle is clicked
//    // carries special VH depending on whether the person clicks a parent drawer (no icon)
//    //of sub item (icon)
//
//    //tells each view what to do dpending on the type of item it contains,
//    //does this by determining 'if' item is an instance of a class
//
//
//
//
//public class DrawerAdapter extends RecyclerView.Adapter {
//    private List<OpenDrawer> data;
//    private LayoutInflater inflater;
//    private ArrayList<Boolean> isToggledArray = new ArrayList<>();
//    public DrawerAdapter(Context context, List<OpenDrawer> data, ArrayList<Boolean> isToggledArray) {
//        this.data = data;
//        this.isToggledArray = isToggledArray;
//        this.inflater = LayoutInflater.from(context);
//    }
//
// //special view holder depending on if its a toggle, parent drawer, or (child) content of parent drawer
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemLayoutView;
//
//
//
//                if(viewType==0) {
//                    itemLayoutView = inflater.inflate(R.layout.parent_drawer_with_icon, parent, false);
//                    ParentDrawerViewHolder holder = new ParentDrawerViewHolder(itemLayoutView);
//                    return holder;
//                }
//            if(viewType==1) {
//                itemLayoutView = inflater.inflate(R.layout.drawer_separator, parent, false);
//                SeparaterViewHolder separaterViewHolder = new SeparaterViewHolder(itemLayoutView);
//                return separaterViewHolder;
//            }
//
//                if(viewType==2) {
//                    itemLayoutView = inflater.inflate(R.layout.contents_of_parent_drawer, parent, false);
//                    ContentsOfParentDrawerViewHolder contentsOfParentDrawerViewHolder = new ContentsOfParentDrawerViewHolder(itemLayoutView);
//                    return contentsOfParentDrawerViewHolder;
//                }
//                    if(viewType==3) {
//                itemLayoutView = inflater.inflate(R.layout.drawer_toggle_layout, parent, false);
//                ToggleViewHolder toggleViewHolder = new ToggleViewHolder(itemLayoutView);
//                return toggleViewHolder;
//        }
//
//        return null;
//    }
//
//  //tells drawer views what to do based on the contents of the drawer
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        final OpenDrawer item = data.get(position);
//
//        if (item instanceof ParentDrawer) {
//            ParentDrawerViewHolder viewHolder = (ParentDrawerViewHolder) holder;
//            viewHolder.name.setText(((ParentDrawer) item).getName());
//            viewHolder.imageView.setImageResource(((ParentDrawer) item).getIconNumber());
//        }
//
//        if (item instanceof ContentOfParentDrawer) {
//            ContentsOfParentDrawerViewHolder viewHolder = (ContentsOfParentDrawerViewHolder) holder;
//            viewHolder.name.setText(((ContentOfParentDrawer) item).getName());
//        }
//
//        if (item instanceof Toggle)  {
//            final ToggleViewHolder viewHolder = (ToggleViewHolder) holder;
//            viewHolder.name.setText(((Toggle) item).getName());
//
//            viewHolder.mswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onToggleChange(CompoundButton buttonView, boolean isToggled) {
//                    if (isToggled) {
//                        viewHolder.mswitch.setChecked(true);
//                        isToggledArray.set(position, true);
//                    } else {
//                        viewHolder.mswitch.setChecked(false);
//                        isToggledArray.set(position, false);
//                    }
//                }
//            });
//
//            if (!isToggledArray.get(position)) {
//                viewHolder.mswitch.setChecked(false);
//            } else {
//                viewHolder.mswitch.setChecked(true);
//            }
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//
//        if (data.get(position) instanceof ParentDrawer) {
//            return 0;
//        }
//        if (data.get(position) instanceof DrawerSeperator) {
//            return 1;
//        }
//        if (data.get(position) instanceof ContentOfParentDrawer) {
//            return 2;
//        }
//        if (data.get(position) instanceof Toggle) {
//            return 3;
//        }
//        return -1;
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//  // Array of booleans based on toggle positions
//    public ArrayList<Boolean> getIsToggledArray() {
//        return isToggledArray;
//    }
//
//
//
//    class ParentDrawerViewHolder extends RecyclerView.ViewHolder {
//        final TextView name;
//        final ImageView imageView;
//
//        public ParentDrawerViewHolder(View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.picturecontentname);
//            imageView = (ImageView) itemView.findViewById(R.id.picturecontentimage);
//        }
//    }
//
//    class SeparaterViewHolder extends RecyclerView.ViewHolder {
//        public SeparaterViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
//
//    class ContentsOfParentDrawerViewHolder extends RecyclerView.ViewHolder {
//        final TextView name;
//
//        public ContentsOfParentDrawerViewHolder(View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.contentsname);
//        }
//    }
//
//    class ToggleViewHolder extends RecyclerView.ViewHolder {
//        final TextView name;
//        final Switch mswitch;
//
//        public ToggleViewHolder(View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.contentsname);
//            mswitch = (Switch) itemView.findViewById(R.id.switchit);
//        }
//    }
//}
