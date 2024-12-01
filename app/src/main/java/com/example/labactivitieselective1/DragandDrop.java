package com.example.labactivitieselective1;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DragandDrop extends AppCompatActivity implements View.OnLongClickListener {
    ImageView batman, superman, ironman, wolverine, dropHere;
    TextView status, heroName;
    ConstraintLayout constraintLayout;
    ClipData clipData;
    String imageName;
    Animation animateImage, animateText;
    Drawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dragand_drop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        dropHere.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                final View v = (View) dragEvent.getLocalState();
                int state = dragEvent.getAction();
                // getting the label which is the tag of the image
                imageName = clipData.getDescription().getLabel().toString();
                // getting the id resources and identifier, the code above assumes you're in an Activity
                int id = getResources().getIdentifier(imageName,"drawable",getPackageName());

                switch (state){
                    case DragEvent.ACTION_DRAG_STARTED:
                        status.setText("ACTION_DRAG_STARTED");
                        heroName.setText("DRAG AND DROP YOUR HERO HERE!");
                        dropHere.setImageResource(R.drawable.hero);
                        dropHere.startAnimation(animateImage);
                        break;
                    case DragEvent.ACTION_DROP:
                        status.setText("ACTION_DROP");
                        heroName.setText("DRAG AND DROP YOUR HERO HERE!");
                        dropHere.setImageDrawable(drawable);
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        status.setText("ACTION_DRAG_ENTERED");
                        heroName.setText("DRAG AND DROP YOUR HERO HERE!");
                        drawable = getResources().getDrawable(id);
                        dropHere.setImageDrawable(drawable);
                        dropHere.startAnimation(animateImage);
                        heroName.setText(imageName);
                        heroName.startAnimation(animateText);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        status.setText("ACTION_DRAG_EXITED");
                        heroName.setText("DRAG AND DROP YOUR HERO HERE!");
                        dropHere.setImageResource(R.drawable.hero);
                        heroName.clearAnimation();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        status.setText("ACTION_DRAG_ENDED");
                        heroName.setText("DRAG AND DROP YOUR HERO HERE!");
                        constraintLayout.removeView(v);
                        v.setVisibility(View.VISIBLE);
                        constraintLayout.addView(v);
                        dropHere.clearAnimation();
                        heroName.clearAnimation();
                        break;
                }
                return true;
            }
        });
    }
    public void init(){
        animateText = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.my_animation);
        animateImage = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        batman = findViewById(R.id.ivBatman);
        superman = findViewById(R.id.ivSuperman);
        wolverine = findViewById(R.id.ivWolverine);
        ironman = findViewById(R.id.ivIronman);
        dropHere = findViewById(R.id.ivHero);
        status = findViewById(R.id.tvStatus);
        heroName = findViewById(R.id.tvNameOfHero);
        constraintLayout = findViewById(R.id.main);
        batman.setOnLongClickListener( this);
        superman.setOnLongClickListener(this);
        ironman.setOnLongClickListener(this);
        wolverine.setOnLongClickListener(this);
    }
    public boolean onLongClick(View view) {
        view.setVisibility(View.INVISIBLE);
        // This will create a shadow everytime you drag an Image
        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
        clipData = ClipData.newPlainText(view.getTag().toString(),null);
        view.startDrag(clipData,dragShadowBuilder,view,0);
        return true; // set return to true to start Drag
    }
}


