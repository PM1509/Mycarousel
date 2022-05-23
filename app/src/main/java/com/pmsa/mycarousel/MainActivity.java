package com.pmsa.mycarousel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselGravity;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.model.CarouselType;
import org.imaginativeworld.whynotimagecarousel.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator2;

public class MainActivity extends AppCompatActivity {

    private boolean isStarted = false;
    ExtendedFloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab=findViewById(R.id.fab_play);

        CircleIndicator2 circle = findViewById(R.id.custom_indicator);

        ImageCarousel carousel = findViewById(R.id.carousel);

// Register lifecycle. For activity this will be lifecycle/getLifecycle() and for fragments it will be viewLifecycleOwner/getViewLifecycleOwner().
        carousel.registerLifecycle(getLifecycle());

        carousel.registerLifecycle(getLifecycle());

        carousel.setShowTopShadow(false);
        carousel.setTopShadowAlpha(0.6f); // 0 to 1, 1 means 100%
        carousel.setTopShadowHeight(Utils.dpToPx(32, this)); // px value of dp

        carousel.setShowBottomShadow(true);
        carousel.setBottomShadowAlpha(0.7f); // 0 to 1, 1 means 100%
        carousel.setBottomShadowHeight(Utils.dpToPx(48, this)); // px value of dp

        carousel.setShowCaption(true);
        carousel.setCaptionMargin(Utils.dpToPx(8, this)); // px value of dp
        carousel.setCaptionTextSize(Utils.spToPx(16, this)); // px value of sp

        carousel.setShowIndicator(false);
        carousel.setIndicatorMargin(Utils.dpToPx(0, this)); // px value of dp

        carousel.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

        carousel.setCarouselBackground(new ColorDrawable(Color.parseColor("#333333")));
        carousel.setImagePlaceholder(ContextCompat.getDrawable(
                this,
                R.drawable.ic_wb_cloudy_with_padding
        ));

        carousel.setCarouselPadding(Utils.dpToPx(0, this));
        carousel.setCarouselPaddingStart(Utils.dpToPx(0, this));
        carousel.setCarouselPaddingTop(Utils.dpToPx(0, this));
        carousel.setCarouselPaddingEnd(Utils.dpToPx(0, this));
        carousel.setCarouselPaddingBottom(Utils.dpToPx(0, this));

        carousel.setShowNavigationButtons(false);
//        carousel.setPreviousButtonLayout(R.layout.custom_previous_button_layout);
//        carousel.setPreviousButtonId(R.id.custom_btn_previous);
//        carousel.setPreviousButtonMargin(Utils.dpToPx(8, this)); // px value of dp
//        carousel.setNextButtonLayout(R.layout.custom_next_button_layout);
//        carousel.setNextButtonId(R.id.custom_btn_next);
//        carousel.setNextButtonMargin(Utils.dpToPx(8, this)); // px value of dp

        carousel.setCarouselType(CarouselType.SHOWCASE);

        carousel.setCarouselGravity(CarouselGravity.CENTER);

        carousel.setScaleOnScroll(false);
        carousel.setScalingFactor(.15f);
        carousel.setAutoWidthFixing(true);
        carousel.setAutoPlay(false);
        carousel.setAutoPlayDelay(3000); // Milliseconds
        carousel.setInfiniteCarousel(true);
        carousel.setTouchToPause(true);

        carousel.setIndicator(circle);

        MaterialButton previousBtn = findViewById(R.id.btn_goto_previous);
        previousBtn.setOnClickListener(v -> carousel.previous());

        MaterialButton nextBtn = findViewById(R.id.btn_goto_next);
        nextBtn.setOnClickListener(v -> carousel.next());

        List<CarouselItem> list = new ArrayList<>();

// Image URL with caption
        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080",
                        "Photo by Aaron Wu on Unsplash"
                )
        );

// Just image URL
        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
                )
        );

// Image URL with header
        Map<String, String> headers = new HashMap<>();
        headers.put("header_key", "header_value");

        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080",
                        headers
                )
        );

// Image drawable with caption
//        list.add(
//                new CarouselItem(
//                        R.drawable.image_1,
//                        "Photo by Kimiya Oveisi on Unsplash"
//                )
//        );
//
//// Just image drawable
//        list.add(
//                new CarouselItem(
//                        R.drawable.image_2
//                )
//        );

// ...

        carousel.setData(list);

        // ----------------------------------------------------------------

        fab.setOnClickListener(v -> {
            if (isStarted) {

                isStarted = false;
                carousel.stop();

                fab.setText("Start");

            } else {

                isStarted = true;
                carousel.start();

                fab.setText("Stop");

            }
        });
    }

}