package com.example.volkandemirproje.util

import android.app.Activity
import android.app.AlertDialog

enum class AlertButonlari{
    BOYUTUNU_BUYUT,BOYUTUNU_KUCULT
}

object AlertDialogUtils {

   fun dialogGoster(activity: Activity, tiklananButtton:(AlertButonlari)-> Unit){

       val builder:AlertDialog.Builder=AlertDialog.Builder(activity)
       builder.setTitle("Liste Elemanları")
       builder.setMessage("Liste elemanlarının boyu büyüsün mü")
       builder.setPositiveButton("EVET",{ dialog, i->
           tiklananButtton(AlertButonlari.BOYUTUNU_BUYUT)
           dialog.dismiss()

       })
       builder.setNegativeButton("Hayır",{dialog, i ->
           tiklananButtton(AlertButonlari.BOYUTUNU_KUCULT)
           dialog.dismiss()
       })
       builder.show()



   }
}