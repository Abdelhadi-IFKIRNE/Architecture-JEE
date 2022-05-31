import 'package:flutter/material.dart';
class MyGallery extends StatelessWidget {
  const MyGallery({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Abdelhadi gallery",style: TextStyle(fontSize: 22,color: Colors.lightGreen),),backgroundColor: Colors.black26,),
    );
  }
}
