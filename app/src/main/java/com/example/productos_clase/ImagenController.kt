package com.example.productos_clase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

object ImagenController {
    //permite seleccionar Imagen XD
    fun selectPhotoFromGallery(activity:Activity,code:Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"
        activity.startActivityForResult(intent,code)
    }



    //Permite salvar la info imagen
    fun saveImagen(context: Context,id:Long,uri:Uri){
        val file = File(context.filesDir,id.toString())
        val bytes=context.contentResolver.openInputStream(uri)?.readBytes()!!
        file.writeBytes(bytes)
    }


    //Evita Errorres
    fun getImageUri(context: Context,id: Long): Uri? {
        var file = File(context.filesDir,id.toString())
        return if (file.exists()) Uri.fromFile(file)
            else Uri.parse("android.resource://com.example.productos_clase/drawable/imageplaceholder")
    }

    fun deleteImage(context: Context,id: Long){
        val file = File(context.filesDir,id.toString())
        file.delete()
    }
}