package com.example.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    Button showAdditional;
    Button save;
    Button cancel;
    LinearLayout invisible_layout;
    MyClickListener myClickListener = new MyClickListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        showAdditional = findViewById(R.id.additionalfields);
        save = findViewById(R.id.save_button);
        cancel = findViewById(R.id.cancel_button);
        invisible_layout = findViewById(R.id.invisible_panel);

        showAdditional.setOnClickListener(myClickListener);
        save.setOnClickListener(myClickListener);
        cancel.setOnClickListener(myClickListener);


    }

    protected class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.additionalfields) {
                String text = ((Button)view).getText().toString();
                if(text.equals("Show Additional Fields")){
                    ((Button)view).setText("Hide Additional Fields");
                    invisible_layout.setVisibility(View.VISIBLE);

                }else {
                    ((Button)view).setText("Show Additional Fields");
                    invisible_layout.setVisibility(View.INVISIBLE);

                }
            }else if(view.getId() == R.id.save_button) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                EditText nameField = findViewById(R.id.numele);
                EditText phonenr = findViewById(R.id.nrtelf);
                EditText email = findViewById(R.id.adresa_electronica);
                EditText posta = findViewById(R.id.adresa_postala);
                EditText company = findViewById(R.id.denumirea_companiei);
                EditText job = findViewById(R.id.pozitia_ocupata);
                EditText website = findViewById(R.id.site_web);
                EditText instantm = findViewById(R.id.identificator_instant_messaging);
                if (nameField.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, nameField.getText().toString());
                }
                if (phonenr.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phonenr.getText().toString());
                }
                if (email.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
                }
                if (posta.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, posta.getText().toString());
                }
                if (company.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, company.getText().toString());
                }
                if (job.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, job.getText().toString());
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if ( website.getText().toString() != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
                    contactData.add(websiteRow);
                }
                if (instantm.getText().toString() != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, instantm.getText().toString());
                    contactData.add(imRow);
                }

                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);


            }else if (view.getId() == R.id.cancel_button){
                finish();
            }
        }
    }
}
