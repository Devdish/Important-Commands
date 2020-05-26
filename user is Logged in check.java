 
        mAuth= FirebaseAuth.getInstance();
        
        if(mAuth.getCurrentUser()== null){
            Intent i= new Intent(profile.this, login.class);
            startActivity(i);
            finish();
        }
