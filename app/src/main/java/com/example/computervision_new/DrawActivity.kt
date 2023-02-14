package com.example.computervision_new

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileDescriptor
import java.io.IOException
import java.util.logging.Logger


class DrawActivity : AppCompatActivity() {
    //var frame: ImageView? = null
    var bitmap: Bitmap? = null
    private var image_uri: Uri? = null
    private val RESULT_LOAD_IMAGE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myCanvasView = MyCanvasView(this)

        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)

        if (bitmap != null) {
            Toast.makeText(baseContext, "Bitmap is not empty", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(baseContext, "Bitmap is empty", Toast.LENGTH_SHORT).show()
        }

        setContentView(myCanvasView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            image_uri = data.data
            //imageView.setImageURI(image_uri);
            bitmap = uriToBitmap(image_uri!!)
            //frame?.setImageBitmap(bitmap)
        }
    }

    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        /*try {
            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return image
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null*/

        return BitmapFactory.decodeStream(this.contentResolver.openInputStream(selectedFileUri))
    }

}