package me.naloaty.vezdekodmobile.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import me.naloaty.vezdekodmobile.R;
import me.naloaty.vezdekodmobile.adapters.OrdersAdapter;
import me.naloaty.vezdekodmobile.model.Order;
import me.naloaty.vezdekodmobile.utils.MockUtils;

public class OrderCardsFragment extends Fragment implements CardStackListener, OrdersAdapter.OnOrderEventListener {

    private CardStackView cardStackView;
    private CardStackLayoutManager layoutManager;
    private OrdersAdapter ordersAdapter;

    private SwipeAnimationSetting rejectSetting;
    private SwipeAnimationSetting acceptSetting;
    private AlertDialog.Builder dialogBuilder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogBuilder = new AlertDialog.Builder(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_cards, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cardStackView = view.findViewById(R.id.csv_cards);
        layoutManager = new CardStackLayoutManager(getContext(), this);
        ordersAdapter = new OrdersAdapter(MockUtils.simpleOrders());

        initStackView();
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {
        if (direction == Direction.Right) {
            dialogBuilder
                    .setTitle(R.string.dlg_order_status)
                    .setMessage(R.string.dlg_order_accepted)
                    .setOnDismissListener(di -> {
                        // Use layoutManager.getTopPosition() if needed
                        getParentFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .setCustomAnimations(
                                        R.anim.slide_in_right,
                                        R.anim.slide_out_left,
                                        R.anim.slide_in_right,
                                        R.anim.slide_out_left
                                )
                                .replace(R.id.fragment_container_view, ActiveOrderFragment.class, null)
                                .commit();
                    }).create().show();
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }

    @Override
    public void onOrderEvent(OrdersAdapter.EventType eventType, Order order) {

        switch (eventType) {
            case REJECT_BTN_CLICK:
                layoutManager.setSwipeAnimationSetting(rejectSetting);
                cardStackView.swipe();
                break;

            case ACCEPT_BTN_CLICK:
                layoutManager.setSwipeAnimationSetting(acceptSetting);
                cardStackView.swipe();
                break;
        }
    }

    private void initStackView() {
        ordersAdapter.setOnOrderEventListener(this);

        rejectSetting = new SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(new AccelerateInterpolator())
                .build();

        acceptSetting = new SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(new AccelerateInterpolator())
                .build();

        layoutManager.setStackFrom(StackFrom.None);
        layoutManager.setVisibleCount(3);
        layoutManager.setTranslationInterval(8.0f);
        layoutManager.setScaleInterval(0.95f);
        layoutManager.setSwipeThreshold(0.3f);
        layoutManager.setMaxDegree(20.0f);
        layoutManager.setDirections(Direction.HORIZONTAL);
        layoutManager.setCanScrollHorizontal(true);
        layoutManager.setCanScrollVertical(false);
        layoutManager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        layoutManager.setOverlayInterpolator(new LinearInterpolator());

        cardStackView.setAdapter(ordersAdapter);
        cardStackView.setLayoutManager(layoutManager);
    }

}
