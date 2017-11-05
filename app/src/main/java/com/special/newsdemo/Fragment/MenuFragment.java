package com.special.newsdemo.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.special.newsdemo.R;
import com.special.newsdemo.model.Menu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    private static String[] menus = {"天大要闻","校园公告","社团风采","视点观察","我喜欢的"};
    private List<Menu> menuList = new ArrayList<Menu>();
    private RecyclerView menuRecyclerView;
    MenuAdapter menuAdapter;
    public MenuFragment() {
        for(int i = 0; i < menus.length; i++) {
            Menu menu = new Menu(menus[i], i + 1);
            menuList.add(menu);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_menu,container,false);
        menuRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_menu);
        menuAdapter = new MenuAdapter(menuList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        menuRecyclerView.setLayoutManager(layoutManager);
        menuRecyclerView.setAdapter(menuAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showNewsContent(0,1);
    }
    public void showNewsContent(int type, int page){
        NewsFragment newsFragment = (NewsFragment) getFragmentManager()
                .findFragmentById(R.id.news_fragment);
        newsFragment.refresh(type,page);
    }
    class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{

        private List<Menu> menuList;

        public MenuAdapter(List<Menu> menuList){
            this.menuList = menuList;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView menuName;

            public ViewHolder(View view){
                super(view);
                menuName = (TextView) view.findViewById(R.id.menu_item);
            }
        }
        @Override
        public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
            final ViewHolder holder = new MenuAdapter.ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Menu menu = menuList.get(holder.getAdapterPosition());
                    showNewsContent(menu.getType(),1);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
            Menu menu = menuList.get(position);
            holder.menuName.setText(menu.getName());
        }

        @Override
        public int getItemCount() {
            return menuList.size();
        }
    }
}
