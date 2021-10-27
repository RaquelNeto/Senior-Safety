package pt.ipp.estg.seniorsafety.medicamentos;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import pt.ipp.estg.seniorsafety.BD_ROOM.Medicamento;
import pt.ipp.estg.seniorsafety.BD_ROOM.RepositoryClass.Repository;
import pt.ipp.estg.seniorsafety.BD_ROOM.Utility;
import pt.ipp.estg.seniorsafety.R;

public class MedicamentoActivity extends AppCompatActivity implements View.OnTouchListener,
        GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener, View.OnClickListener, TextWatcher {

    private static final String TAG = "MedicamentoActivity";
    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;

    //componentes ui
    private LinedEditText mLinedEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;
    private RelativeLayout mCheckContainer, mBackArrowContainer;
    private ImageButton mCheck, mBackArrow;


    //vars
    private boolean mIsNewMedicamento;
    private Medicamento mMedicamentoInicial;
    private int mMode;
    private Repository mRepository;
    private Medicamento mMedicamentoFinal;

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        mLinedEditText = findViewById(R.id.med_text);
        mEditTitle = findViewById(R.id.med_edit_title);
        mViewTitle = findViewById(R.id.med_text_title);
        mCheck = findViewById(R.id.toolbar_check);
        mBackArrow = findViewById(R.id.toolbar_back_arrow);
        mCheckContainer = findViewById(R.id.check_container);
        mBackArrowContainer = findViewById(R.id.back_arrow_container);

        mRepository = new Repository(this);

        if(getIncomingIntent()){
            //quando é um medicamento novo (Edit mode)
            setPropriedadesNewMedicamento();
            enableEditMode();
        }
        else{
            //quando não é um medicamento novo (View mode)
            setPropriedadesMedicamento();
            disableEditMode();
            disableContentInteraction();

        }

        setListeners();

    }

    public boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_med")){

            //Log.d(TAG, "getIncomingIntent: " + mMedicamentoInicial.toString());
            mMedicamentoInicial = getIntent().getParcelableExtra("selected_med");


            //no update
            //se mMedicamentoFinal for alterado , mMedicamentoInicial também é alterado porque partilham a mesma posição na memória
            mMedicamentoFinal = new Medicamento();
            mMedicamentoFinal.setName(mMedicamentoInicial.getName());
            mMedicamentoFinal.setDescription(mMedicamentoInicial.getDescription());
            mMedicamentoFinal.setDate(mMedicamentoInicial.getDate());
            mMedicamentoFinal.setId(mMedicamentoInicial.getId());


            mMode = EDIT_MODE_DISABLED;
            mIsNewMedicamento= false;
            return false;

        }
        mMode = EDIT_MODE_ENABLED;
        mIsNewMedicamento = true;
        return true;
    }

    private void saveChanges(){
        if(mIsNewMedicamento){
            saveNewMedicamento();
        }else{
            updateMedicamento();
        }
    }

    private void saveNewMedicamento(){
        mRepository.insertMedicamento(mMedicamentoFinal);
    }

    private void updateMedicamento(){
        mRepository.updateMedicamento(mMedicamentoFinal);
    }

    private void disableContentInteraction(){
        mLinedEditText.setKeyListener(null);
        mLinedEditText.setFocusable(false);
        mLinedEditText.setFocusableInTouchMode(false);
        mLinedEditText.setCursorVisible(false);
        mLinedEditText.clearFocus();
    }

    private void enableContentInteraction(){
        mLinedEditText.setKeyListener(new EditText(this).getKeyListener());
        mLinedEditText.setFocusable(true);
        mLinedEditText.setFocusableInTouchMode(true);
        mLinedEditText.setCursorVisible(true);
        mLinedEditText.requestFocus();
    }

    private void enableEditMode(){
        mBackArrowContainer.setVisibility(View.GONE);
        mCheckContainer.setVisibility(View.VISIBLE);

        mViewTitle.setVisibility(View.GONE);
        mEditTitle.setVisibility(View.VISIBLE);

        mMode = EDIT_MODE_ENABLED;

        enableContentInteraction();
    }

    private void disableEditMode(){
        mBackArrowContainer.setVisibility(View.VISIBLE);
        mCheckContainer.setVisibility(View.GONE);

        mViewTitle.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.GONE);

        mMode = EDIT_MODE_DISABLED;

        disableContentInteraction();

        //verifica se alterou o medicamento , nao guarda se nao alterar
        //só altera se editar o titulo e a descrição para não ficar em branco
        String temp = mLinedEditText.getText().toString();
        temp = temp.replace("\n", "");
        temp = temp.replace("", "");

        if(temp.length() > 0){
            mMedicamentoFinal.setName(mEditTitle.getText().toString());
            mMedicamentoFinal.setDescription(mLinedEditText.getText().toString());

            //define data de registo do medicamento
            String date = Utility.getCurrentData();
            mMedicamentoFinal.setDate(date);

            //se o medicamento for alterado guarda-o
            if(!mMedicamentoFinal.getDescription().equals(mMedicamentoInicial.getDescription()) ||
                    !mMedicamentoFinal.getName().equals(mMedicamentoInicial.getName())){
                Log.d(TAG, "disableEditMode: called");
                saveChanges();
            }
        }

    }

    private void setPropriedadesNewMedicamento(){
        mViewTitle.setText("Titulo do medicamento");
        mEditTitle.setText("Titulo do medicamento");

        mMedicamentoInicial = new Medicamento();
        mMedicamentoFinal = new Medicamento();
        mMedicamentoInicial.setName("Nome do medicamento");
        mMedicamentoFinal.setName("Nome do medicamento");

    }

    private void setPropriedadesMedicamento(){
        mViewTitle.setText(mMedicamentoInicial.getName());
        mEditTitle.setText(mMedicamentoInicial.getName());
        mLinedEditText.setText(mMedicamentoInicial.getDescription());
    }

    private void setListeners(){

        mLinedEditText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this,this);
        mViewTitle.setOnClickListener(this);
        mCheck.setOnClickListener(this);
        mBackArrow.setOnClickListener(this);
        mEditTitle.addTextChangedListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "onDoubleTap: double tap!!!");
        enableEditMode();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    private void hideSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if(view == null){
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar_check:{
                hideSoftKeyboard();
                disableEditMode();
                break;
            }
            case R.id.med_text_title:{
                enableEditMode();
                mEditTitle.requestFocus();//foca no titulo
                mEditTitle.setSelection(mEditTitle.length());//cursor vai para o final da string
                break;
            }
            case R.id.toolbar_back_arrow:{
                finish();
                break;
            }
        }
    }

    //qd esta no modo editar se carregar na seta do tlm assume a o check da toolbar
    @Override
    public void onBackPressed() {
        if(mMode == EDIT_MODE_ENABLED){
            onClick(mCheck);
        }
        else{
            super.onBackPressed();
        }
    }

    //para nao destruir activity quando muda para modo landscape
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mode", mMode);
    }

    //quando a actividade é recriada vai buscar o que guardou no onSaveInstanceState
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMode = savedInstanceState.getInt("mode");
        if(mMode == EDIT_MODE_ENABLED) {
            enableEditMode();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mViewTitle.setText(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}