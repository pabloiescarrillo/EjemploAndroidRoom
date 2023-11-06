package es.iescarrillo.android.ejemploandroidroom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mindrot.jbcrypt.BCrypt;

import es.iescarrillo.android.ejemploandroidroom.R;
import es.iescarrillo.android.ejemploandroidroom.models.Book;
import es.iescarrillo.android.ejemploandroidroom.models.Person;
import es.iescarrillo.android.ejemploandroidroom.models.UserAccount;
import es.iescarrillo.android.ejemploandroidroom.services.PersonService;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etSurname, etUsername, etPasswordRegister;
    Button btnSave, btnLogout;
    TextView tvMessage;
    SharedPreferences sharedPreferences;

    private PersonService personService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etUsername = findViewById(R.id.etUsername);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btnSave = findViewById(R.id.btnSave);
        tvMessage = findViewById(R.id.tvMessage);

        sharedPreferences = getSharedPreferences("MiAppPreferences", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String rol = sharedPreferences.getString("rol", "");
        Boolean login = sharedPreferences.getBoolean("login", false);
        long id = sharedPreferences.getLong("id", 0);

        tvMessage.setText("Username: " + username + " rol: " + rol + " ¿Logueado? " + login + " id: " + String.valueOf(id));

        personService = new PersonService(getApplication());

        btnSave.setOnClickListener(v -> {

            // Insertar una nueva persona
            Person p = new Person();
            p.setName(etName.getText().toString());
            p.setSurname(etSurname.getText().toString());

            UserAccount u = new UserAccount();
            u.setUsername(etUsername.getText().toString());
            u.setRol("PERSON");

            String encryptPassword = BCrypt.hashpw(etPasswordRegister.getText().toString(), BCrypt.gensalt(5));
            Log.i("password encriptada", encryptPassword);
            u.setPassword(encryptPassword);

            p.setUserAccount(u);

            Thread thread = new Thread(() -> {
                long idPerson = personService.insertPerson(p);
                Log.i("id persona", String.valueOf(idPerson));
            });

            thread.start();
            try{
                thread.join();
            } catch (Exception e){
                Log.i("error", e.getMessage());
            }

        });

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            sharedPreferences.edit().clear().apply();

            sharedPreferences = getSharedPreferences("MiAppPreferences", Context.MODE_PRIVATE);
            String username2 = sharedPreferences.getString("username", "");
            String rol2 = sharedPreferences.getString("rol", "");
            Boolean login2 = sharedPreferences.getBoolean("login", false);
            long id2 = sharedPreferences.getLong("id", 0);

            tvMessage.setText("Username: " + username2 + " rol: " + rol2 + " ¿Logueado? " + login2 + " id: " + String.valueOf(id2));
        });
    }
}