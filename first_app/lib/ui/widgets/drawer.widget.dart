import 'package:first_app/config/global.params.dart';
import 'package:flutter/material.dart';

class MyDrawer extends StatelessWidget {
  const MyDrawer({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(child: ListView(
      children:  [
         DrawerHeader(
            decoration: BoxDecoration(gradient: LinearGradient(colors: [
              Colors.lightGreen,
              Colors.limeAccent
            ])),
            child: Center(
              child: CircleAvatar(
                backgroundImage: AssetImage("images/img.png"),
                radius: 60,
              ),
            )),
        ...(globalParams.menu as List).map((objet) {
        return  ListTile(
            title: Text("${objet["title"]}",style: TextStyle(fontSize: 22,color: Colors.blue),),
            leading: Icon(objet["icon"],color: Colors.pink,),
            trailing: Icon(Icons.arrow_right,color: Colors.blueAccent,),
            onTap: (){
              Navigator.of(context).pop();
              Navigator.pushNamed(context, "${objet["route"]}");
            },
          );
        })
      ],
    ),);
  }
}