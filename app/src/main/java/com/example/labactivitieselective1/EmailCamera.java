package com.example.labactivitieselective1;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;


public class EmailCamera extends AppCompatActivity {
    Button send, capturePic;
    EditText receiver, subject, message;
    ImageView pic;
    Uri imageUri; // For storing the captured image URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email_camera);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        enableRuntimePermission();

        // Set up the button listeners
        sendEmail();
        capturePic();
        capturePic = findViewById(R.id.btnCapturePic);
        capturePic.setVisibility(View.VISIBLE);
    }

    public void init() {
        receiver = findViewById(R.id.etReceiver);
        subject = findViewById(R.id.etSubject);
        message = findViewById(R.id.etMessage);
        pic = findViewById(R.id.ivPic);
        send = findViewById(R.id.btnSend);
        capturePic = findViewById(R.id.btnCapturePic);
    }

    // Handle image capture using the new Activity Result API
    ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && imageUri != null) {
                    // Display the captured image
                    pic.setImageURI(imageUri);
                } else {
                    Toast.makeText(EmailCamera.this, "Image capture cancelled or failed", Toast.LENGTH_SHORT).show();
                }
            }
    );

    public void capturePic() {
        capturePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Prepare the URI for the captured image
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                if (imageUri == null) {
                    Toast.makeText(EmailCamera.this, "Failed to create image URI", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Launch the camera app
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                try {
                    cameraLauncher.launch(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(EmailCamera.this, "Camera app not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sendEmail() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (receiver.getText().toString().isEmpty()) {
                    receiver.setError("Email required!");
                    return;
                }

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{receiver.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());

                // Attach the captured image if available
                if (imageUri != null) {
                    emailIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                }

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send Email"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(EmailCamera.this, "No email clients installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void enableRuntimePermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1
        );
    }
}