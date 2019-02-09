package ru.kozlov_victor.github_users_repositories.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kozlov_victor.github_users_repositories.R;
import ru.kozlov_victor.github_users_repositories.mvp.presenter.list.IUsersRepositoryListPresenter;
import ru.kozlov_victor.github_users_repositories.mvp.view.item.IUsersRepositoryItemView;

public class UserRepositoryAdapter extends RecyclerView.Adapter<UserRepositoryAdapter.ViewHolder> {
    IUsersRepositoryListPresenter presenter;

    public UserRepositoryAdapter(IUsersRepositoryListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_repository, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        RxView.clicks(viewHolder.itemView).map(obj -> viewHolder).subscribe(presenter.getClickSubject());
        viewHolder.pos = position;
        presenter.bindView(viewHolder);
    }

    @Override
    public int getItemCount() {
        return presenter.getUsersRepositoryCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements IUsersRepositoryItemView {

        @BindView(R.id.tv_title_repository)
        TextView titleTextView;

        int pos = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setTitle(String title) {
            titleTextView.setText(title);
        }

        @Override
        public int getPos() {
            return pos;
        }
    }
}
