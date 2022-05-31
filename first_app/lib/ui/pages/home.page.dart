import 'package:flutter/material.dart';

import '../widgets/drawer.widget.dart';

class MyHome extends StatelessWidget {
  const MyHome({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: MyDrawer(),
      appBar: AppBar(title: Text("Abdelhadi Blog",style: TextStyle(color: Colors.blueAccent,fontSize: 15,),),
        backgroundColor: Color.fromARGB(202, 205, 255, 45),),
      body: Center(child:
      Text("From Abdelhadi IFKIRNE",style:Theme.of(context).textTheme.headline1,),
      ),

    );
  }
}