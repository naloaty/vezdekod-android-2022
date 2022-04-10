package me.naloaty.vezdekodmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.naloaty.vezdekodmobile.R;
import me.naloaty.vezdekodmobile.model.Order;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private static final String TAG = "OrdersAdapter";

    private List<Order> orders;
    private OnOrderEventListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView storeTitle;
        TextView storeAddress;
        TextView deliveryTime;

        Button btnReject;
        Button btnAccept;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storeTitle = itemView.findViewById(R.id.tv_store_title);
            storeAddress = itemView.findViewById(R.id.tv_store_address);
            deliveryTime = itemView.findViewById(R.id.tv_delivery_time);

            btnReject = itemView.findViewById(R.id.btn_reject);
            btnAccept = itemView.findViewById(R.id.btn_accept);
        }
    }

    public enum EventType {
        REJECT_BTN_CLICK,
        ACCEPT_BTN_CLICK
    }

    public interface OnOrderEventListener {
        void onOrderEvent(EventType eventType, Order order);
    }

    public OrdersAdapter(List<Order> orders) {
        this.orders = orders;
    }

    public void setOnOrderEventListener(OnOrderEventListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_order_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.storeTitle.setText(order.getStoreTitle());
        holder.storeAddress.setText(order.getStoreAddress());
        holder.deliveryTime.setText(order.getDeliveryTime().toString());

        holder.btnAccept.setOnClickListener(view -> {
            if (listener != null)
                listener.onOrderEvent(EventType.ACCEPT_BTN_CLICK, order);
        });

        holder.btnReject.setOnClickListener(view -> {
            if (listener != null)
                listener.onOrderEvent(EventType.REJECT_BTN_CLICK, order);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
