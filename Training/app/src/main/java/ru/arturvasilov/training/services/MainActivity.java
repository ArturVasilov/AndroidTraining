package ru.arturvasilov.training.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import ru.arturvasilov.training.R;
import ru.arturvasilov.training.contentprovider.Person;
import ru.arturvasilov.training.contentprovider.PersonsProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ServiceReceiver.class);
        sendBroadcast(intent);

        Person person = new Person("Artur", 20);
        PersonsProvider.save(this, person);

        List<Person> persons = PersonsProvider.listFromCursor(
                getContentResolver().query(PersonsProvider.URI,
                        null, null, null, null));
        if (persons.size() != 1) {
            throw new RuntimeException();
        }
    }
}
