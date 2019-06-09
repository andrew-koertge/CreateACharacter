package com.example.drew.createacharacter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declare edit text boxes and spinners
    TextView name_text = (TextView) findViewById(R.id.name_edit_text);
    TextView level_text = (TextView) findViewById(R.id.level_edit_text);
    TextView background_text = (TextView) findViewById(R.id.background_edit_text);
    Spinner race_spinner = (Spinner) findViewById(R.id.race_spinner);
    Spinner class_spinner = (Spinner) findViewById(R.id.class_spinner);
    Spinner sub_class_spinner = (Spinner) findViewById(R.id.sub_class_spinner);
    Spinner alignment_spinner = (Spinner) findViewById(R.id.alignment_spinner);

    //set static variables for stats
    static int initial_strength;
    static int initial_intelligence;
    static int initial_constitution;
    static int initial_dexterity;
    static int initial_wisdom;
    static int initial_charisma;
    static int hit_die;
    static int gold_die;
    static boolean monk_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RaceSpinnerValue();
        classSpinnerValue();
        AlignmentChoice();
    }

    @Override
    public void onPause() {
        //save name text when paused
        final SharedPreferences namePref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences levelPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences backgroundPref = PreferenceManager.getDefaultSharedPreferences(this);
        name_text.setText(namePref.getString("Last_text", ""));
        level_text.setText(levelPref.getString("Last_text", ""));
        background_text.setText(backgroundPref.getString("Last_text", ""));
        name_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                namePref.edit().putString("Last_text", s.toString()).apply();
            }
        });
        //saved entered level info when paused
        level_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                levelPref.edit().putString("Last_text", s.toString()).apply();
            }
        });
        //save background info when paused
        background_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                backgroundPref.edit().putString("Last_text", s.toString()).apply();
            }
        });
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    //sets listener for race spinner
    public void RaceSpinnerValue() {
        race_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_race = race_spinner.getItemAtPosition(position).toString();
                //get user selected position and save it if they return to the main screen
                int user_race_choice = race_spinner.getSelectedItemPosition();
                SharedPreferences race_pref = getSharedPreferences("Values", MODE_PRIVATE);
                SharedPreferences.Editor race_pref_editor = race_pref.edit();
                race_pref_editor.putInt("race_spinner", user_race_choice);
                race_pref_editor.apply();
                switch (selected_race) {
                    //Apply bonus stats depending on race picked
                        case "Aarakocra":
                            initial_dexterity+=2;
                            initial_wisdom++;
                            break;
                        case "Aasimar":
                            initial_charisma+=2;
                            break;
                        case "Bugbear":
                            initial_strength+=2;
                            initial_dexterity++;
                            break;
                        case "Dragonborn":
                            initial_strength+=2;
                            initial_charisma++;
                            break;
                        case "Dwarf":
                            sub_class_spinner.setAdapter(new ArrayAdapter<>(MainActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.dwarf_sub_class)));
                            initial_constitution+=2;
                            break;
                        case "Elf":
                            sub_class_spinner.setAdapter(new ArrayAdapter<>(MainActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.elf_sub_class)));
                            initial_dexterity+=2;
                            break;
                        case "Feral Tiefling":
                            initial_dexterity+=2;
                            initial_intelligence++;
                            break;
                        case "Firbolg":
                            initial_wisdom+=2;
                            initial_strength++;
                            break;
                        case "Genasi":
                            sub_class_spinner.setAdapter(new ArrayAdapter<>(MainActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.genasi_sub_class)));
                            initial_constitution+=2;
                            break;
                        case "Gnome":
                            sub_class_spinner.setAdapter(new ArrayAdapter<>(MainActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.gnome_sub_class)));
                            initial_intelligence+=2;
                            break;
                        case "Goblin":
                            initial_dexterity+=2;
                            initial_constitution++;
                            break;
                        case "Goliath":
                            initial_strength+=2;
                            initial_constitution++;
                            break;
                        case "Half-Elf":
                            initial_charisma+=2;
                            break;
                        case "Halfling":
                            sub_class_spinner.setAdapter(new ArrayAdapter<>(MainActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.halfling_sub_class)));
                            initial_dexterity+=2;
                            break;
                        case "Half-Orc":
                            initial_strength+=2;
                            initial_constitution++;
                            break;
                        case "Hobgoblin":
                            initial_constitution+=2;
                            initial_intelligence++;
                            break;
                        case "Human":
                            initial_charisma++;
                            initial_constitution++;
                            initial_dexterity++;
                            initial_intelligence++;
                            initial_strength++;
                            initial_wisdom++;
                            break;
                        case "Kenku":
                            initial_dexterity+=2;
                            initial_wisdom++;
                            break;
                        case "Kobold":
                            initial_dexterity+=2;
                            initial_strength+=2;
                            break;
                        case "Lizardfolk":
                            initial_constitution+=2;
                            initial_wisdom++;
                            break;
                        case "Orc":
                            initial_strength+=2;
                            initial_constitution++;
                            initial_intelligence-=2;
                            break;
                        case "Tabaxi":
                            initial_dexterity+=2;
                            initial_charisma++;
                            break;
                        case "Tiefling":
                            initial_charisma+=2;
                            initial_intelligence++;
                            break;
                        case "Tortle":
                            initial_strength+=2;
                            initial_wisdom++;
                            break;
                        case "Triton":
                            initial_strength++;
                            initial_constitution++;
                            initial_charisma++;
                            break;
                        case "Yuan-Ti Pureblood":
                            initial_charisma+=2;
                            initial_intelligence++;
                            break;
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void classSpinnerValue() {
        class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_class = parent.getItemAtPosition(position).toString();
                int user_class_choice = class_spinner.getSelectedItemPosition();
                SharedPreferences class_pref = getSharedPreferences("Values", 0);
                SharedPreferences.Editor class_pref_editor = class_pref.edit();
                class_pref_editor.putInt("class_spinner", user_class_choice);
                class_pref_editor.apply();
                //set hit die to use for hit point calculation based on class
                switch (selected_class) {
                    case "Barbarian":
                        hit_die = 12;
                        gold_die=2;
                        break;
                    case "Bard":
                        hit_die = 8;
                        gold_die=5;
                        break;
                    case "Cleric":
                        hit_die = 8;
                        gold_die=5;
                        break;
                    case "Druid":
                        hit_die = 8;
                        gold_die=2;
                        break;
                    case "Fighter":
                        hit_die = 10;
                        gold_die=5;
                        break;
                    case "Monk":
                        hit_die = 8;
                        gold_die=5;
                        monk_selected = true;
                        break;
                    case "Paladin":
                        hit_die = 10;
                        gold_die=5;
                        break;
                    case "Ranger":
                        hit_die = 10;
                        gold_die=5;
                        break;
                    case "Rogue":
                        hit_die = 8;
                        gold_die=4;
                        break;
                    case "Sorcerer":
                        hit_die = 6;
                        gold_die=3;
                        break;
                    case "Warlock":
                        hit_die = 8;
                        gold_die=4;
                        break;
                    case "Wizard":
                        hit_die = 6;
                        gold_die=4;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void AlignmentChoice() {
        alignment_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int align_spinner_choice = alignment_spinner.getSelectedItemPosition();
                SharedPreferences align_pref = getSharedPreferences("Values", 0);
                SharedPreferences.Editor align_pref_editor = align_pref.edit();
                align_pref_editor.putInt("align spinner", align_spinner_choice);
                align_pref_editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
