package com.techease.rkonnect.ui.fragments.Teacher;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.SwipeStackAdapter;
import com.techease.rkonnect.ui.Models.AttendanceRecordModel;
import com.techease.rkonnect.ui.Models.StudentModel;
import com.techease.rkonnect.utils.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class StudentFragment extends Fragment {
    SwipeDeck cardStack;
    FloatingActionButton fab;
    String strName,strFatherName,strRollNo,strAge,getBundleClassName,strCNIC;
    DatabaseReference databaseReference,getDatabaseReference;
    StorageReference storageReference;
    ArrayList<StudentModel> modelArrayList;
    String date,attendence,rollNo;
    int status;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO=2;
    final CharSequence[] items = { "Take Photo", "Choose from Library","Cancel" };
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView imageView;
    Bitmap bm,bitmaps;
    Bitmap thumbnail;
    File destination;
    Uri uri;
    // Folder path for Firebase Storage.
    String Storage_Path = "All_Image_Uploads/";

    // Root Database Name for Firebase Database.
    String Database_Path = "All_Image_Uploads_Database";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student, container, false);

      //  customActionBar();
        sharedPreferences = getActivity().getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        getBundleClassName=getArguments().getString("class");
        cardStack = (SwipeDeck) view.findViewById(R.id.swipe_deck);
        fab = (FloatingActionButton)view.findViewById(R.id.fabAddStudent);
        modelArrayList=new ArrayList<>();



        getDatabaseReference=FirebaseDatabase.getInstance().getReference().child("Classes").child(getBundleClassName).child("Students");
        getDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    StudentModel stdModel1=dataSnapshot1.getValue(StudentModel.class);

                   modelArrayList.add(stdModel1);


                }

                final SwipeStackAdapter adapter = new SwipeStackAdapter(modelArrayList, getActivity());
                cardStack.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

                if (position==0)
                {
                    rollNo=sharedPreferences.getString("r1","");
                }
                else
                if (position==1)
                {
                    rollNo=sharedPreferences.getString("r2","");
                }
                else
                if (position==2)
                {
                    rollNo=sharedPreferences.getString("r3","");
                }
                else
                if (position==3)
                {
                    rollNo=sharedPreferences.getString("r4","");
                }
                else
                if (position==4)
                {
                    rollNo=sharedPreferences.getString("r5","");
                }
                else
                if (position==5)
                {
                    rollNo=sharedPreferences.getString("r6","");

                }
                else
                if (position==6)
                {
                    rollNo=sharedPreferences.getString("r7","");
                }
                else
                if (position==7)
                {
                    rollNo=sharedPreferences.getString("r8","");
                }
                else
                if (position==8)
                {
                    rollNo=sharedPreferences.getString("r9","");
                }
                else
                if (position==9)
                {
                    rollNo=sharedPreferences.getString("r10","");
                }
                else
                if (position==10)
                {
                    rollNo=sharedPreferences.getString("r11","");
                }
                else
                if (position==11)
                {
                    rollNo=sharedPreferences.getString("r12","");
                }
                else
                if (position==12)
                {rollNo=sharedPreferences.getString("r13","");
                }
                else
                if (position==13)
                {
                    rollNo=sharedPreferences.getString("r14","");
                }
                else
                if (position==14)
                {
                    rollNo=sharedPreferences.getString("r15","");
                }
                else
                if (position==15)
                {
                    rollNo=sharedPreferences.getString("r16","");
                }
                else
                if (position==16)
                {
                    rollNo=sharedPreferences.getString("r17","");
                }
                else
                if (position==17)
                {
                    rollNo=sharedPreferences.getString("r18","");
                }
                else
                if (position==18)
                {
                    rollNo=sharedPreferences.getString("r19","");
                }
                else
                if (position==19)
                {
                    rollNo=sharedPreferences.getString("r20","");
                }

                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);
                attendence="Absent";
                AttendanceRecordModel attendenceModel=new AttendanceRecordModel(attendence,formattedDate);
                databaseReference.child("Classes").child(getBundleClassName).
                        child("Students").child(rollNo).child(formattedDate).setValue(attendenceModel);

            }

            @Override
            public void cardSwipedRight(int position) {
                if (position==0)
                {
                    rollNo=sharedPreferences.getString("r1","");
                }
                else
                if (position==1)
                {
                    rollNo=sharedPreferences.getString("r2","");
                }
                else
                if (position==2)
                {
                    rollNo=sharedPreferences.getString("r3","");
                }
                else
                if (position==3)
                {
                    rollNo=sharedPreferences.getString("r4","");
                }
                else
                if (position==4)
                {
                    rollNo=sharedPreferences.getString("r5","");
                }
                else
                if (position==5)
                {
                    rollNo=sharedPreferences.getString("r6","");
                }
                else
                if (position==6)
                {
                    rollNo=sharedPreferences.getString("r7","");
                }
                else
                if (position==7)
                {
                    rollNo=sharedPreferences.getString("r8","");
                }
                else
                if (position==8)
                {
                    rollNo=sharedPreferences.getString("r9","");
                }
                else
                if (position==9)
                {
                    rollNo=sharedPreferences.getString("r10","");
                }
                else
                if (position==10)
                {
                    rollNo=sharedPreferences.getString("r11","");
                }
                else
                if (position==11)
                {
                    rollNo=sharedPreferences.getString("r12","");
                }
                else
                if (position==12)
                {rollNo=sharedPreferences.getString("r13","");
                }
                else
                if (position==13)
                {
                    rollNo=sharedPreferences.getString("r14","");
                }
                else
                if (position==14)
                {
                    rollNo=sharedPreferences.getString("r15","");
                }
                else
                if (position==15)
                {
                    rollNo=sharedPreferences.getString("r16","");
                }
                else
                if (position==16)
                {
                    rollNo=sharedPreferences.getString("r17","");
                }
                else
                if (position==17)
                {
                    rollNo=sharedPreferences.getString("r18","");
                }
                else
                if (position==18)
                {
                    rollNo=sharedPreferences.getString("r19","");
                }
                else
                if (position==19)
                {
                    rollNo=sharedPreferences.getString("r20","");
                }

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);
                attendence="Present";
                AttendanceRecordModel attendenceModel=new AttendanceRecordModel(attendence,formattedDate);
                databaseReference.child("Classes").child(getBundleClassName).
                        child("Students").child(rollNo).child(formattedDate).setValue(attendenceModel);
            }

            @Override
            public void cardsDepleted() {

                Log.i("TeacherDashboard", "no more cards");
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                dialogBuilder.setCancelable(true);
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.custom_add_student, null);
                dialogBuilder.setView(dialogView);
                imageView=(ImageView)dialogView.findViewById(R.id.ivStudentImage);
                final EditText etName=(EditText)dialogView.findViewById(R.id.etName);
                final EditText etFatherName=(EditText)dialogView.findViewById(R.id.etFatherName);
                final EditText etAge=(EditText)dialogView.findViewById(R.id.etAge);
                final EditText etRollNo=(EditText)dialogView.findViewById(R.id.etRollNo);
                final EditText etFatherCnic=(EditText)dialogView.findViewById(R.id.etFatherCNICAddingChild);
                Button btnAdd=(Button)dialogView.findViewById(R.id.btnAdd);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Add Photo!");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (items[which].equals("Take Photo"))
                                {
                                    callCamera();
                                }
                                else if (items[which].equals("Choose from Library"))
                                {
                                    callGallery();

                                }
                                else if (items[which].equals("Cancel"))
                                {
                                    dialog.dismiss();
                                }

                            }
                        });
                        builder.show();
                    }
                });
                final AlertDialog alertDialog = dialogBuilder.create();
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        strAge=etAge.getText().toString();
                        strName=etName.getText().toString();
                        strFatherName=etFatherName.getText().toString();
                        strRollNo=etRollNo.getText().toString();
                        strCNIC=etFatherCnic.getText().toString();
                        final StudentModel model=new StudentModel(strName,strFatherName,strRollNo,strAge,strCNIC);
                        databaseReference.child("Classes").child(getBundleClassName).child("Students").child(strRollNo).setValue(model);
                        UploadImageFileToFirebaseStorage();
                        alertDialog.cancel();
                    }
                });

                alertDialog.show();
            }
        });
        return view;
    }

    private void callCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
    private void callGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, REQUEST_TAKE_PHOTO);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            uri=data.getData();
            onCaptureImageResult(data);
        }
        else if (requestCode==REQUEST_TAKE_PHOTO)
        {
             uri = data.getData();
            String encodeded;
            try {
                bitmaps = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                final ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmaps.compress(Bitmap.CompressFormat.PNG, 90, stream);
                byte[] byteArray = stream.toByteArray();

                encodeded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                byte[] decodedString = Base64.decode(encodeded, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(bitmaps);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            //  onSelectFromGalleryResult(data);

        }
    }
    private void onCaptureImageResult(Intent data) {

        thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(thumbnail);

    }
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (uri != null) {



            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(uri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Showing toast message after done uploading.
                            Toast.makeText(getActivity().getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            StudentModel imageUploadInfo = new StudentModel(strRollNo, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.


                            // Showing exception erro message.
                            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.


                        }
                    });
        }
        else {

            Toast.makeText(getActivity(), "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }
    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getActivity().getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    public void customActionBar() {

        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar_students, null);
        ImageView imageView = (ImageView) mCustomView.findViewById(R.id.ivBack2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new Classes();
                getFragmentManager().beginTransaction().replace(R.id.fragment_main,fragment).commit();
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }

}
